package eu.matherion.currencies;

import eu.matherion.currencies.database.CurrencyDatabase;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesAPI {

    @Getter
    @Setter
    private static JavaPlugin instance;
    @Getter
    protected static CurrenciesAPI currenciesAPI;
    private List<Currency> currencies = new ArrayList<>();

    public static List<Currency> getCurrencies() {
        return currenciesAPI.currencies;
    }

    public static Currency createCurrency(CurrencyDatabase currencyDatabase, String name, String table, String nicknameColumn, String countColumn) {
        Currency currency = new Currency(currencyDatabase, name, table, nicknameColumn, countColumn);
        getCurrencies().add(currency);
        return currency;
    }

}
