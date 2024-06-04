package fr.diginamic.banque.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ASSURANCE_VIE")
public class LifeInsuranceAccount extends Account{
    @Column(name = "DATE_FIN")
    private LocalDate endDate;

    @Column(name = "TAUX")
    private double rate;

    public LifeInsuranceAccount() {
        super();
    }

    public LifeInsuranceAccount(String number, double balance, Bank bank, Set<BankTransaction> bankTransactions, LocalDate endDate, double rate) {
        super(number, balance, bank, bankTransactions);
        this.endDate = endDate;
        this.rate = rate;
    }
}
