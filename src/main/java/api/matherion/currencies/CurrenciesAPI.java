package api.matherion.currencies;

import api.matherion.currencies.database.CurrencyDatabase;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class CurrenciesAPI {

    @Getter
    protected static CurrenciesAPI currenciesAPI;
    @Getter
    @Setter
    private static JavaPlugin instance;

    public CurrenciesAPI() {
        currenciesAPI = this;
    }

    public static Currency createCurrency(CurrencyDatabase currencyDatabase, String name, String table, String nicknameColumn, String countColumn) {
        return new Currency(currencyDatabase, name, table, nicknameColumn, countColumn);
    }

}
