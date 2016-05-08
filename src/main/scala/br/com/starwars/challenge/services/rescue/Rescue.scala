package br.com.starwars.challenge.services.rescue

import br.com.starwars.challenge.model.{Vehicle, Person}

trait Rescue {

  def rescue(people: List[Person], vehicle: Vehicle)

}
