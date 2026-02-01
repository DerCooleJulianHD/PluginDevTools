package de.api.devtools.scoreboard;

import de.api.devtools.scoreboard.builder.ScoreboardBuilder;
import org.bukkit.entity.Player;

public abstract class PlayerScoreboard extends ScoreboardBuilder {
    protected final Player player;

    public PlayerScoreboard(Player player, String displayname) {
        super(player, displayname, true);
        this.player = player;
        this.set(player);
    }

    public Player getPlayer() {
        return player;
    }
}
