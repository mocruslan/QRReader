package com.example.qrreader

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_barcode_reader.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class BarcodeReaderActivity : AppCompatActivity(), ZXingScannerView.ResultHandler{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_reader)

        scanCode()
    }

    private fun scanCode() {
        Dexter.withActivity(this@BarcodeReaderActivity).withPermission(Manifest.permission.CAMERA).withListener( object: PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                scannerView.setResultHandler(this@BarcodeReaderActivity)
                scannerView.startCamera()
            }

            override fun onPermissionRationaleShouldBeShown(
                permission: PermissionRequest?,
                token: PermissionToken?
            ) {
                TODO("Not yet implemented")
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                Toast.makeText(this@BarcodeReaderActivity, "You must first grand the permission!", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_CANCELED)
                finish()
            }

        }).check()
    }

    override fun handleResult(rawResult: Result?) {
        println("-------------------The Result was successful with ${rawResult!!.text}")
        val returnIntent = Intent()
        returnIntent.putExtra("content", rawResult!!.text)
        //returnIntent.putExtra("time", date.getTime())
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera()
    }
}
