package org.akkaconverter
package model

import slick.jdbc.PostgresProfile
import slick.lifted.ProvenShape

final case class User(id: Long, login: String, password: String)

trait UsersTable extends PostgresProfile {

  import api._

  class Users(tag: Tag) extends Table[User](tag, None, "users") {

    val id: Rep[Long]         = column[Long]("id", O.PrimaryKey, O.SqlType("UUID"))
    val login: Rep[String]    = column[String]("login")
    val password: Rep[String] = column[String]("password")

    override def * : ProvenShape[User] = (id, login, password) <> ((User.apply _).tupled, User.unapply)
  }

  val users: TableQuery[Users] = TableQuery[Users] // TODO check doc about
}
