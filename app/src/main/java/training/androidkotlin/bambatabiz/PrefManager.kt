package training.androidkotlin.bambatabiz

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity

/**
 * Created by chadrac on 9/11/18.
 */
class PrefManager {

    lateinit var con: Context

    lateinit var pref: SharedPreferences

    constructor(con: Context){
        this.con = con
        getSP()
    }

    private fun getSP() {
        pref = con.getSharedPreferences(con.getString(R.string.pref_name), Context.MODE_PRIVATE)
    }

    fun writeSP() {
        var editor: SharedPreferences.Editor = pref.edit()
        editor.putString(con.getString(R.string.pref_key), "SUIVANT")
        editor.commit()
    }

    fun checkPreference() : Boolean {
        var status: Boolean = false
        if(pref.getString(con.getString(R.string.pref_key), "").equals("")){
            status = false
        }
        else{
            status = true
        }
        return status
    }

    fun clearPreference(){
        pref.edit().clear().commit()

        con.startActivity(Intent(con,TelConnexionActivity::class.java))
        (con as TelConnexionActivity).finish()
    }
}