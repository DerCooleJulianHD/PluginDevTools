package de.api.devtools.common.scoreboard;

import de.api.devtools.common.plugin.MinecraftPlugin;
import org.bukkit.entity.Player;


public abstract class PlayerScoreboard extends ScoreboardBuilder {
    protected final Player player;

    public PlayerScoreboard(MinecraftPlugin plugin, Player player) {
        super(plugin, player, true);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
