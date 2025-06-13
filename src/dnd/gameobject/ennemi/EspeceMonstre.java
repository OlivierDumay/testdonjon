package dnd.gameobject.ennemi;

import java.util.ArrayList;
import java.util.List;



public class EspeceMonstre {

    private static List<Monstre> m_listeMonstre;


    public static boolean ajouterEspeceMonstre(Monstre monstre) {
        if (m_listeMonstre == null) {
            m_listeMonstre = new ArrayList<>();
            m_listeMonstre.add(monstre);
            return true;
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
            //System.out.println("EspeceMonstre.creerMonstreEspeceExistante : test, espece=" + espece + ", monstre=" + m_listeMonstre.get(i).getEspece() );
            if (m_listeMonstre.get(i).getEspece().equals(espece)) {
                Monstre type = m_listeMonstre.get(i);
                int id = type.getID();
                id ++;
                setId(i ,id);
                //System.out.println("creerMonstreEspeceExistante: test: id= "+ id);
                monstre = new Monstre(type.getNom(), type.getAttaque1(), type.getAttaque2(), type.getArmure(), type.getPV(), type.getVitesse(), type.getForce(), type.getDex(), type.getInit(),type.getEtiquette(), id);
                return monstre;
            }
        }
        System.out.println("Erreur: EspeceMonstre.creerMonstreEspeceExistante : espece incorecte, argument= " + espece);
        return null;
    }

    public static void setId(int i, int id) {
        m_listeMonstre.get(i).setId(id);
    }
}
