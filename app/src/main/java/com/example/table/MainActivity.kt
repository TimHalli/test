package com.example.table

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.table.databinding.ActivityMainBinding
import com.example.table.dialogs.DialogConst
import com.example.table.dialogs.DialogHelper
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var tvAccount: TextView
    private lateinit var rootElement: ActivityMainBinding
    private val dialogHelper = DialogHelper(this)
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityMainBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)
        init()
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(mAuth.currentUser)
    }

    private fun init() {
        val toggle = ActionBarDrawerToggle(
            this,
            rootElement.drawerLayout,
            rootElement.mainContent.toolbar,
            R.string.open,
            R.string.close
        )
        rootElement.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        rootElement.navView.setNavigationItemSelectedListener(this)
        tvAccount = rootElement.navView.getHeaderView(0).findViewById(R.id.tvAccEmail)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_my_ads -> Toast.makeText(this, "Pressed $item", Toast.LENGTH_LONG).show()

            R.id.id_car -> Toast.makeText(this, "Pressed $item", Toast.LENGTH_LONG).show()

            R.id.id_pc -> Toast.makeText(this, "Pressed $item", Toast.LENGTH_LONG).show()

            R.id.id_dm -> Toast.makeText(this, "Pressed $item", Toast.LENGTH_LONG).show()

            R.id.id_sm -> Toast.makeText(this, "Pressed $item", Toast.LENGTH_LONG).show()

            R.id.id_sign_up -> dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)
            R.id.id_sign_in -> dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)

            R.id.id_sign_out -> {
                uiUpdate(null)
                mAuth.signOut()
            }
        }
        rootElement.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun uiUpdate(user: FirebaseUser?) {
        tvAccount.text = if(user == null) {
            resources.getString(R.string.not_reg)
        } else {
            user.email
        }
    }
}