package com.appdesk.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View){

        val buttonSelected = view as Button
        var cellId = 0
        when(buttonSelected.id){
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
         }
        playGame(cellId,buttonSelected)
    }

    var activePlayer = 1

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    private fun playGame(cellId:Int, buttonSelected:Button){

        if (activePlayer == 1){
            buttonSelected.text = "X"
            buttonSelected.setBackgroundResource(R.color.purple_200)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        }else{
            buttonSelected.text = "O"
            buttonSelected.setBackgroundResource(R.color.teal_200)
            player2.add(cellId)
            activePlayer = 1
        }
        buttonSelected.isEnabled = false
        checkWinner()
    }

    private fun autoPlay() {
        val emptyCells = ArrayList<Int>()

        for (cellId in 1..9){
            if (!(player1.contains(cellId) || player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }

        val r = Random()
        val randIndex = r.nextInt(emptyCells.size)
        if (emptyCells.size == 0)
        {
            restartGame()
        }
        val cellId = emptyCells[randIndex]

        val buttonSelected:Button? = when(cellId){
            1 -> button1
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9
            else -> {button1}
        }
        if (buttonSelected != null) {
            playGame(cellId,buttonSelected)
        }
    }

    private fun checkWinner() {
        var winner = -1

        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }
        //row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }
        //row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }
        //Column 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }
        //Column 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }
        //Column 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        if (winner == 1){
            player1wins+=1
            Toast.makeText(this,"Player 1 Win the Game",Toast.LENGTH_SHORT).show()
            restartGame()
        }else if (winner == 2){
            player2wins+=1
            Toast.makeText(this,"Player 2 Win the Game",Toast.LENGTH_SHORT).show()
            restartGame()
        }
    }

    var player1wins = 0
    var player2wins = 0
    fun restartGame(){
        activePlayer = 1
        player1.clear()
        player2.clear()
        for (cellId in 1..9){
            val buttonSelected:Button? = when(cellId){
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {button1}
            }
            buttonSelected!!.text = ""
            buttonSelected.setBackgroundResource(R.color.white)
            buttonSelected.isEnabled = true

        }
        Toast.makeText(this,"Player 1: $player1wins, Player 2: $player2wins",Toast.LENGTH_SHORT).show()
    }
}