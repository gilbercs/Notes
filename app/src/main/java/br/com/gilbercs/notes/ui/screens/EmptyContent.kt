package br.com.gilbercs.notes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.gilbercs.notes.R
import br.com.gilbercs.notes.ui.theme.ICON_EMPTY

@Composable
fun EmptyContent(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.surface),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            modifier = Modifier
                .size(ICON_EMPTY),
            imageVector = Icons.Default.SentimentVeryDissatisfied,
            contentDescription = stringResource(id = R.string.icon_empty),
            tint = MaterialTheme.colors.primary
        )
        Text(
            text = "\n\n" + stringResource(id = R.string.text_empty))

    }
}