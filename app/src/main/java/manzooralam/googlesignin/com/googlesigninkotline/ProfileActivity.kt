package manzooralam.googlesignin.com.googlesigninkotline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_profile.*
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener



class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var Intent1: Intent
        Intent1= getIntent()
        var name : String? =  Intent1.getStringExtra("name")
        var email:String? =Intent1.getStringExtra("email")
        textName.text=name
        textEmail.text=email
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        buttonLogout.setOnClickListener(){
            var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, OnCompleteListener<Void> {
                        val intent = Intent(this@ProfileActivity,MainActivity::class.java);
                        startActivity(intent);
                    })
        }
    }
}
