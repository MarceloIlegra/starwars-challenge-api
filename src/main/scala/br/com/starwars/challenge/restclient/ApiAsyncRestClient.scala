package br.com.starwars.challenge.restclient

import java.net.URL

import br.com.starwars.challenge.model.Person
import com.stackmob.newman.ApacheHttpClient
import com.stackmob.newman.dsl._

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

object ApiAsyncRestClient {

  def findPeople(listOfIds: List[Int]) = {
    val people = for {
      id <- listOfIds
    } yield {
      findPerson(id)
    }
    val sequence: Future[List[Person]] = Future.sequence(people)
    Await.result(sequence, Duration.Inf).asInstanceOf[List[Person]]
  }

  def findPerson(id:Int) = Future {
    val APIUrl = "http://swapi.co/api/people/"
    implicit val httpClient = new ApacheHttpClient
    val url = new URL(APIUrl+id)
    val response = Await.result(GET(url).apply, Duration.Inf)
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
