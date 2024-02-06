package com.rxs.shopeffectivemobile.presentation.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rxs.shopeffectivemobile.R
import com.rxs.shopeffectivemobile.presentation.ui.navigation.Screen
import com.rxs.shopeffectivemobile.presentation.ui.theme.Black
import com.rxs.shopeffectivemobile.presentation.ui.theme.DarkGray
import com.rxs.shopeffectivemobile.presentation.ui.theme.Gray
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightGrayBackground
import com.rxs.shopeffectivemobile.presentation.ui.theme.LightPink
import com.rxs.shopeffectivemobile.presentation.ui.theme.Pink
import com.rxs.shopeffectivemobile.presentation.ui.theme.White
import com.rxs.shopeffectivemobile.presentation.ui.theme.buttonText2
import com.rxs.shopeffectivemobile.presentation.ui.theme.caption1
import com.rxs.shopeffectivemobile.presentation.ui.theme.linkText
import com.rxs.shopeffectivemobile.presentation.ui.theme.placeholderText
import com.rxs.shopeffectivemobile.presentation.ui.theme.title1

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    vm: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    if (vm.isContainedInDb == true) {
        navController.navigate(Screen.CatalogScreen.route)
    } else if (vm.isContainedInDb == false) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Вход",
                    modifier = Modifier.padding(top = 16.dp),
                    color = Black,
                    style = MaterialTheme.typography.title1
                )
                Column(
                    modifier = Modifier.padding(top = 140.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (vm.checkValidation(vm.firstName)) Color.Transparent else Color.Red,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            TextField(
                                value = vm.firstName,
                                onValueChange = { vm.updateFirstName(it) },
                                modifier = Modifier.fillMaxSize(),
                                textStyle = MaterialTheme.typography.placeholderText,
                                placeholder = {
                                    Text(
                                        text = "Имя",
                                        style = MaterialTheme.typography.placeholderText
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Sentences,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Black,
                                    containerColor = LightGrayBackground,
                                    placeholderColor = Gray,
                                    disabledPlaceholderColor = Gray,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent,
                                )
                            )
                            if (vm.firstName.isNotEmpty()) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_big_close),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .size(28.dp)
                                        .clickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = null,
                                            onClick = {
                                                vm.clearFirstName()
                                            }
                                        ),
                                    tint = DarkGray
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (vm.checkValidation(vm.secondName)) Color.Transparent else Color.Red,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            TextField(
                                value = vm.secondName,
                                onValueChange = { vm.updateSecondName(it) },
                                modifier = Modifier.fillMaxSize(),
                                textStyle = MaterialTheme.typography.placeholderText,
                                placeholder = {
                                    Text(
                                        text = "Фамилия",
                                        style = MaterialTheme.typography.placeholderText
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Sentences,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Black,
                                    containerColor = LightGrayBackground,
                                    placeholderColor = Gray,
                                    disabledPlaceholderColor = Gray,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent,
                                )
                            )
                            if (vm.secondName.isNotEmpty()) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_big_close),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .size(28.dp)
                                        .clickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = null,
                                            onClick = {
                                                vm.clearSecondName()
                                            }
                                        ),
                                    tint = DarkGray
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            TextField(
                                value = vm.phoneNumber,
                                onValueChange = { vm.updatePhoneNumber(it) },
                                modifier = Modifier.fillMaxSize(),
                                textStyle = MaterialTheme.typography.placeholderText,
                                placeholder = {
                                    Text(
                                        text = "Номер телефона",
                                        style = MaterialTheme.typography.placeholderText
                                    )
                                },
                                visualTransformation = {
                                    if (vm.phoneNumber.isNotEmpty()) vm.maskNumber(it)
                                    else TransformedText(it, OffsetMapping.Identity)
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Phone,
                                    imeAction = ImeAction.Done
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Black,
                                    containerColor = LightGrayBackground,
                                    placeholderColor = Gray,
                                    disabledPlaceholderColor = Gray,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent,
                                )
                            )
                            if (vm.phoneNumber.isNotEmpty()) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_big_close),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .size(28.dp)
                                        .clickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = null,
                                            onClick = {
                                                vm.clearPhoneNumber()
                                            }
                                        ),
                                    tint = DarkGray
                                )
                            }
                        }
                    }
                    Button(
                        onClick = {
                            vm.saveUser {
                                navController.navigate(Screen.CatalogScreen.route)
                            }
                        },
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = vm.fieldsComplete(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Pink,
                            disabledContainerColor = LightPink
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Войти",
                                color = White,
                                style = MaterialTheme.typography.buttonText2
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Нажимая кнопку “Войти”, Вы принимаете",
                    color = Gray,
                    style = MaterialTheme.typography.caption1
                )
                Text(
                    text = "условия программы лояльности",
                    color = Gray,
                    style = MaterialTheme.typography.linkText
                )
            }
        }
    }
}