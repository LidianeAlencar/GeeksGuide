package br.com.app.geeksguide


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.app.geeksguide.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        btCriar.setOnClickListener {

            mAuth.createUserWithEmailAndPassword(
                    edEmail.text.toString(),
                    edSenha.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {

                    Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                    val returnIntent = Intent()
                    returnIntent.putExtra("email", edEmail.text.toString())
                    setResult(RESULT_OK, returnIntent)
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
