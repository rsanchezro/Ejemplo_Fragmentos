package com.example.ejemplo_fragmentos

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    var fragmento_cargado_A=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null){
            //Solamente añado el fragmento si es la primera vez que se crea la actividad

            Log.i("actividad_ejemplo_fragmento", "Se crea la actividad")
            var bundle= bundleOf(Fragmento_A.ARG_PARAM1 to "dato1",Fragmento_A.ARG_PARAM2 to "dato2")
        supportFragmentManager.commit {
            setReorderingAllowed(true)

            //Añade un fragmento al contenedor, instancia un objeto cada vez

            add<Fragmento_A>(R.id.fragmentContainerView,"",bundle)


        }
    }
        findViewById<FragmentContainerView>(R.id.fragmentContainerView).setOnClickListener {
            if(fragmento_cargado_A)
            {
                //Cargo el fragmento B
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainerView,Fragmento_B.newInstance("dato1B","dato2B"))
                    addToBackStack("fragmentoB")
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                }

            }
            else
            {//Cargo el fragmento A
                supportFragmentManager.commit {
                    //Remplazo el fragmento
                    replace<Fragmento_A>(R.id.fragmentContainerView)
                    addToBackStack("fragmentoA") //Que sucede si elimino esta invocacion
                    //DEfino transacción entre un fragmento y otro
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_CLOSE)
                }

            }

            fragmento_cargado_A=!fragmento_cargado_A



        }
    }


}