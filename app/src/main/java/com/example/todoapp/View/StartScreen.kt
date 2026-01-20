package com.example.todoapp.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.View.Components.OnboardingtextComponents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(onLogin: () -> Unit, onBack: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingtextComponents(
            R.string.start_title,
            R.string.start_content
        )
        Spacer(Modifier.size(400.dp))
        Button(
            onClick = {onLogin()},
            modifier = Modifier.size(height = 48.dp, width = 327.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = stringResource(R.string.start_button_login),
                color = Color.White
            )
        }
        Spacer(Modifier.size(20.dp))
        OutlinedButton(
            onClick = {},
            modifier = Modifier.size(height = 48.dp, width = 327.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(4.dp),
        ) {
            Text(
                text = stringResource(R.string.start_button_create_account),
                color = Color.White
            )
        }
    }

    IconButton(
        onClick = {onBack()},
        modifier = Modifier
            .padding(vertical = 80.dp)
    ) {
        Icon(
            rememberVectorPainter(Icons.AutoMirrored.Outlined.KeyboardArrowLeft),
            contentDescription = "Back",
            tint = {Color.White}

        )

    }
}