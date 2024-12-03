package use_case.compare_playlists;

import entity.CurrentUser;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ComparePlaylistsTest {

    @Test
    void apiFailureTest() {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAccessToken("invalid_token");

        SpotifyPlaylistRepository spotifyRepository = new SpotifyPlaylistRepository();
        TestComparePlaylistsOutputBoundary failurePresenter = new ComparePlaylistsTest.TestComparePlaylistsOutputBoundary();

        ComparePlaylistInteractor interactor = new ComparePlaylistInteractor(spotifyRepository, failurePresenter);

        String mockPlaylist1Name = "mockPlaylist1";
        String mockPlaylist1OwnerName = "mockPlaylist1Owner";
        String mockPlaylist2Name = "mockPlaylist2";
        String mockPlaylist2OwnerName = "mockPlaylist2Owner";
        PlaylistsInputData mockInputData = new PlaylistsInputData(mockPlaylist1Name, mockPlaylist1OwnerName, mockPlaylist2Name, mockPlaylist2OwnerName);

        interactor.execute(mockInputData);

        assertNull(failurePresenter.similarityScore); // No songs should be received
        assertEquals("Error comparing playlists: HTTP Error: 401 Unauthorized", failurePresenter.errorMessage);
    }

    class TestComparePlaylistsOutputBoundary implements PlaylistsOutputBoundary {
        Integer similarityScore = null;
        String errorMessage = null;

        @Override
        public void prepareSuccessView(PlaylistsOutputData playlistsOutputData) {
            this.similarityScore = playlistsOutputData.getSimilarityScore();
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}