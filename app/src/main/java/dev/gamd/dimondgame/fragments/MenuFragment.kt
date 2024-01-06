package dev.gamd.dimondgame.fragments

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.gamd.dimondgame.activitys.GameActivity
import dev.gamd.dimondgame.R
import dev.gamd.dimondgame.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var binding : FragmentMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMenuBinding.inflate(layoutInflater)

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val savedCoins: Int = preferences.getInt("coins", 0)
        binding.moneyTextView.text = savedCoins.toString();

        binding.settingCard.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    SettingFragment()
            ).commit()
        }

        binding.privateCard.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    PrivatePolicyFragment()
                ).commit()
        }

        binding.playButton.setOnClickListener {
            val intent = Intent(requireActivity(), GameActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

}