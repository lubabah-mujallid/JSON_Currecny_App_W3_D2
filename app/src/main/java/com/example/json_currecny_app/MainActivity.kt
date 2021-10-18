package com.example.json_currecny_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var curencyDetails: CurrencyDetails? = null

    lateinit var tvDate: TextView
    lateinit var tvResult: TextView
    lateinit var etNumber: EditText
    lateinit var convertButton: Button

    lateinit var radioButton1: RadioButton
    lateinit var radioButton2: RadioButton
    lateinit var radioButton3: RadioButton
    lateinit var radioButton4: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        6
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Currency Converter"

        tvDate = findViewById(R.id.tvDate)
        tvResult = findViewById(R.id.tvResult)
        etNumber = findViewById(R.id.etNum)
        convertButton = findViewById(R.id.button)
        radioButton1 = findViewById(R.id.radioButton)
        radioButton2 = findViewById(R.id.radioButton2)
        radioButton3 = findViewById(R.id.radioButton3)
        radioButton4 = findViewById(R.id.radioButton4)

        convertButton.setOnClickListener {
            var stringUserInput = etNumber.text.toString()
            var userInput = stringUserInput.toDouble()
            tvDate.text = curencyDetails?.date.toString()

            if (stringUserInput.isNotEmpty()) {
                getCurrency(onResult = {
                    curencyDetails = it
                    when {
                        radioButton1.isChecked -> convert(
                            curencyDetails?.eur?.sar?.toDouble(),
                            userInput
                        );
                        radioButton2.isChecked -> convert(
                            curencyDetails?.eur?.usd?.toDouble(),
                            userInput
                        );
                        radioButton3.isChecked -> convert(
                            curencyDetails?.eur?.aud?.toDouble(),
                            userInput
                        );
                        radioButton4.isChecked -> convert(
                            curencyDetails?.eur?.jpy?.toDouble(),
                            userInput
                        );
                    }
                })
            } else {
                Toast.makeText(this, "please enter a number", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun convert(currencyToday: Double?, UserInput: Double) {
        var demo = 0.0
        if (currencyToday != null) demo = (currencyToday * UserInput)
        tvResult.text = "Results are $demo"
    }

    private fun disp(calc: Double) {
        tvResult.text = "Results are $calc"
    }

    private fun calc(i: Double?, demo: Double): Double {
        var s = 0.0
        if (i != null) s = (i * demo)
        return s
    }

    private fun getCurrency(onResult: (CurrencyDetails?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<CurrencyDetails>? = apiInterface!!.getCurrency()

        call?.enqueue(object : Callback<CurrencyDetails?> {
            override fun onResponse(
                call: Call<CurrencyDetails?>,
                response: Response<CurrencyDetails?>
            ) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<CurrencyDetails?>, t: Throwable) {
                onResult(null)
                Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show();
            }

        })
    }
}

/*
- layout xml
    - textview -- date
    - textEdit -- number
    - list to choose from : type to convert to
    - button to convert
    - textview to show final amount
    -organize and make pretty



- user enters num
- chooses type
    - add the api to choose from it
- press button
    - print date in top
    - make convertion
    - print converted amount in bottom
 */

