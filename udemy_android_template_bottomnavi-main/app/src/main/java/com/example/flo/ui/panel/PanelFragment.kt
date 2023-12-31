package com.example.flo.ui.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentBannerBinding
import com.example.flo.databinding.FragmentPanelBinding

class PanelFragment(val imgRes : Int) : Fragment() {
    lateinit var binding : FragmentPanelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPanelBinding.inflate(inflater, container, false)

        binding.frPanelImage1.setImageResource(imgRes) //인자값으로 받은 이미지로 ImageView의 src값이 변경이 됨

        return binding.root
    }
}