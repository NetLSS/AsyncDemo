package com.lilcode.example.asyncdemo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lilcode.example.asyncdemo.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    private inner class MyTask : AsyncTask<String, Int, String>() {
        override fun doInBackground(vararg params: String?): String {
            var i = 0
            while (i <= 20) {
                try {
                    Thread.sleep(1000)
                    publishProgress(i) // 정기적인 UI 변경
                    i++
                } catch (e: Exception){
                }
            }
            return "Button Pressed"
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val counter = values[0]
            binding.myTextView.text = "Counter  = $counter"
        }

        override fun onPostExecute(result: String?) {
            binding.myTextView.text = result
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buttonClick(view: View) {
        // 하나의 작업
//        val task = MyTask().execute()

        // 여러 작업 병행 시에는 아래와 같이
        val task = MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR) // CPU 코어수로 제한됨.

        Toast.makeText(this, "my cpu cores : ${Runtime.getRuntime().availableProcessors()}", Toast.LENGTH_SHORT).show()
        // 안드로이드 장치 풀에 사용 가능한 최대 스레드 개수는 (CPU 코어수 * 2 + 1)
    }
}