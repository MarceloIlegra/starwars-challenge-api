package br.com.starwars.challenge.utils

import org.scalatest.FunSuite

class DateUtilsTest extends FunSuite{

  test("should return the year") {
    assert(DateUtils.extractYear("150ABY") == 150)
  }

  test("should remove decimal places of year") {
    assert(DateUtils.extractYear("40.1ABY") == 40)
  }

  test("should return the age") {
    assert(DateUtils.extractAge("150ABY") == "ABY")
  }
}
