package com.adidas.threestripes

import com.pinterest.ktlint.core.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiComment
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement

class ThreeStripesCommentRule : Rule("three-stripes-comment") {
    override fun beforeVisitChildNodes(node: ASTNode, autoCorrect: Boolean, emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit) {
        if (node is PsiComment && node is LeafPsiElement) {
            when {
                node.getText().startsWith("/*") -> {}
                !node.getText().startsWith("///") -> emit(
                    node.startOffset,
                    "The comment doesn't start with the Three Stripes",
                    true,
                )
                !node.getText().startsWith("/// ") -> emit(
                    node.startOffset,
                    "Missing space after the adidas Three Stripes ///",
                    true,
                )
            }
        }
    }
}