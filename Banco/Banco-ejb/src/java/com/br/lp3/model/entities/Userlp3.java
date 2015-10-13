/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pedro.michilis
 */
@Entity
@Table(name = "USERLP3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userlp3.findAll", query = "SELECT u FROM Userlp3 u"),
    @NamedQuery(name = "Userlp3.findByIdUser", query = "SELECT u FROM Userlp3 u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "Userlp3.findByUsername", query = "SELECT u FROM Userlp3 u WHERE u.username = :username"),
    @NamedQuery(name = "Userlp3.findByPassword", query = "SELECT u FROM Userlp3 u WHERE u.password = :password"),
    @NamedQuery(name = "Userlp3.findBySaldo", query = "SELECT u FROM Userlp3 u WHERE u.saldo = :saldo")})
public class Userlp3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private double saldo;

    public Userlp3() {
    }

    public Userlp3(Integer idUser) {
        this.idUser = idUser;
    }

    public Userlp3(Integer idUser, String username, String password, double saldo) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.saldo = saldo;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userlp3)) {
            return false;
        }
        Userlp3 other = (Userlp3) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.Userlp3[ idUser=" + idUser + " ]";
    }
    
}
