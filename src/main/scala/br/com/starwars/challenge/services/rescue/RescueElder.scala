package br.com.starwars.challenge.services.rescue

import br.com.starwars.challenge.model.{Vehicle, Person, Travel}

import scala.collection.mutable.ListBuffer

class RescueElder {

  def rescue(people: List[Person], vehicle: Vehicle) = {
    var travels = new ListBuffer[Travel]()
    extractElders(people).foreach(p => needCreateNewTravel(p, travels) match {
        case true => travels += new Travel(vehicle, ListBuffer[Person](p), "high", "elder")
        case _ => travels.last.add(p)
    })
    travels.toList
  }

  def needCreateNewTravel(p: Person, travels: ListBuffer[Travel]): Boolean = travels.isEmpty || !travels.last.itHasFreePlace(p)

  def extractElders(allPeople: List[Person]) =
      allPeople.groupBy(p => p.urlSpecie) map { case (specie, people) => people.sortBy(p => p).head } toList

}


