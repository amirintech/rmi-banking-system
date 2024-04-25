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
import java.util.Map;

public class DatabaseService {
    private final String filename;
    private final Gson gson;
    private Map<String, Map<String, Account>> accounts;
    private Map<String, Map<String, Transaction>> transactions;

    public DatabaseService(String filename) {
        this.filename = filename;
        this.gson = new Gson();
        this.accounts = new HashMap<>();
        this.transactions = new HashMap<>();
        load();
    }

    private void load() {
        try (FileReader reader = new FileReader(filename)) {
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

    public void save() {
        Map<String, Object> data = new HashMap<>();
        data.put("accounts", accounts);
        data.put("transactions", transactions);

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Could not write to the file: " + e.getMessage());
        }
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
}
