package com.powerdino.splatoonwallpapers.ui.composable

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.powerdino.splatoonwallpapers.R


@Composable
fun DropdownTypeSelectorMenu(
    changeTypeOfWallpaper:(String) -> Unit,
    expanded: Boolean,
    onDismissRequest:() -> Unit
){
    val typeList = listOf(
        R.string.type0,
        R.string.type1,
        R.string.type2,
        R.string.type3,
        R.string.type4
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {

        typeList.forEach { option ->
            val stringOption = stringResource(option)
            DropdownMenuItem(
                text = { Text(stringResource(option)) },
                onClick = {
                    changeTypeOfWallpaper(stringOption)
                }
            )
        }

    }
}