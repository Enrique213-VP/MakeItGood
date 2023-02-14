package com.svape.makeitgood.common.ext

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.textButton(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 0.dp)
}

fun Modifier.basicButton(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp)
}

fun Modifier.spacer(): Modifier {
    return this.fillMaxWidth().padding(12.dp)
}

fun Modifier.card(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 0.dp,16.dp, 8.dp)
}

fun Modifier.dropdownSelector(): Modifier {
    return this.fillMaxWidth()
}