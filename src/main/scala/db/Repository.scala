package org.akkaconverter
package db

import scala.concurrent.Future

trait Repository[A] {
  def add(that: A): Future[Int]
  def update(that: A): Future[Int]
  def delete(id: Long): Future[Int]
  def get(id: Long): Future[Seq[A]]

  def prepareRepository(): Future[Unit]
}
