import service.EtudiantService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServeurRMI {
    public static void main(String[] args) {
//        creation des objets distants pour le publier dans l'annuaire et le demarrage de l'annuaire

        try {
            LocateRegistry.createRegistry(1099);
            EtudiantService skeleton = new EtudiantService();
//            publication de l'obet distant dans l'annuaire
            Naming.rebind("rmi://localhost:1099/skeleton",skeleton);

            System.out.println("Serveur démarré : " + skeleton.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}