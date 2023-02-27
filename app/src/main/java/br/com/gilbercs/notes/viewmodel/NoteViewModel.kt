package br.com.gilbercs.notes.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gilbercs.notes.data.model.NoteModel
import br.com.gilbercs.notes.repository.NoteRepo
import br.com.gilbercs.notes.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * viewModelScope é um CoroutineScope predefinido que está incluído nas extensões KTX ViewModel. Todas as corrotinas precisam ser executadas em um escopo. Um CoroutineScope gerencia uma ou mais corrotinas relacionadas.
* launch é uma função que cria uma corrotina e envia a execução do corpo funcional para o agente correspondente.
* Dispatchers.IO indica que essa corrotina deve ser executada em uma linha de execução reservada para operações de E/S.
 * */
@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepo: NoteRepo
): ViewModel() {
    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")

    private val _getNotes = MutableStateFlow<ResultState<List<NoteModel>>>(ResultState.Idle)
    val getNotes: StateFlow<ResultState<List<NoteModel>>> = _getNotes

    private val _selectedNote: MutableStateFlow<NoteModel?> = MutableStateFlow(null)
    val selectedNote: StateFlow<NoteModel?> = _selectedNote
    //listar Anotações
    fun getNotes(){
        viewModelScope.launch {
            val result = try {
                noteRepo.getAllNotes().collect{
                    _getNotes.value = ResultState.Sucess(it)
                }
            }catch (error: Exception){
                ResultState.Error(error)
            }
            Log.d("RESULTSATE","$result")
        }
    }
    //inserir anotações
    private fun insertNote(){
        viewModelScope.launch(Dispatchers.IO){
                val note = NoteModel(
                    title = title.value,
                    description = description.value
                )
                noteRepo.insertNote(noteModel =note)
        }
    }
    //atualizar anotações
    private fun updateNote(){
       viewModelScope.launch(Dispatchers.IO){
           val note = NoteModel(
               id = id.value,
               title = title.value,
               description = description.value
           )
           noteRepo.updateNote(noteModel = note)
       }
    }
    //deletar anotações
    private fun deleteNote(){
        viewModelScope.launch(Dispatchers.IO){
            val note = NoteModel(
                id = id.value,
                title = title.value,
                description = description.value
            )
            noteRepo.deleteNote(noteModel = note)
        }
    }
    //selecionar Anotação
    fun getSelectNoteID(noteID: Int){
        viewModelScope.launch {
            noteRepo.selectNoteID(noteID = noteID).collect{
                _selectedNote.value = it
            }
        }
    }
    //Set Anotação Selecionada nos campos
    fun updateNotesFields(selectedNote: NoteModel?){
        if (selectedNote !=null){
            id.value = selectedNote.id
            title.value = selectedNote.title
            description.value = selectedNote.description
        }else{
            //limpar os campos:
            id.value = 0
            title.value = ""
            description.value = ""
        }
    }
    //validar campos
    fun validateFields():Boolean{
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
    //eventos o banco
    fun dbHandle(action: String): String{
        var result = action
        when(result){
            "INSERT" ->{
                insertNote()
            }
            "UPDATE"->{
                updateNote()
            }
            "DELETE"->{
                deleteNote()
            }else ->{
                 result = "NO_EVENT"
            }

        }
        return result
    }

}