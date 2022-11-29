package com.example.kotlincalcrudoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    var enterNumView: TextView? = null
    var firstNumView: TextView? = null
    var secondNumView: TextView? = null
    var operationView: TextView? = null
    var answerView: TextView? = null
    var editChord: EditText? = null
    var editAngle: EditText? = null
    var editArcLength: EditText? = null
    var squareAnswer: TextView? = null
    var editR: EditText? = null

    var dot: Boolean = false
    var firstNumber: Double = 0.0
    var secondNumber: Double = 0.0

    private var str: String = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        enterNumView = findViewById(R.id.enterNumView)
        firstNumView = findViewById(R.id.firstNumView)
        secondNumView = findViewById(R.id.secondNumView)
        operationView = findViewById(R.id.operationView)

        squareAnswer = findViewById(R.id.squareAnswer)
        editArcLength = findViewById(R.id.editArcLength)
        editAngle = findViewById(R.id.editAngle)
        editChord = findViewById(R.id.editChord)
        editR = findViewById(R.id.editR)

        answerView = findViewById(R.id.answerView)
    }

    private fun chooseOperation(op: String) {
        operationView?.text = (op)
    }

    private fun addTheNumber(num: Int) {
        str += num
        enterNumView?.text = str
    }

    fun numFirstAction(v: View) {
        addTheNumber(1)
    }

    fun numSecondAction(v: View) {
        addTheNumber(2)
    }

    fun numThirdAction(v: View) {
        addTheNumber(3)
    }

    fun numFourAction(v: View) {
        addTheNumber(4)
    }

    fun numFiveAction(v: View) {
        addTheNumber(5)
    }

    fun numSixAction(v: View) {
        addTheNumber(6)
    }

    fun numSevenAction(v: View) {
        addTheNumber(7)
    }

    fun numEightAction(v: View) {
        addTheNumber(8)
    }

    fun numNineAction(v: View) {
        addTheNumber(9)
    }

    fun numZeroAction(v: View) {
        addTheNumber(0)
    }

    fun addDeleteAction(v: View) {
        chooseOperation("/")
    }

    fun addPlusAction(v: View) {
        chooseOperation("+")
    }

    fun addMinusActionOperation(v: View) {
        chooseOperation("-")
    }

    fun addMultiplyAction(v: View) {
        chooseOperation("*")
    }

    fun addSignOrAction(v: View) {
        chooseOperation("|")
    }

    fun str(v: View) {
        chooseOperation("&")
    }

    fun addSignTiledAction(v: View) {
        // val duration = Toast.LENGTH_LONG
        //val toast = Toast.makeText(applicationContext, R.string.forTilda, duration)
        //toast.show()
        chooseOperation("~")
    }

    fun addSignToLeftAction(v: View) {
        //val duration = Toast.LENGTH_LONG
        //val toast = Toast.makeText(applicationContext, R.string.forShl, duration)
        //toast.show()
        chooseOperation("<<")
    }

    fun addMinusAction(v: View) {
        if (str.isEmpty()) str += "-"
        enterNumView?.text = str
    }

    fun addDotAction(v: View) {
        if (!dot && str.isNotEmpty() && str.substring(str.length - 1, str.length) != "-") {
            str += "."
            dot = true
        }
        enterNumView?.text = str
    }

    fun delNumAction(v: View) {
        dotDelete()
        if (str.isNotEmpty()) {
            str = str.substring(0, str.length - 1)
            enterNumView?.text = str
        }
    }

    private fun dotDelete() {
        if (str.substring(str.length - 1, str.length) == ".")
            dot = false
    }

    fun deleteAllAction(v: View) {
        if (dot)
            dot = false
        str = ""
        enterNumView?.text = str
    }

    fun addFirstAction(v: View) {
        if (str == "") firstNumView?.text = "0"
        else if (str == "-") firstNumView?.text = "-1"
        else if (str.substring(str.length - 1, str.length) == ".") firstNumView?.text = str + 0
        else {
            firstNumView?.text = str
        }
    }

    @SuppressLint("SetTextI18n")
    fun addSecondAction(v: View) {
        if (str == "") secondNumView?.text = "0"
        else if (str == "-") secondNumView?.text = "-1"
        else if (str.substring(str.length - 1, str.length) == ".") secondNumView?.text = str + 0
        else {
            secondNumView?.text = str
        }
    }
    fun countSquareArcLength(v:View){
        val arcLength: String = checkEditText(editArcLength)
        val rad:String = checkEditText(editR)
        var square:Double = (arcLength.toDouble() * rad.toDouble())/2
        squareAnswer?.text = square.toString()
    }
    fun countSquareAngle(v:View){
        val angle: String = checkEditText(editAngle)
        val rad:String = checkEditText(editR)
        var square:Double = (angle.toDouble() * (rad.toDouble()).pow(2) * PI)/360
        squareAnswer?.text = square.toString()
    }
    fun countSquareChord(v:View){
        val chord: String = checkEditText(editChord)
        val rad:String = checkEditText(editR)
        var square:Double = (( (rad.toDouble()).pow(2) * PI)/360)* acos(1 - (chord.toDouble().pow(2)/(2*(rad.toDouble()).pow(2))))
        squareAnswer?.text = square.toString()
    }

    private fun checkEditText(editArcLength:EditText?): String {
        return if (editArcLength?.text.toString().isNotEmpty())
            editArcLength?.text.toString()
        else 1.toString()
    }

    @SuppressLint("SuspiciousIndentation")
    fun giveAnswerAction(v: View) {
        if (firstNumView?.text.toString() == "" || secondNumView?.text.toString() == "") {
            answerView?.text = getString(R.string.invalidData)
        }
        val operation: String = operationView?.text.toString()
        if (operation == "+" || operation == "-" || operation == "*" || operation == "/") {
            firstNumView?.let { adaDotAndZero(it) }
            secondNumView?.let { adaDotAndZero(it) }
            firstNumber = firstNumView?.text.toString().toDouble()
            secondNumber = secondNumView?.text.toString().toDouble()

            val answerString: String = (when (operation) {
                "+" -> (firstNumber + secondNumber).toString()
                "-" -> (firstNumber - secondNumber).toString()
                "*" -> (firstNumber * secondNumber).toString()
                "/" -> (if (secondNumber == 0.0) {
                    "Error"
                } else {
                    (firstNumber / secondNumber).toString()
                })
                else -> "Error"
            })
            answerView?.text = answerString
        } else {
            var stringToDel: String = ""
            stringToDel = firstNumView?.let { returnWithoutDot(it) }.toString()
            val firstNumberInt = stringToDel.toInt()
            stringToDel = secondNumView?.let { returnWithoutDot(it) }.toString()
            val secondNumberInt = stringToDel.toInt()

            val answerString: String = (when (operation) {
                "|" -> (firstNumberInt or secondNumberInt).toString()
                "&" -> (firstNumberInt and secondNumberInt).toString()
                "~" -> (firstNumberInt.inv()).toString()
                "<<" -> (firstNumberInt shl secondNumberInt).toString()
                else -> {
                    "Error"
                }
            })
            answerView?.text = answerString
        }
    }

    private fun returnWithoutDot(firstNumView: TextView): String {
        if (firstNumView.text.toString().contains('.')) {
            val indexDot = firstNumView.text.toString().indexOf('.')
            return firstNumView.text.toString().substring(0, indexDot)
        } else {
            return firstNumView.text.toString()
        }
    }

    private fun adaDotAndZero(secondNumView: TextView) {
        if (!secondNumView.text.toString().contains(".", ignoreCase = true)) {
            secondNumView.text = secondNumView.text.toString() + ".0"
        }
    }
}