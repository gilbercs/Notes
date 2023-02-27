package br.com.gilbercs.notes.ui.screens.note

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.gilbercs.notes.R
import br.com.gilbercs.notes.ui.theme.ELEVATION_BAR
import br.com.gilbercs.notes.viewmodel.NoteViewModel

@Composable
fun CreateNoteTopBar(navController: NavController, noteViewModel: NoteViewModel){
    val context = LocalContext.current
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = ELEVATION_BAR,
        title = { Text(text = stringResource(id = R.string.create_app_bar))},
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("home_screen"){
                    popUpTo("home_screen"){
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.icon_arrow_back))
            }
        },
        actions = {
            IconButton(onClick = {
                if (noteViewModel.validateFields()){
                    val insertResult = noteViewModel.dbHandle("INSERT")
                    if (insertResult.equals("INSERT")){
                        Toast.makeText(context,"Gravado com sucesso!!",Toast.LENGTH_LONG).show()
                        navController.navigate("home_screen")
                    }else{
                        Toast.makeText(context,"Erro na Gravação!!",Toast.LENGTH_LONG).show()
                    }
                    Log.d("RESULTINSERT","$insertResult")
                }else{
                    Toast.makeText(context,"Preencha os campos!!",Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.icon_check))
            }
        }
    )
}
@Composable
fun UpdateNoteTopBar(navController: NavController, noteViewModel: NoteViewModel){
    val context = LocalContext.current
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = ELEVATION_BAR,
        title = { Text(text = stringResource(id = R.string.update_app_bar))},
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("home_screen"){
                    popUpTo("home_screen"){
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.icon_arrow_back))
            }
        },
        actions = {
            IconButton(onClick = {
                if (noteViewModel.validateFields()){
                    val updateResult = noteViewModel.dbHandle("UPDATE")
                    if (updateResult.equals("UPDATE")){
                        Toast.makeText(context,"Atualizado com sucesso!!",Toast.LENGTH_LONG).show()
                        navController.navigate("home_screen")
                    }else{
                        Toast.makeText(context,"Erro na Gravação!!",Toast.LENGTH_LONG).show()
                    }
                    Log.d("RESULTINSERT","$updateResult")
                }else{
                    Toast.makeText(context,"Preencha os campos!!",Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.EditNote,
                    contentDescription = stringResource(id = R.string.icon_check))
            }
        }
    )
}