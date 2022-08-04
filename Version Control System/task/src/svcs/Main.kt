package svcs
import java.io.File

var userName: String = ""
var addedFilesList = mutableListOf<String>()
val vcs = File("vcs")
val configFile = File("vcs/config.txt")
val indexFile = File("vcs/index.txt")

fun main(args: Array<String>) {
    if (!vcs.exists()) vcs.mkdir()
    if (!configFile.exists()) configFile.createNewFile()
    if (!indexFile.exists()) indexFile.createNewFile()
    val help = """
                These are SVCS commands:
                config     Get and set a username.
                add        Add a file to the index.
                log        Show commit logs.
                commit     Save changes.
                checkout   Restore a file.""".trimIndent()
    when (args.firstOrNull()) {
        "--help" -> println(help)
        null -> println(help)
        "config" -> config(args)
        "add" -> add(args)
        "log" -> println("Show commit logs.")
        "commit" -> println("Save changes.")
        "checkout" -> println("Restore a file.")
        else -> println("'${args.first()}' is not a SVCS command.")
    }

}
fun config(args: Array<String>){
    when {
        args.size == 1 && (configFile.readText().isEmpty()) -> println("Please, tell me who you are.")
        args.size == 1 -> {
            userName = configFile.readText()
            println("The username is $userName.")
        }
        args.size == 2 -> {
            userName = args[1]
            configFile.writeText(userName)
            println("The username is $userName.")
        }
    }
}
fun add(args: Array<String>){
    when {
        args.size == 1 && (indexFile.readText().isEmpty()) -> println("Add a file to the index.")
        args.size == 1 -> {
            addedFilesList.addAll(indexFile.readText().split(" ").toMutableList())
            println("Tracked files:")
            for (fileName in addedFilesList) println(fileName)
        }
        args.size == 2 -> {
            if (File("./${args[1]}").exists()) {
                indexFile.appendText(args[1] + " ")
                println("The file \'${args[1]}\' is tracked.")
                addedFilesList.add(args[1])
                return
            }
            println("Can't find \'${args[1]}\'.")
        }
    }
}