package br.com.gilbercs.notes.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import br.com.gilbercs.notes.data.model.NoteModel
import br.com.gilbercs.notes.ui.screens.EmptyContent
import br.com.gilbercs.notes.ui.theme.*
import br.com.gilbercs.notes.util.ResultState
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun HomeContent(
    getAllNotes: ResultState<List<NoteModel>>,
    navController: NavController,
    onDelete: (String,NoteModel) -> Unit){
    val listNotes: List<NoteModel>
   if (getAllNotes is ResultState.Sucess){
       listNotes = getAllNotes.data
       if (listNotes.isEmpty()){
           EmptyContent()
       }else{
           LazyColumn{
               items(items = listNotes){note->
                   Card(modifier = Modifier
                       .fillMaxSize()
                       .padding(all = ALL_PADDING),
                   elevation = CARD_ELEVATION,
                   shape = RoundedCornerShape(size = CARD_SHAPE)
                   ){
                       SwipeNote(note = note, navController = navController) {
                           onDelete(
                               "DELETE",
                               note
                           )
                       }
                   }
               }
           }
       }
   }
}
@Composable
fun SwipeNote(
    note: NoteModel,
    navController: NavController,
    onSwipe: () -> Unit ={}){
    val scope = rememberCoroutineScope()
    //swipe
    val deleteSwipe = SwipeAction(
        onSwipe = {
            scope.launch {
                onSwipe()
            }
        },
        icon = {
            Icon(
                modifier = Modifier
                    .padding(ALL_PADDING)
                    .size(ICON_SWIPE),
                imageVector = Icons.Default.DeleteForever,
                contentDescription = "",
                tint = Color.White)
        },
        background = Color.Red
    )
    val updateSwipe = SwipeAction(
        onSwipe = {
            navController.navigate("note_screen/${note.id}")
        },
        icon = {
            Icon(
                modifier = Modifier
                    .padding(ALL_PADDING)
                    .size(ICON_SWIPE),
                imageVector = Icons.Default.EditNote,
                contentDescription = "",
                tint = Color.White)
        },
        background = MaterialTheme.colors.primaryVariant
    )
    SwipeableActionsBox(
        swipeThreshold = ICON_THRESHOLD,
        startActions = listOf(updateSwipe),
        endActions = listOf(deleteSwipe)
    ) {
        NoteItem(note = note)
    }

}
@Composable
fun NoteItem(note: NoteModel){
    //declaração de variavel
    var expanded by remember{ mutableStateOf(false) }

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(all = ALL_PADDING)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = note.title, fontSize = TITLE, fontWeight = FontWeight.Bold)
                if (expanded){
                    Text(text = note.description, fontSize = TEXT_DEFAULT)
                }
            }
            IconButton(onClick = { expanded =! expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded){
                        "Menos"
                    }else{"Mais"})
            }
        }
    }