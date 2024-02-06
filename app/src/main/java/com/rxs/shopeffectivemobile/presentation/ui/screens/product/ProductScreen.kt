package com.rxs.shopeffectivemobile.presentation.ui.screens.product

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rxs.shopeffectivemobile.R
import com.rxs.shopeffectivemobile.common.IMAGES
import com.rxs.shopeffectivemobile.presentation.ui.navigation.Screen
import com.rxs.shopeffectivemobile.presentation.ui.theme.Black
import com.rxs.shopeffectivemobile.presentation.ui.theme.DarkGray
import com.rxs.shopeffectivemobile.presentation.ui.theme.Gray
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightGray
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightGrayBackground
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightPink
import com.rxs.shopeffectivemobile.presentation.ui.theme.Orange
import com.rxs.shopeffectivemobile.presentation.ui.theme.Pink
import com.rxs.shopeffectivemobile.presentation.ui.theme.White
import com.rxs.shopeffectivemobile.presentation.ui.theme.buttonText1
import com.rxs.shopeffectivemobile.presentation.ui.theme.buttonText2
import com.rxs.shopeffectivemobile.presentation.ui.theme.caption1
import com.rxs.shopeffectivemobile.presentation.ui.theme.elementText
import com.rxs.shopeffectivemobile.presentation.ui.theme.largeTitle
import com.rxs.shopeffectivemobile.presentation.ui.theme.priceText
import com.rxs.shopeffectivemobile.presentation.ui.theme.text1
import com.rxs.shopeffectivemobile.presentation.ui.theme.title1
import com.rxs.shopeffectivemobile.presentation.ui.theme.title2

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun ProductScreen(
    vm: ProductViewModel = hiltViewModel(),
    navController: NavController,
    id: String
) {
    var hidden by remember { mutableStateOf(true) }

    vm.loadProduct(id)
    if (vm.product != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_left_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                navController.navigate(Screen.CatalogScreen.route)
                            }
                        ),
                    tint = Black
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Black
                )
            }
            ProductImageCard(
                id = vm.product!!.id,
                isFavorite = vm.isFavorite(),
                onFavoriteClick = { vm.changeFavoriteStatus() }
            )
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = vm.product!!.title,
                    color = Gray,
                    style = MaterialTheme.typography.title1
                )
                Text(
                    text = vm.product!!.subtitle,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Black,
                    style = MaterialTheme.typography.largeTitle
                )
                Text(
                    text = vm.getAvailableText(),
                    modifier = Modifier.padding(top = 10.dp),
                    color = Gray,
                    style = MaterialTheme.typography.text1
                )
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(LightGrayBackground)
                )
                if (vm.product!!.feedback != null) {
                    Row(
                        modifier = Modifier.padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row {
                            repeat(vm.getIntRating()) {
                                Box(
                                    modifier = Modifier
                                        .width(16.dp)
                                        .height(20.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_small_star_stroke),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(9.dp)
                                            .height(10.dp),
                                        tint = Orange
                                    )
                                    Row {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_small_star_left),
                                            contentDescription = null,
                                            modifier = Modifier.size(width = 5.dp, height = 10.dp),
                                            tint = Orange
                                        )
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_small_star_right),
                                            contentDescription = null,
                                            modifier = Modifier.size(width = 5.dp, height = 10.dp),
                                            tint = Orange
                                        )
                                    }
                                }
                            }
                            if (vm.hasFractalRating()) {
                                Box(
                                    modifier = Modifier
                                        .width(16.dp)
                                        .height(20.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_small_star_left),
                                            contentDescription = null,
                                            modifier = Modifier.size(width = 5.dp, height = 10.dp),
                                            tint = Orange
                                        )
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_small_star_left),
                                            contentDescription = null,
                                            modifier = Modifier.size(width = 5.dp, height = 10.dp),
                                            tint = White
                                        )
                                    }
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_small_star_stroke),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(9.dp)
                                            .height(10.dp),
                                        tint = Orange
                                    )
                                }
                            }
                            repeat(vm.getFractalRating()) {
                                Box(
                                    modifier = Modifier
                                        .width(16.dp)
                                        .height(20.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_small_star_stroke),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(9.dp)
                                            .height(10.dp),
                                        tint = Orange
                                    )
                                }
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = vm.product!!.feedback!!.rating.toString(),
                                color = Gray,
                                style = MaterialTheme.typography.text1
                            )
                            Box(
                                modifier = Modifier
                                    .size(2.dp)
                                    .background(color = Gray, shape = CircleShape)
                            )
                            Text(
                                text = vm.getFeedbackCountText(),
                                color = Gray,
                                style = MaterialTheme.typography.text1
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "${vm.product!!.price.priceWithDiscount} ${vm.product!!.price.unit}",
                        color = Black,
                        style = MaterialTheme.typography.priceText
                    )
                    Text(
                        text = "${vm.product!!.price.price} ${vm.product!!.price.unit}",
                        color = Gray,
                        textDecoration = TextDecoration.LineThrough,
                        style = MaterialTheme.typography.text1
                    )
                    Box(
                        modifier = Modifier.background(
                            color = Pink,
                            shape = RoundedCornerShape(4.dp)
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "-${vm.product!!.price.discount}%",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                            color = White,
                            style = MaterialTheme.typography.elementText
                        )
                    }
                }
                Text(
                    text = "Описание",
                    modifier = Modifier.padding(top = 24.dp),
                    color = Black,
                    style = MaterialTheme.typography.title1
                )
                if (!hidden) {
                    Column(
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(
                                    color = LightGrayBackground,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = vm.product!!.title,
                                modifier = Modifier.padding(start = 8.dp),
                                color = Black,
                                style = MaterialTheme.typography.title2
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_right_arrow),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(32.dp)
                            )
                        }
                        Text(
                            text = vm.product!!.description,
                            color = DarkGray,
                            modifier = Modifier.padding(top = 8.dp),
                            style = MaterialTheme.typography.text1
                        )
                        Text(
                            text = "Скрыть",
                            color = Gray,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = {
                                        hidden = true
                                    }
                                ),
                            style = MaterialTheme.typography.buttonText1
                        )
                    }
                    Column(
                        modifier = Modifier.padding(top = 32.dp)
                    ) {
                        Text(
                            text = "Характеристики",
                            color = Black,
                            style = MaterialTheme.typography.title1
                        )
                        Column(
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            repeat(vm.product!!.info.size) {
                                Box(
                                    contentAlignment = Alignment.BottomCenter
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(32.dp),
                                        verticalAlignment = Alignment.Top,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = vm.product!!.info[it].title,
                                            modifier = Modifier.padding(top = 12.dp),
                                            color = DarkGray,
                                            style = MaterialTheme.typography.text1
                                        )
                                        Text(
                                            text = vm.product!!.info[it].value,
                                            modifier = Modifier.padding(top = 12.dp),
                                            color = DarkGray,
                                            style = MaterialTheme.typography.text1
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(LightGray)
                                    )
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.padding(top = 32.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Состав",
                                color = Black,
                                style = MaterialTheme.typography.title1
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_copy),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = Gray
                            )
                        }
                        Text(
                            text = vm.product!!.ingredients,
                            modifier = Modifier.padding(top = 16.dp),
                            color = DarkGray,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            style = MaterialTheme.typography.text1
                        )
                    }
                }
                Text(
                    text = "Подробнее",
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                hidden = false
                            }
                        ),
                    color = Gray,
                    style = MaterialTheme.typography.buttonText1
                )
                ProductButton(
                    modifier = Modifier.padding(top = 32.dp, bottom = 8.dp),
                    priceWithDiscount = "${vm.product!!.price.priceWithDiscount} ${vm.product!!.price.unit}",
                    price = "${vm.product!!.price.price} ${vm.product!!.price.unit}"
                )
            }
        }
    }
}

