package com.example.zillion.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import com.example.zillion.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite
import com.example.zillion.theme.ZillionGold
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionWhite)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Logo
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Jio Wealth Logo",
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Jio Wealth",
                color = ZillionActionGreen,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Onboarding Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageContent(page = page)
        }

        // Indicators
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            repeat(3) { index ->
                val active = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(10.dp)
                        .background(
                            color = if (active) ZillionActionGreen else Color.LightGray,
                            shape = CircleShape
                        )
                )
            }
        }

        // Action Button
        Button(
            onClick = {
                if (pagerState.currentPage < 2) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    onFinished()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ZillionActionGreen,
                contentColor = ZillionWhite
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "GET STARTED",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun OnboardingPageContent(page: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Custom graphic illustration based on slide
        Box(
            modifier = Modifier
                .size(260.dp)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            when (page) {
                0 -> Slide1Illustration()
                1 -> Slide2Illustration()
                2 -> Slide3Illustration()
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        val title = when (page) {
            0 -> "Offers & Vouchers"
            1 -> "Earn Assured Coins"
            else -> "Redeem Your Coins"
        }
        val subtitle = when (page) {
            0 -> "trending for you"
            1 -> "for every transaction"
            else -> "on any to every product"
        }

        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ZillionDark,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subtitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = ZillionGray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Slide1Illustration() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width / 2f, size.height / 2f)
        
        // Draw decorative backgrounds (spheres, shadows)
        drawCircle(
            color = Color(0xFFF0E5FF),
            radius = 110.dp.toPx(),
            center = center
        )
        
        // Draw purple vouchers stacked
        val purpleBrush = Brush.linearGradient(
            colors = listOf(Color(0xFF8C52FF), Color(0xFF5E17EB))
        )
        
        // Back voucher (tilted)
        drawRoundRect(
            brush = purpleBrush,
            topLeft = Offset(center.x - 70.dp.toPx(), center.y - 80.dp.toPx()),
            size = Size(100.dp.toPx(), 140.dp.toPx()),
            cornerRadius = CornerRadius(12.dp.toPx()),
            alpha = 0.7f
        )
        
        // Front voucher
        drawRoundRect(
            brush = purpleBrush,
            topLeft = Offset(center.x - 50.dp.toPx(), center.y - 60.dp.toPx()),
            size = Size(110.dp.toPx(), 150.dp.toPx()),
            cornerRadius = CornerRadius(12.dp.toPx())
        )
        
        // Draw percentage symbol on the front voucher
        val strokeWidth = 8.dp.toPx()
        // Draw diagonal line
        drawLine(
            color = ZillionWhite,
            start = Offset(center.x + 20.dp.toPx(), center.y - 30.dp.toPx()),
            end = Offset(center.x - 20.dp.toPx(), center.y + 50.dp.toPx()),
            strokeWidth = strokeWidth
        )
        // Top-left circle
        drawCircle(
            color = ZillionWhite,
            radius = 12.dp.toPx(),
            center = Offset(center.x - 15.dp.toPx(), center.y - 15.dp.toPx()),
            style = Stroke(width = 5.dp.toPx())
        )
        // Bottom-right circle
        drawCircle(
            color = ZillionWhite,
            radius = 12.dp.toPx(),
            center = Offset(center.x + 15.dp.toPx(), center.y + 35.dp.toPx()),
            style = Stroke(width = 5.dp.toPx())
        )

        // Draw green gifts on the sides
        val greenBrush = Brush.linearGradient(
            colors = listOf(ZillionActionGreen, ZillionGreen)
        )
        // Small gift bottom-left
        drawRoundRect(
            brush = greenBrush,
            topLeft = Offset(center.x - 90.dp.toPx(), center.y + 40.dp.toPx()),
            size = Size(40.dp.toPx(), 40.dp.toPx()),
            cornerRadius = CornerRadius(4.dp.toPx())
        )
        // Gift ribbon
        drawLine(
            color = ZillionWhite,
            start = Offset(center.x - 70.dp.toPx(), center.y + 40.dp.toPx()),
            end = Offset(center.x - 70.dp.toPx(), center.y + 80.dp.toPx()),
            strokeWidth = 3.dp.toPx()
        )
        drawLine(
            color = ZillionWhite,
            start = Offset(center.x - 90.dp.toPx(), center.y + 60.dp.toPx()),
            end = Offset(center.x - 50.dp.toPx(), center.y + 60.dp.toPx()),
            strokeWidth = 3.dp.toPx()
        )

        // Small gift top-right
        drawRoundRect(
            brush = greenBrush,
            topLeft = Offset(center.x + 50.dp.toPx(), center.y - 80.dp.toPx()),
            size = Size(45.dp.toPx(), 45.dp.toPx()),
            cornerRadius = CornerRadius(4.dp.toPx())
        )
        // Gift ribbon
        drawLine(
            color = ZillionWhite,
            start = Offset(center.x + 72.5f.dp.toPx(), center.y - 80.dp.toPx()),
            end = Offset(center.x + 72.5f.dp.toPx(), center.y - 35.dp.toPx()),
            strokeWidth = 3.dp.toPx()
        )
        drawLine(
            color = ZillionWhite,
            start = Offset(center.x + 50.dp.toPx(), center.y - 57.5f.dp.toPx()),
            end = Offset(center.x + 95.dp.toPx(), center.y - 57.5f.dp.toPx()),
            strokeWidth = 3.dp.toPx()
        )
    }
}

@Composable
fun Slide2Illustration() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width / 2f, size.height / 2f)
        
        // Background soft circle
        drawCircle(
            color = Color(0xFFF5EEFF),
            radius = 110.dp.toPx(),
            center = center
        )

        // Draw money bag outline
        val bagBrush = Brush.linearGradient(
            colors = listOf(Color(0xFF8C52FF), Color(0xFF5E17EB))
        )
        
        // Bag Base
        drawCircle(
            brush = bagBrush,
            radius = 65.dp.toPx(),
            center = Offset(center.x, center.y + 20.dp.toPx())
        )
        
        // Bag Neck (flared top)
        val path = Path().apply {
            moveTo(center.x - 40.dp.toPx(), center.y - 40.dp.toPx())
            quadraticTo(
                center.x - 50.dp.toPx(), center.y - 65.dp.toPx(),
                center.x - 30.dp.toPx(), center.y - 70.dp.toPx()
            )
            lineTo(center.x + 30.dp.toPx(), center.y - 70.dp.toPx())
            quadraticTo(
                center.x + 50.dp.toPx(), center.y - 65.dp.toPx(),
                center.x + 40.dp.toPx(), center.y - 40.dp.toPx()
            )
            close()
        }
        drawPath(path = path, brush = bagBrush)
        
        // Draw green tie rope
        drawRoundRect(
            color = ZillionActionGreen,
            topLeft = Offset(center.x - 35.dp.toPx(), center.y - 42.dp.toPx()),
            size = Size(70.dp.toPx(), 8.dp.toPx()),
            cornerRadius = CornerRadius(4.dp.toPx())
        )

        // Gold coins popping out
        drawCircle(
            color = ZillionGold,
            radius = 25.dp.toPx(),
            center = Offset(center.x - 20.dp.toPx(), center.y - 65.dp.toPx())
        )
        drawCircle(
            color = Color(0xFFE5A900),
            radius = 25.dp.toPx(),
            center = Offset(center.x - 20.dp.toPx(), center.y - 65.dp.toPx()),
            style = Stroke(width = 2.dp.toPx())
        )
        
        drawCircle(
            color = ZillionGold,
            radius = 25.dp.toPx(),
            center = Offset(center.x + 15.dp.toPx(), center.y - 75.dp.toPx())
        )
        drawCircle(
            color = Color(0xFFE5A900),
            radius = 25.dp.toPx(),
            center = Offset(center.x + 15.dp.toPx(), center.y - 75.dp.toPx()),
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw gold coin badge in center of the bag
        drawCircle(
            color = ZillionGold,
            radius = 16.dp.toPx(),
            center = Offset(center.x, center.y + 22.dp.toPx())
        )
    }
}

