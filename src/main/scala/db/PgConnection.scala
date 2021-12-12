package org.akkaconverter
package db // TODO full package

import slick.jdbc.JdbcBackend.Database

object PgConnection {
  val db = Database.forConfig("akka-converter-db")
}
