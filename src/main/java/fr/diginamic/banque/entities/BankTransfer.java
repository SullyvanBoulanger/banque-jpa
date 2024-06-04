package fr.diginamic.banque.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "VIREMENT")
public class BankTransfer extends BankTransaction {
    @Column(name = "BENEFICIAIRE")
    private String beneficiary;

    public BankTransfer() {
        super();
    }

    public BankTransfer(LocalDateTime date, double amount, String motif, Account account, String beneficiary) {
        super(date, amount, motif, account);
        this.beneficiary = beneficiary;
    }
}
