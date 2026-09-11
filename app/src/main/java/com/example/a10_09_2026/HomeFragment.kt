package com.example.a10_09_2026

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    interface HomeListener {
        fun onHomeMensagem(mensagem: String)
    }

    private var listener: HomeListener? = null

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        listener = context as? HomeListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btn_home_mensagem).setOnClickListener {
            listener?.onHomeMensagem("Você está no Menu Principal")
        }
    }
}
