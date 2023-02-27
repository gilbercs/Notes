package br.com.gilbercs.notes.ui.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.gilbercs.notes.ui.screens.HomeScreen
import br.com.gilbercs.notes.ui.screens.note.NoteScreen
import br.com.gilbercs.notes.viewmodel.NoteViewModel

@Composable
fun NavScreen(
    navHostController: NavHostController,
    noteViewModel: NoteViewModel){
    NavHost(navController = navHostController, startDestination = "home_screen"){
        composable(route = "home_screen"){
            HomeScreen(navController = navHostController, noteViewModel = noteViewModel)
        }
        composable(
            route = "note_screen/{note_id}",
            arguments = listOf(navArgument("note_id"){type = NavType.IntType})
        ){navBackStackEntry ->
            val noteId = navBackStackEntry.arguments!!.getInt("note_id")
            Log.d("args",navBackStackEntry.arguments?.getInt("note_id").toString())
            LaunchedEffect(key1 = noteId){
                noteViewModel.getSelectNoteID(noteID = noteId)
            }
            val selectedNote by noteViewModel.selectedNote.collectAsState()
            LaunchedEffect(key1 = selectedNote){
                if (selectedNote!=null || noteId == -1){
                    noteViewModel.updateNotesFields(selectedNote = selectedNote)
                }
            }
            NoteScreen(
                navController = navHostController, noteViewModel = noteViewModel, selected = selectedNote)
        }
    }
}