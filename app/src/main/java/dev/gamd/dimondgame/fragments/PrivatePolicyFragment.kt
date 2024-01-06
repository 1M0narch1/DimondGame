package dev.gamd.dimondgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.gamd.dimondgame.R
import dev.gamd.dimondgame.databinding.FragmentPrivatePolicyBinding


class PrivatePolicyFragment : Fragment() {

    private lateinit var binding : FragmentPrivatePolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPrivatePolicyBinding.inflate(layoutInflater)
        binding.back.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    MenuFragment()
                ).commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}