package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.{Vehicle, Travel, Person}
import org.scalatest.FunSuite

class ApiServiceTest extends FunSuite {

  test("Should people by species") {
    val service = new ApiService()

    val a = new Person("url", "person a", "18BBY", "speciea", "100")
    val b = new Person("url", "person b", "20ABY", "speciea", "100")
    val c = new Person("url", "person c", "20ABY", "specieb", "100")
    val d = new Person("url", "person d", "20ABY", "speciec", "100")
    val e = new Person("url", "person e", "20ABY", "speciec", "100")

    val groupedSpecies = service.groupBySpecies(List(a, b, c, d, e))

    assert(groupedSpecies.get("speciea").get.size == 2)
    assert(groupedSpecies.get("specieb").get.size == 1)
    assert(groupedSpecies.get("speciec").get.size == 2)
  }


  test("Should return the elder of each specie") {
    val service = new ApiService()

    val a = new Person("url", "person a", "18BBY", "speciea", "100")
    val b = new Person("url", "person b", "20ABY", "speciea", "100")

    val c = new Person("url", "person c", "20ABY", "specieb", "100")

    val d = new Person("url", "person d", "20ABY", "speciec", "100")
    val e = new Person("url", "person e", "18ABY", "speciec", "100")

    val f = new Person("url", "person f", "50BBY", "specied", "100")
    val g = new Person("url", "person g", "30BBY", "specied", "100")

    val groups = Map("speciea" -> List(a, b), "specieb" -> List(c), "speciec" -> List(d, e), "specied" -> List(f, g))

    val elders = service.getAllElders(groups)

    assert(elders.size == 4)
    assert(elders(0).name == "person a")
    assert(elders(1).name == "person c")
    assert(elders(2).name == "person e")
    assert(elders(3).name == "person f")

  }

  test("Should sort people by age") {
    val service = new ApiService()

    val a = new Person("url", "person a", "20BBY", "speciea", "100")
    val b = new Person("url", "person b", "30ABY", "speciea", "100")
    val c = new Person("url", "person c", "30BBY", "specieb", "100")
    val d = new Person("url", "person d", "10BBY", "speciec", "100")
    val e = new Person("url", "person e", "5ABY", "speciec", "100")

    val people = service.sortList(List(a, b, c, d, e))

    assert(people(0).name == "person c")
    assert(people(1).name == "person a")
    assert(people(2).name == "person d")
    assert(people(3).name == "person e")
    assert(people(4).name == "person b")

  }

  test("should return non-elders by each specie") {
    val service = new ApiService()

    val a = new Person("url", "person a", "18BBY", "speciea", "100")
    val b = new Person("url", "person b", "20ABY", "speciea", "100")

    val c = new Person("url", "person c", "20ABY", "specieb", "100")

    val d = new Person("url", "person d", "20ABY", "speciec", "100")
    val e = new Person("url", "person e", "18ABY", "speciec", "100")

    val f = new Person("url", "person f", "50BBY", "specied", "100")
    val g = new Person("url", "person g", "30BBY", "specied", "100")

    val nonElders = service.getNonElders(Map("speciea" -> List(a, b), "specieb" -> List(c), "speciec" -> List(d, e), "specied" -> List(f, g)))

    assert(nonElders.size == 3)
    assert(nonElders(0).name == "person b")
    assert(nonElders(1).name == "person d")
    assert(nonElders(2).name == "person g")
  }

  test("should create travel OK") {
    val service = new ApiService()
    val vehicle = new Vehicle("xxxxx", 4)

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

    val travels: List[Travel] = service.createTravels(people, vehicle)

    assert(travels(0).people.size == 4)
    assert(travels(1).people.size == 1)
    assert(travels.size == 5)

    assert(travels.count(t => t.priority == "high") == 2)
    assert(travels.count(t => t.priority == "low") == 3)

  }

}
