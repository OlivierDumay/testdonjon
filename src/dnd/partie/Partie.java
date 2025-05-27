package dnd.partie;

import dnd.partie.donjon.*;


public class Partie {

    //ctor
    public Partie (int maxX, int maxY)
    {
        // init carte
        Carte carte = new Carte(maxX, maxY);
        // init perso
        // afficher choix de race et classe

        // init monstre et equipement sur la map
        // afficher, ajout de monstre/equipement/obstacle

        // init initiative
        

        // lance tour 1
    }

    // pour lancer une partie par défaut
    public Partie (int maxX, int maxY, EnumPartie defaut)
    {
        super(maxX, maxY);
    }
}
