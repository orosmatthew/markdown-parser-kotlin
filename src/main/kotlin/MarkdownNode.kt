enum class MarkdownNodeType {
    HEADING_1,
    HEADING_2,
    HEADING_3,
    TEXT,
    BOLD,
    ITALICS,
    BLOCK_QUOTE,
    LINE
}

class MarkdownNode(private val type: MarkdownNodeType, text: String?) : Node(text) {

    override fun toHTML(): String {
        when (type) {
            MarkdownNodeType.HEADING_1 -> {
                return "<h1>${text}</h1>\n"
            }

            MarkdownNodeType.HEADING_2 -> {
                return "<h2>${text}</h2>\n"
            }

            MarkdownNodeType.HEADING_3 -> {
                return "<h3>${text}</h3>\n"
            }

            MarkdownNodeType.TEXT -> {
                return "${text}<br />\n"
            }

            MarkdownNodeType.BOLD -> {
                return "<b>${text}</b><br />\n"
            }

            MarkdownNodeType.ITALICS -> {
                return "<i>${text}</i><br />\n"
            }

            MarkdownNodeType.BLOCK_QUOTE -> {
                return "<blockquote>${text}</blockquote>\n"
            }

            MarkdownNodeType.LINE -> {
                return "<hr />\n"
            }
        }
    }
}