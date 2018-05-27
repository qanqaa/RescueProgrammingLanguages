package com.brentaureli.game.scores;

import com.brentaureli.game.profiles.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerScoreManagerMock {

    private List<PlayerScore> playerScores = new ArrayList<>();

    private static PlayerScoreManagerMock instance = null;

    public static PlayerScoreManagerMock getInstance() {
        if (instance == null) {
            instance = new PlayerScoreManagerMock();
        }
        return instance;
    }

    public List<PlayerScore> prepare10Players() {
        playerScores.add(new PlayerScore(new Profile("2131"), 10));
        playerScores.add(new PlayerScore(new Profile("2231", "photo2.png"), 20));
        playerScores.add(new PlayerScore(new Profile("2434241"), 30));
        playerScores.add(new PlayerScore(new Profile("223131"), 40));
        playerScores.add(new PlayerScore(new Profile("21321311"), 50));
        playerScores.add(new PlayerScore(new Profile("21adada"), 60));
        playerScores.add(new PlayerScore(new Profile("2asdada1"), 70));
        playerScores.add(new PlayerScore(new Profile("2asdzxcz131"), 80));
        playerScores.add(new PlayerScore(new Profile("2daf131"), 90));
        playerScores.add(new PlayerScore(new Profile("vxcvxv2131"), 100));
        sortDescending();
        return playerScores;
    }

    public List<PlayerScore> prepare20Players() {

        playerScores.add(new PlayerScore(new Profile("2131"), 10));
        playerScores.add(new PlayerScore(new Profile("2231"), 20));
        playerScores.add(new PlayerScore(new Profile("2434241"), 30));
        playerScores.add(new PlayerScore(new Profile("223131"), 40));
        playerScores.add(new PlayerScore(new Profile("21321311"), 50));
        playerScores.add(new PlayerScore(new Profile("21adada", "photo2.png"), 60));
        playerScores.add(new PlayerScore(new Profile("2asdada1"), 70));
        playerScores.add(new PlayerScore(new Profile("2asdzxcz131"), 80));
        playerScores.add(new PlayerScore(new Profile("2daf131"), 90));
        playerScores.add(new PlayerScore(new Profile("vxcvxv2131"), 100));
        playerScores.add(new PlayerScore(new Profile("2131"), 50));
        playerScores.add(new PlayerScore(new Profile("2231"), 330));
        playerScores.add(new PlayerScore(new Profile("2434241"), 12));
        playerScores.add(new PlayerScore(new Profile("223131"), 33));
        playerScores.add(new PlayerScore(new Profile("21321311"), 11));
        playerScores.add(new PlayerScore(new Profile("21adada"), 54));
        playerScores.add(new PlayerScore(new Profile("2asdada1"), 31));
        playerScores.add(new PlayerScore(new Profile("2asdzxcz131"), 88));
        playerScores.add(new PlayerScore(new Profile("2daf131"), 99));
        playerScores.add(new PlayerScore(new Profile("vxcvxv2131", "photo2.png"), 1110));
        sortDescending();
        return playerScores;
    }

    private void sortDescending() {
        playerScores.sort((p1, p2) -> p2.getScore() - p1.getScore());
    }

    public int getHighestScore() {
        if (playerScores.size() > 0) {
            return playerScores.get(0).getScore();
        }
        return 0;
    }

    public void updateScore(PlayerScore playerScore) {
        Optional<PlayerScore> playerScoreToUpdate = playerScores.stream().filter(o -> o.getProfile().getName().equals(playerScore.getProfile().getName())).findFirst();
        if (!playerScoreToUpdate.isPresent()) {
            playerScores.add(playerScore);
        } else {
            PlayerScore playerScoreInList = playerScoreToUpdate.get();
            if (playerScoreInList.getScore() < playerScore.getScore()) {
                playerScores.set(playerScores.indexOf(playerScoreInList), playerScore);
            }
        }
        sortDescending();
    }


}
