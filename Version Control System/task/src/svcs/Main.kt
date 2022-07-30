package svcs

fun main(args: Array<String>) {
    val help = """These are SVCS commands:
config     Get and set a username.
add        Add a file to the index.
log        Show commit logs.
commit     Save changes.
checkout   Restore a file."""
    print(
        when (args.firstOrNull()) {
            "--help" -> help
            null -> help
            "config" -> "Get and set a username."
            "add" -> "Add a file to the index."
            "log" -> "Show commit logs."
            "commit" -> "Save changes."
            "checkout" -> "Restore a file."
            else -> "'${args.first()}' is not a SVCS command."
        }
    )
}