package dev.gamd.dimondgame.activitys

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import dev.gamd.dimondgame.R
import dev.gamd.dimondgame.databinding.ActivityGameBinding
import dev.gamd.dimondgame.models.Tile
import dev.gamd.dimondgame.ui.recyclers_view.TileRV

class GameActivity : AppCompatActivity(), TileRV.OnClickTile {

    private lateinit var binding : ActivityGameBinding
    private var countdownTimer: CountDownTimer? = null
    private lateinit var adapterTile: TileRV
    private var selectedTile = Tile(-1,-1,false,false)
    private var haveSelectedTile = false
    private var countPair = 0
    private var prize = 100
    private var listTile : MutableList<Tile> = mutableListOf(
        Tile(1,R.drawable.image1, false, false),
        Tile(2,R.drawable.image1, false, false),
        Tile(3,R.drawable.image2, false, false),
        Tile(4,R.drawable.image2, false, false),
        Tile(5,R.drawable.image3, false, false),
        Tile(6,R.drawable.image3, false, false),
        Tile(7,R.drawable.image4, false, false),
        Tile(8,R.drawable.image4, false, false),
        Tile(9,R.drawable.image5, false, false),
        Tile(10,R.drawable.image5, false, false),
        Tile(11,R.drawable.image6, false, false),
        Tile(12,R.drawable.image6, false, false),
        Tile(13,R.drawable.image7, false, false),
        Tile(14,R.drawable.image7, false, false),
        Tile(15,R.drawable.image8, false, false),
        Tile(16,R.drawable.image8, false, false),
        Tile(17,R.drawable.image9, false, false),
        Tile(18,R.drawable.image9, false, false),
        Tile(19,R.drawable.image10, false, false),
        Tile(20,R.drawable.image10, false, false)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listTile.shuffle()

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val savedCoins: Int = preferences.getInt("coins", 0)
        binding.moneyTextView.text = savedCoins.toString()

        adapterTile = TileRV(this, this)
        adapterTile.setTils(listTile)
        val layoutManager = GridLayoutManager(this, 4)
        binding.tileRv.layoutManager = layoutManager
        binding.tileRv.adapter = adapterTile

        startTimer()
    }

    private fun startTimer() {
            val millisInFuture = 2L * 60L * 1000L

            countdownTimer?.cancel()

            countdownTimer = object : CountDownTimer(millisInFuture, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val seconds = millisUntilFinished / 1000
                    val minutesRemaining = seconds / 60
                    val secondsRemaining = seconds % 60

                    val formattedTime = String.format("%02d:%02d", minutesRemaining, secondsRemaining)
                    binding.timerText.text = formattedTime

                    if(seconds == 100L){
                        prize-=20
                    }
                    else if (seconds<100L){
                        if(prize>10){
                            prize-=5
                        }
                    }
                    binding.moneyTextView.text = prize.toString()
                }

                override fun onFinish() {
                    val intent = Intent(this@GameActivity, EndGamePopupActivity::class.java)
                    intent.putExtra("keyMoney", -1)
                    startActivity(intent)
                    countdownTimer?.cancel()
                    Toast.makeText(this@GameActivity, "Timer finished!", Toast.LENGTH_SHORT).show()
                }
            }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countdownTimer?.cancel()
    }

    override fun onClickTile(t: Tile) {
        if(haveSelectedTile){
            listTile.forEach { tile: Tile ->
                if(tile.id == t.id){
                    if (t.image == selectedTile.image && t.id != selectedTile.id){
                        tile.isPairFound = true
                        countPair+=1
                        listTile.forEach{
                            if(it.id == selectedTile.id){
                                it.isPairFound = true
                            }
                        }
                    }
                    else{
                        listTile.forEach{
                            it.isSelected = false
                        }
                    }
                }
            }
            if(countPair == 10){
                val intent = Intent(this, EndGamePopupActivity::class.java)
                intent.putExtra("keyMoney", prize)
                startActivity(intent)
                countdownTimer?.cancel()
            }
            selectedTile = Tile(-1,-1,false,false)
            haveSelectedTile = false
        }
        else{
            listTile.forEach { tile: Tile ->
                if(tile.id == t.id){
                    tile.isSelected = true
                    selectedTile = tile
                    haveSelectedTile = true
                }
            }
        }
        adapterTile.setTils(listTile)
    }
}