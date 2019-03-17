package br.com.app.geeksguide

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList
import android.Manifest.permission.READ_CONTACTS
import android.content.Intent
import android.widget.Toast
import com.facebook.stetho.Stetho

import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private val newUserRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        Stetho.initializeWithDefaults(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mAuth = FirebaseAuth.getInstance()


        btCriarConta.setOnClickListener {
            startActivityForResult(Intent(this, SignUpActivity::class.java), newUserRequestCode)
        }

        btLogin.setOnClickListener {

            mAuth.signInWithEmailAndPassword(
                    edEmail.text.toString(),
                    edSenha.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    goToMenu()
                } else {
                    Toast.makeText(this@LoginActivity, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goToMenu() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }


    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

}
