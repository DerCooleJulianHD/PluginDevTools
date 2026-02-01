package de.api.devtools.scoreboard;

import org.bukkit.entity.Player;

public class SimplePlayerScoreboard extends SimpleScoreboard {

    protected final Player player;

    public SimplePlayerScoreboard(Player player, String displayname) {
        super(player, displayname ,true);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
