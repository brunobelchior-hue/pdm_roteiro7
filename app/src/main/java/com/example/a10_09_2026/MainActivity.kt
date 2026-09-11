package com.example.a10_09_2026

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(),
    HomeFragment.HomeListener,
    PerfilFragment.PerfilListener,
    ConfigFragment.ConfigListener,
    SegurancaFragment.SegurancaListener {

    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.txt_status)

        findViewById<Button>(R.id.btn_home).setOnClickListener { abrirHome() }
        findViewById<Button>(R.id.btn_perfil).setOnClickListener {
            abrirFragment(PerfilFragment())
        }
        findViewById<Button>(R.id.btn_config).setOnClickListener {
            abrirFragment(ConfigFragment())
        }
        findViewById<Button>(R.id.btn_seguranca).setOnClickListener {
            abrirFragment(SegurancaFragment())
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val atual = supportFragmentManager.findFragmentById(R.id.fragment_container)

                if (atual !is HomeFragment) {
                    abrirHome()
                } else {
                    finish()
                }
            }
        })

        if (savedInstanceState == null) {
            abrirHome()
        }
    }

    private fun abrirHome() {
        supportFragmentManager.popBackStack(
            null,
            androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        trocarFragment(HomeFragment(), false)
        statusText.text = "Menu principal"
    }

    private fun abrirFragment(fragment: Fragment) {
        trocarFragment(fragment, true)
    }

    private fun trocarFragment(fragment: Fragment, adicionarBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)

        if (adicionarBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commitNow()
        enviarDadosParaFragment(fragment)
    }

    // Comunicação Activity -> Fragment
    private fun enviarDadosParaFragment(fragment: Fragment) {
        when (fragment) {
            is PerfilFragment -> fragment.receberNomeDaActivity("Bruno")
            is ConfigFragment -> fragment.receberConfiguracaoDaActivity(true)
            is SegurancaFragment -> fragment.receberNivelDaActivity("Proteção padrão")
        }
    }

    // HomeFragment -> Activity
    override fun onHomeMensagem(mensagem: String) {
        statusText.text = mensagem
    }

    // PerfilFragment -> Activity
    override fun onPerfilSalvo(nome: String) {
        statusText.text = "Perfil salvo: $nome"
    }

    // ConfigFragment -> Activity
    override fun onConfiguracaoAlterada(nome: String, ativado: Boolean) {
        statusText.text = "$nome: ${if (ativado) "ATIVADO" else "DESATIVADO"}"
    }

    // SegurancaFragment -> Activity
    override fun onSegurancaAlterada(recurso: String, ativado: Boolean) {
        statusText.text = "$recurso: ${if (ativado) "ATIVADO" else "DESATIVADO"}"
    }
}
