package com.example.sample
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject


@Composable
fun Home(navController: NavHostController)
{
    val quoteView  : QuoteViewModel = viewModel()
    val quote by quoteView.quote.collectAsState()
    val errorMessage by quoteView.errorMessage.collectAsState()
    var newsArticles by remember { mutableStateOf<List<NewsArticle>?>(null) }
    val geminiExpandedState = remember { mutableStateOf(false) }
    val exerciseExpandedState = remember { mutableStateOf(false) }
    val happyPlacesExpandedState = remember { mutableStateOf(false) }
    Box (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
            contentAlignment = Alignment.Center){

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Spacer(modifier = Modifier.height(6.dp))
                    androidx.compose.material3.Text(
                        "   Welcome to Your Oasis",

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color(0xFF006994)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White
                    )

                    Image(painter = painterResource(id = R.drawable.home_screen), contentDescription = "home_screen_image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(290.dp, 390.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Thought of the Moment:  ",
                        style = MaterialTheme.typography.h4,
                        color = Color(0xFF006994),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.Start),
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        fontStyle = FontStyle.Italic,

                        )

                    Spacer(modifier = Modifier.height(5.dp))

                    if (quote != null) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = 4.dp
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Spacer(modifier = Modifier.height(8.dp))

                                androidx.compose.material3.Text(
                                    "\"${quote!!.content}\"",

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = Color.Cyan),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    fontStyle = FontStyle.Italic
                                )
                                androidx.compose.material3.Text(
                                    "                          - ${quote!!.author}",

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = Color.Cyan),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        }
                    } else if (errorMessage != null) {
                        Text(text = errorMessage!!)
                    } else {
                        CircularProgressIndicator()
                    }
                    Spacer(modifier = Modifier.height(30.dp))



                    Text(
                        text = "  What you will get here?",
                        style = MaterialTheme.typography.h4,
                        color = Color(0xFF006994),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.Start),
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        fontStyle = FontStyle.Italic,

                        )
                    Spacer(modifier = Modifier.height(10.dp))

                    ExpandableCard(
                        title = "Chatbot",
                        description = "Connect with your personal chat assistant, designed to support your mental well-being journey. Gemini helps you track your mood, set achievable goals, and provides motivational support whenever you need it. With Gemini, you'll always have a supportive friend by your side.\n" +
                                "\n",
                        expandedState = geminiExpandedState
                    )

                    ExpandableCard(
                        title = "Exercise Companion",
                        description = "Meet your new workout buddy! Our exercise companion feature offers personalized workout plans tailored to your fitness goals and preferences. Track your progress, stay motivated with interactive challenges, and celebrate your achievements along the way. Whether you're a fitness enthusiast or just starting your journey, our exercise companion is here to help you reach your fitness milestones.",
                        expandedState = exerciseExpandedState
                    )

                    ExpandableCard(
                        title = "Happy Places Storage",
                        description = "Capture and cherish your favorite places with our happy places storage feature. From serene parks to charming cafes and breathtaking hiking trails, you can save your cherished memories and revisit them whenever you need a dose of inspiration or relaxation. Create your own personal collection of happy places and let the memories transport you to moments of joy and tranquility.",
                        expandedState = happyPlacesExpandedState
                    )
                    Spacer(modifier=Modifier.height(10.dp))
                    val logOut : SignUpViewModel = viewModel()
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
                    {

                        androidx.compose.material3.Text(text = "                                                            ")
                        androidx.compose.material3.Button(onClick = {
                            logOut.logout()
                            navController.navigate(ExternalScreens.LoginScreen.route)

                        },
                            modifier = Modifier.weight(0.5f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFB2CCCC),
                                contentColor = Color.Black
                            )
                        ) {
                            androidx.compose.material3.Text("Log Out")

                        }
                    }




                }
            }
        }

    }

}

@Composable
fun NewsCard(article: NewsArticle) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(text = article.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            if (expanded) {
                Text(
                    text = article.description,
                    style = MaterialTheme.typography.body2,
                    maxLines = Int.MAX_VALUE,
                    overflow = TextOverflow.Visible
                )
            }
        }
    }
}
suspend fun fetchNewsData(onNewsFetched: (List<NewsArticle>) -> Unit) {
    val apiKey = "086253ceeeaa469f900f15005cfcba44"
    val apiUrl = "https://newsapi.org/v2/everything?q=mental%20health&sortBy=publishedAt&apiKey=$apiKey"

    withContext(Dispatchers.IO) {
        try {
            val url = URL(apiUrl)
            val connection = url.openConnection() as HttpURLConnection
            val responseCode = connection.responseCode

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }

                reader.close()

                val articles = parseNewsResponse(response.toString())
                onNewsFetched(articles)
            } else {


            }
        } catch (e: Exception) {
            Log.d("","$e")
        }
    }
}
fun parseNewsResponse(response: String): List<NewsArticle> {
    val articles = mutableListOf<NewsArticle>()
    val jsonResponse = JSONObject(response)
    val articlesArray = jsonResponse.optJSONArray("articles")
    if (articlesArray != null) {
        Log.d("","${articlesArray.length()}")
    }
    articlesArray?.let {
        for (i in 0 until it.length()) {
            val articleJson = it.getJSONObject(i)
            val title = articleJson.optString("title")
            val description = articleJson.optString("description")
            val url = articleJson.optString("url")
            articles.add(NewsArticle(title, description, url))
        }
    }

    return articles
}
data class NewsArticle(val title: String, val description: String, val url: String)

@Composable
fun ExpandableCard(title: String, description: String, expandedState: MutableState<Boolean>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expandedState.value = !expandedState.value }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.h6,modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFFEFD5)),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(8.dp))
            if (expandedState.value) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    maxLines = Int.MAX_VALUE,
                    overflow = TextOverflow.Visible

                )
            }
        }
    }
}

