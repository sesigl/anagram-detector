package org.example

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class HelloKtTest {

    @Test
    internal fun name() {
        assertThat(1).isEqualTo(2)
    }
}