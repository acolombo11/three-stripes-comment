package com.adidas.threestripes

import com.pinterest.ktlint.core.RuleProvider
import com.pinterest.ktlint.core.RuleSetProviderV2

class ThreeStripesRuleSetProvider : RuleSetProviderV2("adidas-ktlint-rules", NO_ABOUT) {
    override fun getRuleProviders(): Set<RuleProvider> = setOf(RuleProvider { ThreeStripesCommentRule() })
}
