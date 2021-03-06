package org.intellij.markdown

import java.io.File
import com.intellij.rt.execution.junit.FileComparisonFailure
import junit.framework.AssertionFailedError
import junit.framework.TestCase

val INTELLIJ_MARKDOWN_TEST_KEY = "Intellij.markdown.home"

public fun TestCase.assertSameLinesWithFile(filePath: String, text: String) {
    val file = File(filePath)

    if (!file.exists()) {
        file.writeText(text)
        throw AssertionFailedError("File not found. Created $filePath.")
    }

    val fileText = file.readText()
    if (!fileText.equals(text)) {
        throw FileComparisonFailure("File contents differ from the answer", fileText, text, filePath)
    }
}

public fun TestCase.getIntellijMarkdownHome(): String {
    return System.getProperty(INTELLIJ_MARKDOWN_TEST_KEY) ?: ".";
}

val TestCase.testName : String
    get() {
        val name = name
        assert(name.startsWith("test"))
        return name.substring("test".length).decapitalize()
    }