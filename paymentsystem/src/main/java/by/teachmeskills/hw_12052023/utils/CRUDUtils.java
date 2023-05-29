package by.teachmeskills.hw_12052023.utils;

import by.teachmeskills.hw_12052023.model.AccountStatus;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    private static final String INSERT_MERCHANT_QUERY = "INSERT INTO merchant (id, nameMerchant, createdAt) Values (?, ?, ?)";
    private static final String DELETE_MERCHANT_QUERY = "DELETE FROM merchant WHERE id = ?";
    private static final String GET_MERCHANTS_QUERY = "SELECT * FROM merchant";
    private static final String SELECT_MERCHANT_ID_QUERY = "SELECT * FROM merchant WHERE id = ?";

    private final static String INSERT_BANK_ACCOUNT_QUERY = "INSERT INTO bank_accounts (id, merchantId, status, accountNumber, createdAt) Values (?, ?, ?, ?, ?)";
    private final static String GET_MERCHANT_BANK_ACCOUNTS = "SELECT * FROM bank_accounts WHERE merchantId = ? ORDER BY status ASC, createdAt ASC";
    private static final String DELETE_BANK_ACCOUNT_QUERY = "UPDATE bank_accounts SET status = ? WHERE accountNumber = ?";
    private static final String DELETE_BANK_ACCOUNT_All = "DELETE FROM bank_accounts WHERE merchantId = ?";
    private static final String UPDATE_ACCOUNT_NUMBER = "UPDATE bank_accounts SET accountNumber = ? WHERE merchantId = ? AND id = ?";
    private static final String UPDATE_STATUS_BANK_ACCOUNT_QUERY = "UPDATE bank_accounts SET status = ? WHERE accountNumber = ?";

    private static PreparedStatement preparedStatement;

    public static Merchant createMerchant(Merchant merchant) { //создание мерчанта (1)
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(INSERT_MERCHANT_QUERY);
            preparedStatement.setString(1, merchant.getId());
            preparedStatement.setString(2, merchant.getName());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(merchant.getCreatedAt()));
            preparedStatement.executeUpdate();
            System.out.println("Мерчант добавлен в базу");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При добавлении мерчанта в базу произошла ошибка");
        }
        return merchant;
    }

    public static List<Merchant> getAllMerchants() { //возвращает всех мерчантов (2)
        List<Merchant> merchants = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(GET_MERCHANTS_QUERY);
            while (resultSet.next()) {
                merchants.add(new Merchant(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getTimestamp(3).toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchants;
    }

    public static Merchant getMerchantById(String id) { // получение информации о мерчанте по id (3)
        Merchant merchant = null;
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(SELECT_MERCHANT_ID_QUERY);
            preparedStatement.setString(1, id);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                merchant = new Merchant(set.getString(1), set.getString(2),
                        set.getTimestamp(3).toLocalDateTime());
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return merchant;
    }

    public static void deleteMerchantById(String idMerchant) { // удаление мерчанта (4)
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(DELETE_MERCHANT_QUERY);
            preparedStatement.setString(1, idMerchant);
            deleteBankAccountAll(idMerchant);
            System.out.println("Мерчент с ID " + idMerchant + " успешно удален!\n");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При удалении мерчанта из базы произошла ошибка\n");
        }
    }

    public static void deleteBankAccountAll(String idDelete) { // удаление всех аккаунтов мерчанта (4)
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(DELETE_BANK_ACCOUNT_All);
            preparedStatement.setString(1, idDelete);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static BankAccount addBankAccount(BankAccount bankAccount) { // добавление нового банковского аккаунта мерчанту (5)
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(INSERT_BANK_ACCOUNT_QUERY);
            preparedStatement.setString(1, bankAccount.getId());
            preparedStatement.setString(2, bankAccount.getMerchantId());
            preparedStatement.setString(3, bankAccount.getStatus().name());
            preparedStatement.setString(4, bankAccount.getAccountNumber());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(bankAccount.getCreatedAt()));
            preparedStatement.executeUpdate();
            System.out.println("Банковский аккаунт добавлен в базу\n");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При добавлении банковского аккаунта в базу произошла ошибка\n");
        }
        return bankAccount;
    }

    public static List<BankAccount> getMerchantBankAccounts(Merchant merchant) {// получение информации о банковских аккаунтах мерчанта (6)
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_MERCHANT_BANK_ACCOUNTS);
            preparedStatement.setString(1, merchant.getId());
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                AccountStatus status = (set.getString("status").equals("ACTIVE")) ?
                        AccountStatus.ACTIVE : AccountStatus.DELETED;
                bankAccounts.add(new BankAccount(set.getString("id"), set.getString("merchantId"),
                        status, set.getString("accountNumber"), set.getTimestamp("createdAt").toLocalDateTime()));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bankAccounts;
    }

    public static void updateBankAccount(BankAccount bankAccount, String newNumber) {
        // редактирование банковского аккаунта мерчанта (7)
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_NUMBER);
            preparedStatement.setString(1, newNumber);
            preparedStatement.setString(2, bankAccount.getMerchantId());
            preparedStatement.setString(3, bankAccount.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteBankAccountById(String accountNumber) { // удаление банковского аккаунта мерчанта (8)
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(DELETE_BANK_ACCOUNT_QUERY);
            preparedStatement.setString(1, AccountStatus.DELETED.toString());
            preparedStatement.setString(2, accountNumber);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При удалении банковского аккаунта из базы произошла ошибка\n");
        }
    }

    public static BankAccount updateStatusMerchantBankAccount(BankAccount bankAccount) {
        try (Connection connection = DbUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(UPDATE_STATUS_BANK_ACCOUNT_QUERY);
            preparedStatement.setString(1, bankAccount.getStatus().toString());
            preparedStatement.setString(2, bankAccount.getAccountNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bankAccount;
    }
}