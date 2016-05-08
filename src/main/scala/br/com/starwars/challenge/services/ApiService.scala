package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.{Person, Travel, Vehicle}
import br.com.starwars.challenge.restclient.ApiRestClient
import br.com.starwars.challenge.services.rescue.{RescueElder, RescueNonElder}

class ApiService {

  def exec(idVehicle: Int, idPeople: String) = {
    val vehicle = ApiRestClient.findVehicle(idVehicle)
    val listIdPeople = idPeople.split(",").map(id => id.toInt).toList
    createTravels(ApiRestClient.findPeople(listIdPeople), vehicle)
  }

  def createTravels(people: List[Person], vehicle: Vehicle):List[Travel] = {
    val rescueNonElders = new RescueNonElder
    val rescueElders = new RescueElder
    List(rescueElders.rescue(people, vehicle), rescueNonElders.rescue(people, vehicle)).flatten.toList
  }

  def groupBySpecies(people :List[Person]) = people.groupBy(person => person.urlSpecie)

  def getAllElders(groups: Map[String, List[Person]]) =
    groups map {case (specie, people) => sortList(people)(0)} toList

  def getNonElders(groups: Map[String, List[Person]]) =
    (groups map { case (specie, people) => sortList(people).tail} toList).flatten

  def sortList(people: List[Person]) = people.sortBy(p => p)

}