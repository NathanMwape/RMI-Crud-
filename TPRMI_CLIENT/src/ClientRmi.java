import metier.Etudiant;
import service.IEtudiantRemote;

import java.rmi.Naming;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClientRmi {
    public static void menu(){
        System.out.println("%%=====================================================================%");
        // Afficher le menu de choix
        System.out.println("Bienvenue dans l'application de gestion des étudiants !");
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1. Se connecter");
        System.out.println("2. Lister tous les étudiants");
        System.out.println("3. Ajouter un nouvel étudiant");
        System.out.println("4. Modifier un étudiant existant");
        System.out.println("5. Supprimer un étudiant existant");
        System.out.println("6. Rechercher un étudiant par nom");
        System.out.println("0. Quitter");
        System.out.println("%%=========================================================================");
    }
    public static void main(String[] args) throws Exception {
        // Obtenir le stub de l'objet distant
        IEtudiantRemote stub = (IEtudiantRemote) Naming.lookup("rmi://localhost:1099/skeleton");




        Scanner sc = new Scanner(System.in);
        int choix = -1;

        menu();
        while (choix != 0) {
            System.out.print("Entrez votre choix : ");
            choix = sc.nextInt();
            switch (choix) {
                case 1:
                    // Se connecter
                    System.out.println("Se connecter");
                    System.out.print("Login : ");
                    String login = sc.next();
                    System.out.print("Password : ");
                    String password = sc.next();
                    Etudiant etudiantConnecte = stub.connexion(login, password);
                    if (etudiantConnecte != null) {
                        System.out.println("Connexion réussie pour l'étudiant " + etudiantConnecte.getNomEtudiant());
                        menu();
                    } else {
                        System.out.println("Login ou mot de passe incorrect !");
                        menu();
                    }
                    break;

                case 2:
                    // Lister tous les étudiants
                    List<Etudiant> etudiants = stub.getListEtudiants();
                    System.out.println("\tListe des étudiants déjà enregistrés :");
                    for (Etudiant e : etudiants) {
                        System.out.println(" \t\t"+e.getIdEtudiant() + ". " + e.getNomEtudiant());
                    }
                    break;

                case 3:
                    // Ajouter un nouvel étudiant
                    System.out.println("Ajouter un Etudiant");
                    System.out.print("\tID : ");
                    int idEtudiant = sc.nextInt();
                    System.out.print("\tNom : ");
                    String nom = sc.next();
                    System.out.print("\tLogin : ");
                    String loginAjout = sc.next();
                    System.out.print("\tPassword : ");
                    String passwordAjout = sc.next();
                    Etudiant nouvelEtudiant = new Etudiant(idEtudiant, nom, loginAjout, passwordAjout, new Date());
                    stub.ajouterEtudiant(nouvelEtudiant);
                    System.out.println("Nouvel étudiant ajouté !");
                    break;

                case 4:
                    // Modifier un étudiant existant
                    System.out.println("Modification d'etudiant");
                    System.out.print("\tID de l'étudiant à modifier : ");
                    int idEtudiantModif = sc.nextInt();
                    System.out.print("\tNouveau nom : ");
                    String nomModif = sc.next();
                    System.out.print("\tNouveau login : ");
                    String loginModif = sc.next();
                    System.out.print("\tNouveau password : ");
                    String passwordModif = sc.next();
                    Etudiant etudiantModifie = new Etudiant(idEtudiantModif, nomModif, loginModif, passwordModif, new Date());
                    stub.modifierEtudiant(idEtudiantModif, etudiantModifie);
                    System.out.println("Étudiant modifié !");
                    break;

                case 5:
                    // Supprimer un étudiant existant
                    System.out.println("Suppression d'un etudiant ");
                    System.out.print("\t  ID de l'étudiant à supprimer : ");
                    int idEtudiantSuppr = sc.nextInt();
                    stub.supprimerEtudiant(idEtudiantSuppr);
                    System.out.println("Étudiant numero "+ idEtudiantSuppr+ " supprimé !");
                    break;

                case 6:
                    // Rechercher un étudiant par nom
                    System.out.println("Rechercher un étudiant par nom");
                    System.out.print("\tNom de l'étudiant à rechercher : ");
                    String nomRecherche = sc.next();
                    List<Etudiant> etudiantsTrouves = stub.rechercherEtudiantParNom(nomRecherche);

                    if (etudiantsTrouves.isEmpty()) {
                        System.out.println("Aucun étudiant trouvé !");
                        break;
                    }
                    System.out.println("Liste des étudiants trouvés :");
                    for (Etudiant e : etudiantsTrouves) {
                        System.out.println(e.getIdEtudiant() + ". " + e.getNomEtudiant());
                    }
                    break;

                case 0:
                    System.out.println("\tAu revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
                    break;

            }
        }
    }
}