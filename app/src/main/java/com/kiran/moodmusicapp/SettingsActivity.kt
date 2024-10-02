package com.kiran.moodmusicapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kiran.moodmusicapp.databinding.ActivitySettingsScreenBinding
import android.content.Intent


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivitySettingsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.saveChangesButton.setOnClickListener {
            val email = binding.emailSettings.text.toString().trim()
            val password = binding.passwordSettings.text.toString().trim()

            if (email.isNotEmpty()) {
                // Update email
                auth.currentUser?.updateEmail(email)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Email Updated", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error updating email", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            if (password.isNotEmpty()) {
                // Update password
                auth.currentUser?.updatePassword(password)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password Updated", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error updating password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            // Redirect to Welcome Screen
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
