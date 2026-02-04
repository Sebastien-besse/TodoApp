package com.example.todoapp.View.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.Model.Category
import com.example.todoapp.R
import com.example.todoapp.ui.theme.TodoAppTheme

@Composable
fun CategoryBadge(category: Category) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .height(29.dp)
            .background(category.color)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Image(
            painter = painterResource(id = category.image),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = category.name,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryBadgePreview() {
    TodoAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            val demoCategory = Category(
                name = "Design",
                color = Color(0xFF00ACC1),
                image = R.drawable.design
            )

            CategoryBadge(category = demoCategory)
        }
    }
}