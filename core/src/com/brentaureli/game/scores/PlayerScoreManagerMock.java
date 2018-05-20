package com.brentaureli.game.scores;

import com.brentaureli.game.profiles.Profile;

import java.util.ArrayList;
import java.util.List;

public class PlayerScoreManagerMock {


    public static List<PlayerScore> list10PlayerScores() {
        List<PlayerScore> playerScores = new ArrayList<>();

        playerScores.add(new PlayerScore(new Profile("2131"), 10));
        playerScores.add(new PlayerScore(new Profile("2231"), 20));
        playerScores.add(new PlayerScore(new Profile("2434241"), 30));
        playerScores.add(new PlayerScore(new Profile("223131"), 40));
        playerScores.add(new PlayerScore(new Profile("21321311"), 50));
        playerScores.add(new PlayerScore(new Profile("21adada"), 60));
        playerScores.add(new PlayerScore(new Profile("2asdada1"), 70));
        playerScores.add(new PlayerScore(new Profile("2asdzxcz131"), 80));
        playerScores.add(new PlayerScore(new Profile("2daf131"), 90));
        playerScores.add(new PlayerScore(new Profile("vxcvxv2131"), 100));
        playerScores.sort((p1, p2) -> p2.getScore() - p1.getScore());
        return playerScores;
    }

    public static List<PlayerScore> list20PlayerScores() {
        List<PlayerScore> playerScores = new ArrayList<>();

        playerScores.add(new PlayerScore(new Profile("2131"), 10));
        playerScores.add(new PlayerScore(new Profile("2231"), 20));
        playerScores.add(new PlayerScore(new Profile("2434241"), 30));
        playerScores.add(new PlayerScore(new Profile("223131"), 40));
        playerScores.add(new PlayerScore(new Profile("21321311"), 50));
        playerScores.add(new PlayerScore(new Profile("21adada"), 60));
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
        playerScores.add(new PlayerScore(new Profile("vxcvxv2131"), 1110));
        playerScores.sort((p1, p2) -> p2.getScore() - p1.getScore());
        return playerScores;
    }


}
