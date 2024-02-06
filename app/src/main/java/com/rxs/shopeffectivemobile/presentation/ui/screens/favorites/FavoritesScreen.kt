package com.rxs.shopeffectivemobile.presentation.ui.screens.favorites

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rxs.shopeffectivemobile.R
import com.rxs.shopeffectivemobile.presentation.ui.navigation.Screen
import com.rxs.shopeffectivemobile.presentation.ui.screens.catalog.CatalogItemCard
import com.rxs.shopeffectivemobile.presentation.ui.theme.Black
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightGrayBackground
import com.rxs.shopeffectivemobile.presentation.ui.theme.White
import com.rxs.shopeffectivemobile.presentation.ui.theme.buttonText2
import com.rxs.shopeffectivemobile.presentation.ui.theme.title1

@Composable
fun FavoritesScreen(
    vm: FavoritesViewModel = hiltViewModel(),
    navController: NavController
) {
    val tabs = listOf("Товары", "Бренды")
    var tabIndex by remember { mutableStateOf(0) }

    Column(
        Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_left_arrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(24.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            navController.navigate(Screen.ProfileScreen.route)
                        }
                    )
            )
            Text(
                text = "Избранное",
                color = Black,
                style = MaterialTheme.typography.title1
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(42.dp)
                .background(color = LightGrayBackground, shape = RoundedCornerShape(8.dp))
        ) {
            repeat(2) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                        .padding(
                            start = if (it == 0) 3.dp else 0.dp,
                            end = if (it == 0) 0.dp else 3.dp
                        )
                        .fillMaxSize()
                        .weight(1f)
                        .background(
                            color = if (it == tabIndex) White else LightGrayBackground,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = { tabIndex = it }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tabs[it],
                        color = Black,
                        style = MaterialTheme.typography.buttonText2
                    )
                }
            }
        }
        if (tabIndex == 0) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                items(count = vm.favorites.size) { index ->
                    CatalogItemCard(
                        modifier = Modifier.padding(
                            end = if (index % 2 == 0) 8.dp else 0.dp,
                            bottom = 8.dp
                        ),
                        item = vm.favorites[index],
                        isFavorite = true,
                        onFavoriteClick = {
                            vm.removeFromFavorites(vm.favorites[index])
                        }
                    )
                }
            }
        }
    }
}