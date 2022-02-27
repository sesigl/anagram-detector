package org.example

class AnagramDetector {
    fun countAnagrams(words: List<String>): Int {

        val sortedWordCounts = countSortedWords(words)

        return sortedWordCounts.filterValues(this::moreThanOneExampleForSortedWord)
            .count()
    }

    private fun countSortedWords(words: List<String>) = words
        .groupingBy(this::toSortedWord)
        .eachCount()

    private fun moreThanOneExampleForSortedWord(value: Int) = value > 1

    private fun toSortedWord(word: String): String {
        val arr = word.toCharArray()
        return arr.sorted().joinToString("")
    }

    fun detectAnagrams(words: List<String>): List<List<String>> {
        val sortedWordLists = createSortedWordLists(words)

        return sortedWordLists
            .filterValues(this::withMoreThanOneExample)
            .map { entry -> entry.value }
    }

    private fun withMoreThanOneExample(sortedWordList: List<String>) =
        sortedWordList.count() > 1

    private fun createSortedWordLists(words: List<String>): Map<String, List<String>> {
        return words.groupBy(this::toSortedWord) { it }
    }
}
