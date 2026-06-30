package com.example.zillion.ui.screens.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.zillion.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite
import com.example.zillion.theme.ZillionLightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VouchersTab(
    onItemClick: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val vouchers = listOf(
        VoucherGridData("Amazon Pay Gift Card", "1.5% OFF", Color(0xFFFFE0B2)),
        VoucherGridData("Flipkart Voucher", "1.5% OFF", Color(0xFFBBDEFB)),
        VoucherGridData("Amazon Shopping Voucher", "2.25% OFF", Color(0xFFFFF9C4)),
        VoucherGridData("HP Pay", "0.75% OFF", Color(0xFFD1C4E9)),
        VoucherGridData("Myntra", "4% OFF", Color(0xFFF8BBD0)),
        VoucherGridData("Swiggy", "4% OFF", Color(0xFFFFCCBC))
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            // Header Bar
            TopAppBar(
                title = { Text("Vouchers", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { onItemClick(com.example.zillion.Main) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
            )

            // Available Coins Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ZillionLightGreen)
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Available Coins",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ZillionDark,
                    modifier = Modifier.weight(1f)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CoinIcon(size = 14.dp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "0",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZillionGreen
                    )
                }
            }

            // Vouchers Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(vouchers) { voucher ->
                    VoucherDetailCard(voucher = voucher)
                }
            }
        }

        // Floating Sort Pill Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .background(ZillionGreen, RoundedCornerShape(24.dp))
                .clickable { showBottomSheet = true }
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "T Filters",
                    color = ZillionWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(12.dp))
                VerticalDivider(color = ZillionWhite.copy(alpha = 0.3f), modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "= Sort by",
                    color = ZillionWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                containerColor = ZillionWhite
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .navigationBarsPadding()
                ) {
                    Text(
                        text = "Sort by",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZillionDark
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    val options = listOf("Popularity", "Discount: Low to High", "Discount: High to Low", "New Arrivals")
                    var selectedOption by remember { mutableStateOf("Popularity") }

                    options.forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedOption = option }
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (selectedOption == option),
                                onClick = { selectedOption = option },
                                colors = RadioButtonDefaults.colors(selectedColor = ZillionGreen)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = option, fontSize = 16.sp, color = ZillionDark)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { showBottomSheet = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ZillionGreen),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("APPLY", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = ZillionWhite)
                    }
                }
            }
        }
    }
}

data class VoucherGridData(
    val title: String,
    val discount: String,
    val bgColor: Color
)

@Composable
fun VoucherDetailCard(voucher: VoucherGridData) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ZillionWhite),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(voucher.bgColor),
                contentAlignment = Alignment.Center
            ) {
                val logoRes = when {
                    voucher.title.contains("Amazon", ignoreCase = true) -> R.drawable.amazon_logo
                    voucher.title.contains("Flipkart", ignoreCase = true) -> R.drawable.flipkart_logo
                    voucher.title.contains("Myntra", ignoreCase = true) -> R.drawable.myntra_logo
                    voucher.title.contains("Swiggy", ignoreCase = true) -> R.drawable.swiggy_logo
                    else -> null
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (logoRes != null) {
                        Image(
                            painter = painterResource(id = logoRes),
                            contentDescription = voucher.title,
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Text(
                            text = voucher.title.take(1),
                            color = ZillionGreen,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Discount banner tag bottom-left of the image box (matches screenshot 1000043843)
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(
                            Color(0xFFFFC72C),
                            RoundedCornerShape(topStart = 0.dp, bottomStart = 0.dp, topEnd = 16.dp, bottomEnd = 0.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = voucher.discount,
                        color = ZillionDark,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = voucher.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ZillionDark,
                    maxLines = 1
                )
            }
        }
    }
}
