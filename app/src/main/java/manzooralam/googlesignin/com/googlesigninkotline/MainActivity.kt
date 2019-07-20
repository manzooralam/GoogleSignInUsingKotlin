package manzooralam.googlesignin.com.googlesigninkotline

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException

const val RC_SIGN_IN=123
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso.
       var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        sign_in_button.visibility= View.VISIBLE
        textView.visibility = View.GONE
       // sign_in_button.setSize(SignInButton.SIZE_STANDARD)
        sign_in_button.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            //val personName = acct.displayName
           /* val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl*/
            val personName = acct.displayName
            val personEmail = acct.email
            val intent = Intent(this@MainActivity,ProfileActivity::class.java);
            var userName = personName
            var userEmail = personEmail
            intent.putExtra("name", userName)
            intent.putExtra("email", userEmail)
            startActivity(intent);

            /*sign_in_button.visibility= View.GONE
            textView.text=acct.displayName +"\n"+acct.email
            textView.visibility=View.VISIBLE*/
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) = try {
        val account = completedTask.getResult(ApiException::class.java)

        // Signed in successfully, show authenticated UI.
       // updateUI(account)
        val personName = account.displayName
        val personEmail = account.email
        val intent = Intent(this@MainActivity,ProfileActivity::class.java);
        var userName = personName
        var userEmail = personEmail
        intent.putExtra("name", userName)
        intent.putExtra("email", userEmail)
        startActivity(intent);

       /* sign_in_button.visibility= View.GONE
        textView.text=account.displayName
        textView.visibility=View.VISIBLE*/

    } catch (e: ApiException) {
        // The ApiException status code indicates the detailed failure reason.
        // Please refer to the GoogleSignInStatusCodes class reference for more information.
        Log.w("ApiException","signInResult:failed code=" + e.statusCode)
        //updateUI(null)
        sign_in_button.visibility= View.VISIBLE
        textView.text=""
        textView.visibility=View.GONE
    }
}
