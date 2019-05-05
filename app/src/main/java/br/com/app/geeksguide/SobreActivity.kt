package br.com.app.geeksguide

import android.Manifest
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.widget.Button
import android.widget.Toast

import android.view.View
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.annotation.NonNull
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat.startActivity


class SobreActivity : AppCompatActivity() {

    private val REQUEST_CALL = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)


        val btFind = findViewById<Button>(R.id.btFind)
        val btCall = findViewById<Button>(R.id.btCall)
        val btShare = findViewById<Button>(R.id.btShare)


        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Sobre"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)



        btFind.setOnClickListener{
            //Toast.makeText(this, "Hi there! This is a Toast.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }


        //FAZER LIGACAO
        btCall.setOnClickListener{
            makePhoneCall();
        }


        //COMPARTILHAR CONTEUDO
        btShare.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Conheca nosso APP. Geeks Guide - Disponivel na Google Play");
            startActivity(Intent.createChooser(shareIntent,getString(R.string.shareAPPTitle)))
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun makePhoneCall() {

        if (ContextCompat.checkSelfPermission(this@SobreActivity,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@SobreActivity,
                    arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
        } else {
            val dial = "tel:11977016397"
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
        }


    }






}
