package csc.peticiones.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.StringRequest
import csc.peticiones.api.Util.Companion.consolaDebug
import csc.peticiones.api.Util.Companion.consolaDebugError
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var resultadoUI : TextView
    private lateinit var rGeneros : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultadoUI = findViewById(R.id.json_resultado)

        rGeneros = findViewById(R.id.contenedorLista)

        rGeneros.layoutManager = LinearLayoutManager(this )
        rGeneros.setHasFixedSize(true)

        /*
            COMENTAR Y DESCOMENTAR PARA VER LOS DOS TIPOS DE PETICION
            SOLO DEJAR UNA ACTIVA A LA VEZ
         */
        peticionGetSinParametros()
        //peticionGetConParametros("2")
    }

    private fun peticionGetSinParametros()
    {
        val strReq = object : StringRequest(
                /*
                    TIPO DE PETICION:

                    - GET
                    - POST
                    - DELETE
                 */
                GET,
                getString(R.string.url_actividades),
                { response ->
                    //consolaDebug("MainActivity", "peticionGetSinParametros", response)
                    try {
                        // CONTENEDOR DEL JSONARRAY OBJECT, MEDIANTE EL CUAL PODRAS JUGAR CON SUS DATOS

                        val json = JSONArray(response)

                        val lista : ArrayList<Actividad> = ArrayList()

                        for ( i in 0 until json.length() )
                        {
                            val obj = json.getJSONObject( i )
                            val direccion = obj.getString("direccion_actividad")
                            val cupo = obj.getString("cupo_actividad")

                            lista.add(
                                Actividad(
                                    direccion,
                                    cupo
                                )
                            )

                            consolaDebug( "MainActivity", "peticionGetSinParametros", "Direccion: $direccion")
                        }
                        rvGeneros(lista)
                        resultadoUI.text = response
                    } catch (e: JSONException)
                    {
                        e.printStackTrace()
                        consolaDebugError( "MainActivity", "peticionGetSinParametros", e.message)
                    }
                }, { error ->
            error.printStackTrace()
            consolaDebugError( "MainActivity", "peticionGetSinParametros", error.message)
        })
        {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                /*
                    CUANDO REQUIERAS ENVIAR PARAMETROS AQUI SE DEFINEN
                    EJEMPLO:
                    params["nombre_parametros"] = "VALOR DEL PARAMETRO A ENVIAR"
                 */
                //params["usuario"] = "adadad"
                return params
            }
        }
        // NUNCA GUARDA CACHE DE LA PETICION, PARA DATOS EN TIEMPO REAL SIEMPRE
        strReq.setShouldCache(false)
        VolleySingleton.getInstance(this).addToRequestQueue(strReq)
    }

    private fun peticionGetConParametros(idActividad : String )
    {
        val strReq = object : StringRequest(
                /*
                    TIPO DE PETICION:

                    - GET
                    - POST
                    - DELETE
                 */
                GET,
                getString(R.string.url_actividad) + idActividad,
                { response ->
                    consolaDebug("MainActivity", "peticionGetConParametros", response)
                    try {
                        // CONTENEDOR DEL JSONARRAY OBJECT, MEDIANTE EL CUAL PODRAS JUGAR CON SUS DATOS
                        val json = JSONArray(response)
                        resultadoUI.text = response
                    } catch (e: JSONException)
                    {
                        e.printStackTrace()
                        consolaDebugError( "MainActivity", "peticionGetConParametros", e.message)
                    }
                }, { error ->
            error.printStackTrace()
            consolaDebugError( "MainActivity", "peticionGetConParametros", error.message)
        })
        {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                /*
                    CUANDO REQUIERAS ENVIAR PARAMETROS AQUI SE DEFINEN
                    EJEMPLO:
                    params["nombre_parametros"] = "VALOR DEL PARAMETRO A ENVIAR"
                 */
                return params
            }
        }
        // NUNCA GUARDA CACHE DE LA PETICION, PARA DATOS EN TIEMPO REAL SIEMPRE
        strReq.setShouldCache(false)
        VolleySingleton.getInstance(this).addToRequestQueue(strReq)
    }

    private fun rvGeneros(lista: List<Actividad>) {
        rGeneros.adapter = RvTextos(
            lista,
            INTERFACE_click{ v : View, position : Int ->
                Toast.makeText(
                    this,
                    lista[ position ].direccion + " -> " + lista[ position ].cupo,
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

}
