package com.example.proba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {   //создание родительского класа BaseFragment

    abstract var layoutResource: Int   //создаем переменнну layoutResource типа Int

    override fun onCreateView(   // создаем вьюшку
        inflater: LayoutInflater, container: ViewGroup?,  // создается (надувается) вьюшка из макета (Лайоут) используя ViewGroup - родительский клас содержащий другие втюшки
        savedInstanceState: Bundle?   // сохраняем состояние в Бандл  (? - безопастная операция - защита от нулл ошибки)
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layoutResource, container, false)   //выводим значение layoutResource - создаем вьюшку (но не сейчас а в наследуемом класе
    }
}

