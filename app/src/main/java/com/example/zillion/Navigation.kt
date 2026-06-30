package com.example.zillion

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.zillion.ui.screens.*
import com.example.zillion.ui.screens.tabs.ProductsTab
import com.example.zillion.ui.screens.tabs.VouchersTab
import com.example.zillion.ui.screens.tabs.CouponsTab
import com.example.zillion.ui.screens.tabs.ShopTab

@Composable
fun MainNavigation() {
  val backStack = rememberNavBackStack(Onboarding)
  var isUserLoggedIn by remember { mutableStateOf(false) }

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider =
      entryProvider {
        entry<Onboarding> {
          OnboardingScreen(onFinished = { backStack.add(Main) })
        }
        entry<Login> {
          LoginScreen(
            onLoginSuccess = { 
              isUserLoggedIn = true
              backStack.add(Main) 
            },
            onNavigateToSignUp = { backStack.add(SignUp) },
            onSkip = { backStack.add(Main) }
          )
        }
        entry<SignUp> {
          SignUpScreen(
            onSignUpSuccess = { backStack.add(Login) },
            onNavigateToLogin = { backStack.add(Login) },
            onSkip = { backStack.add(Main) }
          )
        }
        entry<Permissions> {
          PermissionsScreen(onAllowed = { backStack.add(Main) })
        }
        entry<Main> {
          MainContainerScreen(
            isLoggedIn = isUserLoggedIn,
            onNavigate = { navKey ->
              if (navKey == Login) {
                isUserLoggedIn = false
              }
              backStack.add(navKey)
            }
          )
        }
        entry<ShopEarnGrid> {
          ShopTab(onItemClick = { navKey -> backStack.add(navKey) })
        }
        entry<Products> {
          ProductsTab(onItemClick = { navKey -> backStack.add(navKey) })
        }
        entry<Vouchers> {
          VouchersTab(onItemClick = { navKey -> backStack.add(navKey) })
        }
        entry<Coupons> {
          CouponsTab(onItemClick = { navKey -> backStack.add(navKey) })
        }
        entry<Profile> {
          ProfileScreen(
            onItemClick = { navKey ->
              if (navKey == Login) {
                isUserLoggedIn = false
              }
              backStack.add(navKey)
            }
          )
        }
        entry<AccountDetails> {
          AccountDetailsScreen(onBack = { backStack.removeLastOrNull() })
        }
        entry<TransactionHistory> {
          TransactionHistoryScreen(onBack = { backStack.removeLastOrNull() })
        }
        entry<Addresses> {
          AddressesScreen(onBack = { backStack.removeLastOrNull() })
        }
        entry<OrderHistory> {
          OrderHistoryScreen(onBack = { backStack.removeLastOrNull() })
        }
        entry<HelpCenter> {
          HelpCenterScreen(
            onItemClick = { navKey -> backStack.add(navKey) },
            onBack = { backStack.removeLastOrNull() }
          )
        }
        entry<InquiryDetails> { route ->
          InquiryDetailsScreen(
            details = route,
            onBack = { backStack.removeLastOrNull() }
          )
        }
        entry<AboutZillionFAQ> {
          FAQListScreen(
            title = "About Jio Wealth",
            onBack = { backStack.removeLastOrNull() },
            questions = FAQData.aboutZillion
          )
        }
        entry<AccountInfoFAQ> {
          FAQListScreen(
            title = "Account Information",
            onBack = { backStack.removeLastOrNull() },
            questions = FAQData.accountInfo
          )
        }
        entry<ZillionCoinsFAQ> {
          FAQListScreen(
            title = "Jio Wealth Coins",
            onBack = { backStack.removeLastOrNull() },
            questions = FAQData.zillionCoins
          )
        }
        entry<RedemptionInfoFAQ> {
          FAQListScreen(
            title = "Redemption Information",
            onBack = { backStack.removeLastOrNull() },
            questions = FAQData.redemptionInfo
          )
        }
        entry<DeleteAccountFAQ> {
          DeleteAccountFAQScreen(onBack = { backStack.removeLastOrNull() })
        }
        entry<CreateTicket> {
          CreateTicketScreen(onBack = { backStack.removeLastOrNull() })
        }
      },
  )
}

