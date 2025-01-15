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
import com.example.ejemplo_fragmentos.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking



class MainActivity : AppCompatActivity() {
    var fragmento_cargado_A=true
lateinit var mibinding:ActivityMainBinding
var fragment_home:Fragmento_A?=null
 var fragment_book: Fragmento_B? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mibinding=ActivityMainBinding.inflate(layoutInflater)
        Log.i("estadoActivity_ejemplo_fragmento","La actividad esta en OnCreate $savedInstanceState")
        enableEdgeToEdge()
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializar_Componentes()
   /*   if (savedInstanceState == null){
            //Solamente añado el fragmento si es la primera vez que se crea la actividad

            Log.i("actividad_ejemplo_fragmento", "Se crea la actividad")
            var bundle= bundleOf(Fragmento_A.ARG_PARAM1 to "dato1",Fragmento_A.ARG_PARAM2 to "dato2")
        supportFragmentManager.commit {
            setReorderingAllowed(true)

            //Añade un fragmento al contenedor, instancia un objeto cada vez

            add<Fragmento_A>(R.id.fragmentContainerView,"",bundle)


        }


    }*/
        if(savedInstanceState==null) {
            //Con esto consigo que si la actividad se destruya por una rotación
            //la actividad automaticamente restaure todos los fragmentos añadidos
            //a fragmentManager
            //Cargo el fragmento A, pero antes instanciare el objeto
            if (this.fragment_home == null) {
                this.fragment_home = Fragmento_A()
                Log.i("ejemplo_fragmento", "Instancio el fragmentoA")
            }
            Log.i(
                "estado_ejemplo_fragmento",
                "El estado es ${this.fragment_home!!.lifecycle.currentState}"
            )
            supportFragmentManager.commit {
                add(R.id.fragmentContainerView, fragment_home!!)
            }
        }
        else
        {
            //Si entramos por aqui significa que la actividad ya se habría creado anteriormente
            //y por lo tanto se viene de una destrucción de la actividad, por ejemplo, por rotar la pantalla
            //Dado que la actividad mantiene los fragmentos que tuviera añadidos (add o replace) en
            //supporFragmentManager.fragments, podríamos restaurar la instancia de los fragments que ahora es
            //null, pero en esta aplicación solamente podremos restaurar el último que hubieramos añadido
            // o remplazado, podemos comprobar el tipo de clase referenciado con
            //supportFragmentManager.fragments[posicion]::clas.simpleName
            //Ahora bien, lo ideal sería que las instancias de los Fragments estuvieran en un MutableLiveData
            // de esta forma daria igual que el ciclo de vida de la actividad se destruyera.
            Log.i("ejemplo_fragmento_actividad","La actividad regresa de una recuperacion")

        }
        //Dado
            // que lo anterior no es inmediato voy a definir en un hilo nuevo
        //la espera para comprobar que ya se ha cambiado de estado
     /*   GlobalScope.launch {


        //Lo meto en un bucle para que imprima los diferentes estados
           for( i in 1 .. 10)
            { delay(8)
                Log.i(
                    "estado_ejemplo_fragmento",
                    "El estado es ${fragment_home!!.lifecycle.currentState}"
                )
            }

        }*/

        findViewById<FragmentContainerView>(R.id.fragmentContainerView).setOnClickListener {
          /*  if(fragmento_cargado_A)
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
*/


        }
    }

    override fun onStop() {
        super.onStop()
        Log.i("estadoActivity_ejemplo_fragmento","La actividad esta en OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("estadoActivity_ejemplo_fragmento","La actividad esta en OnDestroy")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("estadoActivity_ejemplo_fragmento","La actividad se guarda su estado")
    }

    private fun inicializar_Componentes() {
        mibinding.casa.setOnClickListener {
            //Todo esto se podría cambiar por un patron Singleton
            //Compruebo si el fragmento esta definido y sino lo instacio,
            //esto se podría cambiar por un patron Singleton
            fragment_home=fragment_home?:Fragmento_A()
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                /* Opcion1.- En vez de remplazar voy a borrar el que tenga y añadir este
                 fragment_book?.let { remove(it) }
                 add(R.id.fragmentContainerView,fragment_home!!) */

                /*Opcion2.- Remplazo en vez de borrar y añadir, al final es lo mismo */
                replace(R.id.fragmentContainerView,fragment_home!!)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_CLOSE)
                }



        }
        mibinding.libro.setOnClickListener {
            //Compruebo si el fragmento tiene instancia creada, sino instancio
           fragment_book=fragment_book?: Fragmento_B()
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView,fragment_book!!)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }
        }
    }


}