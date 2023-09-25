import java.io.BufferedWriter
import java.io.FileWriter

fun generateHtml(nodes: List<MarkdownNode>) {
    val writer = BufferedWriter(FileWriter("output.html"))
    for (node in nodes) {
        writer.appendLine(node.toHTML())
    }
    writer.flush()
}

fun main() {
    val parser = MarkdownParser()
    val nodes = parser.parseCode("test.md")
    print(generateHtml(nodes))
}