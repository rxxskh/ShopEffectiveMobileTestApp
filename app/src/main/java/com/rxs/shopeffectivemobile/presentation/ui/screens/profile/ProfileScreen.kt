package com.rxs.shopeffectivemobile.presentation.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rxs.shopeffectivemobile.R
import com.rxs.shopeffectivemobile.presentation.ui.navigation.Screen
import com.rxs.shopeffectivemobile.presentation.ui.theme.Black
import com.rxs.shopeffectivemobile.presentation.ui.theme.DarkGray
import com.rxs.shopeffectivemobile.presentation.ui.theme.Gray
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightGrayBackground
import com.rxs.shopeffectivemobile.presentation.ui.theme.Orange
import com.rxs.shopeffectivemobile.presentation.ui.theme.Pink
import com.rxs.shopeffectivemobile.presentation.ui.theme.White
import com.rxs.shopeffectivemobile.presentation.ui.theme.buttonText2
import com.rxs.shopeffectivemobile.presentation.ui.theme.caption1
import com.rxs.shopeffectivemobile.presentation.ui.theme.title1
import com.rxs.shopeffectivemobile.presentation.ui.theme.title2

@Composable
fun ProfileScreen(
    vm: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Личный кабинет",
                modifier = Modifier.padding(vertical = 16.dp),
                color = Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.title1
            )
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = LightGrayBackground, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_account),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = DarkGray
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${vm.user.firstName} ${vm.user.secondName}",
                            color = Black,
                            style = MaterialTheme.typography.title2
                        )
                        Text(
                            text = vm.getPhoneNumber(),
                            color = Gray,
                            style = MaterialTheme.typography.caption1
                        )
                    }
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = DarkGray
                )
            }
            Column(
                modifier = Modifier.padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = LightGrayBackground, shape = RoundedCornerShape(8.dp))
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                navController.navigate(Screen.FavoritesScreen.route)
                            }
                        )
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_heart_state_default),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Pink
                        )
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Избранное",
                                color = Black,
                                style = MaterialTheme.typography.title2
                            )
                            Text(
                                text = vm.getFavoritesCountText(),
                                color = Gray,
                                style = MaterialTheme.typography.caption1
                            )
                        }
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_right_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = Black
                    )
                }
                ProfileSection(
                    icon = R.drawable.ic_shop,
                    color = Pink,
                    text = "Магазины"
                )
                ProfileSection(
                    icon = R.drawable.ic_feedback,
                    color = Orange,
                    text = "Обратная связь"
                )
                ProfileSection(
                    icon = R.drawable.ic_offer,
                    color = Gray,
                    text = "Оферта"
                )
                ProfileSection(
                    icon = R.drawable.ic_refund,
                    color = Gray,
                    text = "Возврат товара"
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
                .height(50.dp)
                .background(color = LightGrayBackground, shape = RoundedCornerShape(8.dp))
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {
                        vm.quit {
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(0)
                            }
                        }
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Выйти",
                color = Black,
                style = MaterialTheme.typography.buttonText2
            )
        }
    }
}

@Preview
@Composable
fun ProfileSection(
    icon: Int = R.drawable.ic_shop,
    color: Color = Pink,
    text: String = "Магазины"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = LightGrayBackground, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = color
            )
            Text(
                text = text,
                color = Black,
                style = MaterialTheme.typography.title2
            )
        }
        Box(
            modifier = Modifier.size(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Black
            )
        }
    }
}