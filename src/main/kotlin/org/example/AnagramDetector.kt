package org.example

class AnagramDetector {
    fun countAnagrams(words: List<String>): Int {

        val sortedWordCounts = mutableMapOf<String, Int>()

        words
            .map(this::toSortedWord)
            .forEach { sortedWord ->
                incrementWordCount(sortedWordCounts, sortedWord)
            }

        return sortedWordCounts.filterValues(this::moreThanOneExampleForSortedWord)
            .count()
    }

    private fun moreThanOneExampleForSortedWord(value: Int) = value > 1

    private fun incrementWordCount(
        sortedWordCounts: MutableMap<String, Int>,
        sortedWord: String
    ) {
        if (sortedWordCounts.contains(sortedWord).not()) {
            sortedWordCounts[sortedWord] = 1
        } else {
            sortedWordCounts.compute(sortedWord) { _, v -> v!! + 1 }
        }
    }

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

    private fun withMoreThanOneExample(sortedWordList: MutableList<String>) =
        sortedWordList.count() > 1

    private fun createSortedWordLists(words: List<String>): MutableMap<String, MutableList<String>> {
        val sortedWordLists = mutableMapOf<String, MutableList<String>>()
        words.forEach { word ->
            val sortedWord = this.toSortedWord(word)
            addWordToSortedWordList(sortedWordLists, sortedWord, word)
        }
        return sortedWordLists
    }

    private fun addWordToSortedWordList(
        sortedWordLists: MutableMap<String, MutableList<String>>,
        sortedWord: String,
        word: String
    ) {
        if (sortedWordLists.contains(sortedWord).not()) {
            sortedWordLists[sortedWord] = mutableListOf(word)
        } else {
            val sortedWordList = sortedWordLists.getValue(sortedWord)
            sortedWordList.add(word)
        }
    }
}
