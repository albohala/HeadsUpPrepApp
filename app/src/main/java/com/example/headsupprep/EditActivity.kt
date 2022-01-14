package com.example.headsupprep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.headsupprep.databinding.ActivityEditBinding
import com.example.headsupprep.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {
    private val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    lateinit var binding: ActivityEditBinding

    private var celebID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        celebID = intent.extras!!.getInt("celebrityID", 0)

        binding.btnUpdateCeleb.setOnClickListener {
            updateData()
        }
        binding.btnDeleteCeleb.setOnClickListener {
            deleteData()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun updateData() {
        apiInterface?.updateCelebs(celebID, CelebrityItem(
            binding.etEditName.text.toString(),
            0,
            binding.etEditTabooOne.text.toString(),
            binding.etEditTabooTwo.text.toString(),
            binding.etEditTabooThree.text.toString())
        )?.enqueue(object:
            Callback<CelebrityItem> {
            override fun onResponse(call: Call<CelebrityItem>, response: Response<CelebrityItem>) {
                Toast.makeText(this@EditActivity, "Celebrity updated", Toast.LENGTH_LONG).show()
                Log.d("UPDATE", "onResponse: Celeb Added")
            }

            override fun onFailure(call: Call<CelebrityItem>, t: Throwable) {
                Toast.makeText(this@EditActivity, "Celebrity failed to be updated", Toast.LENGTH_LONG).show()
                Log.d("UPDATE", "onResponse: Celeb Added")
            }
        })
    }

    private fun deleteData() {
        apiInterface?.deleteCelebs(celebID)?.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@EditActivity, "Celebrity deleted", Toast.LENGTH_LONG).show()
                Log.d("DELETE", "onResponse: Celeb Added")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@EditActivity, "Celebrity failed to delete", Toast.LENGTH_LONG).show()
                Log.d("DELETE", "onResponse: Did not delete")
            }
        })
    }
}