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
    val coupons = listOf(
        CouponGridData("Bloom by Bold Care", "0% Discount", Color(0xFFE8F5E9)),
        CouponGridData("Bombay Shaving...", "0% Discount", Color(0xFFECEFF1)),
        CouponGridData("Assembly Travel", "0% Discount", Color(0xFFE0F2F1)),
        CouponGridData("Deyga Natural", "0% Discount", Color(0xFFFFF3E0)),
        CouponGridData("Earth Rhythm", "0% Discount", Color(0xFFF1F8E9)),
        CouponGridData("Vahdam Teas", "0% Discount", Color(0xFFE8EAF6))
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
                    .padding(horizontal = 16.dp, vertical = 12.dp),
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
                contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 80.dp),
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
                .clickable { }
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
                val logoRes = if (coupon.title.contains("Bombay", ignoreCase = true)) R.drawable.bombay_shaving_logo else null
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
