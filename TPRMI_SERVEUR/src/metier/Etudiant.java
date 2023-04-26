package metier;

import java.io.Serializable;
import java.util.Date;

public class Etudiant implements Serializable {
    private int idEtudiant;
    private String nomEtudiant;
    private String login;
    private String password;
    private Date dateNaissance;

    public Etudiant() {
    }

    public Etudiant(int idEtudiant, String nomEtudiant, String login, String password, Date dateNaissance) {
        this.idEtudiant = idEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.login = login;
        this.password = password;
        this.dateNaissance = dateNaissance;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "idEtudiant=" + idEtudiant +
                ", nomEtudiant='" + nomEtudiant + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
