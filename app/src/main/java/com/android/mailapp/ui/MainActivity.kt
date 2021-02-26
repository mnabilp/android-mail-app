package com.android.mailapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.mailapp.R
import com.android.mailapp.ui.draft.DraftFragment
import com.android.mailapp.ui.home.HomeFragment
import com.android.mailapp.ui.spam.SpamFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // kita set default nya Home Fragment
        loadFragment(HomeFragment())
        // inisialisasi BottomNavigaionView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_home)
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (menuItem.itemId) {
            R.id.bnv_home -> fragment = HomeFragment()
            R.id.bnv_draft -> fragment = DraftFragment()
            R.id.bnv_spam -> fragment = SpamFragment()
        }
        return loadFragment(fragment)
    }
}