package com.example.headsupprep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.headsupprep.databinding.ActivityAddBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class AddActivity : AppCompatActivity() {
    private val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddCeleb.setOnClickListener{
            addCeleb()
            finish()
        }
        binding.btnBack.setOnClickListener{
            finish()
        }
    }

    private fun addCeleb() {
        apiInterface?.postCelebs(CelebrityItem(
            binding.etAddName.text.toString(),
            0,
            binding.etAddTabooOne.text.toString(),
            binding.etAddTabooTwo.text.toString(),
            binding.etAddTabooThree.text.toString())
        )?.enqueue(object: retrofit2.Callback<CelebrityItem> {
            override fun onResponse(call: Call<CelebrityItem>, response: Response<CelebrityItem>) {
                Toast.makeText(this@AddActivity, "Celebrity added", Toast.LENGTH_LONG).show()
                Log.d("ADDED", "onResponse: Celeb Added")
            }

            override fun onFailure(call: Call<CelebrityItem>, t: Throwable) {
                Toast.makeText(this@AddActivity, "Celebrity failed to be added", Toast.LENGTH_LONG).show()
                Log.d("ADDED", "onResponse: Celeb wasn't added")
            }
        })
    }
}