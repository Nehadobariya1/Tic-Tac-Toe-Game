//
//
//
//package com.example.tiktactoe
//
//import android.app.AlertDialog
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.GridLayout
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var buttons: Array<Array<Button?>>
//    private var currentPlayer = 0
//    private var gameActive = true
//    private lateinit var playerNames: Array<String?>
//    private lateinit var currentPlayerTextView: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        buttons = Array(3) { arrayOfNulls<Button>(3) }
//        playerNames = arrayOf(null, null)
//        currentPlayerTextView = findViewById(R.id.currentPlayerTextView)
//
//        // Get player names
//        getPlayerNames()
//
//        // Initialize buttons
//        for (i in 0..2) {
//            for (j in 0..2) {
//                val buttonId = "button$i$j"
//                val resId = resources.getIdentifier(buttonId, "id", packageName)
//                buttons[i][j] = findViewById(resId)
//                buttons[i][j]?.setOnClickListener { onCellClicked(i, j) }
//            }
//        }
//
//        findViewById<Button>(R.id.resetButton).setOnClickListener { resetGame() }
//
//        // Initialize display of current player's turn
//        updateCurrentPlayerDisplay()
//    }
//
//    private fun getPlayerNames() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Enter Player Names")
//
//        val inputLayout = layoutInflater.inflate(R.layout.dialog_input, null)
//        val player1Input = inputLayout.findViewById<EditText>(R.id.player1Name)
//        val player2Input = inputLayout.findViewById<EditText>(R.id.player2Name)
//
//        builder.setView(inputLayout)
//            .setPositiveButton("OK") { _, _ ->
//                playerNames[0] = player1Input.text.toString().trim()
//                playerNames[1] = player2Input.text.toString().trim()
//                updateCurrentPlayerDisplay() // Update display with the first player's name
//            }
//            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
//
//        builder.show()
//    }
//
//    private fun updateCurrentPlayerDisplay() {
//        currentPlayerTextView.text = "Turn of: ${playerNames[currentPlayer]}"
//    }
//
//    private fun onCellClicked(row: Int, col: Int) {
//        if (!gameActive || buttons[row][col]?.text != "") return
//
//        buttons[row][col]?.text = if (currentPlayer == 0) "X" else "O"
//        if (checkWinner()) {
//            showWinnerDialog(playerNames[currentPlayer]!!)
//            gameActive = false
//        } else if (isBoardFull()) {
//            showDrawDialog()
//            gameActive = false
//        } else {
//            currentPlayer = if (currentPlayer == 0) 1 else 0
//            updateCurrentPlayerDisplay() // Update display for the next player's turn
//        }
//    }
//
//    private fun checkWinner(): Boolean {
//        for (i in 0..2) {
//            if (buttons[i][0]?.text == buttons[i][1]?.text && buttons[i][1]?.text == buttons[i][2]?.text && buttons[i][0]?.text != "") return true
//            if (buttons[0][i]?.text == buttons[1][i]?.text && buttons[1][i]?.text == buttons[2][i]?.text && buttons[0][i]?.text != "") return true
//        }
//        if (buttons[0][0]?.text == buttons[1][1]?.text && buttons[1][1]?.text == buttons[2][2]?.text && buttons[0][0]?.text != "") return true
//        if (buttons[0][2]?.text == buttons[1][1]?.text && buttons[1][1]?.text == buttons[2][0]?.text && buttons[0][2]?.text != "") return true
//        return false
//    }
//
//    private fun isBoardFull(): Boolean {
//        for (i in 0..2) {
//            for (j in 0..2) {
//                if (buttons[i][j]?.text == "") return false
//            }
//        }
//        return true
//    }
//
//    private fun resetGame() {
//        currentPlayer = 0
//        gameActive = true
//        for (i in 0..2) {
//            for (j in 0..2) {
//                buttons[i][j]?.text = ""
//            }
//        }
//        updateCurrentPlayerDisplay() // Update display after reset
//    }
//
//    private fun showWinnerDialog(winnerName: String) {
//        AlertDialog.Builder(this)
//            .setTitle("Game Over")
//            .setMessage("Wow! $winnerName wins!")
//            .setPositiveButton("OK") { dialog, _ ->
//                dialog.dismiss()
//                resetGame() // Reset the game when dialog is dismissed
//            }
//            .show()
//    }
//
//    private fun showDrawDialog() {
//        AlertDialog.Builder(this)
//            .setTitle("Game Over")
//            .setMessage("It's a draw!")
//            .setPositiveButton("OK") { dialog, _ ->
//                dialog.dismiss()
//                resetGame() // Reset the game when dialog is dismissed
//            }
//            .show()
//    }
//}


