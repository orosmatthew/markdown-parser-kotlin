import java.io.BufferedReader
import java.io.FileReader
import java.util.*

class MarkdownParser : CodeParser {
    private val regexHeading1 = Regex("#\\s+(.*)")
    private val regexHeading2 = Regex("##\\s+(.*)")
    private val regexHeading3 = Regex("###\\s+(.*)")
    private val regexTextBold = Regex("\\*\\*(.*?)\\*\\*")
    private val regexTextItalics = Regex("\\*(.*?)\\*")
    private val regexBlockQuote = Regex(">\\s*(.*)")
    private val regexLine = Regex("\\s*-\\s*-\\s*-.*")

    private fun parseHeadings(str: String): MarkdownNode? {
        regexHeading3.matchEntire(str)?.let {
            return MarkdownNode(MarkdownNodeType.HEADING_3, it.groups[1]?.value)
        }
        regexHeading2.matchEntire(str)?.let {
            return MarkdownNode(MarkdownNodeType.HEADING_2, it.groups[1]?.value)
        }
        regexHeading1.matchEntire(str)?.let {
            return MarkdownNode(MarkdownNodeType.HEADING_1, it.groups[1]?.value)
        }
        return null
    }

    private fun parseText(str: String): MarkdownNode {
        regexTextBold.matchEntire(str)?.let {
            return MarkdownNode(MarkdownNodeType.BOLD, it.groups[1]?.value)
        }
        regexTextItalics.matchEntire(str)?.let {
            return MarkdownNode(MarkdownNodeType.ITALICS, it.groups[1]?.value)
        }
        return MarkdownNode(MarkdownNodeType.TEXT, str)
    }

    private fun parseLine(line: String): MarkdownNode {
        parseHeadings(line)?.let {
            return it
        }
        regexBlockQuote.matchEntire(line)?.let {
            return MarkdownNode(MarkdownNodeType.BLOCK_QUOTE, it.groups[1]?.value)
        }
        regexLine.matchEntire(line)?.let {
            return MarkdownNode(MarkdownNodeType.LINE, null)
        }
        return parseText(line)
    }

    override fun parseCode(filename: String): List<MarkdownNode> {
        val reader = BufferedReader(FileReader(filename))
        var line: String?
        val nodes = Vector<MarkdownNode>()
        while (reader.readLine().also { line = it } != null) {
            line?.let {
                nodes.add(parseLine(it))
            }
        }
        return nodes
    }
}