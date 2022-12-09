package uz.digital.wallpaperapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.button.MaterialButton
import uz.digital.wallpaperapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.popularFragment,
                R.id.randomFragment,
                R.id.likedFragment
            ),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        binding.appBarMain.contentMain.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, id, _ ->
            when(id.id) {
                R.id.historyFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isOpen) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun showDialog(item: MenuItem) {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        val view = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setView(view)
        val ok = view.findViewById<MaterialButton>(R.id.btnOk)
        ok.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
    fun history(item: MenuItem) {
        findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.global_action)
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }
    private fun hideBottomNav() {
        binding.appBarMain.contentMain.bottomNav.isVisible = false
    }
    private fun showBottomNav() {
        binding.appBarMain.contentMain.bottomNav.isVisible = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.serach_menu, menu)
        return true
    }
}