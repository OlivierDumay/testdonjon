package dnd.partie;

import dnd.Asset;
import dnd.gameobject.GameObject;
import dnd.objet.Item;
import dnd.partie.donjon.Carte;
import dnd.partie.donjon.Case;

import java.util.List;

import static dnd.affichage.Affichage.afficheActionPerso;

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
                    for (int nAction = 3; nAction >0; nAction--)
                    {
                        afficherActionPerso(ordre.get(i),nAction);
                    }
                    break;
                case MONSTRE:
                    for (int nAction = 3; nAction >0; nAction--)
                    {
                        afficherActionMonstre(ordre.get(i),nAction);
                    }
                    break;
            }
        }


    }

    void seDeplacer(Carte carte, int x, int y, GameObject gameObject)
    {

            int[] postionGameObject =  gameObject.getPosition();
            Case caseGameObject = carte.getCase(postionGameObject[0], postionGameObject[1]);

            float distance = caseGameObject.calculDistance(carte.getCase(x,y));
            if (distance <= (float)gameObject.getVitesse())
            {
                carte.retirerGameObject(gameObject ,postionGameObject[0], postionGameObject[1]);
                carte.ajouterGameObject(gameObject, x, y);
            } else { ;}// erreur distance trop grande


    }

    void attaquer(GameObject attaquant, GameObject defenseur)
    {

    }

    void prendre (GameObject gameObject, Item item)
    {

    }





}
