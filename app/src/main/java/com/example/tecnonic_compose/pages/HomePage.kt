package com.example.tecnonic_compose.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.R

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp),
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.widthIn(max = 400.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            item {
                HeaderSection()
                Spacer(modifier = Modifier.height(24.dp))
                SummarySection()
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                StatisticsImagesSection()
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                NewClientsSection()
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(start = 30.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray, CircleShape)
                    .padding(2.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "Bienvenido",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "Tanya Myroniuk",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun SummarySection() {
    Text(
        text = "Resumen",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier.padding(start = 30.dp),
        )

    Text(
        text = "Mostrar: Este Año ⌄",
        color = Color.Gray,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 30.dp)
        )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SummaryCard("En Ruta", "246", "-16%", Color.Red)
        SummaryCard("Pendiente", "254", "+10%", Color.Green)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SummaryCard("Cancelado", "252", "-16%", Color.Red)
        SummaryCard("Entregado", "248", "+5%", Color.Green)
    }
}

@Composable
fun StatisticsImagesSection() {
    Image(
        painter = painterResource(id = R.drawable.ic_salesfigure),
        contentDescription = "Imagen 1",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Image(
        painter = painterResource(id = R.drawable.ic_figuretwo),
        contentDescription = "Imagen 2",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Image(
        painter = painterResource(id = R.drawable.ic_figurethree),
        contentDescription = "Imagen 3",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
    )
}

@Composable
fun NewClientsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 28.dp, bottom = 160.dp)
    ) {
        Text(
            text = "Nuevos Clientes",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 300.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(clients) { client ->
                ClientItem(client)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "VER TODOS LOS CLIENTES",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun SummaryCard(title: String, value: String, percentage: String, percentageColor: Color) {
    Column(
        modifier = Modifier
            .width(180.dp)
            .padding(start = 30.dp, end = 27.dp)
            .padding(vertical = 8.dp),
    ) {
        Text(
            text = title,
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = percentage,
                color = percentageColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Text(
            text = "Comparado con\n(${getComparisonText(title)})",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ClientItem(client: Client) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = client.profileImage),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray, CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = client.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = client.id,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_email),
            contentDescription = "Enviar mensaje",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}

data class Client(val name: String, val id: String, val profileImage: Int)

val clients = listOf(
    Client("Francis Holzworth", "Cliente ID#00222", R.drawable.ic_profile),
    Client("Kaylyn Yokel", "Cliente ID#00222", R.drawable.ic_profile),
    Client("Kimberly Muro", "Cliente ID#00222", R.drawable.ic_profile),
    Client("Jack Sause", "Cliente ID#00222", R.drawable.ic_profile),
    Client("Rebekkah Lafantano", "Cliente ID#00222", R.drawable.ic_profile)
)

fun getComparisonText(title: String): String {
    return when (title) {
        "En Ruta" -> "267 el mes pasado"
        "Pendiente" -> "230 el mes pasado"
        "Cancelado" -> "300 el mes pasado"
        "Entregado" -> "236 el mes pasado"
        else -> ""
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    HomePage()
}
