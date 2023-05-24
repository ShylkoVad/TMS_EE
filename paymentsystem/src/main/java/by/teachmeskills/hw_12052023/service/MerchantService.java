package by.teachmeskills.hw_12052023.service;

import by.teachmeskills.hw_12052023.exceptions.BankAccountNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.hw_12052023.model.AccountStatus;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.utils.CRUDUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class MerchantService {
    static Scanner scanner = new Scanner(System.in);

    public void createMerchant(String nameMerchant) { //создание мерчанта (1)
        String id = String.valueOf(UUID.randomUUID());
        LocalDateTime createdAt = LocalDateTime.now();
        Merchant merchant = new Merchant(id, nameMerchant, createdAt);
        CRUDUtils.createMerchant(merchant);
    }

    public List<Merchant> getMerchants() { //возвращает всех мерчантов (2)
        List<Merchant> merchants = CRUDUtils.getAllMerchants();
        if (merchants.isEmpty()) {
            System.out.println("Данные о мерчантах в базе данных отсутствуют");
        }
        return merchants;
    }

    public Merchant getMerchantsById(String merchantId) throws MerchantNotFoundException { // получение информации о мерчанте по id (3)
        Merchant merchant = CRUDUtils.getMerchantById(merchantId);
        if (merchant == null) {
            throw new MerchantNotFoundException("Мерчант с ID " + merchantId + " отсутствует в базе.\n");
        }
        return merchant;
    }

    public void deletedMerchant(String merchantId) throws MerchantNotFoundException { // удаление мерчанта (4)
        Merchant merchant = getMerchantsById(merchantId);
        if (merchant != null) {
            CRUDUtils.deleteMerchantById(merchantId);
        }
    }

    public void addBankAccount(Merchant merchant, String numberAccount) throws NoBankAccountsFoundException, MerchantNotFoundException {
        // добавление нового банковского аккаунта мерчанту (5)
        validateBankAccountNumber(numberAccount);
        BankAccount bankAccount = new BankAccount(merchant.getId(), numberAccount);
        List<BankAccount> bankAccounts = CRUDUtils.getMerchantBankAccounts(merchant);
        Optional<BankAccount> account = bankAccounts.stream().filter(s -> s.getAccountNumber().equals(bankAccount.getAccountNumber())).findAny();
        account.ifPresentOrElse(q -> Optional.of(q).filter(s -> s.getStatus().equals(AccountStatus.DELETED)).ifPresent(s -> {
            s.setStatus(AccountStatus.ACTIVE);
            CRUDUtils.updateStatusMerchantBankAccount(s);
        }), () -> CRUDUtils.addBankAccount(bankAccount));
    }

    public List<BankAccount> getMerchantBankAccounts(Merchant merchant) throws NoBankAccountsFoundException {
        // получение информации о банковских аккаунтах мерчанта (6)
        List<BankAccount> accounts = CRUDUtils.getMerchantBankAccounts(merchant);
        if (accounts.isEmpty()) {
            throw new NoBankAccountsFoundException("У мерчанта " + merchant.getName() + " отсутствуют банковские аккаунты\n");
        }
        return accounts;
    }

    public void updateBankAccount(String merchantId) throws NoBankAccountsFoundException, MerchantNotFoundException {
        Merchant merchant = getMerchantsById(merchantId);
        getMerchantBankAccounts(merchant).forEach(s ->
                System.out.printf("Банк аккаунт: id банковского аккаунта  - %s, ID мерчанта - %s, статус -%s," +
                                " номер аккаунта - %s, дата добавления в базу - %s\n", s.getId(), s.getMerchantId(), s.getStatus(),
                        s.getAccountNumber(), s.getCreatedAt()));
        System.out.print("Введите id банковского аккаунта, который необходимо редактировать: ");
        String numberAccount = scanner.nextLine();
        List<BankAccount> account = CRUDUtils.getMerchantBankAccounts(merchant);
        BankAccount bankAccount = account.stream().filter(s -> s.getId().equals(numberAccount)).findAny().orElse(null);
        if (bankAccount == null) {
            throw new NoBankAccountsFoundException("Данного аккаунта не существует.\n");
        }
        System.out.print("Введите новый номер аккаунта: ");
        String newNumber = scanner.nextLine();
        validateBankAccountNumber(newNumber);
        CRUDUtils.updateBankAccount(bankAccount, newNumber);
        System.out.println("Банковский аккаунт успешно отредактирован.\n");
    }

    public void deleteBankAccount(Merchant merchant) throws BankAccountNotFoundException { // удаление банковского аккаунта мерчанта
        List <BankAccount> accounts = CRUDUtils.getMerchantBankAccounts(merchant);
        System.out.print("Введите номер банковского аккаунта который необходимо удалить: ");
        String numberAccount = scanner.nextLine();
        BankAccount account = accounts.stream().filter(s -> s.getAccountNumber().equals(numberAccount)).
                findAny().orElse(null);
        if (account == null){
            throw new BankAccountNotFoundException("Банковский аккаунт отсутствует у мерчанта");
        }
        CRUDUtils.deleteBankAccountById(numberAccount);
        System.out.println("У банковского аккаунта с numberAccount - " + numberAccount + " status изменен на DELETED \n");
    }

    private static boolean validateBankAccountNumber(String bankAccount) throws NoBankAccountsFoundException {
        if (bankAccount.length() == 8 && bankAccount.matches("\\d+")) {
            return true;
        } else {
            throw new NoBankAccountsFoundException("Номер банковского аккаунта введен некорректно.\n");
        }
    }
}
