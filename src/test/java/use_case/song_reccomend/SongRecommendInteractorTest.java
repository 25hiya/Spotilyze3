package use_case.song_reccomend;

import entity.CurrentUser;
import org.junit.jupiter.api.Test;
import use_case.song_recommend.*;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SongRecommendInteractorTest {

    @Test
    void apiFailureTest() {
        // Create a test CurrentUser with a mock token
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAccessToken("invalid_token"); // Simulate an invalid token

        // Test presenter to capture the output
        TestSongRecommendOutputBoundary failurePresenter = new TestSongRecommendOutputBoundary();

        // Initialize the interactor with the CurrentUser
        SongRecommendInteractor interactor = new SongRecommendInteractor(failurePresenter, currentUser);

        // Create SongRecommendInputData with mock data
        String mockTopGenre = "pop";
        SongRecommendInputData mockInputData = new SongRecommendInputData(mockTopGenre, Arrays.asList("Song A", "Song B"));

        // Call the use case with the input data
        interactor.fetchRecommendedSongs(mockInputData);

        // Verify the presenter received the error message
        assertNull(failurePresenter.recommendedSongs); // No songs should be received
        assertEquals("Failed to fetch recommended songs: HTTP Error: 401 Unauthorized", failurePresenter.errorMessage);
    }

    // Test implementation of the SongRecommendOutputBoundary
    class TestSongRecommendOutputBoundary implements SongRecommendOutputBoundary {
        Map<String, String> recommendedSongs = null;
        String errorMessage = null;

        @Override
        public void presentRecommendedSongs(SongRecommendOutputData songRecommendOutputData) {
            this.recommendedSongs = songRecommendOutputData.getRecommendedSongs();
        }

        @Override
        public void handleError(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}