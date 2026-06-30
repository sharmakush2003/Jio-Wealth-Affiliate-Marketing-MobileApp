package com.example.zillion.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.zillion.*
import com.example.zillion.theme.ZillionActionGreen
import com.example.zillion.theme.ZillionDark
import com.example.zillion.theme.ZillionGreen
import com.example.zillion.theme.ZillionGray
import com.example.zillion.theme.ZillionLightGray
import com.example.zillion.theme.ZillionWhite
import com.example.zillion.theme.ZillionLightGreen
import com.example.zillion.theme.ZillionGold

// ==========================================
// 1. HELP CENTER LIST SCREEN
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpCenterScreen(
    onItemClick: (NavKey) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
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
                    text = "Help Center",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = ZillionWhite
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Frequently Asked Questions",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ZillionDark,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column {
                    FAQCategoryRow(title = "About Jio Wealth", onClick = { onItemClick(AboutZillionFAQ) })
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))
                    
                    FAQCategoryRow(title = "Account Information", onClick = { onItemClick(AccountInfoFAQ) })
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))
                    
                    FAQCategoryRow(title = "Jio Wealth Coins", onClick = { onItemClick(ZillionCoinsFAQ) })
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))
                    
                    FAQCategoryRow(title = "Redemption Information", onClick = { onItemClick(RedemptionInfoFAQ) })
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))
                    
                    FAQCategoryRow(title = "Delete Account", onClick = { onItemClick(DeleteAccountFAQ) })
                    HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))
                    
                    FAQCategoryRow(title = "Create Ticket", onClick = { onItemClick(CreateTicket) })
                }
            }
        }
    }
}

@Composable
fun FAQCategoryRow(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 18.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = ZillionDark,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = ZillionGray,
            modifier = Modifier.size(24.dp)
        )
    }
}

// ==========================================
// 2. FAQ QUESTIONS LIST TEMPLATE
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQListScreen(
    title: String,
    onBack: () -> Unit,
    questions: List<FAQItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        TopAppBar(
            title = { Text(title, fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    questions.forEachIndexed { index, item ->
                        var isExpanded by remember { mutableStateOf(false) }
                        
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { isExpanded = !isExpanded }
                                .padding(vertical = 16.dp, horizontal = 20.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item.question,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ZillionDark,
                                    modifier = Modifier.weight(1f),
                                    lineHeight = 20.sp
                                )
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = ZillionGray,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = item.answer,
                                    fontSize = 13.sp,
                                    color = ZillionGray,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                        if (index < questions.size - 1) {
                            HorizontalDivider(color = ZillionLightGray, modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }
    }
}

data class FAQItem(val question: String, val answer: String)

// ==========================================
// 3. DELETE ACCOUNT TIMELINE SCREEN
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountFAQScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialogType by remember { mutableStateOf(0) } // 0=None, 1=Success, 2=AlreadyRequested

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ZillionLightGray)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Delete Account", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = ZillionWhite)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Steps card (Timeline)
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        TimelineItem(
                            stepNumber = "1",
                            description = "Account deletion request initiated via the \"Delete Account\" button.",
                            isLast = false
                        )
                        TimelineItem(
                            stepNumber = "2",
                            description = "Jio Wealth support team will check for any pending payments.",
                            isLast = false
                        )
                        TimelineItem(
                            stepNumber = "3",
                            description = "If no pending payments, then fraud-checks to be done on the account.",
                            isLast = false
                        )
                        TimelineItem(
                            stepNumber = "4",
                            description = "Legal sign-off to be taken from enrolment partner.",
                            isLast = false
                        )
                        TimelineItem(
                            stepNumber = "5",
                            description = "Account will be deleted and an email confirmation will be sent to you.",
                            isLast = true
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Green Warning Card
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = ZillionLightGreen),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = ZillionGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "The account deletion process will be completed within 14 business days of raising the request. Until then, you can still login to your account.",
                            fontSize = 12.sp,
                            color = ZillionDark,
                            lineHeight = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.height(20.dp))

                // Actions buttons to trigger dialog overlays for demo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { showDialogType = 1 },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .border(1.dp, ZillionGreen, RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ZillionWhite,
                            contentColor = ZillionGreen
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Delete (Success)", fontSize = 12.sp)
                    }
                    Button(
                        onClick = { showDialogType = 2 },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .border(1.dp, ZillionGreen, RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ZillionWhite,
                            contentColor = ZillionGreen
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Delete (Already)", fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Delete Account Main Action
                Button(
                    onClick = { showDialogType = 1 },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(1.dp, ZillionGreen, RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ZillionWhite,
                        contentColor = ZillionGreen
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Delete Account", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        // Dialog overlays
        if (showDialogType != 0) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { showDialogType = 0 },
                contentAlignment = Alignment.BottomCenter
            ) {
                Card(
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = ZillionWhite),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = false) {}
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Close icon
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            IconButton(onClick = { showDialogType = 0 }) {
                                Icon(Icons.Default.Close, contentDescription = "Close")
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(10.dp))

                        if (showDialogType == 1) {
                            // Success Dialog (Matches screenshot 1000043855)
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Success",
                                tint = ZillionActionGreen,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "We have accepted your account deletion request.",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = ZillionDark,
                                textAlign = TextAlign.Center,
                                lineHeight = 24.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "You will receive a confirmation email soon.",
                                fontSize = 14.sp,
                                color = ZillionGray,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            // Already Requested Dialog (Matches screenshot 1000043856)
                            // Draw hourglass/warning
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Hourglass",
                                tint = ZillionGold,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "You have already requested for delete your account.",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = ZillionDark,
                                textAlign = TextAlign.Center,
                                lineHeight = 24.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "You will receive a confirmation email soon.",
                                fontSize = 14.sp,
                                color = ZillionGray,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        // OKAY Action button
                        Button(
                            onClick = { showDialogType = 0 },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ZillionGreen, contentColor = ZillionWhite)
                        ) {
                            Text("OKAY", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimelineItem(
    stepNumber: String,
    description: String,
    isLast: Boolean
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(36.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(ZillionLightGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stepNumber,
                    color = ZillionGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp)
                        .background(Color.LightGray)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = description,
            fontSize = 14.sp,
            color = ZillionDark,
            lineHeight = 20.sp,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}
