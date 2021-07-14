package com.it.controller

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.it.model.Model.{Health, Person}
import com.it.service.ServiceApi
import de.heikoseeberger.akkahttpjackson.JacksonSupport

import scala.language.postfixOps


object Controller extends JacksonSupport{

  implicit val system = ActorSystem(Behaviors.empty, "MS_AKKA_API")

  val service: ServiceApi = new ServiceApi()

  val route: Route = concat(
    path("person"){
        concat(
              post {entity(as[Person]) { person => complete(service.createPerson(person))}},
              get {complete(service.getPerson())}
        )
    },
    path("health"){
      concat(
        get {complete(Health())}
      )
    }
  )

  def start(): Unit = {
    Http().newServerAt("0.0.0.0", 8080).bind(route)
  }

}
