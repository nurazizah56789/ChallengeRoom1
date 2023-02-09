package com.example.challengeroom1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeroom1.room.Constant
import com.example.challengeroom1.room.Tbsiswa
import com.example.challengeroom1.room.dbsmksa
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    val db by  lazy { dbsmksa(this) }
    lateinit var siswaAdapter: SiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()

    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val siswa = db.tbsiswaDao().getTbsiswa()
            Log.d("MainActivity","dbResponse: $siswa")
            withContext(Dispatchers.Main){
                siswaAdapter.setData(siswa)

            }
        }
    }

    private fun halEdit(){
        btnInput.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
            startActivity(Intent(this,EditActivity::class.java))
        }
    }

    fun intentEdit(tbsisnis:Int, intentType:Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_nis",tbsisnis)
                .putExtra("intent_type",intentType)

        )

    }

   fun setupRecyclerView(){
        siswaAdapter = SiswaAdapter(arrayListOf(), object : SiswaAdapter.OnAdapterListener {
            override fun onClick(tbsiswa: Tbsiswa) {
                intentEdit(tbsiswa.nis,Constant.TYPE_READ)

            }

            override fun onUpdate(tbsiswa: Tbsiswa) {
                intentEdit(tbsiswa.nis,Constant.TYPE_UPDATE)

            }

            override fun onDelete(tbsiswa: Tbsiswa) {
               hapussiswa(tbsiswa)

            }
        })

        //id recyclerview
        list_datasiswa.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = siswaAdapter
        }
    }

    private fun hapussiswa (tbsiswa: Tbsiswa){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin hapus ${tbsiswa.nama}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbsiswaDao().deleteTbsiswa(tbsiswa)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }

        dialog.show()
    }

}

