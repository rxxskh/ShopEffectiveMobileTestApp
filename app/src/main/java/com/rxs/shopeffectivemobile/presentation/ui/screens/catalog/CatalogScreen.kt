package com.rxs.shopeffectivemobile.presentation.ui.screens.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rxs.shopeffectivemobile.R
import com.rxs.shopeffectivemobile.common.Category
import com.rxs.shopeffectivemobile.common.IMAGES
import com.rxs.shopeffectivemobile.common.SortType
import com.rxs.shopeffectivemobile.data.datasource.remote.model.ItemData
import com.rxs.shopeffectivemobile.presentation.ui.navigation.Screen
import com.rxs.shopeffectivemobile.presentation.ui.theme.Black
import com.rxs.shopeffectivemobile.presentation.ui.theme.DarkBlue
import com.rxs.shopeffectivemobile.presentation.ui.theme.DarkGray
import com.rxs.shopeffectivemobile.presentation.ui.theme.Gray
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightGray
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightGrayBackground
import com.rxs.shopeffectivemobile.presentation.ui.theme.Orange
import com.rxs.shopeffectivemobile.presentation.ui.theme.Pink
import com.rxs.shopeffectivemobile.presentation.ui.theme.White
import com.rxs.shopeffectivemobile.presentation.ui.theme.caption1
import com.rxs.shopeffectivemobile.presentation.ui.theme.elementText
import com.rxs.shopeffectivemobile.presentation.ui.theme.title1
import com.rxs.shopeffectivemobile.presentation.ui.theme.title2
import com.rxs.shopeffectivemobile.presentation.ui.theme.title3
import com.rxs.shopeffectivemobile.presentation.ui.theme.title4

@Composable
fun CatalogScreen(
    vm: CatalogViewModel = hiltViewModel(),
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Каталог",
            modifier = Modifier
                .padding(top = 16.dp),
            color = Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.title1
        )
        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CatalogSortBar(
                selected = vm.selectedSortType,
                onClick = {
                    vm.selectSortType(it)
                    vm.sortCatalog()
                })
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Фильтры",
                    style = MaterialTheme.typography.title4
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .horizontalScroll(
                    rememberScrollState()
                )
        ) {
            val categories = listOf(
                Category.NoneCategory,
                Category.FaceCategory,
                Category.BodyCategory,
                Category.SuntanCategory,
                Category.MaskCategory
            )
            repeat(categories.size) {
                Box(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .background(
                            color = LightGrayBackground,
                            shape = RoundedCornerShape(percent = 100)
                        )
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                vm.selectCategory(categories[it])
                                vm.sortCatalog()
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (vm.selectedCategory == categories[it]) {
                        CatalogSelectedCategory(
                            text = categories[it].description,
                            onClick = {
                                vm.selectCategory(Category.NoneCategory)
                                vm.sortCatalog()
                            })
                    } else {
                        Text(
                            text = categories[it].description,
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 5.dp
                            ),
                            color = Gray,
                            style = MaterialTheme.typography.title4
                        )
                    }
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp)
        ) {
            items(count = vm.sortedCatalog.size) {
                CatalogItemCard(
                    modifier = Modifier
                        .padding(
                            end = if (it % 2 == 0) 8.dp else 0.dp,
                            bottom = 8.dp
                        )
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                navController.navigate(
                                    Screen.ProductScreen.passId(
                                        vm.sortedCatalog[it].id
                                    )
                                )
                            }
                        ),
                    item = vm.sortedCatalog[it],
                    isFavorite = vm.isFavorite(vm.sortedCatalog[it]),
                    onFavoriteClick = { itemData ->
                        vm.changeFavoriteStatus(itemData)
                    }
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CatalogSelectedCategory(
    text: String = "Смотреть все",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.background(
            color = DarkBlue,
            shape = RoundedCornerShape(percent = 100)
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(start = 12.dp, top = 5.dp, bottom = 5.dp),
            color = White,
            style = MaterialTheme.typography.title4
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_small_close),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(20.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {
                        onClick()
                    }
                ),
            tint = White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogSortBar(
    selected: SortType,
    onClick: (SortType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val sortTypes =
        listOf(SortType.RatingSortType, SortType.DescPriceSortType, SortType.AscPriceSortType)


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Row(
            modifier = Modifier
                .menuAnchor(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sort_by),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
                tint = Black
            )
            Text(
                text = selected.label,
                color = DarkGray,
                style = MaterialTheme.typography.title4
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Black
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(White)
        ) {
            sortTypes.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.label,
                            color = Black,
                            style = MaterialTheme.typography.title4
                        )
                    },
                    onClick = {
                        onClick(item)
                        expanded = false
                    },
                    modifier = Modifier.background(White)
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogItemCard(
    modifier: Modifier = Modifier,
    item: ItemData,
    isFavorite: Boolean,
    onFavoriteClick: (ItemData) -> Unit
) {
    val imageSlider = IMAGES.firstOrNull { it.id == item.id }?.images ?: emptyList()
    val pagerState = rememberPagerState(pageCount = { imageSlider.size })

    Card(
        modifier = modifier
            .width(168.dp)
            .height(287.dp)
            .background(White),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = LightGrayBackground)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.TopEnd
                ) {
                    Box(
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        HorizontalPager(
                            state = pagerState
                        ) { page ->
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = imageSlider[page]),
                                    contentDescription = null,
                                    modifier = Modifier.size(144.dp)
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.padding(top = 4.dp),
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
                                        .size(4.dp)
                                )
                            }
                        }
                    }
                    Icon(
                        painter = painterResource(id = if (isFavorite) R.drawable.ic_heart_state_active else R.drawable.ic_heart_state_default),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(6.dp)
                            .size(24.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = { onFavoriteClick(item) }
                            ),
                        tint = Pink
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 6.dp, top = 2.dp, end = 6.dp)
                ) {
                    Text(
                        text = "${item.price.price} ${item.price.unit}",
                        color = Gray,
                        textDecoration = TextDecoration.LineThrough,
                        style = MaterialTheme.typography.elementText
                    )
                    Row(
                        modifier = Modifier.padding(top = 2.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${item.price.priceWithDiscount} ${item.price.unit}",
                            color = Black,
                            style = MaterialTheme.typography.title2
                        )
                        Box(
                            modifier = Modifier.background(
                                color = Pink,
                                shape = RoundedCornerShape(4.dp)
                            ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "-${item.price.discount}%",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                color = White,
                                style = MaterialTheme.typography.elementText
                            )
                        }
                    }
                    Text(
                        text = item.title,
                        modifier = Modifier.padding(top = 2.dp),
                        color = Black,
                        style = MaterialTheme.typography.title3
                    )
                    Text(
                        text = item.subtitle,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .height(37.dp),
                        color = DarkGray,
                        style = MaterialTheme.typography.caption1
                    )
                    if (item.feedback != null) {
                        Row(
                            modifier = Modifier.padding(top = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_small_star),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Orange
                            )
                            Text(
                                text = item.feedback.rating.toString(),
                                color = Orange,
                                style = MaterialTheme.typography.elementText
                            )
                            Text(
                                text = "(${item.feedback.count})",
                                color = Gray,
                                style = MaterialTheme.typography.elementText
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = Pink,
                        shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = White
                )
            }
        }
    }
}