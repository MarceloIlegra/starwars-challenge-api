package br.com.starwars.challenge.model

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class TravelTest extends FunSuite {

  test("should return true when has free place") {
    val a = new Person("url", "person a", "20BBY", "speciea", "100")
    val b = new Person("url", "person b", "30ABY", "speciea", "100")
    val c = new Person("url", "person c", "30BBY", "specieb", "100")
    val d = new Person("url", "person d", "10BBY", "speciec", "100")
    val e = new Person("url", "person e", "5ABY", "speciec", "101")

    val vehicle = new Vehicle("XXXX", 4)

    val travels = new Travel(vehicle, ListBuffer(a, b, c), "", "")

    assert(travels.itHasFreePlace(d))
    assert(!travels.itHasFreePlace(e))
  }

}

