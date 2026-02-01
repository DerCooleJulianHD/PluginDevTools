package de.api.devtools.scoreboard;


import de.api.devtools.utils.TextUtil;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class SimpleScoreboard extends ScoreboardBuilder {

    private final Map<Integer, String> scores = new HashMap<>();

    public SimpleScoreboard(Player player, String displayname) {
        super(player, displayname, false);
    }

    public SimpleScoreboard(String displayname) {
        super(displayname, false);
    }

    @Override
    public void setScore(String content, int score) {
        setScore(content, null, score);
    }

    @Override
    public void setScore(String prefix, String suffix, int score) {
        if (objective == null) return;
        final String content = TextUtil.colorize(prefix + (suffix != null ? suffix : ""));
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
