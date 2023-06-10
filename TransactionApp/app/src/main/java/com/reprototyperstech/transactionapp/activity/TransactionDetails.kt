package com.reprototyperstech.transactionapp.activity

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.reprototyperstech.transactionapp.R
import java.io.File
import java.io.FileOutputStream


class TransactionDetails : AppCompatActivity() {
    lateinit var status: String
    lateinit var amount: String
    lateinit var datetime: String
    lateinit var narration: String
    var imageUri: Uri? = null


    var pageHeight = 1120
    var pageWidth = 792
    private val filepath = "Docs"
    var PERMISSION_CODE = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transaction_details_activity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Transaction Details"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val txt_transaction_status = findViewById<TextView>(R.id.txt_transaction_status)
        val txt_transaction_amount = findViewById<TextView>(R.id.txt_transaction_amount)
        val txt_transaction_datetime = findViewById<TextView>(R.id.txt_transaction_datetime)
        val txt_narration = findViewById<TextView>(R.id.txt_narration)
        val btn_download = findViewById<Button>(R.id.btn_download)

        if (intent != null || intent.extras!!.containsKey("status")) {
            status = intent.extras!!.getString("status").toString()
        }

        if (intent != null || intent.extras!!.containsKey("amount")) {
            amount = intent.extras!!.getString("amount").toString()
        }

        if (intent != null || intent.extras!!.containsKey("datetime")) {
            datetime = intent.extras!!.getString("datetime").toString()
        }

        if (intent != null || intent.extras!!.containsKey("narration")) {
            narration = intent.extras!!.getString("narration").toString()
        }
        txt_transaction_status.text = status
        txt_transaction_amount.text = amount
        txt_transaction_datetime.text = datetime
        txt_narration.text = narration


        btn_download.setOnClickListener {
            if (checkPermissions()) {
                generatePDF()
            } else {
                requestPermission()
            }
        }
    }


    fun generatePDF() {

        val pdfDocument: PdfDocument = PdfDocument()


        val title: Paint = Paint()


        var myPageInfo: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

        var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

        var canvas: Canvas = myPage.canvas

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))

        title.textSize = 15F

        title.setColor(ContextCompat.getColor(this, R.color.black))

        canvas.drawText("Transaction Status : " + status, 100F, 100F, title)
        canvas.drawText("Transaction Amount : " + amount, 100F, 80F, title)
        canvas.drawText("Transaction DateTime : " + datetime, 100F, 60F, title)
        canvas.drawText("Narration : " + narration, 100F, 40F, title)
        title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
        title.setColor(ContextCompat.getColor(this, R.color.black))
        title.textSize = 15F


        pdfDocument.finishPage(myPage)


        val file: File = File(getExternalFilesDir(filepath), "transaction.pdf")

        /* imageUri = FileProvider.getUriForFile(
             this@TransactionDetails, BuildConfig.APPLICATION_ID + ".provider", file)*/

        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(applicationContext, "PDF file generated..", Toast.LENGTH_SHORT).show()
            openPDF()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
                .show()
        }

        pdfDocument.close()
    }

    fun checkPermissions(): Boolean {

        var writeStoragePermission = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )


        var readStoragePermission = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )


        return writeStoragePermission == PackageManager.PERMISSION_GRANTED
                && readStoragePermission == PackageManager.PERMISSION_GRANTED
    }


    fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.size > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun openPDF() {
        // Get the File location and file name.
        val file = File(getExternalFilesDir(filepath), "transaction.pdf")
        Log.d("pdfFIle", "" + file)

        // Get the URI Path of file.
        val uriPdfPath = FileProvider.getUriForFile(this, applicationContext.packageName + ".provider", file)
        Log.d("pdfPath", "" + uriPdfPath)

        // Start Intent to View PDF from the Installed Applications.
        val pdfOpenIntent = Intent(Intent.ACTION_VIEW)
        pdfOpenIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        pdfOpenIntent.clipData = ClipData.newRawUri("", uriPdfPath)
        pdfOpenIntent.setDataAndType(uriPdfPath, "application/pdf")
        pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        try {
            startActivity(pdfOpenIntent)
        } catch (activityNotFoundException: ActivityNotFoundException) {
            Toast.makeText(this, "There is no app to load corresponding PDF", Toast.LENGTH_LONG)
                .show()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true;
    }
}