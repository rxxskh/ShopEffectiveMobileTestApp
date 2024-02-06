package com.rxs.shopeffectivemobile.presentation.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rxs.shopeffectivemobile.data.datasource.local.model.UserData
import com.rxs.shopeffectivemobile.domain.usecase.GetUserUseCase
import com.rxs.shopeffectivemobile.domain.usecase.SaveUserUseCase
import com.rxs.shopeffectivemobile.presentation.ui.theme.Gray
import com.rxs.shopeffectivemobile.presentation.ui.theme.SFProDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val phoneMask = "+7 ХХХ ХХХ-ХХ-ХХ"

    var firstName by mutableStateOf("")
        private set
    var secondName by mutableStateOf("")
        private set
    var phoneNumber by mutableStateOf("")
        private set
    var isContainedInDb by mutableStateOf<Boolean?>(null)
        private set

    init {
        checkUser()
    }

    fun updateFirstName(input: String) {
        firstName = input
    }

    fun updateSecondName(input: String) {
        secondName = input
    }

    fun updatePhoneNumber(input: String) {
        if (input != "7" && input.length <= 10 && isDigits(input)) {
            phoneNumber = input
        }
    }

    fun clearFirstName() {
        firstName = ""
    }

    fun clearSecondName() {
        secondName = ""
    }

    fun clearPhoneNumber() {
        phoneNumber = ""
    }

    fun saveUser(onComplete: () -> Unit) {
        viewModelScope.launch {
            saveUserUseCase.invoke(UserData(firstName, secondName, phoneNumber))
        }.invokeOnCompletion { onComplete() }
    }

    fun checkUser() {
        viewModelScope.launch {
            val userFromDb = getUserUseCase.invoke()
            firstName = userFromDb.firstName
            secondName = userFromDb.secondName
            phoneNumber = userFromDb.phoneNumber
            isContainedInDb = fieldsComplete()
        }
    }

    fun fieldsComplete(): Boolean =
        firstName.isNotEmpty() && checkValidation(firstName)
                && secondName.isNotEmpty() && checkValidation(secondName)
                && phoneNumber.isNotEmpty() && phoneNumber.length == 10

    private fun isDigits(text: String): Boolean = text.all { it.isDigit() }

    fun checkValidation(text: String): Boolean = text.all { it in '\u0400'..'\u044F' }

    fun maskNumber(text: AnnotatedString): TransformedText {
        val annotatedString = AnnotatedString.Builder().run {
            for (i in text.indices) {
                when (i) {
                    0 -> append("+7 ")
                    3 -> append(" ")
                    6, 8 -> append("-")
                }
                append(text[i])
            }
            pushStyle(
                SpanStyle(
                    color = Gray,
                    fontFamily = SFProDisplay,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            )
            append(phoneMask.takeLast(phoneMask.length - length))
            toAnnotatedString()
        }

        val phoneNumberOffsetTranslator = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                return when (offset) {
                    in 0..2 -> offset + 3
                    in 3..5 -> offset + 4
                    in 6..7 -> offset + 5
                    in 8..9 -> offset + 6
                    else -> 16
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when (offset) {
                    in 0..2 -> offset - 3
                    in 3..5 -> offset - 4
                    in 6..7 -> offset - 5
                    in 8..9 -> offset - 6
                    else -> 10
                }
            }

        }

        return TransformedText(annotatedString, phoneNumberOffsetTranslator)
    }
}