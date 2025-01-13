package com.example.ejemplo_fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Fragmento_A.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragmento_A : Fragment() {
    lateinit var mitextview:TextView
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ejemplo_fragmento","Fragmento_A_OnCreate")

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ejemplo_fragmento","Fragmento_A_OnCreateView")

        // Inflate the layout for this fragment
       var v= inflater.inflate(R.layout.fragment_fragmento__a, container, false )
       this.mitextview= v.findViewById<TextView>(R.id.textView)
        this.mitextview.text=param1
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragmento_A.
         */

        // TODO: Rename and change types and number of parameters
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"
       /* fun newInstance(param1: String, param2: String)=
          Fragmento_A().apply {
               if(arguments==null) {
                   arguments = Bundle().apply {
                       Log.i("ejemplo_fragmento", "Dentro de new instance")
                       putString(ARG_PARAM1, param1)
                       putString(ARG_PARAM2, param2)
                   }
               }
            }
            return fragment as Fragmento_A*/

    }


}