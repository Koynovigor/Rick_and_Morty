import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.presentation.characters.components.CharacterCard
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterUi
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterCardTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun characterCard_displaysCharacterInfo() {
        val activity = composeTestRule.activity
        val character = CharacterUi(
            id = 1,
            name = "Rick Sanchez",
            status = CharacterUi.Status.ALIVE,
            species = "Human",
            gender = CharacterUi.Gender.MALE,
            imageUrl = ""
        )

        composeTestRule.setContent {
            CharacterCard(character = character)
        }

        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()

        val genderText = activity.getString(R.string.male)
        val speciesGender = activity.getString(
            R.string.species_and_gender,
            "Human",
            genderText
        )
        composeTestRule.onNodeWithText(speciesGender).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            activity.getString(
                R.string.alive
            ).uppercase()
        ).assertIsDisplayed()
    }

    @Test
    fun characterCard_invokesOnClick() {
        var clicked = false
        val character = CharacterUi(
            id = 2,
            name = "Morty Smith",
            status = CharacterUi.Status.ALIVE,
            species = "Human",
            gender = CharacterUi.Gender.MALE,
            imageUrl = ""
        )

        composeTestRule.setContent {
            CharacterCard(
                character = character,
                onClick = { clicked = true }
            )
        }

        composeTestRule
            .onNodeWithContentDescription("Morty Smith")
            .performClick()

        assertTrue(clicked)
    }
}
