package com.it.repository

import com.it.infra.db.MongoDbConnect
import com.it.model.Model.Person
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.result.InsertOneResult
import org.mongodb.scala.{MongoClient, MongoCollection, Observer, SingleObservable}

import scala.concurrent.duration.Duration
import scala.language.postfixOps

class PersonRepositoryDefault extends RepositoryDefault[Person]{

  val codecRegistry = fromRegistries(fromProviders(classOf[Person]), MongoClient.DEFAULT_CODEC_REGISTRY)

  val collection: MongoCollection[Person] = MongoDbConnect
                                                        .mongoClient()
                                                        .getDatabase("ms_akka_api")
                                                        .withCodecRegistry(codecRegistry)
                                                        .getCollection("person")

  override def saveOne(person: Person): Person = {
    var processing = true
    val insertObservable: SingleObservable[InsertOneResult] = collection.insertOne(person)
    insertObservable.subscribe(new Observer[InsertOneResult] {
      override def onNext(result: InsertOneResult): Unit = println(s"onNext: $result")
      override def onError(e: Throwable): Unit = println(s"onError: $e")
      override def onComplete(): Unit = processing = false
    })
    println(s"Inserting one ${person.toString} ...")
    var time = 0
    while(processing){
      if(time == 12000) processing = false
      Thread.sleep(100)
      time += 100
    }
    person
  }

  override def getOne(id: String): Person = ???

  override def getAll(): List[Person] = {
    var processing = true
    var toReturn = List[Person]()
    collection.find().maxTime(Duration(10, "seconds")).collect().subscribe(v => toReturn = v.toList , e => println(s"onError: $e"), () => processing = false )
    println("Finding all...")
    var time = 0
    while(processing){
      if(time == 12000) processing = false
      Thread.sleep(100)
      time += 100
    }
    toReturn
    //Await.result(collection.find().toFuture, 10 seconds).toList
  }


}
