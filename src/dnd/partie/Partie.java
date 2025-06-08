package dnd.partie;

import dnd.affichage.Affichage;
import dnd.gameobject.personnage.*;
import dnd.gameobject.personnage.race.EnumRace; // ??? ne reconnait pas enumrace sans cet import 
import dnd.gameobject.personnage.classe.*;
import dnd.objet.Item;
import dnd.partie.donjon.*;


import static dnd.affichage.Affichage.*;

// partie contient l'instance de la carte, de l'ordre de jeu
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
        int[] retCreaMJ = new int[3];
        // stock retour de afficherCreaMonstreObjet
        // 1er int: code, 2eme et 3eme : position x,y
        //
        //              0 : fin de creation monstre etc
        //              1: veut creer monstre
        //              2: veut creer un equipement
        //              3: veut placer un obstacle
        //              4: placer un monstre,
        //              5: placer un equipement
        //
        retCreaMJ[0] = 1;// init à != 0
        while (retCreaMJ[0] !=0)
        {
            try {
                retCreaMJ = Affichage.afficherCreaMonstreObjet(carte);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            switch (retCreaMJ[0])
            {
                case 0 : //fin de creation monstre etc
                    break;
                case 1: // veut creer monstre
                    Affichage.afficheCreaMonstre();
                    break;
                case 2: // veut creer un equipement et placer
                    Item item = Affichage.afficheCreaItem();
                    carte.ajouterItem(item, retCreaMJ[1], retCreaMJ[2]);
                    break;
                case 3: // veut placer un obstacle
                    carte.ajouterObstacle(retCreaMJ[1], retCreaMJ[2]);
                    break;
                case 4: // placer un monstre,
                    AffichageAjoutMonstreCarte(carte, ordre, retCreaMJ[1], retCreaMJ[2]);
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
        this.perso = new Personnage(crperso[0], classe, race);
        //ajout de perso dans ordre
        ordre.ajouterGameObject(perso);
        int[] emplacement = new int[2];
        emplacement = Affichage.afficheDemandeEmplacement(carte);
        carte.ajouterGameObject(perso, emplacement[0],emplacement[1]);

        //// tire les initiative et met les monstres et perso dans l'ordre dans ordre.m_ordre
        ordre.triage();

        //// Déroulement de la partie:
        deroulementPartie(ordre, carte);



    }


    public void deroulementPartie (Ordre ordre, Carte carte)
    {
        int nbDonjon = 1;
        int retourDonjon= -1;
        boolean continuer = true;

        while (continuer)
        {
            // retourDonjon
            //  0 tous les monstres sont mort, donjon suivant
            //  1 tous les perso mort, fin de la partie
            retourDonjon = deroulementDonjon(ordre, carte, nbDonjon);
            System.out.println("deroulementPartie, test :  retourDonjon : " + retourDonjon);
            switch (retourDonjon)
            {
                case 0 : // tous les monstres sont mort, donjon suivant
                    if (nbDonjon == 3)
                    {
                        affichePartieTerminee();
                    }
                    nbDonjon++;
                    int[] xyMax = new int [2];
                    xyMax = afficheDonjonSuivant(nbDonjon);
                    carte = new Carte(xyMax[0], xyMax[1]);
                    ordre = new Ordre();

                    break;
                case 1 : // tous les perso mort, fin de la partie
                    afficheFinDePartie();
                    continuer = false;
                    break;
                default:
                    System.out.println("deroulementPartie, erreur :retourDonjon incorecte : " + retourDonjon);
                    break;
            }
        }

    }


    public int deroulementDonjon (Ordre ordre, Carte carte, int nbDonjon)
        {


            int nbTour = 0;
            while (true)
            {
                switch (ordre.testFinDePartie())
                {
                    // retour de testFinDePartie
                    //  0 des monstres et des perso encore en vie, la partie continue
                    //  1 tous les perso mort, fin de la partie
                    //  2 tous les monstres sont mort, donjon suivant
                    case 0 : //des monstres et des perso encore en vie, la partie continue
                        nbTour++;
                        TourDeJeu tour = new TourDeJeu(carte, ordre.m_ordre , nbTour, nbDonjon);
                        break;
                    case 1 : //tous les perso mort, fin de la partient
                        return 1;
                    case 2 : // tous les monstres sont mort, donjon suiva
                        return 0;
                    default:
                        break;
                }
            }

        }




/*
    // pour lancer une partie par défaut
    public Partie (int maxX, int maxY, EnumPartie defaut)
    {
        super(maxX, maxY);
    }
    */

}
