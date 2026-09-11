package com.example.a10_09_2026

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class PerfilFragment : Fragment() {

    interface PerfilListener {
        fun onPerfilSalvo(nome: String)
    }

    private var listener: PerfilListener? = null
    private lateinit var edtNome: EditText
    private lateinit var txtRecebido: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? PerfilListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_perfil, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edtNome = view.findViewById(R.id.edt_nome)
        txtRecebido = view.findViewById(R.id.txt_recebido_activity)

        view.findViewById<Button>(R.id.btn_salvar_perfil).setOnClickListener {
            val nome = edtNome.text.toString().trim()
            listener?.onPerfilSalvo(if (nome.isEmpty()) "Sem nome" else nome)
        }
    }

    // Comunicação Activity -> Fragment
    fun receberNomeDaActivity(nome: String) {
        if (::edtNome.isInitialized) edtNome.setText(nome)
        if (::txtRecebido.isInitialized) txtRecebido.text = "Recebido da Activity: $nome"
    }
}
