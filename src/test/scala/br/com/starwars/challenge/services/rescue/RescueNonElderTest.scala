package br.com.starwars.challenge.services.rescue

import br.com.starwars.challenge.model.{Person, Travel, Vehicle}
import org.scalatest.FunSuite

class RescueNonElderTest extends FunSuite{

  test("should return only nonElders") {
    val vehicle = new Vehicle("XXXX", 4)
    val rescueNonElder = new RescueNonElder

    val a = new Person("url", "person a", "18BBY", "speciea", "100")
    val b = new Person("url", "person b", "20ABY", "speciea", "100")

    val c = new Person("url", "person c", "20ABY", "specieb", "100")

    val d = new Person("url", "person d", "20ABY", "speciec", "100")
    val e = new Person("url", "person e", "18ABY", "speciec", "100")

    val f = new Person("url", "person f", "50BBY", "specied", "100")
    val g = new Person("url", "person g", "30BBY", "specied", "100")
    val g2 = new Person("url", "person g", "30BBY", "specied", "100")

    val h = new Person("url", "person h", "30BBY", "speciee", "100")

    val people = List(a, b, c, d, e, f, g, g2, h)

    val travels: List[Travel] = rescueNonElder.rescue(people, vehicle)

    assert(travels.size == 3)
    assert(travels.count(t => t.priority == "low") == 3)

  }

  test("should return non-elders by each specie") {
    val vehicle = new Vehicle("XXXX", 4)
    val rescueNonElder = new RescueNonElder

    val a = new Person("url", "person a", "18BBY", "speciea", "100")
    val b = new Person("url", "person b", "20ABY", "speciea", "100")
    val c = new Person("url", "person c", "20ABY", "specieb", "100")
    val d = new Person("url", "person d", "20ABY", "speciec", "100")
    val e = new Person("url", "person e", "18ABY", "speciec", "100")
    val f = new Person("url", "person f", "50BBY", "specied", "100")
    val g = new Person("url", "person g", "30BBY", "specied", "100")

    val nonElders = rescueNonElder.getNonElders(List(a, b, c, d, e, f, g))

    assert(nonElders.size == 3)
    assert(nonElders.exists(person => person.name == "person b"))
    assert(nonElders.exists(person => person.name == "person d"))
    assert(nonElders.exists(person => person.name == "person g"))

  }

}
