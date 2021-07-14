package com.it.model

import org.mongodb.scala.bson.BsonObjectId

import java.text.DateFormat
import java.util.UUID

object Model {
  case class Person(_id: String = (UUID.randomUUID().toString), name: String, age: Int)
  case class Health(now: String = DateFormat.getInstance().format(System.currentTimeMillis()), status: String = "UP")
}
