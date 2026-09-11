package com.example.a10_09_2026

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment

class ConfigFragment : Fragment() {

    interface ConfigListener {
        fun onConfiguracaoAlterada(nome: String, ativado: Boolean)
    }

    private var listener: ConfigListener? = null
    private lateinit var swNotificacoes: Switch
    private lateinit var txtRecebido: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ConfigListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_config, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swNotificacoes = view.findViewById(R.id.sw_notificacoes)
        txtRecebido = view.findViewById(R.id.txt_recebido_activity)

        swNotificacoes.setOnCheckedChangeListener { _, checked ->
            listener?.onConfiguracaoAlterada("Notificações", checked)
        }

        view.findViewById<Button>(R.id.btn_salvar_config).setOnClickListener {
            listener?.onConfiguracaoAlterada("Configurações salvas", swNotificacoes.isChecked)
        }
    }

    // Comunicação Activity -> Fragment
    fun receberConfiguracaoDaActivity(ativada: Boolean) {
        if (::swNotificacoes.isInitialized) swNotificacoes.isChecked = ativada
        if (::txtRecebido.isInitialized) {
            txtRecebido.text = "Configuração recebida da Activity"
        }
    }
}
