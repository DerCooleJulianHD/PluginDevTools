package de.api.devtools.database;

import de.api.devtools.plugin.SpigotPlugin;
import org.sqlite.JDBC;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Properties;
import java.util.logging.Level;

//: database type to connect to a db file.
public final class SQLite {

    private final SpigotPlugin plugin = SpigotPlugin.getInstance();
    private Connection connection;
    private final File file;

    public SQLite(File file) {
        this.file = file;

        final JDBC jdbc = new JDBC();
        final Properties properties = new Properties();
        final String url = JDBC.PREFIX + (file.getName().endsWith(".db") ? file : file.getName() + ".db");

        try {
            this.connection = jdbc.connect(url, properties);

            this.connection.setAutoCommit(true);
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "failed to connect to sqlite: " + url);
        }
    }

    public void update(String query) {
        try {
            final Statement statement = this.connection.createStatement();

            statement.executeQuery(query);
            statement.close();

            if (!connection.getAutoCommit())
                connection.commit();
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "failed to create statement.", e);
        }
    }

    public Struct createStruct(String name, Object[] attr) {
        try {
            return connection.createStruct(name, attr);
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "failed to create struct (" + name + ")", e);
        }

        return null;
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
