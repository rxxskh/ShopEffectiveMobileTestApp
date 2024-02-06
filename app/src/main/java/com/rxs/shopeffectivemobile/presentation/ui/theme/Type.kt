package com.rxs.shopeffectivemobile.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.rxs.shopeffectivemobile.R

val SFProDisplay = FontFamily(
    Font(R.font.sf_pro_display_medium, FontWeight.Medium),
    Font(R.font.sf_pro_display_regular, FontWeight.Normal)
)

val Typography.largeTitle: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )
    }

val Typography.title1: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    }

val Typography.title2: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }

val Typography.title3: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    }

val Typography.title4: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
    }

val Typography.text1: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    }

val Typography.caption1: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
    }

val Typography.buttonText1: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    }

val Typography.buttonText2: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }

val Typography.elementText: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp
        )
    }

val Typography.priceText: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        )
    }

val Typography.placeholderText: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    }

val Typography.linkText: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            textDecoration = TextDecoration.Underline
        )
    }