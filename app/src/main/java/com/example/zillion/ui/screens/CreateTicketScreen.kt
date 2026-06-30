package com.example.zillion.ui.screens

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
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zillion.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTicketScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var subject by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Coins Issue") }
    var isSubmitted by remember { mutableStateOf(false) }

    val categories = listOf("Coins Issue", "Redemption", "Account", "Other")
    val randomTicketId = remember { (100000..999999).random() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        // Premium top bar gradient matching home tab
        val topBarBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF0F3BB1), Color(0xFF0A2570))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(topBarBrush)
                .statusBarsPadding()
                .padding(bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = ZillionWhite)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Create Support Ticket",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = ZillionWhite
                )
            }
        }

        if (isSubmitted) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Success",
                            tint = ZillionActionGreen,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Ticket Raised Successfully!",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = ZillionDark,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = ZillionLightGray)
                        Spacer(modifier = Modifier.height(16.dp))

                        // Ticket details summary card
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Ticket ID", color = ZillionGray, fontSize = 14.sp)
                            Text("#JW-$randomTicketId", fontWeight = FontWeight.Bold, color = ZillionDark, fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Category", color = ZillionGray, fontSize = 14.sp)
                            Text(category, fontWeight = FontWeight.Bold, color = ZillionDark, fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Status", color = ZillionGray, fontSize = 14.sp)
                            Text("PENDING", fontWeight = FontWeight.Bold, color = Color(0xFFF59E0B), fontSize = 14.sp)
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Our support agents will review your request and get back to you within 24 hours.",
                            fontSize = 13.sp,
                            color = ZillionGray,
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ZillionGreen),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("GO BACK", fontWeight = FontWeight.Bold, fontSize = 16.sp, letterSpacing = 1.sp)
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
                // Friendly Header Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EFFF)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0xFF0F3BB1).copy(alpha = 0.1f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SupportAgent,
                                contentDescription = null,
                                tint = Color(0xFF0F3BB1),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "How can we help?",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0A2570)
                            )
                            Text(
                                text = "Submit details and our support team will handle it shortly.",
                                fontSize = 12.sp,
                                color = Color(0xFF0F3BB1).copy(alpha = 0.8f)
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
                            text = "Select Inquiry Topic",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = ZillionDark
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Category Chips Selection Layout
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            categories.take(2).forEach { cat ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (category == cat) Color(0xFFE8EFFF) else ZillionLightGray)
                                        .border(
                                            1.dp,
                                            if (category == cat) Color(0xFF0F3BB1) else Color.Transparent,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { category = cat },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = cat,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (category == cat) Color(0xFF0F3BB1) else ZillionDark
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            categories.drop(2).forEach { cat ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (category == cat) Color(0xFFE8EFFF) else ZillionLightGray)
                                        .border(
                                            1.dp,
                                            if (category == cat) Color(0xFF0F3BB1) else Color.Transparent,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { category = cat },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = cat,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (category == cat) Color(0xFF0F3BB1) else ZillionDark
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Subject",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = ZillionDark
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = subject,
                            onValueChange = { subject = it },
                            placeholder = { Text("Short title for your issue") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF0F3BB1),
                                focusedLabelColor = Color(0xFF0F3BB1)
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Detailed Description",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = ZillionDark
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            placeholder = { Text("E.g. My voucher is not loading or my coins didn't update...") },
                            minLines = 4,
                            maxLines = 6,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF0F3BB1),
                                focusedLabelColor = Color(0xFF0F3BB1)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (subject.isNotBlank() && description.isNotBlank()) {
                            isSubmitted = true
                        }
                    },
                    enabled = subject.isNotBlank() && description.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ZillionGreen,
                        contentColor = ZillionWhite,
                        disabledContainerColor = ZillionGreen.copy(alpha = 0.5f),
                        disabledContentColor = ZillionWhite.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("SUBMIT TICKET", fontWeight = FontWeight.Bold, fontSize = 16.sp, letterSpacing = 1.sp)
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
