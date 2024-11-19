package com.example.tecnonic_compose.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tecnonic_compose.MainActivity
import com.example.tecnonic_compose.R
import com.example.tecnonic_compose.ui.components.Header
import com.example.tecnonic_compose.ui.components.RoundedCheckbox

data class Language(
    val name: String,
    val flag: Int, // ID del recurso de la bandera
    var isSelected: Boolean = false
)

@Composable
fun LanguageScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current
    // Lista de ejemplo de idiomas
    var languages by remember {
        mutableStateOf(
            listOf(
                Language("Español", R.drawable.ic_spain_flag),
                Language("English", R.drawable.ic_usa_flag),
                Language("Français", R.drawable.ic_france_flag),
                Language("Australia", R.drawable.ic_australia_flag),
                Language("Vietnam", R.drawable.ic_vietnam_flag),

            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Header(
            title = "Lenguaje",
            onBackPressed = {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("setting", "back")
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de búsqueda
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            placeholder = { Text("Buscar") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar"
                )
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de idiomas
        LazyColumn {
            val filteredLanguages = languages.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }

            items(filteredLanguages) { language ->
                LanguageItem(
                    language = language,
                    onLanguageSelected = { selected ->
                        languages = languages.map {
                            if (it.name == language.name) it.copy(isSelected = selected)
                            else it
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun LanguageItem(
    language: Language,
    onLanguageSelected: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = language.flag),
            contentDescription = "Bandera de ${language.name}",
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Nombre del idioma
        Text(
            text = language.name,
            modifier = Modifier.weight(1f)
        )

        // Checkbox
        Checkbox(
            checked = language.isSelected,
            onCheckedChange = { onLanguageSelected(it) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LanguageScreenPreview() {
    LanguageScreen  ()
}
