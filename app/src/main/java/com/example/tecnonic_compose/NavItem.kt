package com.example.tecnonic_compose

import androidx.compose.ui.graphics.painter.Painter

data class NavItem(
    val label: String,
    val icon: Painter,
    val badgeCount: Int,
)