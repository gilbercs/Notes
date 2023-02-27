package br.com.gilbercs.notes

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.gilbercs.notes.data.room.NoteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {
    @Provides
    @Singleton
    fun getNoteDb(
        @ApplicationContext
        context: Context)= Room.databaseBuilder(
        context = context,
        NoteDB::class.java,
        "note.db"
        ).build()
    @Provides
    @Singleton
    fun getNoteDao(db: NoteDB) = db.noteDao()
}