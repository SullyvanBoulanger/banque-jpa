package fr.diginamic.banque.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Adress {
    @Column(name = "NUMERO")
    private int number;
    
    @Column(name = "RUE")
    private String street;
    
    @Column(name = "CODE_POSTAL")
    private int zipCode;
    
    @Column(name = "VILLE")
    private String city;

    public Adress() {}

    public Adress(int number, String street, int zipCode, String city) {
        this.number = number;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }
}
