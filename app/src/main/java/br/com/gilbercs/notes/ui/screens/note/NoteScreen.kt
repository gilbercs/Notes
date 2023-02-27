package br.com.gilbercs.notes.ui.screens.note

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import br.com.gilbercs.notes.data.model.NoteModel
import br.com.gilbercs.notes.viewmodel.NoteViewModel

@Composable
fun NoteScreen(
    navController: NavController,
    noteViewModel: NoteViewModel,
    selected: NoteModel?){
    //declaração da variaveis
    var title  by noteViewModel.title
    var description by noteViewModel.description

    Scaffold(
        topBar = {
                if (selected==null){
                    CreateNoteTopBar(navController = navController, noteViewModel = noteViewModel)
                }else{
                    UpdateNoteTopBar(navController = navController, noteViewModel = noteViewModel)
                }
        },
    ){it
        NoteContent(
            title = title,
            onTitle = {title = it},
            description = description,
            onDescription = {description = it})
    }
}