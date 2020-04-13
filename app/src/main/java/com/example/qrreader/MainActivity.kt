package com.example.qrreader

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //Clipboard variable
    private var clipboard: ClipboardManager? = null
    private val scanRequestCode = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        //OnClickListeners
        textView_qr_content.setOnClickListener { copyToClipboard() }
        button_scan.setOnClickListener { scanCode() }

    }

    private fun copyToClipboard() {
        val qrCode = textView_qr_content.text
        val clip = ClipData.newPlainText("QR-Code", qrCode)

        clipboard?.setPrimaryClip(clip).let {
            Toast.makeText(this@MainActivity, "QR-Code copied", Toast.LENGTH_SHORT).show();
        }
    }

    private fun scanCode() {
        val barcodeReader = Intent(this@MainActivity, BarcodeReaderActivity::class.java)
        startActivityForResult(barcodeReader, scanRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == scanRequestCode) {
            if (resultCode == Activity.RESULT_OK) {
                //Set QR-Code to TextView
                val codeData = data!!.getStringExtra("data")
                textView_qr_content.text = codeData

                val codeDate = data!!.getStringExtra("date")
                val codeTime = data!!.getStringExtra("time")

                //POST the QR-Code to the backend
                val url: String  = "http://10.0.2.2:5000/qr_data"
                val urlVariableData: String = "data"
                val urlVariableDate: String = "date"
                val urlVariableTime: String = "time"

                val requestHTTP: RequestHTTP = RequestHTTP(this@MainActivity, url, urlVariableData, codeData, urlVariableDate, codeDate, urlVariableTime, codeTime)
                requestHTTP.sendPostRequest()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this@MainActivity, "The scan was cancelled!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
