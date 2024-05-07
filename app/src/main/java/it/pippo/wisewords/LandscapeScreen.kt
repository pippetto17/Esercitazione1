package it.pippo.wisewords

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import it.pippo.wisewords.R
import it.pippo.wisewords.ui.theme.fontSize
import it.pippo.wisewords.ui.theme.iconSize
import it.pippo.wisewords.ui.theme.landscapeFontSize
import it.pippo.wisewords.ui.theme.landscapeIconSize
import it.pippo.wisewords.ui.theme.landscapeLineHeight
import it.pippo.wisewords.ui.theme.landscapeSmallPadding
import it.pippo.wisewords.ui.theme.landscapeTitleSize
import it.pippo.wisewords.ui.theme.lineHeight
import it.pippo.wisewords.ui.theme.romaYellow
import it.pippo.wisewords.ui.theme.smallPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowLandscapeView(text: String, filter: String, onChangeName: (String) -> Unit,
                         onclick: (filter: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = stringResource(id = R.string.title),
                    fontFamily = titleFont(),
                    fontSize = landscapeTitleSize,
                    color = romaYellow,
                )
                OutlinedTextField(
                    value = filter,
                    onValueChange = {
                        onChangeName(it)
                    },
                    modifier = Modifier
                        .padding(landscapeSmallPadding)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    label = {
                        Text(
                            text = "Filter",
                            fontSize = fontSize,
                            fontFamily = fontFamily(),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold

                        )
                    },
                    textStyle = TextStyle(
                        fontSize = fontSize,
                        fontFamily = fontFamily(),
                        color= Color.White
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedLabelColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        unfocusedTrailingIconColor = Color.White,

                        focusedLabelColor = romaYellow,
                        focusedBorderColor = romaYellow,
                        focusedTrailingIconColor = romaYellow,
                        cursorColor = romaYellow,
                    ),
                    trailingIcon = {
                        Icon(Icons.Rounded.Search,
                            contentDescription = null,
                            modifier = Modifier
                                .size(iconSize, iconSize)
                                .clickable {
                                    onclick(filter)
                                    keyboardController?.hide()
                                })
                    }
                )
            }
        }
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(landscapeSmallPadding)
            .defaultMinSize(minHeight = 200.dp)
            .border(width = 4.dp, color = romaYellow, shape = RoundedCornerShape(30.dp))
            .clickable {
                keyboardController?.hide()
                focusManager.clearFocus()
                onclick(filter)
            },
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = Color.White,
                text = if (text == "") {
                    stringResource(id = R.string.message)
                } else {
                    text
                },
                textAlign = TextAlign.Center,
                fontSize = landscapeFontSize,
                fontFamily = fontFamily()
            )
        }
    }
}