package br.com.starwars.challenge.restclient

import java.net.URL

import com.stackmob.newman._
import com.stackmob.newman.dsl._

import scala.concurrent._
import scala.concurrent.duration._

object ApiRestClient {

  def findPeople(listOfIds: List[Int]) = {
    listOfIds.map(id => findPerson(id))
  }

  def findPerson(id:Int) = {
    val APIUrl = "http://swapi.co/api/people/"
    implicit val httpClient = new ApacheHttpClient
    val url = new URL(APIUrl+id)
    val response = Await.result(GET(url).apply, 70.second)
    ApiRestParser.personParse(response)
  }

  def findVehicle(id: Int) = {
    val APIUrl = "http://swapi.co/api/vehicles/"
    implicit val httpClient = new ApacheHttpClient
    val url = new URL(APIUrl+id)
    val response = Await.result(GET(url).apply, 70.second)
    ApiRestParser.vehicleParse(response)
  }

}
