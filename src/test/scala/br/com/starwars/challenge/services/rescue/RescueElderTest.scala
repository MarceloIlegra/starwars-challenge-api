package br.com.starwars.challenge.services.rescue

import br.com.starwars.challenge.model.{Travel, Person, Vehicle}
import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class RescueElderTest extends FunSuite{

  test("Should return only elders of each specie") {

    val vehicle = new Vehicle("XXXX", 4)
    val service = new RescueElder()

    val a = new Person("url", "person a", "18BBY", "speciea", "100")
    val b = new Person("url", "person b", "20ABY", "speciea", "100")
    val c = new Person("url", "person c", "20ABY", "specieb", "100")
    val d = new Person("url", "person d", "20ABY", "speciec", "100")
    val e = new Person("url", "person e", "18ABY", "speciec", "100")
    val f = new Person("url", "person f", "50BBY", "specied", "100")
    val g = new Person("url", "person g", "30BBY", "specied", "100")
    val h = new Person("url", "person g", "30BBY", "speciee", "100")

    val elders = service.rescue(List(a, b, c, d, e, f, g, h), vehicle)

    assert(elders.size == 2)
    assert(elders.count(t => t.priority == "high") == 2)
    assert(elders.count(t => t.specie == "elder") == 2)
  }

  test("Should return one elder") {
    val service = new RescueElder()

    val vehicle = new Vehicle("XXXX", 4)
    val a = new Person("url", "person a", "18BBY", "speciea", "100")
    val elders = service.rescue(List(a), vehicle)

    assert(elders.size == 1)
    assert(elders.count(t => t.priority == "high") == 1)
    assert(elders.count(t => t.specie == "elder") == 1)
  }

  test("Should return empty list") {
    val service = new RescueElder()

    val vehicle = new Vehicle("XXXX", 4)
    val elders = service.rescue(List(), vehicle)

    assert(elders.isEmpty)
  }

  test("shoud return true when travels list is empty ") {
    val service = new RescueElder()
    var travels = new ListBuffer[Travel]()
    val person = new Person("url", "person a", "18BBY", "speciea", "100")
    assert(service.needCreateNewTravel(person, travels) == true)
  }

  test("shoud return true when travels has not free place ") {
    val service = new RescueElder()
    var travels = new ListBuffer[Travel]()
    val vehicle = new Vehicle("XXXX", 4)
    travels += new Travel(vehicle, ListBuffer[Person](new Person("url", "person a", "18BBY", "speciea", "100")), "high", "elder")
    travels.last.add(new Person("url", "person b", "18BBY", "specieb", "100"))
    travels.last.add(new Person("url", "person c", "18BBY", "speciec", "100"))
    travels.last.add(new Person("url", "person d", "18BBY", "specied", "100"))

    val person = new Person("url", "person a", "18BBY", "speciea", "100")
    assert(service.needCreateNewTravel(person, travels) == true)
  }

  test("shoud return false when travels has free place ") {
    val service = new RescueElder()
    var travels = new ListBuffer[Travel]()
    val vehicle = new Vehicle("XXXX", 4)
    travels += new Travel(vehicle, ListBuffer[Person](new Person("url", "person a", "18BBY", "speciea", "100")), "high", "elder")
    travels.last.add(new Person("url", "person b", "18BBY", "specieb", "100"))
    travels.last.add(new Person("url", "person c", "18BBY", "speciec", "100"))

    val person = new Person("url", "person a", "18BBY", "speciea", "100")
    assert(service.needCreateNewTravel(person, travels) == false)
  }

}
