package com.nurizdal.nurizdaldi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nurizdal.nurizdaldi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupViewPager()
        setupBottomNavigationView()
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding!!.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding!!.tabLayout, binding!!.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Movie"
                1 -> tab.text = "TV"
                2 -> tab.text = "Profile"
            }
        }.attach()
    }

    private fun setupBottomNavigationView() {
        binding!!.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_movies -> {
                    Log.d("BottomNav", "Movies selected")
                    makeCurrentFragment(MovieFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_tv_shows -> {
                    Log.d("BottomNav", "TV Shows selected")
                    makeCurrentFragment(TVFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_account -> {
                    Log.d("BottomNav", "Account selected")
                    makeCurrentFragment(AccountFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Optional: If you want fragment navigation history
            .commit()
    }
}
