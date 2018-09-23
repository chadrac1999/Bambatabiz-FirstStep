package training.androidkotlin.bambatabiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_suivant.visibility = View.INVISIBLE

        Handler().postDelayed({
            btn_suivant.visibility = View.VISIBLE
            demarrer()
        }, 3000)
    }

    private fun demarrer(){
        val button_commencer = findViewById<Button>(R.id.btn_suivant)
        button_commencer.setOnClickListener{
            val intent = Intent(this, AppIntroActivity:: class.java)
            startActivity(intent)
        }
    }
}
