package de.api.devtools.common.scoreboard;

import de.api.devtools.common.plugin.MinecraftPlugin;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;


public abstract class PlayerScoreboard extends ScoreboardBuilder {
    protected final Player player;

    public PlayerScoreboard(@NonNull MinecraftPlugin plugin, Player player) {
        super(plugin, player, true);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
