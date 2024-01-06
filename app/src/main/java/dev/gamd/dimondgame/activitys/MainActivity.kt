package dev.gamd.dimondgame.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.gamd.dimondgame.databinding.ActivityMainBinding
import dev.gamd.dimondgame.fragments.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(
            binding.container.id,
            MenuFragment()
        ).commit()
    }
}