package br.pro.nigri.assessmentandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class ApplicationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)


        val navController = findNavController(R.id.fragmentContatos)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.listContatosFragment, R.id.listFavoritosFragment,R.id.contaFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_navigation_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController = findNavController(R.id.fragmentContatos)
        var id = item.itemId

        if (id == R.id.createContatoFragment){
            navController.navigate(R.id.createContatoFragment)
        }else{
            navController.navigate(R.id.listContatosFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}