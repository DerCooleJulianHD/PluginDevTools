package de.api.devtools.scoreboard.builder;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.scoreboard.IScoreboard;
import de.api.devtools.scoreboard.score.AnimatedScore;
import de.api.devtools.scoreboard.score.IScore;
import de.api.devtools.scoreboard.score.SimpleScore;
import de.api.devtools.scoreboard.util.Criteria;
import de.api.devtools.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ScoreboardBuilder implements IScoreboard {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final Scoreboard scoreboard;
    protected final Objective mainObjective;

    protected final Map<Integer, IScore> scores = new HashMap<>();

    protected ScoreboardBuilder(String displayname, boolean replace) {
        this.scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        this.mainObjective = this.registerObjective("main", Criteria.DUMMY, TextUtil.colorize(displayname), replace);
    }

    protected ScoreboardBuilder(Player player, String displayname, boolean replace) {
        player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
        this.scoreboard = player.getScoreboard();
        this.mainObjective = this.registerObjective("main", Criteria.DUMMY, TextUtil.colorize(displayname), replace);
    }

    @Override
    public final @NonNull Objective registerObjective(String id, Criteria criteria, String displayname, boolean replace) {
        Objective objective = getObjective(id);

        // here when the old objective is exists.
        if (objective != null) {
            if (!replace) return objective; //: and won't be replaced.
            objective.unregister(); //: and will be removed for replacing it
        }

        // here when the objective is not exist or will be created after replace.
        objective = getBoard().registerNewObjective(id, criteria.getId());
        objective.setDisplayName(displayname);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        return objective;
    }

    @Override
    public final @NonNull Map<Integer, IScore> getScores() {
        return scores;
    }

    @Override
    public final @NonNull Scoreboard getBoard() {
        return scoreboard;
    }

    @Override
    public final @NonNull Objective getMainObjective() {
        return mainObjective;
    }

    private void setScore(@NonNull IScore<?> score) {
        getScores().put(score.getScore(), score);
    }

    @Override
    public void setSimpleScore(@NonNull String content, int id) {
        this.setScore(new SimpleScore(this, content, id));
    }

    @Override
    public void setAnimatedScore(long ticks, @NonNull List<String> content, int id) {
        this.setScore(new AnimatedScore(this, ticks, content, id));
    }

    public final SpigotPlugin getPlugin() {
        return plugin;
    }
}
