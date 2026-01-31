package de.api.devtools.scoreboard;


import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class SimpleScoreboard extends ScoreboardBuilder {

    private final Map<Integer, String> scores = new HashMap<>();

    public SimpleScoreboard(Player player, String displayname, boolean replace) {
        super(player, displayname, replace);
    }

    public SimpleScoreboard(String displayname, boolean replace) {
        super(displayname, replace);
    }

    @Override
    public void setScore(String content, int score) {
        if (objective == null) return;
        objective.getScore(content).setScore(score);
        scores.put(score, content);
    }

    @Override
    public void removeScore(int score) {
        if (objective == null)
            return;

        final String content = scores.get(score);

        if (content == null)
            return;

        if (!objective.getScore(content).isScoreSet())
            return;

        scoreboard.resetScores(content);
        scores.remove(score);
    }

    public Map<Integer, String> getScores() {
        return scores;
    }
}
