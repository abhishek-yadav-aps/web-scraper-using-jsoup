package com.thelazypeople.tester


import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements


class MainActivity : AppCompatActivity() {
    var datakeeper=data()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            starExtracter()
            submissionExtractor()
        }
    }

    private fun submissionExtractor() {
        val job=GlobalScope.launch(Dispatchers.Default) {
            val url="https://www.hackerrank.com/abhishek_aps"
            val doc:org.jsoup.nodes.Document=Jsoup.connect(url).get()
            val div: Elements? =doc.select("svg.graph")
            val graphDiv=div!!.select("svg.graph-domain m_3 y_2019").eq(0)
            val graphGroup=graphDiv.select("svg.graph-subdomain-group").eq(0)
            val graphDays=graphGroup.select("g")
            Log.i("tagger",graphDays.size.toString()+"graphsize")
        }
    }

    private suspend fun starExtracter() {
        val job=GlobalScope.launch(Dispatchers.Default) {
            val url="https://www.hackerrank.com/abhishek_aps"
            val doc:org.jsoup.nodes.Document=Jsoup.connect(url).get()
            val div: Elements? =doc.select("div.ui-badge-wrap")
            val size=div!!.size
            Log.i("tagger",div.size.toString()+"divsize")
            for(i in 0..size-1){
                val titleOfStar=div.select("text.badge-title").eq(i).text()
                val noOfStar=div.select("g.star-section").eq(i).select("svg").eq(0).select("svg.badge-star").size
                //Toast.makeText(this@MainActivity,noOfStar,Toast.LENGTH_SHORT).show()
                Log.i("tagger",titleOfStar.toString()+"->>"+noOfStar.toString())
               // datakeeper.star.add(noOfStar)
            }
        }
        job.join()

    }

    private fun shower() {
        Toast.makeText(this,datakeeper.star.isEmpty().toString(),Toast.LENGTH_SHORT).show()
        for (i in 0..datakeeper.star.size-1){
            Toast.makeText(this,datakeeper.star[i].toString(),Toast.LENGTH_SHORT).show()
        }
    }
}