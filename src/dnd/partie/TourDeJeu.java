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

    public TourDeJeu(Carte carte, List<Asset> ordre, int n){

        // deroulement du tour: boucle qui parcours ordre.m_ordre
        //      3 action possible pour perso
        //          carte.seDeplacer(case, gameObject)
        //          carte.attaquer(attaquant, defenseur)
        //          carte.prendre(gameObject, item)

        for (int i = 0; i < ordre.size(); i++) //parcours ordre.m_ordre
        {
            // test du type
            switch (ordre.get(i).getType())
            {
                case PERSONNAGE:
                    Personnage p = (Personnage) ordre.get(i);
                    for (int nAction = 3; nAction >0; nAction--)
                    {
                        afficherActionPerso(carte, p, nAction);
                    }
                    break;
                case MONSTRE:
                    Monstre m = (Monstre) ordre.get(i);
                    for (int nAction = 3; nAction >0; nAction--)
                    {
                        afficherActionMonstre(carte, m, nAction);
                    }
                    break;
            }
        }


    }







}
