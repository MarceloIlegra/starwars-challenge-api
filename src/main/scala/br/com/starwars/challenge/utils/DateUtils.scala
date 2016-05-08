package br.com.starwars.challenge.utils

object DateUtils {

  def extractYear(birthYear: String) = {
    val year: String = birthYear.dropRight(3)
    year.contains(".") match {
      case true => year.split("\\.")(0).toInt
      case _ => year.toInt
    }
  }

  def extractAge(birthYear: String) = birthYear takeRight 3

}
