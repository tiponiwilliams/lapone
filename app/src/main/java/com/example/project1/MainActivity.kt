package com.example.project1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.R.id.plus10

class MainActivity : AppCompatActivity() {
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById< ImageButton>(R.id.add1button)
        val textView = findViewById<TextView>(R.id.textView)
        val upgradeButton = findViewById<Button>(R.id.upgradeBtn) // +2
        val btnplus10 = findViewById<Button>(R.id.plus10)         // +10

        button.setOnClickListener {
            counter++
            textView.text = counter.toString()

            if (counter >= 100) {
                upgradeButton.visibility = View.VISIBLE
                upgradeButton.setOnClickListener {
                    // switch main button to +2


                    // if you're already at/over 150 when you press upgrade, show +10 now
                    if (counter >= 150) btnplus10.visibility = View.VISIBLE

                    // NEW main button listener (+2) — REPEAT the 150 check here
                    button.setOnClickListener {
                        counter += 2
                        textView.text = counter.toString()

                        if (counter >= 150) {
                            btnplus10.visibility = View.VISIBLE
                            btnplus10.setOnClickListener {
                                // switch main button to +10
                                button.setOnClickListener {
                                    counter += 10
                                    textView.text = counter.toString()
                                }
                                btnplus10.visibility = View.INVISIBLE
                            }
                        }
                    }

                    upgradeButton.visibility = View.INVISIBLE
                }
            }

            if (counter >= 150) {
                btnplus10.visibility = View.VISIBLE
                btnplus10.setOnClickListener {
                    // switch main button to +10
                    button.setOnClickListener {
                        counter += 10
                        textView.text = counter.toString()
                    }
                    btnplus10.visibility = View.INVISIBLE
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}