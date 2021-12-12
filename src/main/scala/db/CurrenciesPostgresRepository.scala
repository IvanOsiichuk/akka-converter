package org.akkaconverter
package db

import org.akkaconverter.model.{CurrenciesTable, Currency}

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class CurrenciesPostgresRepository(db: Database) extends Repository[Currency] with CurrenciesTable {
  override def add(currency: Currency): Future[Int] =
    db.run(currencies += currency)

  override def update(currency: Currency): Future[Int] =
    db.run(
      currencies
        .filter(_.id === currency.id)
        .map(x => (x.id, x.userId, x.currency1, x.currency2, x.date)) // TODO reformat
        .update((currency.id, currency.userId, currency.currency1, currency.currency2, currency.date))
    )

  override def delete(currencyId: Long): Future[Int] =
    db.run(
      currencies.filter(_.id === currencyId).delete
    )

  override def get(currencyId: Long): Future[Seq[Currency]] =
    db.run(
      currencies.filter(_.id === currencyId).result
    )

  override def prepareRepository(): Future[Unit] =
    db.run(
      currencies.schema.createIfNotExists
    )
}
