package com.example.praktikum10.catatan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    var nama: String,
    var status: String,
    var alamat: String,
    var priority: Int

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 }