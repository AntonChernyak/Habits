package ru.doubletapp.eduapp.habits.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.databinding.ActivityMainBinding

class HabitsListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        appBarConfig = AppBarConfiguration(navController.graph, binding.drawerLayout)

        binding.navigationDrawerView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val drawer = binding.drawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.applicationInfoFragment -> {
                navController.navigate(R.id.applicationInfoFragment)
            }
            R.id.habitsListFragment -> {
                navController.navigate(
                    R.id.applicationInfoFragment,
                    null
                )
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}