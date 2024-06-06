package com.example.loginsignupauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.loginsignupauth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.home -> openFragment(HomeFragment())
                R.id.search -> openFragment(SearchFragment())
                R.id.library -> openFragment(LibraryFragment())
            }
            true
        }

        binding.navigationDrawer.setNavigationItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.profile -> openFragment(ProfileFragment())
                R.id.settings -> openFragment(SettingsFragment())
                R.id.about -> openFragment(AboutFragment())
                R.id.help -> Toast.makeText(this, "Contact: learningapp@gmail.com", Toast.LENGTH_SHORT).show()
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true

        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransactions: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransactions.replace(R.id.flContainer, fragment)
        fragmentTransactions.commit()

    }

    override fun onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}