@Composable
fun ProductButton(
    modifier: Modifier = Modifier,
    priceWithDiscount: String,
    price: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Pink, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = priceWithDiscount,
                color = White,
                style = MaterialTheme.typography.buttonText2
            )
            Text(
                text = price,
                color = LightPink,
                textDecoration = TextDecoration.LineThrough,
                style = MaterialTheme.typography.caption1
            )
        }
        Text(
            text = "Добавить корзину",
            color = White,
            style = MaterialTheme.typography.buttonText2
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductImageCard(
    id: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    val imageSlider = IMAGES.firstOrNull { it.id == id }?.images ?: emptyList()
    val pagerState = rememberPagerState(pageCount = { imageSlider.size })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(340.dp)
                .height(368.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                ) { page ->
                    Image(
                        painter = painterResource(id = imageSlider[page]),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_question),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(24.dp)
                )
            }
            Icon(
                painter = painterResource(id = if (isFavorite) R.drawable.ic_heart_state_active else R.drawable.ic_heart_state_default),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            onFavoriteClick()
                        }
                    ),
                tint = Pink
            )
        }
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(imageSlider.size) {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(
                            color = if (pagerState.currentPage == it) Pink else LightGray,
                            shape = CircleShape
                        )
                        .size(6.dp)
                )
            }
        }
    }
}