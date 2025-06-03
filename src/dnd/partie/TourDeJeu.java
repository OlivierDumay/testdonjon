package dnd.partie;

import dnd.Asset;
import dnd.gameobject.GameObject;
import dnd.objet.Item;
import dnd.partie.donjon.Carte;
import dnd.partie.donjon.Case;

import java.util.List;

public class TourDeJeu
{
    private int nbTour;

    public TourDeJeu(Carte carte, List<Asset> ordre, int n){

        // deroulement du tour: boucle qui parcours ordre.m_ordre
        // 3 action possible
        //      carte.seDeplacer(case, gameObject)
        //      carte.attaquer(attaquant, defenseur)
        //      carte.prendre(gameObject, item)

        //condition de break: perso mort, ou tout les monstre mort


    }

    void seDeplacer(int x, int y, GameObject gameObject)
    {

    }

    void attaquer(GameObject attaquant, GameObject defenseur)
    {

    }

    void prendre (GameObject gameObject, Item item)
    {

    }





}
