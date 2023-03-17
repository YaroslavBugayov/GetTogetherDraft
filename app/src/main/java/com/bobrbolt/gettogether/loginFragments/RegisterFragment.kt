package com.bobrbolt.gettogether.loginFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bobrbolt.gettogether.R
import com.bobrbolt.gettogether.loginDb.Account
import com.bobrbolt.gettogether.loginDb.AccountDatabase
import com.bobrbolt.gettogether.mainFragments.FeedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class RegisterFragment(applicationContext: Context) : Fragment() {

    val url = "http://10.0.2.2:8080/register"
    private val requestQueue = Volley.newRequestQueue(applicationContext)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_register, container, false)
        inf.findViewById<Button>(R.id.buttonSignUpRegister).setOnClickListener { signUp() }
        inf.findViewById<ImageButton>(R.id.buttonBack).setOnClickListener { back() }
        return inf
    }

    private fun signUp() {
        val email = requireActivity().findViewById<EditText>(R.id.inputRegisterEmail).text.toString()
        val login = requireActivity().findViewById<EditText>(R.id.inputRegisterLogin).text.toString()
        val password = requireActivity().findViewById<EditText>(R.id.inputRegisterPassword).text.toString()

        val authJson = JSONObject("{\"login\":\"${login}\", \"password\":\"${password}\", \"email\":\"${email}\"}")
        val request = object : JsonObjectRequest(
            Method.POST,
            url,
            authJson,
            {
                requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.VISIBLE
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .remove(this)
                    .replace(R.id.placeholder, FeedFragment.newInstance())
                    .commit()
                Thread {
                    saveToken(login, it["token"].toString())
                }.start()
            },
            {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
        ) {

        }
        requestQueue.add(request)
    }

    private fun back() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainLayout, LoginFragment(requireContext().applicationContext))
            .commit()
    }

    private fun saveToken(login: String, token: String) {
        Log.d("tag", "test")
        val database = AccountDatabase.getDatabase(requireContext().applicationContext)
        if (database.accountDao().getCount() == 0) {
            database.accountDao().insert(Account(login = login, token = token))
        } else {
            database.accountDao().update(Account(login = login, token = token))
        }
    }

}