package com.example.tiktactoe

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Array<Button?>>
    private var currentPlayer = 0
    private var gameActive = true
    private lateinit var playerNames: Array<String?>
    private lateinit var currentPlayerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons = Array(3) { arrayOfNulls<Button>(3) }
        playerNames = arrayOf(null, null)
        currentPlayerTextView = findViewById(R.id.currentPlayerTextView)

        // Get player names
        getPlayerNames()

        // Initialize buttons
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonId = "button$i$j"
                val resId = resources.getIdentifier(buttonId, "id", packageName)
                buttons[i][j] = findViewById(resId)
                buttons[i][j]?.setOnClickListener { onCellClicked(i, j) }
            }
        }

        findViewById<Button>(R.id.resetButton).setOnClickListener { resetGame() }

        // Initialize display of current player's turn
        updateCurrentPlayerDisplay()
    }

    private fun getPlayerNames() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Player Names")

        val inputLayout = layoutInflater.inflate(R.layout.dialog_input, null)
        val player1Input = inputLayout.findViewById<EditText>(R.id.player1Name)
        val player2Input = inputLayout.findViewById<EditText>(R.id.player2Name)

        builder.setView(inputLayout)
            .setPositiveButton("OK") { _, _ ->
                playerNames[0] = player1Input.text.toString().trim()
                playerNames[1] = player2Input.text.toString().trim()
                updateCurrentPlayerDisplay() // Update display with the first player's name
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun updateCurrentPlayerDisplay() {
        currentPlayerTextView.text = "Turn of: ${playerNames[currentPlayer]}"
    }

    private fun onCellClicked(row: Int, col: Int) {
        if (!gameActive || buttons[row][col]?.text != "") return

        buttons[row][col]?.text = if (currentPlayer == 0) "X" else "O"
        if (checkWinner()) {
            showWinnerDialog(playerNames[currentPlayer]!!)
            gameActive = false
        } else if (isBoardFull()) {
            showDrawDialog()
            gameActive = false
        } else {
            currentPlayer = if (currentPlayer == 0) 1 else 0
            updateCurrentPlayerDisplay() // Update display for the next player's turn
        }
    }

    private fun checkWinner(): Boolean {
        for (i in 0..2) {
            if (buttons[i][0]?.text == buttons[i][1]?.text && buttons[i][1]?.text == buttons[i][2]?.text && buttons[i][0]?.text != "") return true
            if (buttons[0][i]?.text == buttons[1][i]?.text && buttons[1][i]?.text == buttons[2][i]?.text && buttons[0][i]?.text != "") return true
        }
        if (buttons[0][0]?.text == buttons[1][1]?.text && buttons[1][1]?.text == buttons[2][2]?.text && buttons[0][0]?.text != "") return true
        if (buttons[0][2]?.text == buttons[1][1]?.text && buttons[1][1]?.text == buttons[2][0]?.text && buttons[0][2]?.text != "") return true
        return false
    }

    private fun isBoardFull(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (buttons[i][j]?.text == "") return false
            }
        }
        return true
    }

    private fun resetGame() {
        currentPlayer = 0
        gameActive = true
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]?.text = ""
            }
        }
        updateCurrentPlayerDisplay() // Update display after reset
    }

    private fun showWinnerDialog(winnerName: String) {
        AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage("Wow! $winnerName wins!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                resetGame() // Reset the game when dialog is dismissed
            }
            .show()
    }

    private fun showDrawDialog() {
        AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage("It's a draw!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                resetGame() // Reset the game when dialog is dismissed
            }
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                // Start AboutUsActivity
                val intent = Intent(this, AboutUsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
