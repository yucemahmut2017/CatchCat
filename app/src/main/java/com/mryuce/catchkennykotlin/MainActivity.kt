package com.mryuce.catchkennykotlin

import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<Button>()
    var randomValues:Int?=null
    var scocreText:Int?=null

    var runnable:Runnable= Runnable {  }
    var handler:Handler= Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        list.addAll(listOf(btn1, btn2, btn3,btn4,btn5,btn6))

        for(item in list){

            item.visibility=View.INVISIBLE
        }






        object :CountDownTimer(10000,1000){
            override fun onTick(millifinished: Long) {

                timeTextView.text="Time : ${millifinished/1000}"

            }

            override fun onFinish() {

                handler.removeCallbacks(runnable)
                timeTextView.text="Time : 0"

                val alert=AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over!!")
                alert.setMessage("Score : ${scocreText}")
                alert.setCancelable(false)
                alert.setPositiveButton("Yes"){dialog,which ->

                }
                alert.setNegativeButton("No"){dialog,which->



                }
                alert.show()
                scocreText=0
            }


        }.start()

        startGame()





    }

    fun startGame(){

        scocreText=0

        runnable=object :Runnable{
            override fun run() {



                for(item in list){

                    item.visibility=View.INVISIBLE
                }

                randomValues= Random.nextInt(6)
                list[randomValues!!].visibility=View.VISIBLE
                handler.postDelayed(this,1000)

            }




        }

        handler.post(runnable)
    }
    fun scoreUp(view: View){
      scocreText= scocreText?.plus(1)
        scoreTextView.text="Score : "+scocreText.toString()

    }



}


