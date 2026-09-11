package com.example.a10_09_2026

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment

class SegurancaFragment : Fragment() {

    interface SegurancaListener {
        fun onSegurancaAlterada(recurso: String, ativado: Boolean)
    }

    private var listener: SegurancaListener? = null
    private lateinit var swBiometria: Switch
    private lateinit var swBloqueio: Switch
    private lateinit var txtNivel: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SegurancaListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_seguranca, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swBiometria = view.findViewById(R.id.sw_biometria)
        swBloqueio = view.findViewById(R.id.sw_bloqueio)
        txtNivel = view.findViewById(R.id.txt_nivel)

        swBiometria.setOnCheckedChangeListener { _, checked ->
            listener?.onSegurancaAlterada("Biometria", checked)
        }

        swBloqueio.setOnCheckedChangeListener { _, checked ->
            listener?.onSegurancaAlterada("Bloqueio automático", checked)
        }
    }

    // Comunicação Activity -> Fragment
    fun receberNivelDaActivity(nivel: String) {
        if (::txtNivel.isInitialized) txtNivel.text = "Nível recebido da Activity: $nivel"
    }
}
