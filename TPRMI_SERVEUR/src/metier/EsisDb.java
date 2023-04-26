package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EsisDb {
    List<Etudiant> listeEtudiants;
    public EsisDb(){
    }

    public List<Etudiant> getListeEtudiants(){
        return listeEtudiants;
    }


    public Etudiant connexion(String login, String password){
        for (Etudiant etudiant:listeEtudiants){
            if (etudiant.getLogin().equals(login) && etudiant.getPassword().equals(password))
                return etudiant;
        }
        return null;
    }
}
