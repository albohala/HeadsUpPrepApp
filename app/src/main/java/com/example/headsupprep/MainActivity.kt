package com.example.headsupprep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.headsupprep.databinding.ActivityAddBinding
import com.example.headsupprep.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
    lateinit var binding: ActivityMainBinding

    private lateinit var celebInfo: ArrayList<CelebrityItem>
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        celebInfo = arrayListOf()

        rvAdapter = RVAdapter(celebInfo)
        binding.rvMain.adapter = rvAdapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        binding.btnSubmit.setOnClickListener {
            updateCelebrity()
        }
        displayData()
    }

    private fun displayData() {
        apiInterface?.getCelebs()?.enqueue(object: retrofit2.Callback<Celebrity> {
            override fun onResponse(call: Call<Celebrity>, response: Response<Celebrity>) {
                try {
                    celebInfo = response.body()!!
                    rvAdapter.update(celebInfo)
                    Log.d("ADD_USER", "onResponse: $celebInfo")
                } catch (e: Exception) {
                    Log.d("DISPLAY_DATA", "onResponse: Did not display data")
                    Log.d("EXCEPTION", "onResponse: $e")
                }
            }

            override fun onFailure(call: Call<Celebrity>, t: Throwable) {
                Log.d("FAILED", "onResponse: Did not display data")
            }

        })
    }

    private fun updateCelebrity(){
        var celebrityID = 0
        for(celebrity in celebInfo){
            if(binding.etEnterCelebName.text.toString() == celebrity.name){
                celebrityID = celebrity.pk
                intent = Intent(applicationContext, EditActivity::class.java)
                intent.putExtra("celebrityID", celebrityID)
                startActivity(intent)
            }else{
                Toast.makeText(this, "${binding.etEnterCelebName.text} not found", Toast.LENGTH_LONG).show()
            }
        }
    }
}
