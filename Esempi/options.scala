object Options {

  /*
  Implementazione del metodo get fornita nativamente:
  def get[A, B](key: A): Option[B] = {
    if (contains(key)) new Some(getValue(key)) else None
  }
  */

  def main(args: Array[String]) = {
    val Capitals = Map(
      "Germany" -> "Berlin",
      "France" -> "Paris",
      "Italy" -> "Rome",
      "Spain" -> "Madrid"
      // ...
    )
    println("Get the capitals wrapped in Options:")
    println("Germany: " + Capitals.get("Germany"))
    println("France: " + Capitals.get("France"))
    println("Earth: " + Capitals.get("Earth"))
    println("Get the capitals themselves out of the Options:")
    println("Italy: " + Capitals.get("Italy").getOrElse("Oops1!"))
    println("Spain: " + Capitals.get("Spain").getOrElse("Oops2!"))
    println("Earth: " + Capitals.get("Earth").getOrElse("Oops3!"))
    /*
    Si può usare anche Capitals.get(Country).get,
    ma se il dato manca verrà sollevata una NoSuchElementException
    */
  }

}