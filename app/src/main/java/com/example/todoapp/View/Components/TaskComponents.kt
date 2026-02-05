package com.example.todoapp.View.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.Model.Category
import com.example.todoapp.Model.Priority
import com.example.todoapp.Model.Todo
import com.example.todoapp.R
import com.example.todoapp.Utils.formatLongToDate
import com.example.todoapp.ui.theme.TodoAppTheme

@Composable
fun TaskComponent(todo: Todo, onCompleteClick: (Todo) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(2.dp, MaterialTheme.colorScheme.onPrimary, CircleShape)
                .clickable {
                    onCompleteClick(todo)
                }
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = todo.content,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = formatLongToDate(todo.date),
                color = Color.LightGray,
                style = MaterialTheme.typography.labelSmall
            )
        }
        CategoryBadge(category = todo.category)
        PriorityBadge(priority = todo.priority.level)
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskComponentPreview() {
    TodoAppTheme {
        val mockCategory = Category(
            name = "Design",
            color = Color(0xFF00ACC1),
            image = R.drawable.design
        )
        val mockTodo = Todo(
            id = "preview-id-123",
            content = "Faire le design de l'app",
            date = 1738594800000L,
            category = mockCategory,
            priority = Priority.Level1
        )
        TaskComponent(
            todo = mockTodo,
            onCompleteClick = {}
        )
    }
}