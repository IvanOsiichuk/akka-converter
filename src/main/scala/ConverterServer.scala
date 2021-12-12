package org.akkaconverter

import TwirlImplicits._

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import play.twirl.api.Html

import scala.concurrent.ExecutionContextExecutor
import scala.io.{Source, StdIn}

object ConverterServer extends Directives {

  def readFile(path: String, sep: String = ""): String = {
    val file = Source.fromFile(path)
    try file.getLines.mkString(sep)
    finally file.close()
  }

  def toJavaScript(code: String): Html =
    Html("<script>" + code + "</script>")

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing]               = ActorSystem(Behaviors.empty, "general-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val styles    = readFile("src/main/twirl/style.css")
    val countries = readFile("src/main/twirl/country-list.js", "\n")
    val script    = readFile("src/main/twirl/script.js", "\n")

    val route =
      pathEndOrSingleSlash {
        get {
          complete(html.converter.render(styles, toJavaScript(countries), toJavaScript(script)))
        }
      }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(s"Server now online. Please navigate to http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
