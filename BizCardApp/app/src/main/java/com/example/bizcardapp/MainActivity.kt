package com.example.bizcardapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcardapp.MainActivity.Companion.TAG
import com.example.bizcardapp.ui.theme.BizCardAppTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "BizCardTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    BizCardAppTheme {
        CreateBizCard()
    }
}

@Composable
fun CreateBizCard() {
    val portfolioVisibleState = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier.padding(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column(
                verticalArrangement = Arrangement.Top, // Default is Top
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CreateProfileImage()
                Divider(modifier = Modifier.padding(8.dp))
                CreateUserInfo()
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        portfolioVisibleState.value = !portfolioVisibleState.value

                        Log.d(TAG, "Portfolio button clicked: ${portfolioVisibleState.value}")
                    }) {
                    Text(
                        text = "Portfolio",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                AnimatedVisibility(visible = portfolioVisibleState.value) {
                    PortfolioList()
                }
            }
        }
    }
}

@Composable
private fun CreateUserInfo() {
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mohsin R.",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(2.dp),
            text = "Android Compose Developer!"
        )
        Text(
            modifier = Modifier.padding(2.dp),
            text = "@mohsin97"
        )
    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.onPrimary,
        border = BorderStroke(1.dp, Color.LightGray),
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile image",
        )
    }
}

@Composable
fun PortfolioList() {
    val data = listOf(
        "Project # 1",
        "Project # 2",
        "Project # 3",
        "Project # 4",
        "Project # 5",
        "Project # 6",
        "Project # 7",
        "Project # 8",
        "Project # 9",
        "Project # 10",
    )
    Surface(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        LazyColumn {
            items(data) { item ->
                PortfolioItem(item)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PortfolioItem(item: String) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        onClick = {
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
        }
    ) {
        Row(modifier = Modifier.padding(12.dp, 8.dp)) {
            CreateProfileImage(modifier = Modifier.size(80.dp))
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = item,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "$item Description!",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

