package de.api.devtools.scoreboard.builder;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.scoreboard.IScoreboard;
import de.api.devtools.scoreboard.score.AnimatedScore;
import de.api.devtools.scoreboard.score.SimpleScore;
import de.api.devtools.scoreboard.util.Criteria;
import de.api.devtools.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ScoreboardBuilder implements IScoreboard {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final Scoreboard scoreboard;
    protected final Objective mainObjective;

    protected final Map<Integer, SimpleScore> simpleScores = new HashMap<>();
    protected final Map<Integer, AnimatedScore> animatedScores = new HashMap<>();

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
    public final @Nonnull Objective registerObjective(String id, Criteria criteria, String displayname, boolean replace) {
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
    public final @Nonnull Scoreboard getBoard() {
        return scoreboard;
    }

    @Override
    public final @Nonnull Objective getMainObjective() {
        return mainObjective;
    }

    @Override
    public @Nonnull Map<Integer, SimpleScore> getSimpleScores() {
        return simpleScores;
    }

    @Override
    public @Nonnull Map<Integer, AnimatedScore> getAnimatedScores() {
        return animatedScores;
    }

    @Override
    public void setSimpleScore(@Nonnull String content, int id) {
        simpleScores.put(id, new SimpleScore(this, content, id));
    }

    @Override
    public void setAnimatedScore(long ticks, @Nonnull List<String> content, int id) {
        animatedScores.put(id, new AnimatedScore(this, ticks, content, id));
    }

    public final SpigotPlugin getPlugin() {
        return plugin;
    }
}
