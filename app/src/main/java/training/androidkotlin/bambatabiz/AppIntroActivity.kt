package training.androidkotlin.bambatabiz

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ViewSwitcher
import java.text.FieldPosition

class AppIntroActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mPager: ViewPager

    var layouts: IntArray = intArrayOf(R.layout.first_slide, R.layout.second_slide, R.layout.third_slide)

    lateinit var dotsLayout: LinearLayout

    lateinit var dots: Array<ImageView>

    lateinit var mAdapter: PageAdapter

    lateinit var btnRetour : Button

    lateinit var btnNext : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PrefManager(this).checkPreference()){
            loadHome()
        }
        if (Build.VERSION.SDK_INT >= 19){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        setContentView(R.layout.activity_app_intro)
        mPager = findViewById<ViewPager>(R.id.pager) as ViewPager
        mAdapter = PageAdapter(layouts, this)
        mPager.adapter = mAdapter
        dotsLayout = findViewById<LinearLayout>(R.id.dots) as LinearLayout
        btnRetour = findViewById<Button>(R.id.btn_retour) as Button
        btnNext = findViewById<Button>(R.id.btn_next) as Button
        btnRetour.setOnClickListener(this)
        btnNext.setOnClickListener(this)
       // createdots(0)
        mPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

              //  createdots(position)

                if (position == layouts.size -3){
                    btnRetour.visibility = View.INVISIBLE
                }
                else if (position == layouts.size - 1){
                    btnNext.setText("COMMENCER")
                }
                else{
                    btnNext.setText("SUIVANT")
                    btnRetour.visibility = View.VISIBLE
                }

            }
        })
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.btn_retour ->
            {
                loadPrevSlide()
            }

            R.id.btn_next ->
            {
                loadNextSlide()
            }
        }

    }

    private fun loadPrevSlide() {
        var prevSlider: Int = mPager.currentItem - 1

        if (prevSlider < layouts.size){
            mPager.setCurrentItem(prevSlider)
        }
        else{
            loadMainActivity()
        }
    }

    private fun loadNextSlide() {
        var nextSlider: Int = mPager.currentItem + 1

        if (nextSlider < layouts.size){
            mPager.setCurrentItem(nextSlider)
        }
        else{
            loadHome()
            PrefManager(this).writeSP()
        }
    }

    private fun loadHome() {
        startActivity(Intent(this, TelConnexionActivity::class.java))
        finish()
    }

    private fun loadMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

/*
    fun createdots(position: Int){
        if (dotsLayout !== null){
            dotsLayout.removeAllViews()
        }
        dots = Array(layouts.size, {i -> ImageView(this)})

        for (i in 0..layouts.size){
            dots[i] = ImageView(this)
            if (i == position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots))
            }
            else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots))
            }
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)

            params.setMargins(0, 4, 0, 4)
            dotsLayout.addView(dots[i], params)
        }
    }
*/
}
