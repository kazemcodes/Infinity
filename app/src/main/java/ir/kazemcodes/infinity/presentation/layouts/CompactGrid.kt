package ir.kazemcodes.infinity.presentation.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ir.kazemcodes.infinity.domain.models.Book
import ir.kazemcodes.infinity.presentation.screen.components.BookImageComposable

@ExperimentalFoundationApi
@Composable
fun CompactGridLayoutComposable(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onClick: (index: Int) -> Unit,
    scrollState: LazyListState = rememberLazyListState(),
) {
    LazyVerticalGrid(
        state = scrollState,
        modifier = modifier.fillMaxSize(),
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        content = {
            items(books.size) { index ->
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clickable(role = Role.Button) { onClick(index) },
                ) {
                    BookImageComposable(
                        modifier = modifier
                            .height(230.dp)
                            .shadow(8.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                        image = books[index].coverLink ?: "",
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 3f,  // 1/3
                                    endY = 80F
                                )
                            )
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(
                            modifier = modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 8.dp),
                            text = books[index].bookName,
                            style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }

                }
            }
        })
}