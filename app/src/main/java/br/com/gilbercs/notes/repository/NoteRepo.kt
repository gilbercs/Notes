package br.com.gilbercs.notes.repository

import br.com.gilbercs.notes.data.model.NoteModel
import br.com.gilbercs.notes.data.room.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepo @Inject constructor(
    private val noteDao: NoteDao
): NoteDao {
    override fun getAllNotes(): Flow<List<NoteModel>> {
        return noteDao.getAllNotes()
    }

    override fun selectNoteID(noteID: Int): Flow<NoteModel?> {
        return noteDao.selectNoteID(noteID = noteID)
    }

    override suspend fun insertNote(noteModel: NoteModel) {
        return noteDao.insertNote(noteModel = noteModel)
    }

    override suspend fun updateNote(noteModel: NoteModel) {
        return noteDao.updateNote(noteModel = noteModel)
    }

    override suspend fun deleteNote(noteModel: NoteModel) {
        return noteDao.deleteNote(noteModel = noteModel)
    }
}