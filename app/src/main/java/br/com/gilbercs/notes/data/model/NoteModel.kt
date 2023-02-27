package br.com.gilbercs.notes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import javax.annotation.concurrent.Immutable

@Entity(
    tableName = "tb_notes",
    indices = [
        Index("title", unique = true)
    ]
)
@Immutable
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String
)
