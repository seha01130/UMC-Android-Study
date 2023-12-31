package com.example.flo.ui.signup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.data.entities.User
import com.example.flo.data.remote.AuthService
import com.example.flo.databinding.ActivitySignupBinding

//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit

class SignUpActivity : AppCompatActivity() , SignUpView {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpSignUpBtn.setOnClickListener {
            signUp()
//            finish() //로그인화면으로 이동하면서 LoginActivity가 켜질수있게 됨 (LoginActivity에서 SignupActivity를 호출)
        }
    }

    //값 가져오기
    private fun getUser(): User {
        val email: String =
            binding.signUpIdEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
        val name: String = binding.signUpNameEt.text.toString()
        val pwd: String = binding.signUpPasswordEt.text.toString()

        return User(email, pwd, name)
    }

    //회원가입 진행 //ROOMDB
//    private fun signUp() {
//        if (binding.signUpIdEt.text.toString().isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
//            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        //정보 DB 저장
//        val userDB = SongDatabase.getInstance(this)!!
//        userDB.userDao().insert(getUser())
//
//        val users = userDB.userDao().getUsers()
//
//        Log.d("SIGNUPACT", users.toString())
//    }

    //회원가입 진행 //네트워크를 통한 회원가입. Retrofit공식문서 보면서 진행
    private fun signUp() {
        if (binding.signUpIdEt.text.toString().isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpNameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

//        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
//        authService.signUp(getUser()).enqueue(object: Callback<AuthResponse>{
//            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
//                Log.d("SIGNUP-ACT/RESPONSE", response.toString())
//
//                val resp: AuthResponse = response.body()!!
//
//                when(resp.code) {
//                    1000 -> finish()
//                    2016, 2017 -> {
//                        binding.signUpEmailErrorTv.text = resp.message
//                        binding.signUpEmailErrorTv.visibility = View.VISIBLE
//                    }
//                    2018 -> {
//
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                Log.d("SIGNUP-ACT/ERROR", t.message.toString())
//            }
//        })
//
//        Log.d("SIGNUP-ACT/ASYNC", "Hello, FLO")

        val authService = AuthService()
        authService.setSignUpView(this)
        authService.signUp(getUser())
    }

    override fun onSignUpSuccess() {
        finish()
    }

    override fun onSignUpFailure() {
        //실패처리
    }
}