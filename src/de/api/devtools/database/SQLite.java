package de.api.devtools.database;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.FileManager;
import org.sqlite.JDBC;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

//: database type to connect to a db file.
public final class SQLite implements SQLDatabase {

    private final SpigotPlugin plugin = SpigotPlugin.getInstance();
    private Connection connection;
    private final File file;

    public SQLite(File file) {
        if (!file.getName().endsWith(".db")) {
            throw new IllegalStateException("Database Files need to end with '.db'");
        }

        this.file = file;

        final JDBC jdbc = new JDBC();
        final Properties properties = new Properties();

        FileManager.create(file);

        final String url = JDBC.PREFIX + file;

        try {
            this.connection = jdbc.connect(url, properties);

            this.connection.setAutoCommit(true);
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
