package org.sopt.dosopttemplate.presentation


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)

        binding.btnLogin.setOnClickListener {
            if (binding.etID.text.length >= 6) {
                Snackbar.make(
                    binding.root,
                    "로그인에 성공했습니다",
                    Snackbar.LENGTH_SHORT
                ).show()
                startActivity(intent)
            }

        }
    }

}