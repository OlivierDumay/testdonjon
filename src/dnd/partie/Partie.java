package dnd.partie;

import dnd.Asset;
import dnd.Obstacle;
import dnd.affichage.*;
import dnd.gameobject.personnage.*;
import dnd.gameobject.personnage.race.EnumRace; // ??? ne reconnait pas enumrace sans cet import 
import dnd.gameobject.personnage.classe.*;
import dnd.partie.*;
import dnd.partie.donjon.*;




import java.util.List;


public class Partie {

    public Personnage perso; // en public pour l'utiliser dans affichage

    //ctor
    public Partie (int maxX, int maxY)
    {
        //// init carte
        Carte carte = new Carte(maxX, maxY);

        //// init ordre de jeu
        Ordre ordre = new Ordre();

        //// init monstre et equipement sur la map,
        int[] retCreaMJ = new int[3];  // stock retour de afficherCreaMonstreObjet
                                            // 1er int: code, 2eme et 3eme : position x,y
        retCreaMJ[0] = 1;// init à != 0
        while (retCreaMJ[0] !=0)
        {
            retCreaMJ = Affichage.afficherCreaMonstreObjet();
            switch (retCreaMJ[0])
            {
                case 0 :
                    break;
                case 1:
                    Asset monstre = Affichage.afficheCreaMonstre();
                    carte.ajouterAsset(monstre, carte.getCase(retCreaMJ[1], retCreaMJ[2]));
                    ordre.ajouterAsset(monstre);
                    break;
                case 2:
                    carte.ajouterAsset(Affichage.afficheCreaEquipement(), carte.getCase(retCreaMJ[1], retCreaMJ[2]));
                    break;
                case 3:
                    Obstacle obstacle = new Obstacle();
                    carte.ajouterAsset(obstacle, carte.getCase(retCreaMJ[1], retCreaMJ[2]));
                    break;
            }
        }

        //// init perso
        // Afficher choix de race et classe, recuperer les arguments(nom, classe, race)

        String crperso[] = Affichage.afficherCreaPerso(); // affiche les instruction et recupere 3 arument de ctor personnage
        EnumClasse classe;
        EnumRace race;
        switch (crperso[1])
        {
            case "1" :
                classe=EnumClasse.CLERC;
                break;
            case "2" :
                classe=EnumClasse.GUERRIER;
                break;
            case "3" :
                classe=EnumClasse.MAGICIEN;
                break;
            case "4" :
                classe=EnumClasse.ROUBLARD;
                break;
            default:
                throw new IllegalArgumentException("Erreur: dnd.partie.Partie constructeur, init du perso, classe");
        }
        switch (crperso[2])
        {
            case "1" :
                race=EnumRace.ELFE;
                break;
            case "2" :
                race=EnumRace.HALFLIN;
                break;
            case "3" :
                race=EnumRace.HUMAIN;
                break;
            case "4" :
                race=EnumRace.NAIN;
                break;
            default:
                throw new IllegalArgumentException("Erreur: dnd.partie.Partie constructeur, init du perso, classe");

        }
        this.perso = new Personnage(crperso[1], classe, race);
        //ajout de perso dans ordre
        ordre.ajouterAsset(perso);
        int[] emplacement = new int[2];
        carte.ajouterAsset(perso, carte.getCase(emplacement[0],emplacement[1]));

        //// tire les initiative et met les monstres et perso dans l'ordre dans ordre.m_ordre
        ordre.triage();

        //// Déroulement de la partie:
        int nbTour = 0;
        while (perso)// tant que le perso est vivant ou un monstre est vivant: un lance un nouveau tour
        {
            nbTour++;
            TourDeJeu tour = new TourDeJeu((List<Asset>) ordre, nbTour);
        }

    }

    // pour lancer une partie par défaut
    public Partie (int maxX, int maxY, EnumPartie defaut)
    {
        super(maxX, maxY);
    }
}
