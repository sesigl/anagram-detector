package org.fullstack101

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import java.io.File

/**
 * Exercise from: http://codekata.com/kata/kata06-anagrams/
 */
class AnagramDetectorTest {

    @Test
    fun `countAnagrams - when ist is empty count is 0`() {
        val anagramDetector = AnagramDetector()

        val anagramCount = anagramDetector.countAnagrams(listOf())

        assertThat(anagramCount).isEqualTo(0)
    }

    @Test
    fun `countAnagrams - when no anagrams in list then count is 0`() {
        val anagramDetector = AnagramDetector()

        val anagramCount = anagramDetector.countAnagrams(listOf("house", "price"))

        assertThat(anagramCount).isEqualTo(0)
    }

    @Test
    fun `countAnagrams - when single anagrams span over 2 words in list then count is 1`() {
        val anagramDetector = AnagramDetector()

        val anagramCount = anagramDetector.countAnagrams(listOf("sinks", "skins"))

        assertThat(anagramCount).isEqualTo(1)
    }

    @Test
    fun `countAnagrams - when single anagrams span over 2 words of 3 given words then count is 1`() {
        val anagramDetector = AnagramDetector()

        val anagramCount = anagramDetector.countAnagrams(listOf("sinks", "skins", "house"))

        assertThat(anagramCount).isEqualTo(1)
    }

    @Test
    fun `countAnagrams - when two anagrams span over multiple words then the anagram count is returned`() {
        val anagramDetector = AnagramDetector()

        val anagramCount = anagramDetector.countAnagrams(
            listOf(
                "sinks", "skins",
                "house",
                "enlist", "inlets", "listen", "silent"
            )
        )

        assertThat(anagramCount).isEqualTo(2)
    }

    @Test
    fun `detectAnagrams - when no words then no anagrams`() {
        val anagramDetector = AnagramDetector()

        val anagrams = anagramDetector.detectAnagrams(
            listOf()
        )

        assertThat(anagrams).isEqualTo(listOf())
    }

    @Test
    fun `detectAnagrams - when single word then no anagrams`() {
        val anagramDetector = AnagramDetector()

        val anagrams = anagramDetector.detectAnagrams(
            listOf("house")
        )

        assertThat(anagrams).isEqualTo(listOf())
    }

    @Test
    fun `detectAnagrams - when two words are anagrams then both words are returned`() {
        val anagramDetector = AnagramDetector()

        val anagrams = anagramDetector.detectAnagrams(
            listOf("sunders", "undress")
        )

        assertThat(anagrams).isEqualTo(
            listOf(
                listOf("sunders", "undress")
            )
        )
    }

    @Test
    fun `detectAnagrams - when two words are anagrams and a third one is not, then the anagram only is returned`() {
        val anagramDetector = AnagramDetector()

        val anagrams = anagramDetector.detectAnagrams(
            listOf("sunders", "house", "undress")
        )

        assertThat(anagrams).isEqualTo(
            listOf(
                listOf("sunders", "undress")
            )
        )
    }

    @Test
    fun `use detect anagrams to solve the exercise`() {
        val anagramDetector = AnagramDetector()

        val words = File(getFileFromClassPath("/wordlist.txt")).bufferedReader().readLines()

        val anagrams = anagramDetector.detectAnagrams(
            words
        )

        // provided by http://codekata.com/kata/kata06-anagrams/
        assertThat(anagrams.size).assertThat(20683)
    }

    private fun getFileFromClassPath(path: String): String {
        val resource = object {}.javaClass.getResource(path)

        checkNotNull(resource)

        return resource.file
    }
}
