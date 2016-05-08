package br.com.starwars.challenge.restclient.model

case class PersonRestModel(val name: String, val birth_year: String, val species: List[String], val mass: String)
