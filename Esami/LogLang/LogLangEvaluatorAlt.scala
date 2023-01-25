import scala.util.parsing.combinator._
import java.nio.charset._
import java.nio.file._
import java.io._
import util.Try

class LogLangEvaluator extends JavaTokenParsers{

    class TaskRunner(taskname: String, taskoperations: List[Any]){

        def run = {
            println(taskname)
            var num = 1
            taskoperations.foreach( operation => {
                val result: Boolean = operation match {
                    case "remove"~(file: String) => new File(file).delete
                    case "rename"~(oldname: String)~(newname: String) => new File(oldname).renameTo(new File(newname))
                    case "backup"~(source: String)~(destination: String) => Try{
                        val bak = new File(destination).toPath
                        bak.equals(Files.copy(new File(source).toPath, bak, StandardCopyOption.REPLACE_EXISTING))
                    }.getOrElse(false)
                    case "merge"~(source1: String)~(source2: String)~(destination: String) => Try{
                        val dest = new File(destination).toPath
                        val content = scala.io.Source.fromFile(source1).mkString + "\n" + scala.io.Source.fromFile(source2).mkString
                        dest.equals(Files.write(dest, content.getBytes(StandardCharsets.UTF_8)))
                    }.getOrElse(false)
                }
                printf("[Op %d] %b\n", num += 1, result)
            })
        }

    }

    def parse = rep1(task)
    def task = "task" ~> ident ~ taskbody ^^ { case name~commandslist => new TaskRunner(name, commandslist) }
    def taskbody = "{" ~> rep(command) <~ "}"
    def command = remove | rename | backup | merge
    def remove = "remove" ~ filename
    def rename = "rename" ~ filename ~ filename
    def backup = "backup" ~ filename ~ filename
    def merge = "merge" ~ filename ~ filename ~ filename
    def filename = stringLiteral ^^ { case name => name.replace("\"", "") }

}

object LogLangEvaluator{

    def main(args: Array[String]) = {
        val llparser = new LogLangEvaluator
        llparser.parseAll(llparser.parse, scala.io.Source.fromFile(args(0)).mkString) match {
            case llparser.Success(tasks, _) => tasks.foreach( task => task.run )
            case error => println(error)
        }
    }

}