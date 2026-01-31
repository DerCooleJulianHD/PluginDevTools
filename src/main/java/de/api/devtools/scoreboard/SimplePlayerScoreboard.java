package de.api.devtools.scoreboard;

import org.bukkit.entity.Player;

public class SimplePlayerScoreboard extends SimpleScoreboard {

    protected final Player player;

    public SimplePlayerScoreboard(Player player, String displayname, boolean replace) {
        super(player, displayname, replace);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
