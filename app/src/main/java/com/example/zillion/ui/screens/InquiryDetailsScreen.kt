package com.example.zillion.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zillion.InquiryDetails
import com.example.zillion.R
import com.example.zillion.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InquiryDetailsScreen(
    details: InquiryDetails,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("Rajesh Sharma") }
    var mobile by remember { mutableStateOf("8233816674") }
    var preferredTime by remember { mutableStateOf("Morning (9 AM - 12 PM)") }
    var message by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val times = listOf("Morning (9 AM - 12 PM)", "Afternoon (12 PM - 4 PM)", "Evening (4 PM - 7 PM)")
    val accentColor = try {
        Color(android.graphics.Color.parseColor(details.accentColorHex))
    } catch (e: Exception) {
        ZillionGreen
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        TopAppBar(
            title = { Text("Submit Inquiry", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
        )

        if (isSubmitted) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Success",
                    tint = ZillionActionGreen,
                    modifier = Modifier.size(72.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Inquiry Submitted!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = ZillionDark,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Your request for ${details.name} has been received.\nOur expert financial advisor will contact you within 2 hours on $mobile.",
                    fontSize = 15.sp,
                    color = ZillionGray,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ZillionGreen),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("GO BACK", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Brand info Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val logoRes = when {
                            details.name.contains("sbi", ignoreCase = true) -> R.drawable.sbi_logo
                            details.name.contains("pnb", ignoreCase = true) -> R.drawable.pnb_logo
                            details.name.contains("hdfc", ignoreCase = true) -> R.drawable.hdfc_logo
                            details.name.contains("amazon", ignoreCase = true) -> R.drawable.amazon_logo
                            details.name.contains("flipkart", ignoreCase = true) -> R.drawable.flipkart_logo
                            details.name.contains("myntra", ignoreCase = true) -> R.drawable.myntra_logo
                            else -> null
                        }

                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (logoRes == null) accentColor.copy(alpha = 0.1f) else Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            if (logoRes != null) {
                                Image(
                                    painter = painterResource(id = logoRes),
                                    contentDescription = details.name,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Fit
                                )
                            } else {
                                Text(
                                    text = details.name.take(2).uppercase(),
                                    color = accentColor,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 26.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = details.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = ZillionDark
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = details.rate,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = ZillionGreen
                            )
                            Text(
                                text = details.label,
                                fontSize = 12.sp,
                                color = ZillionGray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Lead Form Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Fill Details for Callback",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = ZillionDark
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Full Name",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ZillionDark
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = { Text("Enter your name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = ZillionGreen,
                                focusedLabelColor = ZillionGreen
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Mobile Number",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ZillionDark
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = mobile,
                            onValueChange = { mobile = it },
                            placeholder = { Text("Enter mobile number") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = ZillionGreen,
                                focusedLabelColor = ZillionGreen
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Preferred Callback Time",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ZillionDark
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                                .clickable { expanded = true }
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = preferredTime, fontSize = 16.sp, color = ZillionDark)
                                Text("▼", color = ZillionGray, fontSize = 12.sp)
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.fillMaxWidth(0.8f)
                            ) {
                                times.forEach { t ->
                                    DropdownMenuItem(
                                        text = { Text(t) },
                                        onClick = {
                                            preferredTime = t
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Your Message / Requirement",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ZillionDark
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            placeholder = { Text("E.g. Home loan needed for ₹30 Lakhs...") },
                            minLines = 3,
                            maxLines = 5,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = ZillionGreen,
                                focusedLabelColor = ZillionGreen
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (name.isNotBlank() && mobile.isNotBlank()) {
                            isSubmitted = true
                        }
                    },
                    enabled = name.isNotBlank() && mobile.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ZillionGreen,
                        contentColor = ZillionWhite,
                        disabledContainerColor = ZillionGreen.copy(alpha = 0.5f),
                        disabledContentColor = ZillionWhite.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("SUBMIT INQUIRY", fontWeight = FontWeight.Bold, fontSize = 16.sp, letterSpacing = 1.sp)
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
