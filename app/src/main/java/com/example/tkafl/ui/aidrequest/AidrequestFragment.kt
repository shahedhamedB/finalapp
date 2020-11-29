package com.example.tkafl.ui.aidrequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tkafl.R
import com.example.tkafl.ui.my_alms.My_almsViewModel


class AidrequestFragment : Fragment() {
    private lateinit var AidrequestViewModel: AidrequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AidrequestViewModel =
            ViewModelProvider(this).get(AidrequestViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_aidrequest, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        AidrequestViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}