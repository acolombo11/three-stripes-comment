package com.adidas.threestripes

import com.pinterest.ktlint.test.lint
import com.pinterest.ktlint.core.LintError
import org.junit.Test

class ThreeStripesCommentRuleTest {

    private val ruleSet
        get() = ThreeStripesRuleSetProvider().getRuleProviders()

    @Test
    fun testDoubleForwardSlashComment() {
        assert(
            ruleSet.lint("// Comment is not using Three Stripes, Adi not  is happy").single() == LintError(
                1,
                1,
                "three-stripes-comment",
                "The comment doesn't start with the Three Stripes",
            ),
        )
    }

    @Test
    fun testThreeStripesCommentNoSpace() {
        assert(
            ruleSet.lint("///Comment is not using Three Stripes, Adi not  is happy").single() == LintError(
                1,
                1,
                "three-stripes-comment",
                "Missing space after the adidas Three Stripes ///",
            ),
        )
    }

    @Test
    fun testThreeStripesComment() {
        assert(
            ruleSet.lint("/// Comment is not using Three Stripes, Adi not  is happy").isEmpty(),
        )
    }
}