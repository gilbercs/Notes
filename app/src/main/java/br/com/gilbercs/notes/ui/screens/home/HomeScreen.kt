package br.com.gilbercs.notes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import br.com.gilbercs.notes.R
import br.com.gilbercs.notes.ui.components.HomeFab
import br.com.gilbercs.notes.ui.screens.home.HomeContent
import br.com.gilbercs.notes.ui.theme.ELEVATION_BAR
import br.com.gilbercs.notes.viewmodel.NoteViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavController,
    noteViewModel: NoteViewModel){
    LaunchedEffect(key1 = Unit){
        noteViewModel.getNotes()
    }
    val getAllNotes by noteViewModel.getNotes.collectAsState()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = ELEVATION_BAR,
                backgroundColor = MaterialTheme.colors.surface,
                title = { Text(text = stringResource(id = R.string.home_app_bar), fontWeight = FontWeight.Bold)}
            )
        },
        content = {it
            HomeContent(
                getAllNotes = getAllNotes,
                navController = navController,
            onDelete = {event, note ->
                noteViewModel.dbHandle(event)
                noteViewModel.updateNotesFields(selectedNote = note)
            })
        },
        floatingActionButtonPosition = FabPosition.Center
        ,
        floatingActionButton = {
            HomeFab(navClick = navController)
        }
    )
}