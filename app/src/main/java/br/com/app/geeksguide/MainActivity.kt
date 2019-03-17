package br.com.app.geeksguide

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText("home")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                message.setText("listar")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                message.setText("sobre")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_logout-> {
                message.setText("sair")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
