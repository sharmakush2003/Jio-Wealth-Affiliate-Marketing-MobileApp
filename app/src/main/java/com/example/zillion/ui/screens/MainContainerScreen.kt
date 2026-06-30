package com.example.zillion.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.zillion.*
import com.example.zillion.ui.screens.tabs.HomeTab
import com.example.zillion.ui.screens.tabs.ShopTab
import com.example.zillion.ui.screens.tabs.ProductsTab
import com.example.zillion.ui.screens.tabs.VouchersTab
import com.example.zillion.ui.screens.tabs.CouponsTab
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainerScreen(
    isLoggedIn: Boolean,
    onNavigate: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = ZillionWhite,
                modifier = Modifier.width(300.dp)
            ) {
                // Drawer Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ZillionGreen)
                        .padding(horizontal = 24.dp, vertical = 32.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .background(ZillionWhite, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile Avatar",
                                tint = ZillionGreen,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (isLoggedIn) "Rajesh Sharma" else "Guest User",
                            color = ZillionWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (isLoggedIn) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Jio Wealth ID: 9401 1614 7488 3606",
                                color = ZillionWhite.copy(alpha = 0.8f),
                                fontSize = 13.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Drawer Options
                NavigationDrawerItem(
                    label = { Text("Profile", fontWeight = FontWeight.SemiBold) },
                    selected = false,
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigate(if (isLoggedIn) Profile else Login)
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Transaction History", fontWeight = FontWeight.SemiBold) },
                    selected = false,
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigate(if (isLoggedIn) TransactionHistory else Login)
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                NavigationDrawerItem(
                    label = { Text("Order History", fontWeight = FontWeight.SemiBold) },
                    selected = false,
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigate(if (isLoggedIn) OrderHistory else Login)
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Help & Support", fontWeight = FontWeight.SemiBold) },
                    selected = false,
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigate(HelpCenter)
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                NavigationDrawerItem(
                    label = { 
                        Text(
                            text = if (isLoggedIn) "Sign Out" else "Sign In", 
                            color = if (isLoggedIn) Color.Red else ZillionGreen, 
                            fontWeight = FontWeight.Bold
                        ) 
                    },
                    selected = false,
                    icon = { 
                        Icon(
                            imageVector = Icons.Default.ExitToApp, 
                            contentDescription = null, 
                            tint = if (isLoggedIn) Color.Red else ZillionGreen
                        ) 
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigate(Login)
                    },
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    ) {
        Scaffold(
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                HomeTab(
                    isLoggedIn = isLoggedIn,
                    onItemClick = onNavigate,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        }
    }
}

@Composable
fun RowScope.BottomBarItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) ZillionGreen else ZillionGray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = if (isSelected) ZillionGreen else ZillionGray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun ScanEarnPlaceholder(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionDark.copy(alpha = 0.9f))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Scan & Earn Scanner",
            color = ZillionWhite,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Simulating Camera View for Code Scans",
            color = ZillionWhite.copy(alpha = 0.7f),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        
        // Draw scanner frame
        Canvas(modifier = Modifier.size(200.dp)) {
            val stroke = Stroke(width = 4.dp.toPx())
            drawRect(color = ZillionActionGreen, style = stroke)
            
            // Scanner red line indicator animation placeholder
            drawLine(
                color = Color.Red,
                start = Offset(0f, size.height / 2f),
                end = Offset(size.width, size.height / 2f),
                strokeWidth = 3.dp.toPx()
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = onClose,
            colors = ButtonDefaults.buttonColors(containerColor = ZillionActionGreen)
        ) {
            Text("CLOSE CAMERA")
        }
    }
}

@Composable
fun SpendNavigationContainer(
    onItemClick: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    // A screen displaying products & vouchers access as spend options
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Redeem your coins",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ZillionDark
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(Products) },
            colors = CardDefaults.cardColors(containerColor = ZillionWhite)
        ) {
            Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("Redeem Products", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.weight(1f))
                Text(">", fontWeight = FontWeight.Bold, color = ZillionGreen)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(Vouchers) },
            colors = CardDefaults.cardColors(containerColor = ZillionWhite)
        ) {
            Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("Redeem Vouchers", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.weight(1f))
                Text(">", fontWeight = FontWeight.Bold, color = ZillionGreen)
            }
        }
    }
}
