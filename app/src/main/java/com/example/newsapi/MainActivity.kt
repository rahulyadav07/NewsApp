package com.example.newsapi
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newsapi.Color.getcolor
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter :NewsAdapter
    private var articles = mutableListOf<Article>()
    var pagecont =0
    var totalresult =-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity,articles)
        newsList.adapter= adapter

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object :StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
               container.setBackgroundColor(Color.parseColor(getcolor()))
                if(totalresult>layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition()>=layoutManager.itemCount-5){
                    pagecont++
                    getNews()
                }
            }
        })
        newsList.layoutManager  = layoutManager
        getNews()
    }
    private fun getNews() {
        val new = NewsService.NewsInstace.getHeadLines("in",pagecont++)
        new.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if(news!=null){
                    Toast.makeText(this@MainActivity,"Request is SucessFull",Toast.LENGTH_SHORT).show()
                    totalresult =news.totalResults
                    articles    .addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
                else{
                    Toast.makeText(this@MainActivity,"empty",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Request is not SucessFull",Toast.LENGTH_SHORT).show()
            }
        })
    }
}