package dnd.partie;

import dnd.Type;
import dnd.des.De;
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

    public void tirerLesInitiatives()
    {
        // creation d'une copie d'm_ordre
        List<GameObject> copieOrdre = new ArrayList<GameObject>();
        for (int i = 0; i < this.m_ordre.size(); i++)
        {
            copieOrdre.add(this.m_ordre.get(i));
        }

        int tailleOrdre = m_ordre.size();
        int[] tabIndiceInitiative = new int[tailleOrdre]; // pour stocker les jet d'initiative au meme indice que dans copieOrdre

        for (int i = 0; i < this.m_ordre.size(); i++) // parcours d'ordre pour tirer les init
        {
            int init = this.m_ordre.get(i).getInitiative();
            int resDe = De.lancerDe(1,20,(" initiative de " + this.m_ordre.get(i).getNom() + " : " + init + " + "));
            init += resDe;
            tabIndiceInitiative[i] = init;
        }



        List<GameObject> nouvelOrdre = new ArrayList<GameObject>();
        for (int i = 0; i < this.m_ordre.size(); i++) // parcours d'ordre pour tirer les init
        {
            int plusGrandeInit = 0;
            int indice = 0;
            for (int j = 0; j < tabIndiceInitiative.length; j++) // on cherche le plus petit initiative dans tabIndiceInit
            {
                if (tabIndiceInitiative[j] > plusGrandeInit)
                {
                    plusGrandeInit = tabIndiceInitiative[j]; // on change le plus grande initiative
                    indice = j; // on stocke l'indice du plus grand init
                }
            }
            tabIndiceInitiative[indice] = 0; // on met l'init de l'indice a 0 pour qu'il ne soit plus selectionner
            nouvelOrdre.add(this.m_ordre.get(indice));
        }

        m_ordre.clear(); // on remplace les valeur d'ordre par celle de nouvelOrdre
        for (int i = 0; i < nouvelOrdre.size(); i++)
        {
            m_ordre.add(nouvelOrdre.get(i));
        }
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
            //System.out.println("ordre.supprimmerObjet: test: affichage de la liste ordre");
            /*for (int i = 0 ; i<m_ordre.size(); i++)
            {
                System.out.println(i + " " + m_ordre.get(i).getNom());

            }*/
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
        //System.out.println("Ordre.testFinDePartie(): test: ");
        for (int i = 0; i < m_ordre.size(); i++)
        {
            if (m_ordre.get(i).getType() == Type.PERSONNAGE)
            {
                //System.out.println("Ordre.testFinDePartie(): test: perso en vie");
                unPersoEnVie = true;
            }
            if (m_ordre.get(i).getType() == Type.MONSTRE)
            {
                //System.out.println("Ordre.testFinDePartie(): test: monstre en vie");
                unMonstreEnVie = true;
            }
        }
        //System.out.println("Ordre.testFinDePartie(): test: unPersoEnVie: " + unPersoEnVie + ", unMonstreEnVie: " + unMonstreEnVie);

        if(!unPersoEnVie) {return 1;}
        if (!unMonstreEnVie) {return 2;}
        else {return 0;}

    }
}
