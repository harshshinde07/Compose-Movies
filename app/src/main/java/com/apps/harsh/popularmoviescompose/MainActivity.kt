package com.apps.harsh.popularmoviescompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.apps.harsh.popularmoviescompose.data.Movie
import com.apps.harsh.popularmoviescompose.data.MovieData
import com.apps.harsh.popularmoviescompose.ui.PopularMoviesComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopularMoviesComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PopularMoviesMainPage()
                }
            }
        }
    }
}

@Composable
fun PopularMoviesListItem(movie: Movie) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .height(height = 375.dp)
    ) {
        Column {
            Image(
                asset = imageResource(id = movie.image),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.preferredHeight(height = 200.dp),
                alignment = Alignment.TopCenter
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(all = 8.dp),
                style = MaterialTheme.typography.h6
            )
            Row(
                modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Release Date: ${movie.releaseDate}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Vote: ${movie.voteAverage}/10",
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Text(
                text = movie.overview,
                modifier = Modifier.padding(all = 8.dp),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularMoviesMainPage() {
    Column {
        TopAppBar(title = { Text(text = "Popular Movies") })
        LazyColumnFor(items = MovieData.list) {
            PopularMoviesListItem(it)
        }
    }
}