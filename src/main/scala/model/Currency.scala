package org.akkaconverter
package model

import slick.jdbc.PostgresProfile
import slick.lifted.ProvenShape

import java.sql.Date

final case class Currency(id: Long, userId: Long, currency1: String, currency2: String, date: Date)

trait CurrenciesTable extends PostgresProfile {

  import api._

  class Currencies(tag: Tag) extends Table[Currency](tag, None, "currencies") {

    val id: Rep[Long]          = column[Long]("id", O.PrimaryKey, O.SqlType("UUID"))
    val userId: Rep[Long]      = column[Long]("user_id")
    val currency1: Rep[String] = column[String]("currency1")
    val currency2: Rep[String] = column[String]("currency2")
    val date: Rep[Date]        = column[Date]("date")

    override def * : ProvenShape[Currency] =
      (id, userId, currency1, currency2, date) <> ((Currency.apply _).tupled, Currency.unapply)
  }

  val currencies: TableQuery[Currencies] = TableQuery[Currencies]
}
