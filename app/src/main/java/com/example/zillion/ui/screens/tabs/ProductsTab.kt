package com.example.zillion.ui.screens.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.zillion.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite
import com.example.zillion.theme.ZillionLightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsTab(
    onItemClick: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val products = listOf(
        ProductDetailData("Usha El 2801 LT...", "The ultra-lightweight...", "3,798 coins", Color(0xFFE0E0E0)),
        ProductDetailData("High Speed Hand...", "Hand mixers are powerf...", "9,321 coins", Color(0xFFCFD8DC)),
        ProductDetailData("Element 1010...", "Description Perfect for...", "2,144 coins", Color(0xFFECEFF1)),
        ProductDetailData("Philips HR1855/70...", "Description Perfect for...", "8,432 coins", Color(0xFFD7CCC8))
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            // Header Bar
            TopAppBar(
                title = { Text("Products", fontWeight = FontWeight.Bold) },
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

            // Chips row
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    FilterChip(
                        selected = true,
                        onClick = {},
                        label = { Text("✓ Top selling") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ZillionWhite,
                            selectedLabelColor = ZillionDark
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = false,
                        onClick = {},
                        label = { Text("Home & Kitchen") }
                    )
                }
                item {
                    FilterChip(
                        selected = false,
                        onClick = {},
                        label = { Text("Electronics") }
                    )
                }
            }

            // Products List
            LazyColumn(
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(products) { product ->
                    ProductDetailRow(product = product)
                }
            }
        }

        // Floating Filter Pill Button (Matches screenshot 1000043842)
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

                    val options = listOf("Popularity", "Coins: Low to High", "Coins: High to Low", "New Arrivals")
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

data class ProductDetailData(
    val title: String,
    val description: String,
    val coins: String,
    val placeholderColor: Color
)

@Composable
fun ProductDetailRow(product: ProductDetailData) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ZillionWhite),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image Block
            val imgRes = when {
                product.title.contains("Usha", ignoreCase = true) -> R.drawable.product_iron
                product.title.contains("Mixer", ignoreCase = true) -> R.drawable.product_mixer
                product.title.contains("Element", ignoreCase = true) -> R.drawable.product_bottle
                product.title.contains("Philips", ignoreCase = true) -> R.drawable.product_juicer
                else -> null
            }
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (imgRes == null) product.placeholderColor else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                if (imgRes != null) {
                    Image(
                        painter = painterResource(id = imgRes),
                        contentDescription = product.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(ZillionGreen.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ZillionDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    fontSize = 12.sp,
                    color = ZillionGray
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CoinIcon(size = 12.dp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = product.coins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZillionGreen
                    )
                }
            }
        }
    }
}
