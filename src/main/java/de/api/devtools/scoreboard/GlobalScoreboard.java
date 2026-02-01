package de.api.devtools.scoreboard;

import de.api.devtools.scoreboard.builder.ScoreboardBuilder;

public final class GlobalScoreboard extends ScoreboardBuilder {

    public GlobalScoreboard(String displayname) {
        super(displayname, false);
    }
}
