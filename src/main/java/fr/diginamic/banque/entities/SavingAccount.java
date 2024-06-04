package fr.diginamic.banque.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIVRET_A")
public class SavingAccount extends Account {
    @Column(name = "TAUX")
    private double rate;

    public SavingAccount() {
        super();
    }

    public SavingAccount(String number, double balance, Bank bank, Set<BankTransaction> bankTransactions, double rate) {
        super(number, balance, bank, bankTransactions);
        this.rate = rate;
    }
}
