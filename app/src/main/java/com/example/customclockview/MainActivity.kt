package com.example.customclockview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.customclockview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        binding!!.btnNav.setupWithNavController(navController!!)

        setupOnBackPressed()


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setupOnBackPressed() {
        onBackPressedDispatcher.addCallback(this) {
            if (navController!!.currentDestination?.id == R.id.firstPresentationFragment) {
                finish()
            } else {
                navController!!.navigateUp()
            }
        }
    }
}