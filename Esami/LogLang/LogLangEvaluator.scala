import scala.util.parsing.combinator._
import java.nio.charset._
import java.nio.file._
import java.io._
import util.Try

class LogLangEvaluator extends JavaTokenParsers{

    def parse = rep1(task)

    def task = "task" ~> ident ~ taskbody ^^ { case name~commandslist => (name, commandslist) }

    def taskbody = "{" ~> rep(command) <~ "}"

    def command = remove | rename | backup | merge

    def remove = "remove" ~ filename ^^ { case cmd~file => (cmd, file) }

    def rename = "rename" ~ filename ~ filename ^^ { case cmd~oldname~newname => (cmd, oldname, newname) }

    def backup = "backup" ~ filename ~ filename ^^ { case cmd~source~destination => (cmd, source, destination) }

    def merge = "merge" ~ filename ~ filename ~ filename ^^ { case cmd~source1~source2~destination => (cmd, source1, source2, destination) }

    def filename = stringLiteral ^^ { case name => name.replace("\"", "") }

    def removeFile(path: String): Boolean = {
        new File(path).delete
    }

    def renameFile(oldPath: String, newPath: String): Boolean = {
        new File(oldPath).renameTo(new File(newPath))
    }

    def backupFile(original: String, backup: String): Boolean = {
        Try{
            val bak = new File(backup).toPath
            bak.equals(Files.copy(new File(original).toPath, bak, StandardCopyOption.REPLACE_EXISTING))
        }.getOrElse(false)
    }

    def mergeFiles(source1: String, source2: String, destination: String): Boolean = {
        Try{
            val dest = new File(destination).toPath
            val content = scala.io.Source.fromFile(source1).mkString + "\n" + scala.io.Source.fromFile(source2).mkString
            dest.equals(Files.write(dest, content.getBytes(StandardCharsets.UTF_8)))
        }.getOrElse(false)
    }

}

object LogLangEvaluator{

    def main(args: Array[String]) = {
        val llparser = new LogLangEvaluator
        llparser.parseAll(llparser.parse, scala.io.Source.fromFile(args(0)).mkString) match {
            case llparser.Success(tasks, _) => tasks.foreach(
                task => task match {
                    case (taskname, ops) =>
                        println(taskname)
                        var i = 0
                        ops.foreach( op => {
                            i += 1
                            op match {
                                case ("remove", file: String) => printOp(i, llparser.removeFile(file))
                                case ("rename", oldname: String, newname: String) => printOp(i, llparser.renameFile(oldname, newname))
                                case ("backup", source: String, destination: String) => printOp(i, llparser.backupFile(source, destination))
                                case ("merge", source1: String, source2: String, destination: String) => printOp(i, llparser.mergeFiles(source1, source2, destination))
                            }
                        }
                    )
                }
            )
            case error => println(error)
        }
    }

    def printOp(opnumber: Int, result: Boolean) = {
        printf("[Op %d] %b\n", opnumber, result)
    }

}