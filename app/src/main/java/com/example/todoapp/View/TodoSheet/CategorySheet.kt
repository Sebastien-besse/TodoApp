package com.example.todoapp.View.TodoSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.Model.Category
import com.example.todoapp.Model.State.TodoUiState
import com.example.todoapp.View.Components.SelectCategoryComponent
import androidx.compose.ui.res.stringResource
import com.example.todoapp.R
import com.example.todoapp.View.Components.ButtonLarge


@Composable
fun CategoryPickerSheet(
    uiState: TodoUiState,
    onCategorySelected: (Category) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.tertiary,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.category_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .height(2.dp)
                        .clip(RoundedCornerShape(50)),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(uiState.categories) { category ->
                        SelectCategoryComponent(
                            value = category.name,
                            color = category.color,
                            imageRes = category.image,
                            isSelected = category == uiState.category,
                            onClick = { onCategorySelected(category) }
                        )
                    }
                }
            }
        },

        confirmButton = {

            ButtonLarge(
                text = stringResource(R.string.category_button),
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth(),
                isStroke = false
            )

        },

    )
}