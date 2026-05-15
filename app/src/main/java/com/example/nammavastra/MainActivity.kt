package com.example.nammavastra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import android.net.Uri
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.background
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.nammavastra.ui.theme.NammavastraTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = FirebaseFirestore.getInstance()
        fun uploadData() {
            val sareeData = hashMapOf(
                "name" to "Ilkal Saree",
                "price" to "2500"
            )
            db.collection("sarees")
                .add(sareeData)
                .addOnSuccessListener {
                    println("Data Added")
                }
                .addOnFailureListener {
                    println("Failed")
                }
        }
        enableEdgeToEdge()
        setContent {
            NammavastraTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen(navController)
                    }
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("home") {
                        HomeScreen(navController)
                    }
                    composable("trending") {
                        TrendingScreen()
                    }
                    composable("upload") {
                        UploadScreen()
                    }
                    composable("gallery") {
                        GalleryScreen()
                    }
                    composable("calculator") {
                        CalculatorScreen()
                    }
                    composable("story") {
                        StoryScreen()
                    }
                }
            }
        }
    }
}
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                    },
                    icon = {
                        Icon(Icons.Default.Home, null)
                    },
                    label = {
                        Text("Home")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("trending")
                    },
                    icon = {
                        Icon(Icons.Default.Favorite, null)
                    },
                    label = {
                        Text("Trends")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("upload")
                    },
                    icon = {
                        Icon(Icons.Default.Add, null)
                    },
                    label = {
                        Text("Upload")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("gallery")
                    },
                    icon = {
                        Icon(Icons.Default.List, null)
                    },
                    label = {
                        Text("Gallery")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("story")
                    },
                    icon = {
                        Icon(Icons.Default.Info, null)
                    },
                    label = {
                        Text("Story")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("calculator") },
                    icon = { Icon(Icons.Default.Add, null) },
                    label = { Text("Cost") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFF3E0),
                            Color(0xFFF8BBD0),
                            Color(0xFFE1BEE7)
                        )
                    )
                )
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )
            Text(
                text = "Namma Vastra",
                fontSize = 38.sp,
                color = Color(0xFF6A1B9A)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Traditional Handloom Marketplace",
                fontSize = 18.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(30.dp))
            AsyncImage(
                model = "https://images.unsplash.com/photo-1610030469983-98e550d6193c",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    NammavastraTheme {
        HomeScreen(navController)
    }
}
@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF8BBD0),
                        Color(0xFFE1BEE7),
                        Color(0xFFFFF3E0)
                    )
                )
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Namma Vastra",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A148C)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text(
                    "Username",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(
                    "Password",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                if (
                    username == "akshay" &&
                    password == "1234"
                ) {

                    navController.navigate("home")
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Login",
                fontSize = 18.sp
            )
        }
    }
}
@Composable
fun UploadScreen() {

    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var sareeName by remember { mutableStateOf("") }
    var sareePrice by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1))
            .padding(20.dp)
    ) {

        Text(
            text = "Upload Saree",
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = sareeName,
            onValueChange = {
                sareeName = it
            },
            label = {
                Text("Saree Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = sareePrice,
            onValueChange = {
                sareePrice = it
            },
            label = {
                Text("Price")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (
                    sareeName.isNotEmpty() &&
                    sareePrice.isNotEmpty()
                ) {

                    val sareeData = hashMapOf(
                        "name" to sareeName,
                        "price" to sareePrice
                    )

                    db.collection("sarees")
                        .add(sareeData)
                        .addOnSuccessListener {

                            Toast.makeText(
                                context,
                                "Uploading...",
                                Toast.LENGTH_SHORT
                            ).show()
                            Toast.makeText(
                                context,
                                "Upload Successful",
                                Toast.LENGTH_SHORT
                            ).show()

                            sareeName = ""
                            sareePrice = ""
                        }

                        .addOnFailureListener {

                            Toast.makeText(
                                context,
                                "Upload Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                } else {

                    Toast.makeText(
                        context,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Upload")
        }
    }
}
data class Trend(
    val title: String,
    val image: String
)
@Composable
fun GalleryScreen() {

    val context = LocalContext.current

    val sareeList = listOf(

        Saree(
            "Ilkal Saree",
            "2500",
            R.drawable.ilkal
        ),

        Saree(
            "Mysore Silk Saree",
            "4500",
            R.drawable.mysore
        ),

        Saree(
            "Molakalmuru Saree",
            "6500",
            R.drawable.molakalmuru
        ),

        Saree(
            "Banarasi Saree",
            "5000",
            R.drawable.banarasi
        ),

        Saree(
            "Kanchipuram Saree",
            "7000",
            R.drawable.kanchi
        ),

        Saree(
            "Chanderi Cotton Saree",
            "2800",
            R.drawable.chanderi
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1))
            .padding(10.dp)
    ) {

        items(sareeList) { saree ->

            Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                Image(
                    painter = painterResource(id = saree.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = saree.name,
                        fontSize = 22.sp
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "₹ ${saree.price}",
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {

                            val number = "919972947659"

                            val message =
                                "Hello, I am interested in ${saree.name} priced at ₹${saree.price}"

                            val url =
                                "https://wa.me/$number?text=${Uri.encode(message)}"

                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(url)
                            )

                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text("WhatsApp Inquiry")
                    }
                }
            }
        }
    }
}
@Composable
fun TrendingScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Trending Sarees",
            fontSize = 36.sp,
            color = Color(0xFFD84316)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Trending Colors of the Month",
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(" Pastel Pink")
                Text(" Sage Green")
                Text(" Golden Beige")
                Text(" Royal Blue")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Popular City Trends",
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(" Bengaluru → Minimal Silk Sarees")
                Text(" Mysuru → Traditional Mysore Silk")
                Text(" Hyderabad → Banarasi Wedding Collection")
                Text(" Chennai → Kanchipuram Silk")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Top Trending Sarees",
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text("• Ilkal Saree")
                Text("• Mysore Silk Saree")
                Text("• Molakalmuru Saree")
                Text("• Banarasi Saree")
                Text("• Kanchipuram Silk Saree")
                Text("• Chanderi Cotton Saree")
            }
        }
    }
}
@Composable
fun CalculatorScreen() {

    var silkPrice by remember {
        mutableStateOf("")
    }

    var cottonPrice by remember {
        mutableStateOf("")
    }

    var totalPrice by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF3E0),
                        Color(0xFFF8BBD0)
                    )
                )
            )
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Price Calculator",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6A1B9A)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = silkPrice,
            onValueChange = {
                silkPrice = it
            },

            label = {
                Text(
                    "Silk Price",
                    color = Color.Black
                )
            },

            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            ),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = cottonPrice,
            onValueChange = {
                cottonPrice = it
            },

            label = {
                Text(
                    "Cotton Price",
                    color = Color.Black
                )
            },

            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            ),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                val silk =
                    silkPrice.toIntOrNull() ?: 0

                val cotton =
                    cottonPrice.toIntOrNull() ?: 0

                totalPrice =
                    (silk + cotton).toString()
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Calculate",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Total Price: ₹ $totalPrice",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD84315)
        )
    }
}
@Composable
fun StoryScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Weaver Story",
            fontSize = 30.sp,
            color = Color(0xFF6A1B9A)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .background(Color(0xFFFFF8E1))
                    .padding(16.dp)
            ) {

                Text(
                    text = "Ilkal Saree Heritage",
                    fontSize = 24.sp,
                    color = Color(0xFFD84315)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text =
                        "Ilkal Sarees are one of Karnataka's oldest traditional handloom sarees. " +
                                "These sarees are woven by skilled artisans using unique weaving techniques " +
                                "passed from generation to generation.\n\n" +

                                "Ilkal sarees originated in Bagalkot district of Karnataka and are famous " +
                                "for their rich borders, vibrant colors, and traditional pallu designs.\n\n" +

                                "The special weaving method called Tope Teni joins the saree body and pallu " +
                                "using a unique interlocking technique.\n\n" +

                                "These sarees are widely used during festivals, weddings, and cultural events. " +
                                "The weaving industry supports many rural artisan families and preserves " +
                                "India's textile heritage.",

                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .background(Color(0xFFF3E5F5))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Molakalmuru Silk Tradition",
                    fontSize = 24.sp,
                    color = Color(0xFF1565C0)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text =
                        "Molakalmuru sarees are famous for rich silk texture, elegant zari work, " +
                                "and attractive weaving patterns.\n\n" +

                                "These sarees originate from Chitradurga district of Karnataka and are " +
                                "highly popular for weddings and grand occasions.\n\n" +

                                "The weaving culture provides employment opportunities for thousands of " +
                                "rural weavers and helps preserve Karnataka's traditional handloom industry.\n\n" +

                                "Molakalmuru sarees combine modern fashion trends with traditional art, " +
                                "making them unique and culturally valuable.",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFFE8F5E9))
                    .padding(16.dp)
            ) {
                Text(
                    text = "About Namma Vastra",
                    fontSize = 24.sp,
                    color = Color(0xFF2E7D32)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text =
                        "Namma Vastra helps local weavers connect directly with customers " +
                                "without middlemen, improving artisan income and promoting handloom traditions.\n\n" +

                                "The application supports Karnataka's traditional sarees like Ilkal, " +
                                "Molakalmuru, Mysore Silk, and Banarasi collections.\n\n" +

                                "Users can explore saree galleries, trending collections, pricing details, " +
                                "and directly contact sellers using WhatsApp inquiry features.",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}
@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000)
        navController.navigate("login") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFE0B2),
                        Color(0xFFF8BBD0),
                        Color(0xFFE1BEE7)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://cdn-icons-png.flaticon.com/512/892/892458.png",
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Namma Vastra",
            fontSize = 36.sp,
            color = Color(0xFF6A1B9A)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Handloom Fashion Marketplace",
            fontSize = 18.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(40.dp))
        CircularProgressIndicator(
            color = Color(0xFF6A1B9A)
        )
    }
}
data class Saree(
    val name: String,
    val price: String,
    val imageRes: Int
)