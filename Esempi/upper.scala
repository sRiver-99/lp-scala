/*
Queste prime due versioni possono essere:
1) Interpretate come script:
    scala upper.scala
2) Eseguite in una sessione interattiva (REPL):
    scala
    :load upper.scala
*/

/*
class Upper {
    def upper(strings: String*): Seq[String] = {
        strings.map((s:String) => s.toUpperCase())
    }
}
val up = new Upper
Console.println(up.upper("A", "First", "Scala", "Program"))
*/

/*
object Upper {
    def upper(strings: String*) = strings.map(_.toUpperCase())
}
println(Upper.upper("A", "First", "Scala", "Program"))
*/

/*
Questa terza versione è eseguibile anche con:
    scalac upper.scala
    scala Upper <argomenti>
*/

object Upper {
    
    def main(args: Array[String]) = {
        args.map(_.toUpperCase()).foreach(printf("%s ", _))
        println("")
    }

}