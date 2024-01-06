package dev.gamd.dimondgame.activitys

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dev.gamd.dimondgame.R
import dev.gamd.dimondgame.databinding.ActivityEndGamePopupBinding


class EndGamePopupActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEndGamePopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndGamePopupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var prize = intent.getIntExtra("keyMoney", 0)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val bank=preferences.getInt("coins", 0)+prize
        val editor = preferences.edit()
        editor.putInt("coins", bank)
        editor.apply()

        binding.moneyTextView.text = prize.toString()

        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.setDimAmount(0.5f)

        if(prize<0){
            binding.resultImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_change_circle_24))
            binding.resultText.text = "LOSS"
        }else{
            binding.resultText.text = "WIN"
        }

        binding.cardView5.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        binding.homeCard.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}