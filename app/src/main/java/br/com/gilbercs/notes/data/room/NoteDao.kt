package br.com.gilbercs.notes.data.room

import androidx.room.*
import br.com.gilbercs.notes.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    //Listar Anotações
    @Query("SELECT * FROM tb_notes ORDER BY id ASC")
    fun getAllNotes(): Flow<List<NoteModel>>
    //Selecionar Anotação
    @Query("SELECT * FROM tb_notes WHERE id=:noteID")
    fun selectNoteID(noteID: Int): Flow<NoteModel?>
    //Inserir Anotações
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteModel: NoteModel)
    //Atualizar Anotação
    @Update
    suspend fun updateNote(noteModel: NoteModel)
    //Deletar Anotação
    @Delete
    suspend fun deleteNote(noteModel: NoteModel)
}