object FAQData {
  val aboutZillion = listOf(
    FAQItem(
      "What is Jio Wealth?",
      "Jio Wealth is a multi-brand loyalty program to reward your routine spending by enabling you to earn Coins across many categories."
    ),
    FAQItem(
      "What are the benefits I get with Jio Wealth?",
      "We add a splash of excitement to your everyday spends by returning value as coins that can be spent on various rewards."
    ),
    FAQItem(
      "What are the eligibility criteria to become a Jio Wealth Member?",
      "One should be above the age of 18 years; while some partners are enrolling with specific age limits."
    ),
    FAQItem(
      "How can I enroll in the Jio Wealth program/membership?",
      "You can enroll for a free membership in the Jio Wealth loyalty program through its online/offline partner portals."
    ),
    FAQItem(
      "Who are the Jio Wealth Partners?",
      "We have 50+ in-store and online partners where you can earn and redeem your coins."
    ),
    FAQItem(
      "Where can I find Jio Wealth partners?",
      "You can find Jio Wealth Partners on our app & website. Alternately, you could call our customer service center."
    )
  )

  val accountInfo = listOf(
    FAQItem(
      "How do I log in to my new Jio Wealth Account?",
      "Download the Jio Wealth app or Visit our website and enter your registered mobile number and OTP."
    ),
    FAQItem(
      "How do I update my account?",
      "Contact us at support@jiowealth.in for any help with your Jio Wealth account, and we will update it for you."
    ),
    FAQItem(
      "Can I transfer my Jio Wealth Account to another person’s name?",
      "Once Jio Wealth Membership is issued to an individual, the same cannot be transferred to anyone else."
    ),
    FAQItem(
      "How can I cancel my Jio Wealth Membership/account?",
      "A member may terminate his/her Jio Wealth Membership by reaching out to us via the customer care support."
    ),
    FAQItem(
      "How to reactivate my account?",
      "Unfortunately, the canceled accounts cannot be reactivated."
    )
  )

  val zillionCoins = listOf(
    FAQItem(
      "How do I earn Jio Wealth Coins?",
      "It is super easy and quick. You can earn Coins by transacting on the Jio Wealth platform or clicking out to partners."
    ),
    FAQItem(
      "When are the Coins credited?",
      "Jio Wealth Coins get credited instantly in most cases, however on transactions done at a few partners it can take up to 7 days."
    ),
    FAQItem(
      "When do Coins expire?",
      "Coins expire 3 years from the date of transaction."
    ),
    FAQItem(
      "Is there a limit on earning Coins?",
      "The number of Coins you earn depends on the Partner and might vary. To know more, review the partner policies."
    ),
    FAQItem(
      "Can I use my Coins to fill fuel?",
      "You can redeem your Coins at HPCL outlets to get fuel by quoting your Linked Mobile number."
    ),
    FAQItem(
      "Oops! My coins have not been credited. Why?",
      "Please reach out to us at 1860-258-5000 (Local call charges apply) or write to us at support."
    )
  )

  val redemptionInfo = listOf(
    FAQItem(
      "How long will it take to receive a product redeemed from the Jio Wealth Catalog?",
      "We try to deliver your product within 10 working days unless there is any unforeseen delay in transit."
    ),
    FAQItem(
      "What if I receive a wrong or damaged product?",
      "We’re sorry if that ever happens! However, in this scenario, contact us within 7 days of receipt and we will replace it."
    ),
    FAQItem(
      "How long will it take to receive a voucher redeemed from the Jio Wealth Catalog?",
      "Vouchers redeemed through our catalog are delivered instantly via SMS & email."
    ),
    FAQItem(
      "Can I cancel my redemption order? If yes, what is the time frame?",
      "Product Orders placed through the Jio Wealth Catalog can be canceled only by contacting us within 2 hours of booking."
    ),
    FAQItem(
      "Can I request the product be shipped to an alternate mailing address?",
      "Please log in to your account on the Jio Wealth App or Website to update your mailing address before ordering."
    )
  )
}
