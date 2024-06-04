package fr.diginamic.banque.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity
@Table(name = "OPERATION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@TableGenerator(name = "bank_transaction_generator", table = "ID_OPERATION", allocationSize = 1, initialValue = 1)
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "bank_transaction_generator")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "MONTANT")
    private double amount;

    @Column(name = "MOTIF")
    private String motif;

    @ManyToOne
    @JoinColumn(name = "ID_COMPTE")
    private Account account;

    public BankTransaction() {}

    public BankTransaction(LocalDateTime date, double amount, String motif, Account account) {
        this.date = date;
        this.amount = amount;
        this.motif = motif;
        this.account = account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
