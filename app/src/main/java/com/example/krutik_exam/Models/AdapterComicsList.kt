package com.example.krutik_exam.Models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.krutik_exam.R
import java.util.ArrayList

class AdapterComicsList(private val context: Context, comicsItems: ArrayList<ComicsItem>) : BaseAdapter() {
    private var comicsItems: List<ComicsItem> = comicsItems
    private var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return comicsItems.size
    }

    override fun getItem(i: Int): Any {
        return comicsItems[i]
    }

    override fun getItemId(i: Int): Long {
        return comicsItems[i].id.toLong()
    }

    private fun getComicsItem(position: Int): ComicsItem {
        return getItem(position) as ComicsItem
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var localeView: View? = view

        if (localeView == null) {
            localeView = inflater.inflate(R.layout.listview_item_comics, viewGroup, false)
        }

        val item = getComicsItem(i)

        Glide.with(localeView)
            .load(item.thumbnail)
            .into(localeView!!.findViewById(R.id.imageview_poster))


        val title = localeView.findViewById(R.id.textview_title) as TextView
        title.text = item.title

        val price = localeView.findViewById(R.id.textview_price) as TextView
        price.text = "Цена: ${item.price}$"

        val description = localeView.findViewById(R.id.textview_description) as TextView
        description.text = "${item.description}"

        return localeView
    }
}