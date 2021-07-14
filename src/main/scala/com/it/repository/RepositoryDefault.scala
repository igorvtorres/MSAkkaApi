package com.it.repository


trait RepositoryDefault[T] {

  def saveOne(t: T): T
  def getOne(id: String): T
  def getAll(): List[T]
}
