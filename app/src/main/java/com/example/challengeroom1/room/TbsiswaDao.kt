package com.example.challengeroom1.room

import androidx.room.*

@Dao

interface TbsiswaDao {

    @Insert
    fun addTbsiswa(tbsiswa: Tbsiswa)

    @Update
    fun updateTbsiswa(tbsiswa: Tbsiswa)

    @Delete
    fun deleteTbsiswa(tbsiswa: Tbsiswa)

    @Query("SELECT * FROM tbsiswa")
    fun getTbsiswa():List<Tbsiswa>

    @Query("SELECT * FROM Tbsiswa Where nis =:tbsis_nis")
    fun tampilsiswa(tbsis_nis: Int):List<Tbsiswa>


}
