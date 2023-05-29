package by.teachmeskills.client;

import by.teachmeskills.hw_12052023.exceptions.BankAccountNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.service.MerchantService;

import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MerchantService merchantService = new MerchantService();
        boolean input = false;

        System.out.println("""
                Выберите необходимый пункт меню:
                1 - создание мерчанта;
                2 - получение всех мерчантов, зарегистрированных в платежной системе;
                3 - получение информации о мерчанте по id;
                4 - удаление мерчанта;
                5 - добавление нового банковского аккаунта мерчанту;     
                6 - получение информации о банковских аккаунтах мерчанта;
                7 - редактирование банковского аккаунта мерчанта;
                8 - удаление банковского аккаунта мерчанта;
                0 - выход;
                """);

        while (!input) {
            System.out.print("Введите число: ");
            switch (scanner.nextLine()) {

                case "0" -> input = true;

                case "1" -> {
                    System.out.print("Введите название мерчанта: ");
                    String nameMerchant = scanner.nextLine();
                    merchantService.createMerchant(nameMerchant);
                    System.out.println();
                }

                case "2" -> {
                    merchantService.getMerchants().forEach(s ->
                            System.out.printf("Мерчант: ID - %s, имя - %s, дата добавления в базу - %s \n",
                                    s.getId(), s.getName(), s.getCreatedAt()));
                    System.out.println();
                }

                case "3" -> {
                    System.out.print("Введите ID мерчанта, о котором надо получить информацию: ");
                    String merchantId = scanner.nextLine();
                    try {
                        System.out.println(merchantService.getMerchantsById(merchantId));
                    } catch (MerchantNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case "4" -> {
                    System.out.print("Введите ID меранта, которое хотите удалить: ");
                    String merchantId = scanner.nextLine();
                    try {
                        merchantService.deletedMerchant(merchantId);
                    } catch (MerchantNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case "5" -> {
                    System.out.print("Введите ID мерчанта, которому необходимо добавить банковский аккаунт: ");
                    String merchantId = scanner.nextLine();
                    try {
                        Merchant merchant = merchantService.getMerchantsById(merchantId);
                        System.out.print("Введите номер банковского аккаунта мерчента: ");
                        String numberAccount = scanner.nextLine();
                        merchantService.addBankAccount(merchant, numberAccount);
                    } catch (NoBankAccountsFoundException | MerchantNotFoundException e) {
                        System.out.print(e.getMessage());
                    }
                }

                case "6" -> {
                    System.out.print("Введите ID мерчента, для которого необходимо предоставить банковские аккаунты: ");
                    String merchantId = scanner.nextLine();
                    try {
                        Merchant merchant = merchantService.getMerchantsById(merchantId);
                        merchantService.getMerchantBankAccounts(merchant).forEach(s ->
                                System.out.printf("Банк аккаунт: id банковского аккаунта  - %s, ID мерчанта - %s, статус -%s," +
                                                " номер аккаунта - %s, дата добавления в базу - %s\n", s.getId(), s.getMerchantId(), s.getStatus(),
                                        s.getAccountNumber(), s.getCreatedAt()));
                        System.out.println();
                    } catch (MerchantNotFoundException | NoBankAccountsFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case "7" -> {
                    System.out.print("Введите id мерчента, у которого редактируется аккаунт: ");
                    String merchantId = scanner.nextLine();

                    try {
                        Merchant merchant = merchantService.getMerchantsById(merchantId);
                        System.out.print("Введите id банковского аккаунта, который необходимо редактировать: ");
                        String numberAccount = scanner.nextLine();
                        System.out.print("Введите новый номер аккаунта: ");
                        String newNumber = scanner.nextLine();
                        merchantService.updateBankAccount(merchant, numberAccount, newNumber);
                    } catch (NoBankAccountsFoundException | MerchantNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "8" -> {
                    System.out.print("Введите ID мерчанта, о которого надо удалить аккаунт: ");
                    String merchantId = scanner.nextLine();
                    try {
                        Merchant merchant = merchantService.getMerchantsById(merchantId);
                        System.out.print("Введите номер банковского аккаунта который необходимо удалить: ");
                        String numberAccount = scanner.nextLine();
                        merchantService.deleteBankAccount(merchant, numberAccount);
                    } catch (MerchantNotFoundException | BankAccountNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}

