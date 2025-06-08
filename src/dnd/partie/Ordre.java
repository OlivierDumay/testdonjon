package dnd.partie;

import dnd.Type;
import dnd.gameobject.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Ordre {

    List<GameObject> m_ordre;

    public Ordre()
    {
        this.m_ordre = new ArrayList<>();
    }

    public List<GameObject> getListOrdre()
    {
        return m_ordre;
    }

    public void triage()
    {

    }

    public void ajouterGameObject(GameObject gameObject)
    {
        if (!m_ordre.contains(gameObject))
        {
            m_ordre.add(gameObject);
        }
    }

    public void supprimerGameObject(GameObject gameObject)
    {
        if (m_ordre.contains(gameObject))
        {
            m_ordre.remove(gameObject);
            System.out.println("ordre.supprimmerObjet: test: affichage de la liste ordre");
            for (int i = 0 ; i<m_ordre.size(); i++)
            {
                System.out.println(i + " " + m_ordre.get(i).getNom());

            }
        }

    }

    public int testFinDePartie()
    {
        //retour:
        //  0 des monstres et des perso encore en vie, la partie continue
        //  1 tous les perso mort, fin de la partie
        //  2 tous les monstres sont mort, donjon suivant
        boolean unPersoEnVie = false;
        boolean unMonstreEnVie = false;
        System.out.println("Ordre.testFinDePartie(): test: ");
        for (int i = 0; i < m_ordre.size(); i++)
        {
            if (m_ordre.get(i).getType() == Type.PERSONNAGE)
            {
                System.out.println("Ordre.testFinDePartie(): test: perso en vie");
                unPersoEnVie = true;
            }
            if (m_ordre.get(i).getType() == Type.MONSTRE)
            {
                System.out.println("Ordre.testFinDePartie(): test: monstre en vie");
                unMonstreEnVie = true;
            }
        }
        System.out.println("Ordre.testFinDePartie(): test: unPersoEnVie: " + unPersoEnVie + ", unMonstreEnVie: " + unMonstreEnVie);

        if(!unPersoEnVie) {return 1;}
        if (!unMonstreEnVie) {return 2;}
        else {return 0;}

    }
}
