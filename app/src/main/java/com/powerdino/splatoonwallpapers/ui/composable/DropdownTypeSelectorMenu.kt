package com.powerdino.splatoonwallpapers.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.powerdino.splatoonwallpapers.R


@Composable
fun DropdownTypeSelectorMenu(

){
    var expanded by remember { mutableStateOf(false) }
    val typeList = listOf(
        R.string.type1,
        R.string.type2,
        R.string.type3,
        R.string.type4
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false}
    ) {
        typeList.forEach { option ->
            DropdownMenuItem(
                text = { Text(stringResource(option)) },
                onClick = { /* Do something... */ }
            )
        }
    }
}