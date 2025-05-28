package dnd.partie;

import dnd.Asset;
import dnd.gameobject.personnage.race.EnumRace;
import dnd.partie.donjon.*;
import dnd.gameobject.personnage.*;
import dnd.gameobject.personnage.classe.*;
import dnd.affichage.*;

import java.util.List;


public class Partie {

    //ctor
    public Partie (int maxX, int maxY)
    {
        // init carte
        Carte carte = new Carte(maxX, maxY);
        // init perso
        // Afficher choix de race et classe, recuperer les arguments(nom, classe, race)

        String crperso[] = Affichage.afficherCreaPerso(); // affiche les instruction et recupere 3 arument de ctor personnage
        EnumClasse classe;
        EnumRace race;
        switch (crperso[1])
        {
            case "clerc" :
                classe=EnumClasse.CLERC;
                break;
            case "guerrier" :
                classe=EnumClasse.GUERRIER;
                break;
            case "magicien" :
                classe=EnumClasse.MAGICIEN;
                break;
            case "roublard" :
                classe=EnumClasse.ROUBLARD;
                break;
            default:
                throw new IllegalArgumentException("Erreur: dnd.partie.Partie constructeur, init du perso, classe");
        }
        switch (crperso[2])
        {
            case "elfe" :
                race=EnumRace.ELFE;
                break;
            case "halflin" :
                race=EnumRace.HALFLIN;
                break;
            case "humain" :
                race=EnumRace.HUMAIN;
                break;
            case "nain" :
                race=EnumRace.NAIN;
                break;
            default:
                throw new IllegalArgumentException("Erreur: dnd.partie.Partie constructeur, init du perso, classe");

        }
        Personnage perso1 = new Personnage(crperso[1], classe, race);

        // init ordre de jeu
        List<Asset>[] ordre;

        // init monstre et equipement sur la map, 
        Affichage.afficherCreaMonstreObjet();




        TourDeJeu(ordre, 1);

        // lance tour 1
    }

    // pour lancer une partie par défaut
    public Partie (int maxX, int maxY, EnumPartie defaut)
    {
        super(maxX, maxY);
    }
}
