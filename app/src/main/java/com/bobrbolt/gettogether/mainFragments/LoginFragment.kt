package com.bobrbolt.gettogether.mainFragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bobrbolt.gettogether.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class LoginFragment(
    applicationContext: Context,
    private val bottomNav: BottomNavigationView
    ) : Fragment() {

    val url = "http://10.0.2.2:8080/login"
    private val requestQueue = Volley.newRequestQueue(applicationContext)
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var inputLogin: EditText
    private lateinit var inputPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_login, container, false)
        loginButton = inf.findViewById(R.id.buttonSignIn)
        registerButton = inf.findViewById(R.id.buttonSignUp)
        inputLogin = inf.findViewById(R.id.inputLogin)
        inputPassword = inf.findViewById(R.id.inputPassword)
        return inf
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener { login() }
    }

    private fun login() {
        val login = inputLogin.text.toString()
        val password = inputPassword.text.toString()

        val authJson = JSONObject("{\"login\":\"${login}\", \"password\":\"${password}\"}")
        val request = object : JsonObjectRequest(
            Method.POST,
            url,
            authJson,
            {
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            },
            {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
        ) {

        }
        requestQueue.add(request)
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNav.visibility = View.VISIBLE
    }
}