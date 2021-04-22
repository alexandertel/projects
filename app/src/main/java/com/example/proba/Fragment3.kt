package com.example.proba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment3 : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.button).setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.bigMT, Fragment1()).commit()


        }
        view.findViewById<View>(R.id.imageButton).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.bigMT, Fragment2()).commit()
        }
    }
}