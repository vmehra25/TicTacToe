package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var board: Array<Array<Button>>
    var player = true
    var turnCount = 0
    var boardStatus = Array(3){ IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        initBoardStatus();

        for (arr in board){
            for(but in arr){
                but.setOnClickListener(this);
            }
        }

        reset.setOnClickListener{
            initBoardStatus()
            updateDisplay("Player X turn");
            turnCount = 0
            player = true
        }

    }

    private fun initBoardStatus() {
        for (i in 0..2){
            for (j in 0..2){
                boardStatus[i][j] = -1;
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button1 -> {
                updateVal(row = 0, col = 0, player = player)
            }
            R.id.button2 -> {
                updateVal(row = 0, col = 1, player = player)
            }
            R.id.button3 -> {
                updateVal(row = 0, col = 2, player = player)
            }
            R.id.button4 -> {
                updateVal(row = 1, col = 0, player = player)
            }
            R.id.button5 -> {
                updateVal(row = 1, col = 1, player = player)
            }
            R.id.button6 -> {
                updateVal(row = 1, col = 2, player = player)
            }
            R.id.button7 -> {
                updateVal(row = 2, col = 0, player = player)
            }
            R.id.button8 -> {
                updateVal(row = 2, col = 1, player = player)
            }
            R.id.button9 -> {
                updateVal(row = 2, col = 2, player = player)
            }
        }
        if(checkWinner()){
            return;
        }
        player = !player
        turnCount += 1;
        if(player){
            updateDisplay("Player X turn");
        }else{
            updateDisplay("Player O turn");
        }
        if(turnCount == 9){
            updateDisplay("Draw");
        }
    }

    private fun checkWinner(): Boolean {
        
        return true
    }

    private fun updateDisplay(s: String) {
        displayTv.text = s;
    }

    private fun updateVal(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "O";
        val p = if(player) 1 else 0
        board[row][col].apply {
            setText(text)
            isEnabled = false
        }
        boardStatus[row][col] = p
    }
}