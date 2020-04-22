package com.example.mynote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*

class AddEditNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.mynote.EXTRA_ID"
        const val EXTRA_JUDUL = "com.example.mynote.EXTRA_JUDUL"
        const val EXTRA_DESKRIPSI = "com.example.mynote.EXTRA_DESKRIPSI"
        const val EXTRA_TELEPON = "com.example.mynote.EXTRA_TELEPON"
        const val EXTRA_PRIORITAS = "com.example.mynote.EXTRA_PRIORITAS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 100

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Biodata"
            edit_text_title.setText(intent.getStringExtra(EXTRA_JUDUL))
            edit_text_description.setText(intent.getStringExtra(EXTRA_DESKRIPSI))
            edit_text_phone.setText(intent.getStringExtra(EXTRA_TELEPON))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITAS, 1)
        } else {
            title = "Tambah Biodata"
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
        if (edit_text_title.text.toString().trim().isBlank() || edit_text_description.text.toString().trim().isBlank() || edit_text_phone.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Catatan kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_JUDUL, edit_text_title.text.toString())
            putExtra(EXTRA_DESKRIPSI, edit_text_description.text.toString())
            putExtra(EXTRA_TELEPON, edit_text_phone.text.toString())
            putExtra(EXTRA_PRIORITAS, number_picker_priority.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}