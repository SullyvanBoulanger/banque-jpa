package fr.diginamic.banque.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "PRENOM")
    private String firstName;
    
    @Column(name = "NOM")
    private String lastName;
    
    @Column(name = "DATE_NAISSANCE")
    private LocalDate birthDate;

    @Embedded
    private Adress adress;

    @ManyToMany
    @JoinTable(
        name = "CLIENTS_COMPTES",
        joinColumns = @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "ID_COMPTE", referencedColumnName = "ID")
    )
    private Set<Account> accounts;

    public Customer() {}

    public Customer(String firstName, String lastName, LocalDate birthDate, Adress adress, Set<Account> accounts){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.adress = adress;
        this.accounts = accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
