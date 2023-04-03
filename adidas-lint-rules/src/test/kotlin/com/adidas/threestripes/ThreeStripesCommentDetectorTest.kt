package com.adidas.threestripes

import com.adidas.threestripes.ThreeStripesCommentDetector.Companion.ID
import com.adidas.threestripes.ThreeStripesCommentDetector.Companion.MSG_DOUBLE_FORWARD_SLASH
import com.adidas.threestripes.ThreeStripesCommentDetector.Companion.MSG_NO_SPACE
import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

class ThreeStripesCommentDetectorTest {
    private fun check(code: TestFile, expectedMessage: String) = TestLintTask.lint()
        .allowMissingSdk()
        .files(code)
        .issues(ThreeStripesCommentDetector.ISSUE)
        .run()
        .expect(expectedMessage)

    /// region Success /// adidas Three Stripes
    private val javaThreeStripes = TestFiles.java(
        """
    package test.pkg;
    public class TestClass {
        /// Comment is using Three Stripes, Adi is happy
    }
        """,
    )

    @Test
    fun testJavaThreeStripesComment() {
        check(javaThreeStripes, expectedNoWarnings)
    }

    ///Kotlin
    private val kotlinThreeStripes = TestFiles.kotlin(
        """
    package test.pkg
    class TestClass {
        /// Comment is using Three Stripes, Adi is happy
    }
        """,
    )

    @Test
    fun testKotlinThreeStripesComment() {
        check(kotlinThreeStripes, expectedNoWarnings)
    }
    /// endregion Success (/// adidas Three Stripes)

    /// region Warning //Double Forward Slashes
    private val javaExpectedMessage = """
    src/test/pkg/TestClass.java:4: Warning: $MSG_DOUBLE_FORWARD_SLASH [$ID]
            // Comment is using double forward slashes, Adi Dassler is not happy
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    0 errors, 1 warnings
    """.trimIndent()

    private val javaDoubleForwardSlashes = TestFiles.java(
        """
    package test.pkg;
    public class TestClass {
        // Comment is using double forward slashes, Adi Dassler is not happy
    }
        """,
    )

    @Test
    fun testJavaFileDoubleForwardSlashComment() {
        check(javaDoubleForwardSlashes, javaExpectedMessage)
    }

    ///Kotlin
    private val kotlinExpectedMessage = """
    src/test/pkg/TestClass.kt:4: Warning: $MSG_DOUBLE_FORWARD_SLASH [$ID]
            // Comment is using double forward slashes, Adi Dassler is not happy
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    0 errors, 1 warnings
    """.trimIndent()

    private val kotlinDoubleForwardSlashes = TestFiles.kotlin(
        """
    package test.pkg
    class TestClass {
        // Comment is using double forward slashes, Adi Dassler is not happy
    }
        """,
    )

    @Test
    fun testKotlinFileDoubleForwardSlashComment() {
        check(kotlinDoubleForwardSlashes, kotlinExpectedMessage)
    }

    private val expectedNoWarnings = "No warnings."
    /// endregion

    /// region Warning ///adidas Three Stripes no space
    private val javaExpectedNoSpaceMessage = """
    src/test/pkg/TestClass.java:4: Warning: $MSG_NO_SPACE [$ID]
            ///Comment is using Three Stripes, but there is no space before the first word :(
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    0 errors, 1 warnings
    """.trimIndent()

    private val javaThreeStripesNoSpace = TestFiles.java(
        """
    package test.pkg;
    public class TestClass {
        ///Comment is using Three Stripes, but there is no space before the first word :(
    }
        """,
    )

    @Test
    fun testJavaThreeStripesNoSpaceComment() {
        check(javaThreeStripesNoSpace, javaExpectedNoSpaceMessage)
    }

    ///Kotlin
    private val kotlinExpectedNoSpaceMessage = """
    src/test/pkg/TestClass.kt:4: Warning: $MSG_NO_SPACE [$ID]
            ///Comment is using Three Stripes, but there is no space before the first word :(
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    0 errors, 1 warnings
    """.trimIndent()

    private val kotlinThreeStripesNoSpace = TestFiles.kotlin(
        """
    package test.pkg
    class TestClass {
        ///Comment is using Three Stripes, but there is no space before the first word :(
    }
        """,
    )

    @Test
    fun testKotlinThreeStripesNoSpaceComment() {
        check(kotlinThreeStripesNoSpace, kotlinExpectedNoSpaceMessage)
    }
    /// endregion Success (/// adidas Three Stripes)
}