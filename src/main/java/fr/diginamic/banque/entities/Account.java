package fr.diginamic.banque.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity
@Table(name = "COMPTE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@TableGenerator(name = "account_generator", table = "ID_COMPTE", allocationSize = 1, initialValue = 1)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "account_generator")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NUMERO")
    private String number;

    @Column(name = "SOLDE")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "ID_BANQUE")
    private Bank bank;

    @OneToMany(mappedBy = "account")
    private Set<BankTransaction> bankTransactions;

    public Account() {
    }

    public Account(String number, double balance, Bank bank, Set<BankTransaction> bankTransactions) {
        this.number = number;
        this.balance = balance;
        this.bank = bank;
        this.bankTransactions = bankTransactions;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setBankTransactions(Set<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }
}
