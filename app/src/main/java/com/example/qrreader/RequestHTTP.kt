package com.example.qrreader

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RequestHTTP (val context: Context, val url: String, val urlVariable: String, val data: String) {

    private val requestQueue = Volley.newRequestQueue(context);

    fun sendPostRequest() {
        val postUrl = buildUrl()

        //Build String for the request (also returns a value)
        val request = StringRequest(Request.Method.POST, postUrl,
            Response.Listener<String> { response ->
                println("HTTP-Post response: $response");
                Toast.makeText(context, "Data uploaded: $response", Toast.LENGTH_SHORT).show();
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, "Error during upload:  + $error.message", Toast.LENGTH_SHORT).show()
        })
        requestQueue.add(request)
    }

    private fun buildUrl(): String {
        return "$url?$urlVariable=$data"
    }
}