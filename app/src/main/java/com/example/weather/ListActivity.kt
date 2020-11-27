package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ListView

class ListActivity : AppCompatActivity() {

    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listView = findViewById(R.id.listView)
        var list = mutableListOf<Model>()

        list.add(Model("London",      R.drawable.london  ))
        list.add(Model("Lisboa",       R.drawable.portugal   ))
        list.add(Model("Madrid",       R.drawable.madrid   ))
        list.add(Model("Paris",       R.drawable.paris   ))
        list.add(Model("Berlim",       R.drawable.berlim   ))
        list.add(Model("Copenhaga",       R.drawable.copenhaga   ))
        list.add(Model("Roma",       R.drawable.roma   ))
        list.add(Model("Dublin",       R.drawable.dublin   ))
        list.add(Model("Praga",       R.drawable.praga   ))
        list.add(Model("Viena",       R.drawable.viena   ))

        listView.adapter = ListAdapter(this,R.layout.row,list)

        listView.setOnItemClickListener{parent, view, position, id ->

            if (position==0){
                val nome = "London"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==1){
                val nome = "Lisboa"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==2){
                val nome = "Madrid"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==3){
                val nome = "Paris"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==4){
                val nome = "Berlim"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==5){
                val nome = "Copenhaga"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==6){
                val nome = "Roma"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==7){
                val nome = "Dublin"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==8){
                val nome = "Praga"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }
            if (position==9){
                val nome = "Viena"
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                }
                startActivity(intent)
            }

        }

    }


}
