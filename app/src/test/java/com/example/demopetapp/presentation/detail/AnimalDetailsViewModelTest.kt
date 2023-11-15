import com.example.demopetapp.MainDispatcherRule
import com.example.demopetapp.domain.ViewState
import com.example.demopetapp.domain.entity.AnimalDetails
import com.example.demopetapp.domain.usecase.GetAnimalDetailsTask
import com.example.demopetapp.presentation.detail.AnimalDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AnimalDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getAnimalDetailsTask: GetAnimalDetailsTask = mockk()

    private lateinit var animalDetailsViewModel: AnimalDetailsViewModel

    private val animalDetails = AnimalDetails(
        name = "name",
        breed = "breed",
        size = "size",
        gender = "gender",
        status = "status",
        distance = 1.2
    )

    @Before
    fun setup() {
        animalDetailsViewModel = AnimalDetailsViewModel(getAnimalDetailsTask)
    }

    @Test
    fun `getAnimalDetails success`() = runTest {
        coEvery { getAnimalDetailsTask(any()) } returns flowOf(animalDetails)

        animalDetailsViewModel.getAnimalDetails(123)

        assert(animalDetailsViewModel.animalStateFlow.value is ViewState.Success)
        assert((animalDetailsViewModel.animalStateFlow.value as ViewState.Success).data == animalDetails)
    }

    @Test
    fun `getAnimalDetails error`() = runTest {
        val errorException = RuntimeException("error message")
        coEvery { getAnimalDetailsTask(any()) } throws errorException

        animalDetailsViewModel.getAnimalDetails(456)

        assert(animalDetailsViewModel.animalStateFlow.value is ViewState.Error)
        assert((animalDetailsViewModel.animalStateFlow.value as ViewState.Error).message == "error message")
    }
}
