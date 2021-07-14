package com.it.service

import com.it.model.Model.Person
import com.it.repository._
import com.it.repository.PersonRepositoryDefault

import javax.inject.Singleton

@Singleton
class ServiceApi {

  val personRepo: RepositoryDefault[Person] = new PersonRepositoryDefault()

  def createPerson(person: Person): Person = {
    personRepo.saveOne(person)
  }

  def getPerson(): List[Person] = {
    personRepo.getAll()
  }

}
