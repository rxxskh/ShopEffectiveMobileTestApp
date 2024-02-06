package com.rxs.shopeffectivemobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rxs.shopeffectivemobile.presentation.ui.navigation.BottomNavigationBar
import com.rxs.shopeffectivemobile.presentation.ui.navigation.NavigationGraph
import com.rxs.shopeffectivemobile.presentation.ui.navigation.Screen
import com.rxs.shopeffectivemobile.presentation.ui.screens.catalog.CatalogScreen
import com.rxs.shopeffectivemobile.presentation.ui.screens.login.LoginScreen
import com.rxs.shopeffectivemobile.presentation.ui.screens.profile.ProfileScreen
import com.rxs.shopeffectivemobile.presentation.ui.theme.SFProDisplay
import com.rxs.shopeffectivemobile.presentation.ui.theme.ShopEffectiveMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopEffectiveMobileTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        val notAuth =
                            when (navController.currentBackStackEntryAsState().value?.destination?.route) {
                                Screen.LoginScreen.route, null -> false
                                else -> true
                            }
                        if (notAuth) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { paddingValue ->
                    Box(modifier = Modifier.padding(paddingValue)) {
                        NavigationGraph(navController = navController)
                    }
                }
            }
        }
    }
}
