package service;

import metier.Connexion;
import metier.EsisDb;
import metier.Etudiant;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantService extends UnicastRemoteObject implements IEtudiantRemote {


    Connection conn = Connexion.getConneXion();
    public EtudiantService() throws RemoteException {
    }

    @Override
    public Etudiant connexion(String login, String password) throws RemoteException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Etudiant etudiant = null;
        try {
            String sql = "SELECT * FROM etudiant WHERE login = ? AND passwords = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idEtudiant");
                String nom = rs.getString("nomEtudiant");
                Date dateNaissance = rs.getDate("dateNaissance");
                etudiant = new Etudiant(id, nom, login, password, dateNaissance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }


    @Override
    public List<Etudiant> getListEtudiants() throws RemoteException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Etudiant> etudiants = new ArrayList<Etudiant>();
        try {
            String sql = "SELECT * FROM etudiant";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idEtudiant");
                String nom = rs.getString("nomEtudiant");
                String login = rs.getString("login");
                String password = rs.getString("passwords");
                Date dateNaissance = rs.getDate("dateNaissance");
                Etudiant etudiant = new Etudiant(id, nom, login, password, dateNaissance);
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            throw new RemoteException("Erreur lors de l'exécution de la requête SQL", e);
        }
        return etudiants;
    }


    @Override
    public void supprimerEtudiant(int id) throws RemoteException {
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM etudiant WHERE idEtudiant = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void modifierEtudiant(int id, Etudiant etudiant) throws RemoteException {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE etudiant SET nomEtudiant = ?, login = ?, passwords = ?, dateNaissance = ? WHERE idEtudiant = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, etudiant.getNomEtudiant());
            stmt.setString(2, etudiant.getLogin());
            stmt.setString(3, etudiant.getPassword());
            stmt.setDate(4, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void ajouterEtudiant(Etudiant etudiant) throws RemoteException {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO etudiant (nomEtudiant, login, passwords, dateNaissance) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, etudiant.getNomEtudiant());
            stmt.setString(2, etudiant.getLogin());
            stmt.setString(3, etudiant.getPassword());
            stmt.setDate(4, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Etudiant> rechercherEtudiantParNom(String nom) throws RemoteException {
        List<Etudiant> etudiants = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM etudiant WHERE nomEtudiant LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nom + "%");
            // Exécuter la requête SQL
            rs = stmt.executeQuery();
            // Parcourir les résultats de la requête et créer les objets Etudiant correspondants
            while (rs.next()) {
                int id = rs.getInt("idEtudiant");
                String nomEtudiant = rs.getString("nomEtudiant");
                String login = rs.getString("login");
                String password = rs.getString("passwords");
                Date dateNaissance = rs.getDate("dateNaissance");
                Etudiant etudiant = new Etudiant(id, nomEtudiant, login, password, dateNaissance);
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }



}
