package br.com.gilbercs.notes.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.gilbercs.notes.data.model.NoteModel

@Database(
    entities = [
        NoteModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NoteDB: RoomDatabase(){
    abstract fun noteDao(): NoteDao
}