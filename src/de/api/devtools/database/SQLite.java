package de.api.devtools.database;

import de.api.devtools.plugin.SpigotPlugin;
import org.sqlite.JDBC;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

public final class SQLite {

    private final SpigotPlugin plugin = SpigotPlugin.getInstance();
    private Connection connection;
    private final File file;

    public SQLite(File file) {
        this.file = file;

        final JDBC jdbc = new JDBC();
        final Properties properties = new Properties();
        final String url = JDBC.PREFIX + file;

        try {
            this.connection = jdbc.connect(url, properties);
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "failed to connect to sqlite: " + url);
        }
    }

    public SQLite(File dir, String filename) {
        this(new File(dir, filename));
    }

    public SpigotPlugin getPlugin() {
        return plugin;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public File getFile() {
        return file;
    }
}
