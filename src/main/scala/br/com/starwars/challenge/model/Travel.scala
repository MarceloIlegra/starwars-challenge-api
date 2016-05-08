package br.com.starwars.challenge.model

import scala.collection.mutable.ListBuffer

class Travel(val vehicle: Vehicle, var people: ListBuffer[Person], val priority: String, val specie: String) {

  def itHasFreePlace(person: Person) = {
    val actualMass = people.map(p => p.mass.toInt).sum
    val finalMass = actualMass + person.mass.toInt
    finalMass <= (vehicle.passengers * 100)
  }

  def add(person: Person) = {
    if(itHasFreePlace(person)) people += person
    else throw new Error("Limit exceeded")
  }

}
