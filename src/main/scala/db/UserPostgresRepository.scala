package org.akkaconverter
package db

import model.{User, UsersTable}

import akka.actor.TypedActor.dispatcher
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.util.Success

class UserPostgresRepository(db: Database) extends Repository[User] with UsersTable {

  override def add(user: User): Future[Int] =
    db.run(users += user)

  override def update(user: User): Future[Int] =
    db.run(
      users
        .filter(_.id === user.id)
        .map(x => (x.id, x.login, x.password))
        .update((user.id, user.login, user.password))
    )

  override def delete(userId: Long): Future[Int] =
    db.run(
      users.filter(_.id === userId).delete
    )

  override def get(userId: Long): Future[Seq[User]] =
    db.run(
      users.filter(_.id === userId).result
    )

  def verifyUser(login: String, password: String): Future[Boolean] =
    Future(
      db.run(users.filter(x => x.login === login && x.password === password).size.result)
        .value
        .getOrElse(Success(0))
        .getOrElse(0) > 0
    )

  override def prepareRepository(): Future[Unit] =
    db.run(
      users.schema.createIfNotExists
    )
}
