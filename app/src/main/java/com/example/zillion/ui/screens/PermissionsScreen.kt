package com.example.zillion.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import com.example.zillion.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite
import com.example.zillion.theme.ZillionGold

@Composable
fun PermissionsScreen(
    onAllowed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        
        // Header Logo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Mewar Dhani Logo",
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Mewar Dhani",
                color = ZillionActionGreen,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Title
        Text(
            text = "Please allow below permissions to set up your Mewar Dhani account.",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = ZillionDark,
            lineHeight = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Permission Card 1: Location
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ZillionWhite),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(ZillionLightGray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        tint = ZillionGray,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Location",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZillionDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "To Help us find better offers near you.",
                        fontSize = 13.sp,
                        color = ZillionGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Permission Card 2: Notification
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ZillionWhite),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(ZillionLightGray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notification Icon",
                        tint = ZillionGray,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Notification",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZillionDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "To keep you updated about sales & big deals.",
                        fontSize = 13.sp,
                        color = ZillionGray
                    )
                }
            }
        }

        // Illustration Area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LocationPinIllustration()
        }

        // Allow Button
        Button(
            onClick = onAllowed,
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
                text = "ALLOW",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun LocationPinIllustration() {
    Canvas(modifier = Modifier.size(160.dp)) {
        val center = Offset(size.width / 2f, size.height / 2f)

        // Draw shadow at base
        drawOval(
            color = Color(0xFFE0D8ED),
            topLeft = Offset(center.x - 35.dp.toPx(), center.y + 45.dp.toPx()),
            size = Size(70.dp.toPx(), 15.dp.toPx())
        )

        // Base stand (purple ring/plate)
        drawRoundRect(
            brush = Brush.linearGradient(colors = listOf(Color(0xFF8C52FF), Color(0xFF5E17EB))),
            topLeft = Offset(center.x - 40.dp.toPx(), center.y + 35.dp.toPx()),
            size = Size(80.dp.toPx(), 15.dp.toPx()),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
        )

        // Big location pin body
        val pinPath = Path().apply {
            moveTo(center.x, center.y + 35.dp.toPx())
            // Left curve up to top circle
            cubicTo(
                center.x - 45.dp.toPx(), center.y - 5.dp.toPx(),
                center.x - 40.dp.toPx(), center.y - 45.dp.toPx(),
                center.x, center.y - 45.dp.toPx()
            )
            // Right curve down
            cubicTo(
                center.x + 40.dp.toPx(), center.y - 45.dp.toPx(),
                center.x + 45.dp.toPx(), center.y - 5.dp.toPx(),
                center.x, center.y + 35.dp.toPx()
            )
            close()
        }
        drawPath(
            path = pinPath,
            brush = Brush.linearGradient(colors = listOf(Color(0xFF8C52FF), Color(0xFF5E17EB)))
        )

        // Golden center dot in location pin
        drawCircle(
            color = ZillionGold,
            radius = 28.dp.toPx(),
            center = Offset(center.x, center.y - 12.dp.toPx())
        )
        // Golden border accent
        drawCircle(
            color = Color(0xFFE5A900),
            radius = 28.dp.toPx(),
            center = Offset(center.x, center.y - 12.dp.toPx()),
            style = Stroke(width = 2.dp.toPx())
        )

        // Orbit ring (pink/purple ring circling the pin)
        val ringPath = Path().apply {
            moveTo(center.x - 50.dp.toPx(), center.y + 15.dp.toPx())
            cubicTo(
                center.x - 65.dp.toPx(), center.y - 10.dp.toPx(),
                center.x + 65.dp.toPx(), center.y - 10.dp.toPx(),
                center.x + 50.dp.toPx(), center.y + 15.dp.toPx()
            )
            cubicTo(
                center.x + 35.dp.toPx(), center.y + 30.dp.toPx(),
                center.x - 35.dp.toPx(), center.y + 30.dp.toPx(),
                center.x - 50.dp.toPx(), center.y + 15.dp.toPx()
            )
        }
        drawPath(
            path = ringPath,
            color = Color(0xFFFF66C4),
            style = Stroke(width = 6.dp.toPx())
        )
    }
}
