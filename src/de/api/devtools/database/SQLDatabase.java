package de.api.devtools.database;

import de.api.devtools.plugin.SpigotPlugin;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.logging.Level;

public interface SQLDatabase {

    @Nullable
    Connection getConnection();

    default void update(String query) {
        if (!isConnected())
            return;

        try {
            final Statement statement = getConnection().createStatement();

            statement.executeQuery(query);
            statement.close();

            if (!getConnection().getAutoCommit())
                getConnection().commit();
        } catch (SQLException e) {
            getPlugin().getLogger().log(Level.SEVERE, "failed to create statement.", e);
        }
    }

    @Nullable
    default Struct createStruct(String name, Object[] attr) {
        if (!isConnected())
            return null;

        try {
            return getConnection().createStruct(name, attr);
        } catch (SQLException e) {
            getPlugin().getLogger().log(Level.SEVERE, "failed to create struct (" + name + ")", e);
        }

        return null;
    }

    default boolean isConnected() {
        return getConnection() != null;
    }

    SpigotPlugin getPlugin();
}
