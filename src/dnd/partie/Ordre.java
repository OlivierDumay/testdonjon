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

    public void triage(){}

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
        for (int i = 0; i < m_ordre.size(); i++)
        {
            GameObject obj = m_ordre.get(i);
            if (obj.getType() == Type.PERSONNAGE && obj.testEtatVie())
            {
                unPersoEnVie = true;
            }
            if (obj.getType() == Type.MONSTRE && obj.testEtatVie())
            {
                unMonstreEnVie = true;
            }
        }
        if(!unPersoEnVie) {return 1;}
        if (!unMonstreEnVie) {return 2;}
        else {return 0;}
    }
}
