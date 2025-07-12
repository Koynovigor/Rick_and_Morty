package com.l3on1kl.rick_and_morty.presentation.characters.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.l3on1kl.rick_and_morty.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> EnumPicker(
    modifier: Modifier = Modifier,
    labelRes: Int,
    selected: T?,
    onValueChange: (T?) -> Unit,
    entries: Array<T>,
    textResMapper: (T) -> Int
) where T : Enum<T> {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selected?.let {
                stringResource(textResMapper(it))
            } ?: "",
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(stringResource(labelRes))
            },
            modifier = Modifier
                .menuAnchor(
                    MenuAnchorType.PrimaryNotEditable,
                    enabled = true
                )
                .fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            }
        )
        ExposedDropdownMenu(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(stringResource(R.string.any))
                },
                onClick = {
                    onValueChange(null)
                    expanded = false
                }
            )
            entries.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(stringResource(textResMapper(option)))
                    },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
