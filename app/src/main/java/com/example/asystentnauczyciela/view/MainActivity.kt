package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.database.AsystentNauczycielaDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNav()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.clear_all_data_menu_option) {
            lifecycleScope.launch(Dispatchers.IO) {
                AsystentNauczycielaDatabase.getDatabase(application).clearAllTables()
            }
            
            Toast.makeText(
                applicationContext,
                "Wyczyszczono wszystkie dane!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController(R.id.fragmentContainerView).navigate(R.id.subjectsListFragment)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupNav() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.subjectsListFragment, R.id.students))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)
    }
}