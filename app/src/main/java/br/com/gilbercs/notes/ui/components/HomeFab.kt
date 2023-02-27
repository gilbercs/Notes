package br.com.gilbercs.notes.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.gilbercs.notes.R
import br.com.gilbercs.notes.ui.theme.ICON_FAB

@Composable
fun HomeFab(
    navClick: NavController
){
    FloatingActionButton(
        onClick = {
                  navClick.navigate("note_screen/${-1}")
                  },
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Icon(
            imageVector = Icons.Default.EditNote,
            contentDescription = stringResource(id = R.string.icon_edit_note),
            Modifier.size(ICON_FAB)
        )
    }
}