package com.example.praktikum10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*

class AddEditNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.praktikum10.EXTRA_ID"
        const val EXTRA_NAMA = "com.example.praktikum10.EXTRA_NAMA"
        const val EXTRA_STATUS = "com.example.praktikum10.EXTRA_STATUS"
        const val EXTRA_ALAMAT = "com.example.praktikum10.EXTRA_ALAMAT"
        const val EXTRA_PRIORITAS = "com.example.praktikum10.EXTRA_PRIORITAS"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 4
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Data Pasien"
            edit_text_nama.setText(intent.getStringExtra(EXTRA_NAMA))
            edit_text_status.setText(intent.getStringExtra(EXTRA_STATUS))
            edit_text_alamat.setText(intent.getStringExtra(EXTRA_ALAMAT))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITAS, 1)
        } else {
            title = "Tambah Data Pasien"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        if (edit_text_nama.text.toString().trim().isBlank() || edit_text_status.text.toString().trim().isBlank() || edit_text_alamat.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Catatan kosong!", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent().apply {
            putExtra(EXTRA_NAMA, edit_text_nama.text.toString())
            putExtra(EXTRA_STATUS, edit_text_status.text.toString())
            putExtra(EXTRA_ALAMAT, edit_text_alamat.text.toString())
            putExtra(EXTRA_PRIORITAS, number_picker_priority.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}