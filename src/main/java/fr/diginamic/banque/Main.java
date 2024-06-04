package fr.diginamic.banque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.diginamic.banque.entities.Account;
import fr.diginamic.banque.entities.Adress;
import fr.diginamic.banque.entities.Bank;
import fr.diginamic.banque.entities.BankTransaction;
import fr.diginamic.banque.entities.BankTransfer;
import fr.diginamic.banque.entities.Customer;
import fr.diginamic.banque.entities.LifeInsuranceAccount;
import fr.diginamic.banque.entities.SavingAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        initDatabaseElements(entityManager);

        entityTransaction.commit();

        entityManager.close();
    }

    private static void initDatabaseElements(EntityManager entityManager) {

        Bank b1 = new Bank("ChatEpargneIci", null);
        Bank b2 = new Bank("TanièreÀFric", null);

        Account a1 = new Account(
                "1",
                200,
                b1,
                null);

        Account a2 = new Account(
                "2",
                500,
                b2,
                null);

        Account a3 = new SavingAccount(
                "3",
                2000,
                b1,
                null,
                0.20);

        Account a4 = new LifeInsuranceAccount(
                "3",
                4000,
                b2,
                null,
                LocalDate.of(2040, 3, 10),
                0.20);

        b1.setAccounts(Stream.of(a1, a3).collect(Collectors.toCollection(HashSet::new)));
        b2.setAccounts(Stream.of(a2, a4).collect(Collectors.toCollection(HashSet::new)));

        BankTransaction bt1 = new BankTransaction(
                LocalDateTime.of(2020, 5, 8, 19, 30),
                -100,
                "Achat",
                a1);

        BankTransaction bt2 = new BankTransaction(
                LocalDateTime.of(2022, 2, 1, 12, 0),
                -20,
                "Repas",
                a2);
        a1.setBankTransactions(Stream.of(bt1, bt2).collect(Collectors.toCollection(HashSet::new)));

        BankTransaction bt3 = new BankTransaction(
                LocalDateTime.of(2024, 2, 1, 12, 0),
                -30,
                "Repas",
                a2);
        a2.setBankTransactions(Stream.of(bt3).collect(Collectors.toCollection(HashSet::new)));

        BankTransaction bt4 = new BankTransfer(
                LocalDateTime.of(2024, 5, 7, 12, 0),
                -30,
                "Virement vers compte Courant",
                a3,
                "Compte courant");
        a3.setBankTransactions(Stream.of(bt4).collect(Collectors.toCollection(HashSet::new)));

        Set<Account> accounts1 = new HashSet<>();
        accounts1.add(a1);
        accounts1.add(a2);

        Set<Account> accounts2 = new HashSet<>();
        accounts1.add(a2);

        Customer c1 = new Customer(
                "Chat",
                "Gaze ?",
                LocalDate.of(2004, 3, 25),
                new Adress(
                        17,
                        "Rue des chats",
                        59000,
                        "Paradis"),
                accounts1);

        Customer c2 = new Customer(
                "Fox",
                "Foxy",
                LocalDate.of(2002, 6, 5),
                new Adress(
                        10,
                        "Rue des Renards",
                        62000,
                        "Réalité"),
                accounts2);

        
        Set<Account> accounts3 = Stream.of(a3, a4).collect(Collectors.toCollection(HashSet::new));
        Customer c3 = new Customer(
                "Panda",
                "Roux",
                LocalDate.of(2000, 6, 5),
                new Adress(
                        10,
                        "Rue des Panda",
                        62000,
                        "Paradis"),
                accounts3);

        entityManager.persist(b1);
        entityManager.persist(b2);
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);
        entityManager.persist(a4);
        entityManager.persist(bt1);
        entityManager.persist(bt2);
        entityManager.persist(bt3);
        entityManager.persist(bt4);
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);
    }
}