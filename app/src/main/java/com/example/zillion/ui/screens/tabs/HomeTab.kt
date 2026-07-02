package com.example.zillion.ui.screens.tabs

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.zillion.*
import com.example.zillion.R
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite
import com.example.zillion.theme.ZillionGold
import com.example.zillion.theme.ZillionLightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTab(
    isLoggedIn: Boolean,
    onItemClick: (NavKey) -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
            .verticalScroll(rememberScrollState())
    ) {
        val scope = rememberCoroutineScope()
        var showBalanceDialog by remember { mutableStateOf(false) }
        var pinValue by remember { mutableStateOf("") }
        var pinSubmitted by remember { mutableStateOf(false) }
        var isCheckingBalance by remember { mutableStateOf(false) }


        // UPI PIN / Balance Check Simulation
        if (showBalanceDialog) {
            AlertDialog(
                onDismissRequest = {
                    showBalanceDialog = false
                    pinValue = ""
                    pinSubmitted = false
                },
                title = {
                    Text(
                        text = if (pinSubmitted) "Account Balance" else "Enter UPI PIN",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = ZillionDark
                    )
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (isCheckingBalance) {
                            CircularProgressIndicator(color = ZillionGreen)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Fetching balance securely...", color = ZillionGray)
                        } else if (pinSubmitted) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Success",
                                tint = ZillionActionGreen,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Jio Wealth Wallet Balance",
                                fontSize = 14.sp,
                                color = ZillionGray
                            )
                            Text(
                                text = "2,450 Coins",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = ZillionDark
                            )
                            Text(
                                text = "Approx Value: ₹612.50",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = ZillionGreen
                            )
                        } else {
                            Text(
                                text = "Enter 4-digit UPI PIN to fetch balance for Jio Wealth account ending in 3606",
                                fontSize = 13.sp,
                                color = ZillionGray,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                repeat(4) { index ->
                                    val char = pinValue.getOrNull(index)
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .border(2.dp, ZillionGreen, RoundedCornerShape(8.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (char != null) {
                                            Box(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(ZillionDark, CircleShape)
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            val rows = listOf(
                                listOf("1", "2", "3"),
                                listOf("4", "5", "6"),
                                listOf("7", "8", "9"),
                                listOf("Clear", "0", "OK")
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                rows.forEach { row ->
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        row.forEach { digit ->
                                            Button(
                                                onClick = {
                                                    if (digit == "Clear") {
                                                        pinValue = ""
                                                    } else if (digit == "OK") {
                                                        if (pinValue.length == 4) {
                                                            scope.launch {
                                                                isCheckingBalance = true
                                                                delay(1500)
                                                                isCheckingBalance = false
                                                                pinSubmitted = true
                                                            }
                                                        }
                                                    } else {
                                                        if (pinValue.length < 4) {
                                                            pinValue += digit
                                                        }
                                                    }
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = if (digit == "OK") ZillionGreen else Color(0xFFF0F0F0),
                                                    contentColor = if (digit == "OK") ZillionWhite else ZillionDark
                                                ),
                                                shape = RoundedCornerShape(8.dp),
                                                modifier = Modifier.size(width = 68.dp, height = 40.dp),
                                                contentPadding = PaddingValues(0.dp)
                                            ) {
                                                Text(digit, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    if (pinSubmitted) {
                        TextButton(
                            onClick = {
                                showBalanceDialog = false
                                pinValue = ""
                                pinSubmitted = false
                            }
                        ) {
                            Text("DONE", color = ZillionGreen, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(ZillionGreen)
                .padding(bottom = 16.dp)
        ) {
            Column {
                // PhonePe Styled Top Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onMenuClick,
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White.copy(alpha = 0.15f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile icon",
                            tint = ZillionWhite,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Home",
                                color = ZillionWhite,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown",
                                tint = ZillionWhite,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = "Chittorgarh, Rajasthan",
                            color = ZillionWhite.copy(alpha = 0.8f),
                            fontSize = 11.sp,
                            maxLines = 1
                        )
                    }
                    
                    IconButton(
                        onClick = { /* Handle Notifications */ },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = ZillionWhite,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { onItemClick(HelpCenter) },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.HelpOutline,
                            contentDescription = "Help Support",
                            tint = ZillionWhite,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // High-End Luxury Wallet Card (Purple themed to match PhonePe)
                val cardBrush = Brush.linearGradient(
                    colors = listOf(Color(0xFF5F259F), Color(0xFF8338EC))
                )
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(90.dp)
                        .clickable { showBalanceDialog = true }
                        .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(cardBrush)
                            .padding(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(ZillionWhite.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                                .align(Alignment.TopEnd)
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Coins Balance",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = ZillionWhite.copy(alpha = 0.8f)
                                )
                                 Text(
                                     text = "JIO WEALTH",
                                     fontSize = 11.sp,
                                     fontWeight = FontWeight.ExtraBold,
                                     color = ZillionGold,
                                     letterSpacing = 1.5.sp
                                 )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                     Image(
                                         painter = painterResource(id = R.drawable.app_logo),
                                         contentDescription = "Jio Wealth Logo",
                                         modifier = Modifier
                                             .size(24.dp)
                                             .clip(RoundedCornerShape(6.dp)),
                                         contentScale = ContentScale.Crop
                                     )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "2,450",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = ZillionWhite
                                    )
                                }
                                Text(
                                    text = "Value: ₹612.50",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ZillionWhite
                                )
                            }
                        }
                    }
                }
            }
        }


        // Banners Carousel
        Spacer(modifier = Modifier.height(16.dp))
        PromoBannerSlider()


        // Shop & Earn Coins Section
        Spacer(modifier = Modifier.height(16.dp))
        SectionHeader(title = "Shop & Earn Coins", onActionClick = { onItemClick(ShopEarnGrid) })
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val brands = listOf(
                BrandEarnData("Amazon", "Upto 7", "per ₹100", Color(0xFFFF9900)),
                BrandEarnData("Flipkart", "Upto 200", "per transaction", Color(0xFF2874F0)),
                BrandEarnData("Myntra", "Upto 10", "per ₹100", Color(0xFFF15F22))
            )
            items(brands) { brand ->
                BrandEarnCard(brand = brand, onClick = { onItemClick(ShopEarnGrid) })
            }
        }

        // Loan Inquiry Section
        Spacer(modifier = Modifier.height(16.dp))
        SectionHeader(title = "Loan Inquiry", onActionClick = { onItemClick(HelpCenter) })
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val loanBrands = listOf(
                BrandEarnData("SBI Loan", "8.4% Avg", "Home/Car Loan", Color(0xFF0054A6)),
                BrandEarnData("PNB Loan", "8.7% Avg", "Personal Loan", Color(0xFF8F1E1D)),
                BrandEarnData("HDFC Loan", "8.5% Avg", "All Loans", Color(0xFF1C3F94)),
                BrandEarnData("ICICI Loan", "8.65% Avg", "Business Loan", Color(0xFFE26E26))
            )
            items(loanBrands) { brand ->
                BrandEarnCard(
                    brand = brand,
                    onClick = {
                        val colorHex = "#" + String.format("%08x", brand.colorAccent.value.toLong()).takeLast(8)
                        onItemClick(InquiryDetails(brand.name, brand.rate, brand.label, colorHex))
                    }
                )
            }
        }

        // Insurance Inquiry Section
        Spacer(modifier = Modifier.height(16.dp))
        SectionHeader(title = "Insurance Inquiry", onActionClick = { onItemClick(HelpCenter) })
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val insuranceBrands = listOf(
                BrandEarnData("LIC", "Term Plan", "Best Premium", Color(0xFF00539B)),
                BrandEarnData("HDFC Life", "Life Cover", "Upto ₹1 Crore", Color(0xFF003087)),
                BrandEarnData("Sunlife", "Health Cover", "Critical Illness", Color(0xFFD32F2F)),
                BrandEarnData("ICICI Pru", "Investment", "ULIP/Retirement", Color(0xFFE26E26))
            )
            items(insuranceBrands) { brand ->
                BrandEarnCard(
                    brand = brand,
                    onClick = {
                        val colorHex = "#" + String.format("%08x", brand.colorAccent.value.toLong()).takeLast(8)
                        onItemClick(InquiryDetails(brand.name, brand.rate, brand.label, colorHex))
                    }
                )
            }
        }

        // Credit Card Inquiry Section
        Spacer(modifier = Modifier.height(16.dp))
        SectionHeader(title = "Credit Card Inquiry", onActionClick = { onItemClick(HelpCenter) })
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val cardBrands = listOf(
                BrandEarnData("HDFC Card", "Regalia Gold", "Premium Lounge", Color(0xFF1C3F94)),
                BrandEarnData("SBI Card", "SimplyClick", "Upto 10x Rewards", Color(0xFF0054A6)),
                BrandEarnData("ICICI Card", "Coral Card", "Fuel/Dining", Color(0xFFE26E26)),
                BrandEarnData("Axis Card", "Neo Card", "10% Discount", Color(0xFF9E1F63))
            )
            items(cardBrands) { brand ->
                BrandEarnCard(
                    brand = brand,
                    onClick = {
                        val colorHex = "#" + String.format("%08x", brand.colorAccent.value.toLong()).takeLast(8)
                        onItemClick(InquiryDetails(brand.name, brand.rate, brand.label, colorHex))
                    }
                )
            }
        }

        // Coupons Section
        Spacer(modifier = Modifier.height(16.dp))
        SectionHeader(title = "Coupons", onActionClick = { onItemClick(Coupons) }, badgeText = "New")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val coupons = listOf(
                CouponData("Amazon Pay Gift Card", "Up to 10% Off", Color(0xFFFF9900)),
                CouponData("Amazon Shopping Gift Card", "Flat ₹150 Off", Color(0xFFFF9900)),
                CouponData("Flipkart Gift Card", "Flat 10% Off", Color(0xFF2874F0))
            )
            items(coupons) { coupon ->
                CouponCard(coupon = coupon, onClick = { onItemClick(Coupons) })
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

// Helpers & Sub-composables
@Composable
fun CoinIcon(size: androidx.compose.ui.unit.Dp, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(size)) {
        val canvasSize = this.size
        val radius = canvasSize.minDimension / 2f
        val center = Offset(canvasSize.width / 2f, canvasSize.height / 2f)

        // Radial gold gradient for coin base
        val goldGradient = Brush.radialGradient(
            colors = listOf(Color(0xFFFFEA7A), Color(0xFFF59E0B), Color(0xFFB45309)),
            center = center,
            radius = radius
        )

        // Draw outer coin face
        drawCircle(
            brush = goldGradient,
            radius = radius,
            center = center
        )

        // Draw darker gold rim edge
        drawCircle(
            color = Color(0xFF92400E),
            radius = radius,
            center = center,
            style = Stroke(width = radius * 0.08f)
        )

        // Draw inner coin lip/rim inset
        drawCircle(
            color = Color(0xFFFFF59D).copy(alpha = 0.5f),
            radius = radius * 0.82f,
            center = center,
            style = Stroke(width = radius * 0.05f)
        )

        // Draw center gold fill
        drawCircle(
            color = Color(0xFFD97706),
            radius = radius * 0.65f,
            center = center
        )

        // Draw elegant golden star/spark in center representing Jio Wealth rewards
        val innerRadius = radius * 0.45f
        val path = Path().apply {
            moveTo(center.x, center.y - innerRadius)
            lineTo(center.x + innerRadius * 0.25f, center.y - innerRadius * 0.25f)
            lineTo(center.x + innerRadius, center.y)
            lineTo(center.x + innerRadius * 0.25f, center.y + innerRadius * 0.25f)
            lineTo(center.x, center.y + innerRadius)
            lineTo(center.x - innerRadius * 0.25f, center.y + innerRadius * 0.25f)
            lineTo(center.x - innerRadius, center.y)
            lineTo(center.x - innerRadius * 0.25f, center.y - innerRadius * 0.25f)
            close()
        }
        drawPath(path = path, color = Color(0xFFFFF9C4))
    }
}

@Composable
fun SectionHeader(
    title: String,
    onActionClick: () -> Unit,
    badgeText: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = ZillionDark
        )
        if (badgeText != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFFE64A19), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(badgeText, color = ZillionWhite, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "VIEW ALL >",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = ZillionGreen,
            modifier = Modifier.clickable(onClick = onActionClick)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromoBannerSlider() {
    val banners = listOf(
        R.drawable.banner_promo1,
        R.drawable.banner_promo2,
        R.drawable.banner_promo3,
        R.drawable.banner_promo4
    )
    val pagerState = rememberPagerState(pageCount = { banners.size })

    // Auto-scroll marquee effect (rotates every 3 seconds)
    LaunchedEffect(key1 = pagerState) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = ZillionWhite)
            ) {
                Image(
                    painter = painterResource(id = banners[page]),
                    contentDescription = "Promo Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Dot indicators
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(banners.size) { index ->
                val color = if (pagerState.currentPage == index) ZillionGreen else ZillionGray.copy(alpha = 0.5f)
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(color, CircleShape)
                )
            }
        }
    }
}

data class BrandEarnData(val name: String, val rate: String, val label: String, val colorAccent: Color)

@Composable
fun BrandEarnCard(brand: BrandEarnData, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ZillionWhite),
        modifier = Modifier
            .width(130.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val logoRes = when {
                brand.name.lowercase().contains("amazon") -> R.drawable.amazon_logo
                brand.name.lowercase().contains("flipkart") -> R.drawable.flipkart_logo
                brand.name.lowercase().contains("myntra") -> R.drawable.myntra_logo
                brand.name.lowercase().contains("sbi") -> R.drawable.sbi_logo
                brand.name.lowercase().contains("pnb") -> R.drawable.pnb_logo
                brand.name.lowercase().contains("hdfc") -> R.drawable.hdfc_logo
                else -> null
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (logoRes == null) brand.colorAccent.copy(alpha = 0.1f) else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                if (logoRes != null) {
                    Image(
                        painter = painterResource(id = logoRes),
                        contentDescription = brand.name,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text(
                        text = brand.name.take(2).uppercase(),
                        color = brand.colorAccent,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(brand.rate, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ZillionDark)
                Spacer(modifier = Modifier.width(3.dp))
                CoinIcon(size = 11.dp)
            }
            Text(brand.label, fontSize = 10.sp, color = ZillionGray, textAlign = TextAlign.Center)
        }
    }
}


data class CouponData(val name: String, val discount: String, val bg: Color)

@Composable
fun CouponCard(coupon: CouponData, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ZillionWhite),
        modifier = Modifier
            .width(130.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val logoRes = when {
                coupon.name.contains("Amazon", ignoreCase = true) -> R.drawable.amazon_logo
                coupon.name.contains("Flipkart", ignoreCase = true) -> R.drawable.flipkart_logo
                else -> null
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (logoRes == null) coupon.bg.copy(alpha = 0.1f) else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                if (logoRes != null) {
                    Image(
                        painter = painterResource(id = logoRes),
                        contentDescription = coupon.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Text(
                        text = coupon.name.take(2).uppercase(),
                        color = coupon.bg,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = coupon.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ZillionDark,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = coupon.discount,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD32F2F),
                textAlign = TextAlign.Center
            )
        }
    }
}

data class VoucherData(val title: String, val brand: String, val bg: Color)

@Composable
fun PopularVoucherCard(voucher: VoucherData, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ZillionWhite),
        modifier = Modifier
            .width(140.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            val logoRes = when {
                voucher.brand.contains("Amazon", ignoreCase = true) -> R.drawable.amazon_logo
                voucher.brand.contains("Flipkart", ignoreCase = true) -> R.drawable.flipkart_logo
                else -> null
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (logoRes == null) voucher.bg else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                if (logoRes != null) {
                    Image(
                        painter = painterResource(id = logoRes),
                        contentDescription = voucher.brand,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Text(
                        text = voucher.title,
                        color = ZillionGreen,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = voucher.brand, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ZillionDark)
        }
    }
}

data class ProductData(val title: String, val price: String, val bg: Color)

@Composable
fun ProductCard(product: ProductData, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ZillionWhite),
        modifier = Modifier
            .width(140.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            val imgRes = when {
                product.title.contains("Usha", ignoreCase = true) -> R.drawable.product_iron
                product.title.contains("Mixer", ignoreCase = true) -> R.drawable.product_mixer
                else -> null
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (imgRes == null) product.bg else Color.Transparent),
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
                            .background(ZillionGreen.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.title, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = ZillionDark)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = product.price, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ZillionGreen)
        }
    }
}


@Composable
fun UtilityPayItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .width(72.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFF5F2F9), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = ZillionGreen,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = ZillionDark,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

