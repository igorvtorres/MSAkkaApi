package com.it.infra.db
import org.mongodb.scala._

import javax.inject.Singleton

@Singleton
object MongoDbConnect {
  var client: MongoClient = null
  def mongoClient(): MongoClient ={
    if(client == null){
      try{
        val url = buildUrl()
        println(s"Connectando no mongo... $url")
        //System.setProperty("org.mongodb.async.type", "netty")
        client = MongoClient(url)
        client.listDatabaseNames().foreach(print)
      }catch{
        case e: Exception=> e.printStackTrace()
      }

    }
    println("Mongo connectado")
    client
  }

  def buildUrl(): String = {
    var url = ""
    //if(sys.env.contains("MONGO_USER") && sys.env.contains("MONGO_PASSWORD")) url = "mongodb://" + sys.env("MONGO_USER") + ":" + sys.env("MONGO_PASSWORD") + "@" + sys.env("MONGO_URL") + "/?connectTimeoutMS=10000&w=majority"
    //else
    url = "mongodb://" + sys.env("MONGO_URL") + "/?connectTimeoutMS=10000&w=majority"
    url

  }



}
