package com.l3on1kl.rick_and_morty.presentation.character_detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.presentation.character_detail.components.AttributeCard
import com.l3on1kl.rick_and_morty.presentation.character_detail.components.EpisodesCard
import com.l3on1kl.rick_and_morty.presentation.character_detail.components.ErrorState
import com.l3on1kl.rick_and_morty.presentation.character_detail.components.HeroHeader
import com.l3on1kl.rick_and_morty.presentation.character_detail.components.LoadingState
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUi
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUiState
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.asTextRes
import com.l3on1kl.rick_and_morty.presentation.character_detail.util.DetailUtil.lerpDp
import kotlin.math.max
import kotlin.math.min

@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    uiState: CharacterDetailUiState,
    avatarUrl: String? = null,
    onBack: () -> Unit = {}
) {
    when (uiState) {
        CharacterDetailUiState.Loading -> LoadingState(modifier)

        is CharacterDetailUiState.Error -> ErrorState(modifier, uiState)

        is CharacterDetailUiState.Success -> DetailContent(
            uiState.data,
            avatarUrl,
            onBack,
            modifier
        )
    }
}

@Composable
private fun DetailContent(
    character: CharacterDetailUi,
    avatarUrl: String?,
    onBack: () -> Unit,
    modifier: Modifier,
) {
    val listState = rememberLazyListState()
    val density = LocalDensity.current

    val windowInfo = LocalWindowInfo.current
    val screenWidthDp = with(density) {
        windowInfo.containerSize.width.toDp()
    }

    val heroExpanded = screenWidthDp
    val heroCollapsed = 200.dp

    val collapseRangePx = with(density) {
        max(1f, (heroExpanded - heroCollapsed).toPx())
    }

    val collapseFraction by remember {
        derivedStateOf {
            min(
                1f,
                listState.firstVisibleItemScrollOffset / collapseRangePx
            )
        }
    }

    val heroHeight: Dp by animateDpAsState(
        targetValue = lerpDp(
            heroExpanded,
            heroCollapsed,
            collapseFraction
        ),
        animationSpec = tween(300)
    )
    val avatarSize: Dp by animateDpAsState(
        targetValue = lerpDp(
            160.dp,
            48.dp,
            collapseFraction
        ),
        animationSpec = tween(300)
    )

    Scaffold(
        modifier = modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        containerColor = Color.Transparent,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                contentPadding = padding
            ) {
                item {
                    HeroHeader(
                        imageUrl = avatarUrl ?: character.imageUrl,
                        name = character.name,
                        statusRes = character.status.asTextRes(),
                        avatarSize = avatarSize,
                        heroHeight = heroHeight,
                    )
                }

                item {
                    AttributeCard(character)
                }

                item {
                    EpisodesCard(character.episodes)
                }

                item {
                    Spacer(Modifier.height(64.dp))
                }

                item {
                    Spacer(
                        Modifier.height(
                            WindowInsets.safeDrawing.asPaddingValues().calculateBottomPadding()
                        )
                    )
                }
            }

            FilledIconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .statusBarsPadding()
                    .padding(
                        start = 16.dp,
                        top = 16.dp
                    ),
                shape = CircleShape,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
    }
}
