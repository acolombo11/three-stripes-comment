package com.adidas.threestripes

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Location
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlScanner
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UFile

class ThreeStripesCommentDetector : Detector(), XmlScanner, Detector.UastScanner {
    override fun getApplicableUastTypes(): List<Class<out UElement>> = listOf(UFile::class.java)
    override fun createUastHandler(context: JavaContext): UElementHandler = TodoScanner(context)

    class TodoScanner(private val context: JavaContext) : UElementHandler() {
        override fun visitFile(node: UFile) {
            node.allCommentsInFile.forEach { comment ->
                when {
                    comment.text.startsWith("/*") -> {}
                    !comment.text.startsWith("///") -> reportDoubleForwardSlash(context.getLocation(comment))
                    !comment.text.startsWith("/// ") -> reportNoSpace(context.getLocation(comment))
                }
            }
        }

        private fun reportNoSpace(location: Location) = context.report(
            issue = ISSUE,
            location = location,
            message = MSG_NO_SPACE,
            quickfixData = LintFix.create()
                .name("Add a space to the comment after the adidas Three Stripes ///")
                .replace()
                .pattern("///")
                .with("/// ")
                .robot(true)
                .independent(true)
                .build(),
        )

        private fun reportDoubleForwardSlash(location: Location) = context.report(
            issue = ISSUE,
            location = location,
            message = MSG_DOUBLE_FORWARD_SLASH,
            quickfixData = LintFix.create()
                .name("Replace comment's // double forward slash with /// adidas Three Stripes")
                .replace()
                .pattern(Regex("// ?").pattern)
                .with("/// ")
                .robot(true)
                .independent(true)
                .build(),
        )
    }

    companion object {
        internal const val ID: String = "NoThreeStripesComment"
        internal const val MSG_DOUBLE_FORWARD_SLASH = "Please use the adidas Three Stripes for comments /// and remember to follow with a space"
        internal const val MSG_NO_SPACE = "Missing space after the adidas Three Stripes ///"

        internal val ISSUE: Issue = Issue.create(
            id = ID,
            briefDescription = "Comment isn't using the adidas Three Stripes",
            explanation = "Don't comment with a double forward slash, comment with the adidas Three Stripes /// and don't forget the space",
            category = Category.CORRECTNESS,
            priority = 1,
            severity = Severity.WARNING,
            implementation = Implementation(
                ThreeStripesCommentDetector::class.java,
                Scope.JAVA_FILE_SCOPE,
            ),
        )
    }
}