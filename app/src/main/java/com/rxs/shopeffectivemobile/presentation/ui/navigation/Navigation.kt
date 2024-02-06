package com.rxs.shopeffectivemobile.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.rxs.shopeffectivemobile.presentation.ui.screens.catalog.CatalogScreen
import com.rxs.shopeffectivemobile.presentation.ui.screens.favorites.FavoritesScreen
import com.rxs.shopeffectivemobile.presentation.ui.screens.login.LoginScreen
import com.rxs.shopeffectivemobile.presentation.ui.screens.product.ProductScreen
import com.rxs.shopeffectivemobile.presentation.ui.screens.profile.ProfileScreen
import com.rxs.shopeffectivemobile.presentation.ui.theme.DarkGray
import com.rxs.shopeffectivemobile.presentation.ui.theme.Pink
import com.rxs.shopeffectivemobile.presentation.ui.theme.White
import com.rxs.shopeffectivemobile.presentation.ui.theme.caption1

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.MainScreen.route
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
            )
        }
        composable(
            route = Screen.CatalogScreen.route
        ) {
            CatalogScreen(navController = navController)
        }
        composable(
            route = Screen.CardScreen.route
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
            )
        }
        composable(
            route = Screen.DiscountScreen.route
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
            )
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.ProductScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            ProductScreen(
                navController = navController,
                id = it.arguments?.getString("id")!!
            )
        }
        composable(
            route = Screen.FavoritesScreen.route
        ) {
            FavoritesScreen(navController = navController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val screens = listOf(
        Screen.MainScreen,
        Screen.CatalogScreen,
        Screen.CardScreen,
        Screen.DiscountScreen,
        Screen.ProfileScreen
    )
    val backStackEntry = navController.currentBackStackEntryAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFE8E9EC))
    ) {
        NavigationBar(
            modifier = Modifier
                .padding(top = 1.dp)
                .fillMaxSize(),
            containerColor = White,
            contentColor = DarkGray,
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                screens.forEach { screen ->
                    val selected = screen.route == backStackEntry.value?.destination?.route
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = {
                                    navController.navigate(screen.route)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = screen.icon!!),
                                contentDescription = screen.route,
                                modifier = Modifier.size(24.dp),
                                tint = if (selected) Pink else DarkGray,
                            )
                            Text(
                                text = screen.label!!,
                                color = if (selected) Pink else DarkGray,
                                style = MaterialTheme.typography.caption1
                            )
                        }
                    }
                }
            }
        }
    }
}