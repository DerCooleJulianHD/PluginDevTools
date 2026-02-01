package de.api.devtools.scoreboard;

import org.bukkit.entity.Player;

public abstract class AnimatedPlayerScoreboard extends AnimatedScoreboard {

    protected final Player player;

    public AnimatedPlayerScoreboard(Player player, String displayName) {
        super(player, displayName, true);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
