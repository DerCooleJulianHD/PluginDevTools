package de.api.devtools.scoreboard;

import org.bukkit.entity.Player;

public abstract class AnimatedPlayerScoreboard extends AnimatedScoreboard {

    protected final Player player;

    public AnimatedPlayerScoreboard(Player player, String displayName, long ticks) {
        super(player, displayName, ticks, true);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
