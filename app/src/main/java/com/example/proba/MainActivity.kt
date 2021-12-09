package com.example.proba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {   //конструктор активити
    override fun onCreate(savedInstanceState: Bundle?) {  //Переопределяем функцию onCreate (создание активности) сщхраняем состояние активности в Бандл
        super.onCreate(savedInstanceState)    // Вызов конструктора родительского класа
        setContentView(R.layout.activity_main)  // Создает содержимое слоя по макету в activity_main
        supportFragmentManager.beginTransaction().add(R.id.bigMT, FragmentArticleList()).commit()  // Вызов менеджера фрагментов - вызвать фрагмент FragmentArticleList в макет bigMT
    }
}
