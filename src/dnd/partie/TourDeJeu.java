package dnd.partie;

import dnd.Asset;
import dnd.gameobject.GameObject;
import dnd.gameobject.ennemi.Monstre;
import dnd.gameobject.personnage.Personnage;
import dnd.objet.Item;
import dnd.partie.donjon.Carte;
import dnd.partie.donjon.Case;

import java.util.List;

import static dnd.affichage.Affichage.afficherActionMonstre;
import static dnd.affichage.Affichage.afficherActionPerso;

public class TourDeJeu
{
    private int nbTour;

    public TourDeJeu(Carte carte, Ordre ordre, int nTour, int ndonjon){

        // deroulement du tour: boucle qui parcours ordre.m_ordre
        //      3 action possible pour perso
        //          carte.seDeplacer(case, gameObject)
        //          carte.attaquer(attaquant, defenseur)
        //          carte.prendre(gameObject, item)

        boolean finTour = false;
        for (int i = 0; i < ordre.m_ordre.size(); i++) //parcours ordre.m_ordre
        {
            if (ordre.m_ordre.get(i) == null)
            {   //System.out.println("tourDeJeu: test monstre null dans ordre: i :" +i);
                continue;}
            //System.out.println("tourDeJeu: test avant tour: (vrai si le tour doit se terminer) finTour: " + finTour);
            if  (!finTour)
            {
                // test du type
                switch (ordre.m_ordre.get(i).getType())
                {
                    case PERSONNAGE:
                        boolean finTourPerso = false;
                        Personnage p = (Personnage) ordre.m_ordre.get(i);
                        if (p.testEtatVie())
                        {

                            for (int nAction = 3; nAction >0; nAction--)
                            {
                                if  (finTour) {break;}
                                boolean[] retourAction = new boolean[2];
                                retourAction = afficherActionPerso(carte, ordre, p, nAction, nTour, ndonjon);
                                finTour = retourAction[0];
                                finTourPerso = retourAction[1];
                            }
                        }
                        break;
                    case MONSTRE:
                        boolean finTourMonstre = false;
                        Monstre m = (Monstre) ordre.m_ordre.get(i);
                        if (m.testEtatVie())
                        {
                            for (int nAction = 3; nAction >0; nAction--)
                            {
                                if  (finTourMonstre) {break;}
                                boolean[] retourAction = new boolean[2];
                                retourAction = afficherActionMonstre(carte, ordre, m, nAction, nTour, ndonjon);
                                finTour = retourAction[0];
                                finTourMonstre = retourAction[1];
                                //System.out.println("tourDeJeu: test dans tour monstre: nAction " + nAction + " (vrai si le tour doit se terminer) finTour: " + finTour);

                            }
                        }
                        break;
                }
            }

        }


    }







}
