package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Account;
import models.Transaction;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseService {
    private final Gson gson;
    private final String path;
    private Map<String, Map<String, Account>> accounts;
    private Map<String, Map<String, Transaction>> transactions;

    public DatabaseService(DBLocation location) {
        this.path = "src/main/resources/" + location.getValue();
        this.gson = new Gson();
        this.accounts = new HashMap<>();
        this.transactions = new HashMap<>();
        load();
    }

    private void load() {
        try (FileReader reader = new FileReader(path)) {
            Type accountsType = new TypeToken<Map<String, Map<String, Account>>>() {
            }.getType();
            Type transactionsType = new TypeToken<Map<String, Map<String, Transaction>>>() {
            }.getType();

            Map<String, Object> data = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {
            }.getType());
            if (data != null) {
                this.accounts = gson.fromJson(gson.toJson(data.get("accounts")), accountsType);
                this.transactions = gson.fromJson(gson.toJson(data.get("transactions")), transactionsType);
            }
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }

    private void save() {
        Map<String, Object> data = new HashMap<>();
        data.put("accounts", accounts);
        data.put("transactions", transactions);

        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Could not write to the file: " + e.getMessage());
        }
    }

    public Map<String, Account> getWorkerAccounts(String id) {
        Map<String, Account> accounts = this.accounts.get(id);
        if (accounts == null)
            accounts = new HashMap<>();

        return accounts;
    }

    public Map<String, Transaction> getAccountTransactions(String id) {
        Map<String, Transaction> transactions = this.transactions.get(id);
        if (transactions == null)
            transactions = new HashMap<>();

        return transactions;
    }

    public void addAccount(String workerId, Account account) {
        accounts.putIfAbsent(workerId, new HashMap<>());
        accounts.get(workerId).put(account.getId(), account);
        save();
    }

    public void addTransaction(String workerId, Transaction transaction) {
        transactions.putIfAbsent(workerId, new HashMap<>());
        transactions.get(workerId).put(transaction.getId(), transaction);
        save();
    }

    public enum DBLocation {
        WEST("west_db.json"),
        EAST("east_db.json");

        private final String value;

        DBLocation(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
