package br.com.starwars.challenge.model

import br.com.starwars.challenge.utils.DateUtils

class Person(val urlPeople: String, val name: String, val birthYear: String, val urlSpecie: String, val mass: String)  extends Ordered [Person]{

  def isOlderThan(that: Person):Boolean = {

    val thisBirthAge = DateUtils.extractAge(this.birthYear)
    val thatBirthAge = DateUtils.extractAge(that.birthYear)

    if(thisBirthAge == thatBirthAge){
      val thisYear = DateUtils.extractYear(this.birthYear)
      val thatYear = DateUtils.extractYear(that.birthYear)
      if(thisBirthAge == "BBY"){
        thisYear > thatYear
      } else {
        thisYear < thatYear
      }
    } else if(thisBirthAge == "BBY" && thatBirthAge == "ABY"){
      true
    } else {
      false
    }

  }

  override def compare(that: Person): Int = if(isOlderThan(that)) -1 else 1

}
