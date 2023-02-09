package com.example.challengeroom1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challengeroom1.room.Constant
import com.example.challengeroom1.room.Tbsiswa
import com.example.challengeroom1.room.dbsmksa
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { dbsmksa(this) }
    private var tbsisnis: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolPerintah()
        setupView()
        tbsisnis = intent.getIntExtra("intent_nis",tbsisnis)
        Toast.makeText(this,tbsisnis.toString(),Toast.LENGTH_SHORT).show()
    }



    fun setupView() {
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnsave.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                ETnis.visibility = View.GONE
                tampilsiswa()
            }
            Constant.TYPE_UPDATE -> {
                btnsave.visibility = View.GONE
                ETnis.visibility = View.GONE
                tampilsiswa()
            }


            }
        }

        fun tombolPerintah() {
            btnsave.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbsiswaDao().addTbsiswa(
                        Tbsiswa(
                            ETnis.text.toString().toInt(),
                            ETname.text.toString(),
                            ETkelas.text.toString(),
                            ETalamat.text.toString()
                        )
                    )
                    finish()
                }
            }

            btnUpdate.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbsiswaDao().updateTbsiswa(
                        Tbsiswa(tbsisnis,
                            ETname.text.toString(),
                            ETkelas.text.toString(),
                            ETalamat.text.toString()
                        )
                    )
                    finish()
                }
            }
        }

        fun tampilsiswa() {
            tbsisnis = intent.getIntExtra("intent_nis", 0)
            CoroutineScope(Dispatchers.IO).launch {
                val siswa = db.tbsiswaDao().tampilsiswa(tbsisnis)[0]
                //ETnis.setText(siswa.nis)
                ETname.setText(siswa.nama)
                ETkelas.setText(siswa.kelas)
                ETalamat.setText(siswa.alamat)
            }
        }
}




