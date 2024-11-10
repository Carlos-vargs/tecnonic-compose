package com.example.tecnonic_compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.tecnonic_compose.pages.HomePage
import com.example.tecnonic_compose.pages.IndicatorPage
import com.example.tecnonic_compose.pages.SettingsPage
import com.example.tecnonic_compose.pages.Statistics

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navItemList = listOf(
        NavItem("Inicio", painterResource(id = R.drawable.ic_iconhome), 0),
        NavItem("Indicadores", painterResource(id = R.drawable.ic_indicators), 0),
        NavItem("Estadisticas", painterResource(id = R.drawable.ic_statistics), 0),
        NavItem("Ajuste", painterResource(id = R.drawable.ic_settings), 0)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            BadgedBox(badge = {
                                if (navItem.badgeCount > 0)
                                    Badge {
                                        Text(text = navItem.badgeCount.toString())
                                    }
                            }) {
                                Icon(
                                    painter = navItem.icon,
                                    contentDescription = navItem.label,
                                    modifier = Modifier.size(23.dp),
                                    tint = if (selectedIndex == index) Color.Blue else Color.Gray // Change icon color
                                )
                            }
                        },
                        label = {
                            Text(
                                text = navItem.label,
                                fontSize = 12.sp,  // Set smaller font size
                                color = if (selectedIndex == index) Color.Blue else Color.Gray // Change text color
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent // Remove background indicator color
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int) {
    when (selectedIndex) {
        0 -> HomePage()
        1 -> IndicatorPage()
        2 -> Statistics()
        3 -> SettingsPage()
    }
}
