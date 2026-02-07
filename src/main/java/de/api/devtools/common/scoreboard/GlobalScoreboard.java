package de.api.devtools.common.scoreboard;

import de.api.devtools.common.plugin.MinecraftPlugin;

import javax.annotation.Nonnull;

public final class GlobalScoreboard extends ScoreboardBuilder {

    public GlobalScoreboard(@Nonnull MinecraftPlugin plugin) {
        super(plugin, false);
    }
}
