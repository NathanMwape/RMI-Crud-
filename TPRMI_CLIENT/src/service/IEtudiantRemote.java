package service;

import metier.Etudiant;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEtudiantRemote extends Remote {
    public Etudiant connexion(String login, String password) throws RemoteException;
    public List<Etudiant> getListEtudiants() throws RemoteException;

    public void supprimerEtudiant(int id) throws RemoteException;
    public void modifierEtudiant(int id, Etudiant etudiant) throws RemoteException;
    public void ajouterEtudiant(Etudiant etudiant) throws RemoteException;
    public List<Etudiant> rechercherEtudiantParNom(String nom) throws RemoteException;


}
