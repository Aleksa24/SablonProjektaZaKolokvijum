/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * izmeni equals i hashcode
 * @author Aleksa
 */
public class Korisnik implements Serializable{//izmeni equals i hashcode
    
    String korisnickoIme;
    //parametri

    public Korisnik(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.korisnickoIme);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        if (!this.korisnickoIme.equals(other.korisnickoIme)) {
            return false;
        }
        return true;
    }
    
    
    
}
