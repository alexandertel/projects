package com.example.proba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Fragment1 : Fragment() {

    val mylist = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    lateinit var adapter1: Adapter1

    val retrofit = Retrofit.Builder()
        .baseUrl("https://test.spaceflightnewsapi.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        val rrr = rv
        //adapter1.notifyDataSetChanged()
        GlobalScope.launch(Dispatchers.IO) {
            val listArticles = api.getArticles()
            launch(Dispatchers.Main)
            {
              //  adapter1 = Adapter1(mylist)
                adapter1 = Adapter1(listArticles)
                rv.adapter = adapter1

//                Toast.makeText(requireContext(), listArticles.size.toString(), Toast.LENGTH_SHORT)
//                    .show()
            }

        }

    }
}