@Composable
fun Slide3Illustration() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width / 2f, size.height / 2f)
        
        // Background soft circle
        drawCircle(
            color = Color(0xFFECE5FF),
            radius = 110.dp.toPx(),
            center = center
        )

        // Draw open box bottom
        val boxBrush = Brush.linearGradient(
            colors = listOf(Color(0xFF8C52FF), Color(0xFF5E17EB))
        )
        
        // Box Front Face
        drawRoundRect(
            brush = boxBrush,
            topLeft = Offset(center.x - 55.dp.toPx(), center.y - 10.dp.toPx()),
            size = Size(110.dp.toPx(), 90.dp.toPx()),
            cornerRadius = CornerRadius(6.dp.toPx())
        )
        
        // Box Left Open Flap
        val leftFlap = Path().apply {
            moveTo(center.x - 55.dp.toPx(), center.y - 10.dp.toPx())
            lineTo(center.x - 85.dp.toPx(), center.y - 35.dp.toPx())
            lineTo(center.x - 85.dp.toPx(), center.y + 10.dp.toPx())
            lineTo(center.x - 55.dp.toPx(), center.y + 20.dp.toPx())
            close()
        }
        drawPath(path = leftFlap, brush = boxBrush, alpha = 0.8f)

        // Box Right Open Flap (represented open lid)
        val rightFlap = Path().apply {
            moveTo(center.x + 55.dp.toPx(), center.y - 10.dp.toPx())
            lineTo(center.x + 85.dp.toPx(), center.y - 50.dp.toPx())
            lineTo(center.x + 60.dp.toPx(), center.y - 65.dp.toPx())
            lineTo(center.x + 35.dp.toPx(), center.y - 10.dp.toPx())
            close()
        }
        drawPath(path = rightFlap, brush = boxBrush, alpha = 0.8f)

        // Draw Green percentage tag floating out of the box
        val tagBrush = Brush.linearGradient(
            colors = listOf(ZillionActionGreen, ZillionGreen)
        )
        
        val tagPath = Path().apply {
            moveTo(center.x - 20.dp.toPx(), center.y - 20.dp.toPx())
            lineTo(center.x + 30.dp.toPx(), center.y - 65.dp.toPx())
            lineTo(center.x + 55.dp.toPx(), center.y - 30.dp.toPx())
            lineTo(center.x + 5.dp.toPx(), center.y + 15.dp.toPx())
            close()
        }
        drawPath(path = tagPath, brush = tagBrush)
        
        // Draw tiny percent symbol inside the tag
        drawCircle(
            color = ZillionWhite,
            radius = 3.dp.toPx(),
            center = Offset(center.x + 10.dp.toPx(), center.y - 28.dp.toPx())
        )
        drawCircle(
            color = ZillionWhite,
            radius = 3.dp.toPx(),
            center = Offset(center.x + 22.dp.toPx(), center.y - 15.dp.toPx())
        )
        drawLine(
            color = ZillionWhite,
            start = Offset(center.x + 22.dp.toPx(), center.y - 28.dp.toPx()),
            end = Offset(center.x + 10.dp.toPx(), center.y - 15.dp.toPx()),
            strokeWidth = 2.dp.toPx()
        )
    }
}
