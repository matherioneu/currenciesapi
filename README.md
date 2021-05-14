## Currencies API
API pro pracování se všemi měnami, tvoření měn apod.

### Použití
Při prvním použití je potřeba nastavit instanci pro API v onEnablu Vašeho pluginu.
```
CurrenciesAPI.setInstance(JavaPlugin javaPlugin);
```
Po nastavení můžete pracovat s dalšími objekty. Pojďme si vytvořit implementaci pro měnu Kredity, která je na serveru od začátku. Nejprve si musíme vytvořit nový databázový objekt **CurrencyDatabase** a uložíme si ho do proměnné **creditsDB**.
```
CurrencyDatabase creditsDB = new CurrencyDatabase(String address, int port, String database, String username, String password);
```

Nyní máme hotový objekt pro pracování s databází. Můžeme si tedy vytvořit objekt Currency, který reprezentuje samotnou měnu, v našem případě Kredity. Můžeme ho vytvořit pomocí CurrenciesAPI nebo pomocí nové instance. Do argumentu **currencyDatabase** dáme náš objekt **creditsDB**. Argument **name** můžete prozatím nechat null, protože se nikde nepoužívá, ale do budoucna bych ho doporučoval vyplnit. 
```
Currency credits = new Currency(CurrencyDatabase currencyDatabase, String name, String table, String nicknameColumn, String countColumn);

Currency credits = CurrenciesAPI.createCurrency(CurrencyDatabase currencyDatabase, String name, String table, String nicknameColumn, String countColumn);
```

Nyní máme vytvořenou novou měnu, která ještě není napojená na databázi. Pro napojení tu je metoda **Currency#connect();**. Pro odpojení **Currency#disconnect();**.

Po napojení můžeme pracovat s dalšími metodami. Zde je jejich list:
```
Currency#get(String nickname); - return int
Currency#set(String nickname, int count); - void
Currency#setAsync(String nickname, int count); - void
Currency#add(String nickname, int count); - void
Currency#addAsync(String nickname, int count); - void
Currency#remove(String nickname, int count); - void
Currency#removeAsync(String nickname, int count); - void
```
