package com.example.kimchiroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.kimchiroom.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
  private var binding: ActivityMainBinding? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding?.root)

    val employeeDao = (application as EmployeeApp).db.employeeDao()
    binding?.btnAdd?.setOnClickListener {
      addRecord(employeeDao)
    }
  }

  fun addRecord(employeeDao: EmployeeDao) {
    val name = binding?.etName?.text.toString()
    val email = binding?.etEmailId?.text.toString()

    if (name.isNotEmpty() && email.isNotEmpty()) {
      lifecycleScope.launch {
        employeeDao.insert(EmployeeEntity(name = name, email = email))
        Toast.makeText(
          applicationContext,
          "Record Saved",
          Toast.LENGTH_LONG
        ).show()

        binding?.etName?.text?.clear()
        binding?.etEmailId?.text?.clear()

      }
    } else {
      Toast.makeText(
          applicationContext,
          "Email or name cannot be blank",
          Toast.LENGTH_LONG
        ).show()
    }
  }
}