package de.api.devtools.common.scoreboard;

import de.api.devtools.common.plugin.MinecraftPlugin;


public final class GlobalScoreboard extends ScoreboardBuilder {

    public GlobalScoreboard(MinecraftPlugin plugin) {
        super(plugin, false);
    }
}
