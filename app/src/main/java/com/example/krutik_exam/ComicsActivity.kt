package com.example.krutik_exam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import com.bumptech.glide.Glide
import com.example.krutik_exam.Models.AdapterComicsList
import com.example.krutik_exam.Models.ComicsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_comics.*
import java.io.Serializable

class ComicsActivity : AppCompatActivity() {
    private lateinit var listviewComics: ListView
    private var comicsList = ArrayList<ComicsItem>()
    private lateinit var adapterComicsList: AdapterComicsList

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)


        NetHelper.instance.getComics()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    Log.d("MainActivity",it.toString())
                    for (i in 0 until it.data.results.count()){
                        val desc = if (it.data.results[i].description != null) it.data.results[i].description else "Описание отсутствует"
                        val comic = ComicsItem(
                            it.data.results[i].id,
                            it.data.results[i].title,
                            it.data.results[i].prices[0].price,
                            "${it.data.results[i].thumbnail.path}/portrait_xlarge.${it.data.results[i].thumbnail.extension}",
                            desc
                        )
                        comicsList.add(comic)
                        Log.d("MainActivity",it.data.results[i].title + " was added")

                        listviewComics = findViewById(R.id.listview_comics)
                        adapterComicsList = AdapterComicsList(this, comicsList)
                        listviewComics.adapter = adapterComicsList

                        listviewComics.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
                            val intent = Intent(this, ComicsDescriptionActivity::class.java)
                            intent.putExtra("thumbnail", comicsList[i].thumbnail)
                            intent.putExtra("title", comicsList[i].title)
                            intent.putExtra("price", comicsList[i].price)
                            intent.putExtra("description", comicsList[i].description)
                            intent.putExtra("id", comicsList[i].id)
                            startActivity(intent)
                        }
                    }
                },
                onError = {
                    Log.d("MainActivity",it.toString())
                }
            )



        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_comics -> {
                //do nothing
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_creators -> {
                //open intent
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourite -> {
                //open intent
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                finish()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
