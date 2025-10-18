package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//ToDo 4: Match the UI as in drawable gpa_design.png. Use the following hints:
//ToDo 5: Add the GpaAppScreen composable button that clears the input fields when clicked

@Composable
fun GpaAppScreen() {
    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }
    var gpa by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = grade1,
            onValueChange = { grade1 = it },
            label = { Text("Course 1 Grade") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(horizontal = 30.dp)
        )

        OutlinedTextField(
            value = grade2,
            onValueChange = { grade2 = it },
            label = { Text("Course 2 Grade") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(horizontal = 30.dp)

        )

        OutlinedTextField(
            value = grade3,
            onValueChange = { grade3 = it },
            label = { Text("Course 3 Grade") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(horizontal = 30.dp)

        )

        Spacer(Modifier.height(28.dp))

        Button(
            onClick = {
                val g1 = grade1.toDoubleOrNull()
                val g2 = grade2.toDoubleOrNull()
                val g3 = grade3.toDoubleOrNull()
                gpa = if (g1 != null && g2 != null && g3 != null) {
                    String.format("%.2f", (g1 + g2 + g3) / 3.0)
                } else {
                    "Invalid input"
                }
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6A5AE0) // purple pill like the screenshot
            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .wrapContentWidth()
        ) {
            Text("Calculate GPA", fontSize = 18.sp)
        }

        // Small clear button to satisfy ToDo 5
        TextButton(
            onClick = {
                grade1 = ""
                grade2 = ""
                grade3 = ""
                gpa = null
            }
        ) {
            Text("Clear")
        }

        gpa?.let { Text(text = "GPA: $it", fontSize = 18.sp) }
    }
}
