package eu.matherion.currencies.database;

import eu.matherion.currencies.CurrenciesAPI;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CurrencyDatabase {

    @Getter
    private Connection connection;
    private final String address;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public CurrencyDatabase(String address, int port, String database, String username, String password) {
        this.address = address;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + address + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false", username, password);
                CurrenciesAPI.getInstance().getLogger().info("CurrenciesAPI has been connected to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                CurrenciesAPI.getInstance().getLogger().info("CurrenciesAPI has been disconnected to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return (connection != null);
    }

}
