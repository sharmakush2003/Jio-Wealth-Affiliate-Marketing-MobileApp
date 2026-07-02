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
import androidx.compose.ui.draw.clip
import com.example.zillion.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun CouponsTab(
    onItemClick: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val coupons = listOf(
        CouponGridData("Amazon", "Up to 10% Off", Color(0xFFFFF3E0)),
        CouponGridData("Flipkart", "Flat 15% Off", Color(0xFFE3F2FD)),
        CouponGridData("Myntra", "Flat ₹200 Off", Color(0xFFFCE4EC)),
        CouponGridData("Nykaa", "Flat 10% Off", Color(0xFFFCE4EC)),
        CouponGridData("Zomato", "Flat 20% Off", Color(0xFFE3F2FD)),
        CouponGridData("Cleartrip", "Flat 10% Off", Color(0xFFFFF3E0)),
        CouponGridData("BookMyShow", "Flat 15% Off", Color(0xFFFCE4EC)),
        CouponGridData("JioMart", "Flat ₹150 Off", Color(0xFFE3F2FD))
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            // Header Bar
            TopAppBar(
                title = { Text("Coupons", fontWeight = FontWeight.Bold) },
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

            // Coupons Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(coupons) { coupon ->
                    CouponDetailCard(coupon = coupon)
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

data class CouponGridData(
    val title: String,
    val discount: String,
    val bgColor: Color
)

@Composable
fun CouponDetailCard(coupon: CouponGridData) {
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
                    .background(coupon.bgColor),
                contentAlignment = Alignment.Center
            ) {
                val logoRes = when {
                    coupon.title.contains("Amazon", ignoreCase = true) -> R.drawable.amazon_logo
                    coupon.title.contains("Flipkart", ignoreCase = true) -> R.drawable.flipkart_logo
                    coupon.title.contains("Myntra", ignoreCase = true) -> R.drawable.myntra_logo
                    coupon.title.contains("Nykaa", ignoreCase = true) -> R.drawable.nykaa_logo
                    coupon.title.contains("Ajio", ignoreCase = true) -> R.drawable.ajio_logo
                    coupon.title.contains("Swiggy", ignoreCase = true) -> R.drawable.swiggy_logo
                    coupon.title.contains("Zomato", ignoreCase = true) -> R.drawable.zomato_logo
                    coupon.title.contains("Cleartrip", ignoreCase = true) -> R.drawable.cleartrip_logo
                    coupon.title.contains("BookMyShow", ignoreCase = true) -> R.drawable.bookmyshow_logo
                    coupon.title.contains("JioMart", ignoreCase = true) -> R.drawable.jiomart_logo
                    else -> null
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (logoRes != null) {
                        Image(
                            painter = painterResource(id = logoRes),
                            contentDescription = coupon.title,
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Text(
                            text = coupon.title.take(1),
                            color = ZillionGreen,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = coupon.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ZillionDark,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = coupon.discount,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F) // red discount text (matches screenshot 1000043844)
                )
            }
        }
    }
}
