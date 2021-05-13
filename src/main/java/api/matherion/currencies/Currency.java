package api.matherion.currencies;

import api.matherion.currencies.database.CurrencyDatabase;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class Currency {

    private final CurrencyDatabase currencyDatabase;
    private final String name;
    private final String table;
    private final String nicknameColumn;
    private final String countColumn;

    public void connect() {
        currencyDatabase.connect();
    }

    public void disconnect() {
        currencyDatabase.disconnect();
    }

    public int get(String nickname) {
        try {
            PreparedStatement st = currencyDatabase.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE " + nicknameColumn + " = ?");
            st.setString(1, nickname);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(countColumn);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void set(String nickname, int count) {
        if (get(nickname) == -1) {
            try {
                PreparedStatement st = currencyDatabase.getConnection().prepareStatement("INSERT INTO " + table + " (" + nicknameColumn + "," + countColumn + ") VALUES (?,?)");
                st.setString(1, nickname);
                st.setInt(2, count);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            PreparedStatement st = currencyDatabase.getConnection().prepareStatement("UPDATE " + table + " SET " + countColumn + " = ? WHERE " + nicknameColumn + " = ?");
            st.setString(2, nickname);
            st.setInt(1, count);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAsync(String nickname, int count) {
        Bukkit.getScheduler().runTaskAsynchronously(CurrenciesAPI.getInstance(), () -> set(nickname, count));
    }

    public void add(String nickname, int count) {
        set(nickname, get(nickname) + count);
    }

    public void addAsync(String nickname, int count) {
        setAsync(nickname, get(nickname) + count);
    }

    public void remove(String nickname, int count) {
        set(nickname, get(nickname) - count);
    }

    public void removeAsync(String nickname, int count) {
        setAsync(nickname, get(nickname) - count);
    }

}
