package de.api.devtools.common.scoreboard;

import de.api.devtools.common.plugin.MinecraftPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;


public final class GlobalScoreboard extends ScoreboardBuilder {

    public GlobalScoreboard(@NonNull MinecraftPlugin plugin) {
        super(plugin, false);
    }
}
