package de.api.devtools.scoreboard;

import org.bukkit.entity.Player;

public abstract class PlayerScoreboard extends ScoreboardBuilder {
    protected final Player player;

    public PlayerScoreboard(Player player) {
        super(player, true);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
