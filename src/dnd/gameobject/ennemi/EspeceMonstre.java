package dnd.gameobject.ennemi;

import dnd.Asset;
import dnd.Type;
import dnd.gameobject.Caracteristique;
import dnd.gameobject.GameObject;
import dnd.gameobject.personnage.Personnage;
import dnd.objet.Item;
import dnd.partie.donjon.Case;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static dnd.Type.MONSTRE;

public class EspeceMonstre {

    private static List<Monstre> m_listeMonstre;


    public static boolean ajouterEspeceMonstre(Monstre monstre) {
        if (m_listeMonstre == null) {
            m_listeMonstre = new ArrayList<>();
            m_listeMonstre.add(monstre);
        }
        for (int i = 0; i < m_listeMonstre.size(); i++) {
            if (m_listeMonstre.get(i).getNom() == monstre.getNom()) {
                System.out.println("L'espèce des " + monstre.getNom() + " est déjà créer");
                return false;
            }
        }
        m_listeMonstre.add(monstre);
        return true;
    }

    public static List<Monstre> getListeEspece() {
        return m_listeMonstre;
    }

    public static Monstre creerMonstreEspeceExistante(String espece) // ou string de l'espece
    {
        Monstre monstre;
        for (int i = 0; i < m_listeMonstre.size(); i++) {
            if (m_listeMonstre.get(i).getNom() == espece) {
                Monstre type = m_listeMonstre.get(i);
                monstre = new Monstre(type.getNom(), type.getAttaque1(), type.getAttaque2(), type.getArmure(), type.getPV(), type.getVitesse(), type.getEtiquette(), type.getID() + 1);
                return monstre;
            }
        }
        System.out.println("Erreur de creation de monstre : espece incorecte");
        return null;
    }
}
