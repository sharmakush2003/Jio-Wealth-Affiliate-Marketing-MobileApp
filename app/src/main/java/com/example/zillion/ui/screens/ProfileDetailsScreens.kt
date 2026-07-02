package com.example.zillion.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.zillion.Profile
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite
import com.example.zillion.theme.ZillionLightGreen

// ==========================================
// 1. ACCOUNT DETAILS SCREEN
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        TopAppBar(
            title = { Text("Profile", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // Name
                    ReadOnlyField(label = "Name", value = "Rajesh Sharma")
                    Spacer(modifier = Modifier.height(20.dp))

                    // Mobile Number
                    ReadOnlyField(label = "Mobile Number", value = "8233816674")
                    Spacer(modifier = Modifier.height(20.dp))

                    // Email ID
                    ReadOnlyField(label = "Email ID", value = "rajesh.sharma@jiowealth.in")
                    Spacer(modifier = Modifier.height(20.dp))

                    // Jio Wealth ID
                    ReadOnlyField(label = "Jio Wealth ID", value = "9401 1614 7488 3606")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // EDIT PROFILE button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(1.dp, ZillionGreen, RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ZillionWhite,
                    contentColor = ZillionGreen
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("EDIT PROFILE", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
        }
    }
}

@Composable
fun ReadOnlyField(
    label: String,
    value: String,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 13.sp, color = ZillionGray)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value.ifEmpty { " " },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ZillionDark,
                modifier = Modifier.weight(1f)
            )
            trailingIcon?.invoke()
        }
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
    }
}

// ==========================================
// 2. TRANSACTION HISTORY SCREEN
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionHistoryScreen(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    modifier: Modifier = Modifier
) {
    var selectedSection by remember { mutableStateOf(0) } // 0 = Coins, 1 = Cash
    var selectedFilter by remember { mutableStateOf(0) } // 0=All, 1=Earned, 2=Expired, 3=Redeemed

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        TopAppBar(
            title = { Text("Transaction History", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                if (showBackButton) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Coins/Cash Toggle Bar (Matches screenshot 1000043846)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            color = if (selectedSection == 0) ZillionGreen else Color.Transparent,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { selectedSection = 0 },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Coins",
                        color = if (selectedSection == 0) ZillionWhite else ZillionDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            color = if (selectedSection == 1) ZillionGreen else Color.Transparent,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { selectedSection = 1 },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Cash",
                        color = if (selectedSection == 1) ZillionWhite else ZillionDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Coins Balance Cards row
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("Coins Balance", fontSize = 12.sp, color = ZillionGray)
                        Text("0", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ZillionGreen)
                    }
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(40.dp)
                            .background(Color.LightGray)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("Coins in process", fontSize = 12.sp, color = ZillionGray)
                        Text("0", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ZillionGray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Alert banner
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionLightGreen),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = ZillionGreen,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Your recent transactions are with us and will reflect within 24 hrs.",
                        fontSize = 12.sp,
                        color = ZillionDark,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filter header with Date Selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Filter By",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = ZillionDark,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select Date",
                        tint = ZillionGreen,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "SELECT DATE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZillionGreen
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Segmented buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val filters = listOf("All", "Earned", "Expired", "Redeemed")
                filters.forEachIndexed { index, name ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                color = if (selectedFilter == index) ZillionGreen else Color.Transparent,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable { selectedFilter = index },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = name,
                            color = if (selectedFilter == index) ZillionWhite else ZillionDark,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Transaction list container
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("No Transactions Yet", fontWeight = FontWeight.Bold, color = ZillionGray)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Your activity will appear here", fontSize = 12.sp, color = ZillionGray)
                    }
                }
            }
        }
    }
}

// ==========================================
// 3. ADDRESSES SCREEN
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressesScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        TopAppBar(
            title = { Text("Address", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Address Added",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZillionDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ADD ADDRESS button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ZillionGreen,
                    contentColor = ZillionWhite
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("ADD ADDRESS", fontWeight = FontWeight.Bold, fontSize = 16.sp, letterSpacing = 1.sp)
            }
        }
    }
}

// ==========================================
// 4. ORDER HISTORY SCREEN
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        TopAppBar(
            title = { Text("Order History", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                // Vector graphic placeholder drawing
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val center = Offset(size.width / 2f, size.height / 2f)
                    
                    // Outer green loop (magnifying glass frame)
                    drawCircle(
                        color = ZillionActionGreen,
                        radius = 45.dp.toPx(),
                        center = center,
                        style = Stroke(width = 8.dp.toPx())
                    )
                    // Glass handle
                    drawLine(
                        color = ZillionGreen,
                        start = Offset(center.x - 30.dp.toPx(), center.y + 30.dp.toPx()),
                        end = Offset(center.x - 65.dp.toPx(), center.y + 65.dp.toPx()),
                        strokeWidth = 10.dp.toPx()
                    )
                    
                    // Simple drawing of document in background
                    drawRect(
                        color = Color.LightGray.copy(alpha = 0.5f),
                        topLeft = Offset(center.x - 20.dp.toPx(), center.y - 70.dp.toPx()),
                        size = Size(60.dp.toPx(), 80.dp.toPx())
                    )
                    // Line stripes
                    drawLine(
                        color = ZillionGreen,
                        start = Offset(center.x - 10.dp.toPx(), center.y - 50.dp.toPx()),
                        end = Offset(center.x + 30.dp.toPx(), center.y - 50.dp.toPx()),
                        strokeWidth = 4.dp.toPx()
                    )
                    drawLine(
                        color = ZillionGreen,
                        start = Offset(center.x - 10.dp.toPx(), center.y - 30.dp.toPx()),
                        end = Offset(center.x + 20.dp.toPx(), center.y - 30.dp.toPx()),
                        strokeWidth = 4.dp.toPx()
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "No Data Found",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ZillionDark
            )
        }
    }
}
