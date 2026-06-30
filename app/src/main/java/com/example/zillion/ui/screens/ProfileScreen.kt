package com.example.zillion.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.zillion.*
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onItemClick: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
            .statusBarsPadding()
    ) {
        // Toolbar
        TopAppBar(
            title = { Text("Profile", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { onItemClick(Main) }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // User Header Card (Dark Green, matching screenshot 1000043837)
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionGreen),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Rajesh Sharma",
                        color = ZillionWhite,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Mewar Dhani ID: 9401 1614 7488 3606",
                        color = ZillionWhite.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                }
            }

            // Menu Items List Card (White)
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column {
                    ProfileMenuRow(
                        icon = Icons.Default.Person,
                        title = "Account Details",
                        onClick = { onItemClick(AccountDetails) }
                    )
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))
                    
                    ProfileMenuRow(
                        icon = Icons.Default.List,
                        title = "Transaction History",
                        onClick = { onItemClick(TransactionHistory) }
                    )
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))

                    ProfileMenuRow(
                        icon = Icons.Default.Home,
                        title = "Addresses",
                        onClick = { onItemClick(Addresses) }
                    )
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))

                    ProfileMenuRow(
                        icon = Icons.Default.ShoppingCart,
                        title = "Order History",
                        onClick = { onItemClick(OrderHistory) }
                    )
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))

                    ProfileMenuRow(
                        icon = Icons.Default.Info,
                        title = "Help & Support",
                        onClick = { onItemClick(HelpCenter) }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Footer zillion logo
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Canvas(modifier = Modifier.size(24.dp)) {
                    val path = Path().apply {
                        moveTo(size.width * 0.5f, 0f)
                        lineTo(size.width, size.height * 0.3f)
                        lineTo(size.width * 0.8f, size.height)
                        lineTo(size.width * 0.2f, size.height)
                        lineTo(0f, size.height * 0.3f)
                        close()
                    }
                    drawPath(path = path, color = ZillionActionGreen)
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Mewar Dhani",
                    color = ZillionActionGreen,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.5).sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "App version 116",
                fontSize = 12.sp,
                color = ZillionGray
            )
            Text(
                text = "Terms of Service | Privacy Policy",
                fontSize = 11.sp,
                color = ZillionGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // SIGN OUT button (white button with green border/text)
            Button(
                onClick = { onItemClick(Login) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(1.dp, ZillionActionGreen, RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ZillionWhite,
                    contentColor = ZillionActionGreen
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("SIGN OUT", fontWeight = FontWeight.Bold, fontSize = 15.sp, letterSpacing = 1.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProfileMenuRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = ZillionGreen,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = ZillionDark,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = ZillionGreen,
            modifier = Modifier.size(24.dp)
        )
    }
}
