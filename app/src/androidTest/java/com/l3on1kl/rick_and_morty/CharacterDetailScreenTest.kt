package com.l3on1kl.rick_and_morty

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.l3on1kl.rick_and_morty.presentation.character_detail.CharacterDetailScreen
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUi
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUiState
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun sampleCharacter() = CharacterDetailUi(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterDetailUi.Status.ALIVE,
        species = "Human",
        type = "",
        gender = CharacterDetailUi.Gender.MALE,
        imageUrl = "",
        originName = "Earth",
        originUrl = "",
        locationName = "Citadel",
        locationUrl = "",
        episodes = listOf("1", "2"),
        url = "",
        created = "2017-11-04T18:48:46.250Z"
    )

    @Test
    fun detailScreen_displaysData() {
        val character = sampleCharacter()
        composeTestRule.setContent {
            CharacterDetailScreen(
                uiState = CharacterDetailUiState.Success(character),
                avatarUrl = character.imageUrl,
                onBack = {}
            )
        }

        val activity = composeTestRule.activity
        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()

        composeTestRule.onNodeWithText(
            activity.getString(R.string.alive),
            ignoreCase = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            activity.getString(R.string.detail_species).trim(),
            substring = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            "Episodes: 2",
            substring = true
        ).assertIsDisplayed()
    }

    @Test
    fun detailScreen_backButtonCallbacks() {
        val character = sampleCharacter()
        var clicked = false
        composeTestRule.setContent {
            CharacterDetailScreen(
                uiState = CharacterDetailUiState.Success(character),
                avatarUrl = character.imageUrl,
                onBack = { clicked = true }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                composeTestRule.activity.getString(R.string.back)
            )
            .performClick()

        assertTrue(clicked)
    }
}
