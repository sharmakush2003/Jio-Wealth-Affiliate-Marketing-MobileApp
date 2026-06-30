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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.zillion.ui.screens.tabs.CoinIcon
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTab(
    onItemClick: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    val shopEarnBrands = listOf(
        BrandShopData("Amazon", "Upto 7", "per ₹100", Color(0xFFFF9900), false),
        BrandShopData("Flipkart", "Upto 200", "per transaction", Color(0xFF2874F0), false),
        BrandShopData("Myntra", "Upto 10", "per ₹100", Color(0xFFF15F22), false),
        BrandShopData("AJIO", "Upto 12", "per ₹100", Color(0xFF002244), false),
        BrandShopData("Jockey", "Upto 500", "per transaction", Color(0xFF000000), false),
        BrandShopData("Cleartrip", "Upto 400", "per transaction", Color(0xFFFF5A5F), false),
        BrandShopData("Dot & Key", "Upto 15%", "on orders", Color(0xFF26C6DA), true),
        BrandShopData("Shyaway", "Upto 8%", "on orders", Color(0xFFEC407A), true)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
            .statusBarsPadding()
    ) {
        // App Tool Bar
        TopAppBar(
            title = {
                Text(
                    text = "Shop & Earn Coins",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ZillionDark
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(shopEarnBrands) { brand ->
                ShopBrandCard(brand = brand)
            }
        }
    }
}

data class BrandShopData(
    val name: String,
    val rate: String,
    val label: String,
    val colorAccent: Color,
    val isNew: Boolean
)

@Composable
fun ShopBrandCard(brand: BrandShopData) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ZillionWhite),
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val logoRes = when (brand.name.lowercase()) {
                    "amazon" -> R.drawable.amazon_logo
                    "flipkart" -> R.drawable.flipkart_logo
                    "myntra" -> R.drawable.myntra_logo
                    "ajio" -> R.drawable.ajio_logo
                    else -> null
                }
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (logoRes == null) brand.colorAccent.copy(alpha = 0.1f) else Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    if (logoRes != null) {
                        Image(
                            painter = painterResource(id = logoRes),
                            contentDescription = brand.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Text(
                            text = brand.name.take(2).uppercase(),
                            color = brand.colorAccent,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = brand.rate,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        color = ZillionDark
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    CoinIcon(size = 12.dp)
                }
                Text(
                    text = brand.label,
                    fontSize = 10.sp,
                    color = ZillionGray,
                    textAlign = TextAlign.Center
                )
            }

            if (brand.isNew) {
                Box(
                    modifier = Modifier
                        .background(
                            Color.Red,
                            RoundedCornerShape(topStart = 0.dp, bottomStart = 8.dp, topEnd = 12.dp, bottomEnd = 0.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = "NEW",
                        color = ZillionWhite,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}
