package com.adidas.threestripes

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class ThreeStripesIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(ThreeStripesCommentDetector.ISSUE)
    override val api = CURRENT_API
}