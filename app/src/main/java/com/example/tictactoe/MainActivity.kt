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
        // rows
        var ans:Boolean = false;
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    updateDisplay("Player X wins");
                }else if(boardStatus[i][0] == 0){
                    updateDisplay("Player O wins");
                }else{
                    continue;
                }
                ans = true;
                break;
            }
        }

        // col
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X wins");
                }else if(boardStatus[0][i] == 0){
                    updateDisplay("Player O wins");
                }else{
                    continue;
                }
                ans = true;
                break;
            }
        }

        // diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2] && boardStatus[0][0] != -1){
            if(boardStatus[0][0] == 1){
                updateDisplay("Player X wins");
            }else{
                updateDisplay("Player O wins");
            }
            ans = true;
        }
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0] && boardStatus[0][2] != -1){
            if(boardStatus[0][2] == 1){
                updateDisplay("Player X wins");
            }else{
                updateDisplay("Player O wins");
            }
            ans = true;
        }


        return ans;
    }

    private fun updateDisplay(s: String) {
        displayTv.text = s;
        if(s.contains("wins")){
            disableButtons();
        }
    }

    private fun disableButtons() {
        for (i in 0..2){
            for (j in 0..2){
                board[i][j].isEnabled = false;
            }
        }
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