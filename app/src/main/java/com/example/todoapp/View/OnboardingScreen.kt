package com.example.todoapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.Model.Onboarding
import com.example.todoapp.View.Components.OnboardingComponent
import kotlinx.coroutines.launch
import com.example.todoapp.R

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pages = Onboarding.pages
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Le contenu de l'onboarding avec défilement
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            OnboardingComponent(
                page = pages[index],
                pageSize = pages.size,
                currentPage = pagerState.currentPage
            )
        }

        //Bouton pour passer l'onboarding et attérrire sur l'écran de login
        TextButton(
            onClick = onFinish,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp, start = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.onboarding_button_skip),
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 16.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Bouton pour retourner à la page précèdente
            TextButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                enabled = pagerState.currentPage > 0
            ) {
                Text(
                    text = stringResource(R.string.onboarding_button_back),
                    color = if (pagerState.currentPage > 0) Color.White.copy(alpha = 0.5f) else Color.Transparent
                )
            }

            //Bouton pour aller à la page suivante ou si c'est la dernière page ça envoie à l'ecran de login
            Button(
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinish()
                    }
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (pagerState.currentPage == pages.size - 1) stringResource(R.string.onboarding_button_started) else stringResource(R.string.onboarding_button_next),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
private fun OnboardingPreview(){
    OnboardingScreen(onFinish = {})
}
