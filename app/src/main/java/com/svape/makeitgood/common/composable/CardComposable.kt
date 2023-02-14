package com.svape.makeitgood.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import com.svape.makeitgood.common.ext.dropdownSelector


@ExperimentalMaterialApi
@Composable
fun RegularCardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit
) {
    CardEditor(title, icon, content, modifier, onEditClick, MaterialTheme.colors.onSurface)
}

@ExperimentalMaterialApi
@Composable
fun DangerousCardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit
) {
    CardEditor(title, icon, content, modifier, onEditClick, MaterialTheme.colors.primary)
}

@ExperimentalMaterialApi
@Composable
fun CardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit,
    highlightColor: Color
) {
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = modifier,
        onClick = onEditClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(stringResource(title), color = highlightColor)
            }

            if (content.isNotBlank()) {
                Text(text = content, modifier = Modifier.padding(16.dp, 0.dp))
            }

            Icon(
                painter = painterResource(icon),
                contentDescription = "Icon",
                tint = highlightColor
            )
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun CardSelector(
    @StringRes label: Int,
    options: List<String>,
    selection: String,
    modifier: Modifier,
    onNewValue: (String) -> Unit
) {
    Card(backgroundColor = MaterialTheme.colors.onPrimary, modifier = modifier) {
        DropdownSelector(label, options, selection, modifier.dropdownSelector(), onNewValue)
    }
}