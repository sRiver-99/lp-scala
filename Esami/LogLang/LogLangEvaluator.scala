import scala.util.parsing.combinator._
import java.nio.charset._
import java.nio.file._
import java.io._
import util.Try

class LogLangEvaluator extends JavaTokenParsers{

    var opid = 1

    def parse = rep1(task)

    def task = "task" ~> taskname ~ taskbody
    
    def taskbody = "{" ~> rep(command) <~ "}" ^^ { case a => opid = 1 }

    def taskname = ident ^^ { case name => printf("Task %s\n", name) }

    def command = remove | rename | backup | merge

    def remove = "remove" ~> filename ^^ { case f => printOp(removeFile(f.replace("\"",""))) }

    def rename = "rename" ~> filename ~ filename ^^ { case o~n => printOp(renameFile(o.replace("\"",""), n.replace("\"",""))) }

    def backup = "backup" ~> filename ~ filename ^^ { case o~b => printOp(backupFile(o.replace("\"",""), b.replace("\"",""))) }

    def merge = "merge" ~> filename ~ filename ~ filename ^^ { case a~b~c => printOp(mergeFiles(a.replace("\"",""), b.replace("\"",""), c.replace("\"",""))) }

    def filename = stringLiteral

    def printOp(b: Boolean) = {
        printf("[Op %d] %b\n", opid, b)
        opid = opid + 1
    }

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
        val pars = new LogLangEvaluator
        pars.parseAll(pars.parse, scala.io.Source.fromFile(args(0)).mkString) match {
            case pars.Success(result, _) => printf("")
            case error => println(error)
        }
    }

}