package br.com.gilbercs.notes.ui.screens.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import br.com.gilbercs.notes.R
import br.com.gilbercs.notes.ui.theme.ALL_PADDING
import br.com.gilbercs.notes.ui.theme.SPACER_HEIGHT
import br.com.gilbercs.notes.ui.theme.TEXT_DEFAULT

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteContent(
    title: String,
    onTitle:(String)-> Unit,
    description: String,
    onDescription:(String) ->Unit
){

    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.surface)
        .padding(ALL_PADDING)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {onTitle(it)},
            label = { Text(text = stringResource(id = R.string.note_title), fontSize = TEXT_DEFAULT)},
            placeholder = { Text(text = stringResource(id = R.string.place_holder_note_title), fontSize = TEXT_DEFAULT)},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(SPACER_HEIGHT))
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = {onDescription(it)},
            label = { Text(text = stringResource(id = R.string.note_description), fontSize = TEXT_DEFAULT)},
            placeholder = { Text(text = stringResource(id = R.string.place_holder_note_description), fontSize = TEXT_DEFAULT)},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
    }
}