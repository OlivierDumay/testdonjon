package dnd.partie;

import dnd.affichage.Affichage;
import dnd.gameobject.ennemi.EspeceMonstre;
import dnd.gameobject.ennemi.Monstre;
import dnd.gameobject.personnage.*;
import dnd.gameobject.personnage.race.EnumRace; // ??? ne reconnait pas enumrace sans cet import 
import dnd.gameobject.personnage.classe.*;
import dnd.objet.Item;
import dnd.objet.arme.ArmeGuerre;
import dnd.partie.donjon.*;


import static dnd.affichage.Affichage.*;

// partie contient l'instance de la carte, de l'ordre de jeu
public class Partie {

    public Personnage perso; // en public pour l'utiliser dans affichage

    //ctor
    public Partie (int maxX, int maxY)
    {
        int ret = Affichage.afficheChoixCreaPartie();
        switch (ret)
        {
            case 1:
                break;
            case 2:
                partiePreconcue();
                return;
        }

        //// init carte
        Carte carte = new Carte(maxX, maxY);

        //// init ordre de jeu
        Ordre ordre = new Ordre();

        //// init monstre et equipement sur la map,
        initMonstreItem(carte, ordre);


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

        //// Déroulement de la partie:
        deroulementPartie(ordre, carte);



    }


    public void deroulementPartie (Ordre ordre, Carte carte)
    {
        int nbDonjon = 1;
        int retourDonjon= -1;
        boolean continuer = true;
        Personnage sauvPerso = perso;

        while (continuer)
        {
            // retourDonjon
            //  0 tous les monstres sont mort, donjon suivant
            //  1 tous les perso mort, fin de la partie
            retourDonjon = deroulementDonjon(ordre, carte, nbDonjon);
            //System.out.println("deroulementPartie, test :  retourDonjon : " + retourDonjon);
            switch (retourDonjon)
            {
                case 0 : // tous les monstres sont mort, donjon suivant
                    if (nbDonjon == 3)
                    {
                        affichePartieTerminee();
                        return;
                    }
                    nbDonjon++;
                    int[] xyMax = new int [2];
                    xyMax = afficheDonjonSuivant(nbDonjon);

                    // nouvelle carte
                    carte.viderGrille();
                    carte = new Carte(xyMax[0], xyMax[1]);
                    //nouvel ordre
                    ordre.viderListe();
                    ordre = new Ordre();
                    // nouveau monstre
                    initMonstreItem(carte, ordre);

                    // placement du perso
                    int[] npos = new int[2];
                    System.out.println("Ou voulez vous placer " + perso.getNom()+ "?\n");
                    afficherCarte(carte);
                    npos = afficheDemandeEmplacement(carte);
                    carte.ajouterGameObject(perso, npos[0], npos[1]);
                    ordre.ajouterGameObject(perso);
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
        ordre.tirerLesInitiatives(); // on tire les initiative
        while (true)
        {
            int resultat = ordre.testFinDePartie();
            switch (resultat)
            {
                // retour de testFinDePartie
                //  0 des monstres et des perso encore en vie, la partie continue
                //  1 tous les perso mort, fin de la partie
                //  2 tous les monstres sont mort, donjon suivant
                case 0 : //des monstres et des perso encore en vie, la partie continue
                    nbTour++;
                    TourDeJeu tour = new TourDeJeu(carte, ordre , nbTour, nbDonjon);
                    break;
                case 1 : //tous les perso mort, fin de la partie
                    return 1;
                case 2 : // tous les monstres sont mort, donjon suivant
                    return 0;
                default:
                    System.out.println("deroulementDonjon, erreur : résultat de testFinDePartie incorrect : " + resultat);
                    return 1;
            }
        }
    }

    public void initMonstreItem (Carte carte, Ordre ordre)
    {
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
    }

    public void partiePreconcue () {
        // construction donjon 1
        Carte carte = new Carte(10, 15);
        Ordre ordre = new Ordre();

        Monstre orc = new Monstre("orc", 1, 8, 22, 35, 12, 15, 8, 10, "orc", 0);
        EspeceMonstre.ajouterEspeceMonstre(orc);
        Monstre goblin = new Monstre("gobelin", 1, 4, 10, 12, 20, 8, 15, 20, "gob", 0);
        EspeceMonstre.ajouterEspeceMonstre(goblin);
        Monstre Singe = new Monstre("singe", 1, 3, 7, 6, 22, 3, 20, 20, "°|s", 0);
        EspeceMonstre.ajouterEspeceMonstre(Singe);
        Monstre bandit = new Monstre("bandit de grand chemin", 1, 8, 27, 45, 10, 12, 10, 13, "\\|o", 0);
        EspeceMonstre.ajouterEspeceMonstre(bandit);
        Monstre dragon = new Monstre("dragon", 2, 8, 30, 66, 6, 20, 17, 3, "d\\r", 0);
        EspeceMonstre.ajouterEspeceMonstre(dragon);

        Monstre monstre1 = EspeceMonstre.creerMonstreEspeceExistante("orc");
        carte.ajouterGameObject(monstre1, 2, 2);
        ordre.ajouterGameObject(monstre1);
        Monstre monstre2 = EspeceMonstre.creerMonstreEspeceExistante("gobelin");
        carte.ajouterGameObject(monstre2, 3, 2);
        ordre.ajouterGameObject(monstre2);
        Monstre monstre3 = EspeceMonstre.creerMonstreEspeceExistante("gobelin");
        carte.ajouterGameObject(monstre3, 1, 4);
        ordre.ajouterGameObject(monstre3);
        Monstre monstre4 = EspeceMonstre.creerMonstreEspeceExistante("gobelin");
        carte.ajouterGameObject(monstre4, 5, 4);
        ordre.ajouterGameObject(monstre4);

        carte.ajouterObstacle(5, 5);
        carte.ajouterObstacle(4, 5);
        carte.ajouterObstacle(8, 7);
        perso = new Personnage("Canon le Barbant", EnumClasse.GUERRIER, EnumRace.HUMAIN);
        ordre.ajouterGameObject(perso);

        carte.ajouterGameObject(perso, 6, 6);

        // deroulement donjon 1
        int retourDonjon = deroulementDonjon(ordre, carte, 1);

        switch (retourDonjon) {
            case 0: // tous les monstres sont mort, donjon suivant
                System.out.print("Bravo! vous êtes venu à bout du donjon n°1\n\n\tPréparez vous pour le prochain donjon!\n\n");
                carte.viderGrille();
                carte = new Carte(23, 11);
                //nouvel ordre
                ordre.viderListe();
                ordre = new Ordre();
                // nouveau monstre
                Monstre monstre5 = EspeceMonstre.creerMonstreEspeceExistante("bandit de grand chemin");
                carte.ajouterGameObject(monstre5, 20, 5);
                ordre.ajouterGameObject(monstre5);
                Monstre monstre6 = EspeceMonstre.creerMonstreEspeceExistante("singe");
                carte.ajouterGameObject(monstre6, 19, 2);
                ordre.ajouterGameObject(monstre6);
                Monstre monstre7 = EspeceMonstre.creerMonstreEspeceExistante("singe");
                carte.ajouterGameObject(monstre7, 18, 7);
                ordre.ajouterGameObject(monstre7);
                Monstre monstre8 = EspeceMonstre.creerMonstreEspeceExistante("singe");
                carte.ajouterGameObject(monstre8, 21, 7);
                ordre.ajouterGameObject(monstre8);
                carte.ajouterObstacle(8, 7);
                carte.ajouterObstacle(8, 8);
                carte.ajouterObstacle(7, 7);
                carte.ajouterObstacle(17, 3);
                carte.ajouterObstacle(8, 10);
                ArmeGuerre epeevorpale = new ArmeGuerre("Epée Vorpale",3, 10, 1);
                carte.ajouterItem(epeevorpale, 12,6);
                // placement du perso

                carte.ajouterGameObject(perso, 5,3);
                ordre.ajouterGameObject(perso);
                break;
            case 1: // tous les perso mort, fin de la partie
                afficheFinDePartie();
                return;
            default:
                System.out.println("deroulementPartie, erreur :retourDonjon incorecte : " + retourDonjon);
                break;
        }

        retourDonjon = deroulementDonjon(ordre, carte, 2);

        switch (retourDonjon) {
            case 0: // tous les monstres sont mort, donjon suivant
                System.out.print("Bravo! vous êtes venu à bout du donjon n°2\n\n\tPréparez vous pour le dernier donjon!\n\n");
                carte.viderGrille();
                carte = new Carte(25, 25);
                //nouvel ordre
                ordre.viderListe();
                ordre = new Ordre();
                // nouveau monstre
                Monstre monstre9 = EspeceMonstre.creerMonstreEspeceExistante("dragon");
                carte.ajouterGameObject(monstre9, 12, 7);
                ordre.ajouterGameObject(monstre9);
                Monstre monstre10 = EspeceMonstre.creerMonstreEspeceExistante("singe");
                carte.ajouterGameObject(monstre10, 19, 2);
                ordre.ajouterGameObject(monstre10);
                Monstre monstre11 = EspeceMonstre.creerMonstreEspeceExistante("singe");
                carte.ajouterGameObject(monstre11, 4, 9);
                ordre.ajouterGameObject(monstre11);
                Monstre monstre12 = EspeceMonstre.creerMonstreEspeceExistante("singe");
                carte.ajouterGameObject(monstre12, 10, 22);
                ordre.ajouterGameObject(monstre12);
                carte.ajouterObstacle(13, 15);
                carte.ajouterObstacle(14, 15);
                carte.ajouterObstacle(10, 10);
                carte.ajouterObstacle(10, 9);
                carte.ajouterObstacle(10, 11);
                // placement du perso

                carte.ajouterGameObject(perso, 13,20);
                ordre.ajouterGameObject(perso);
                break;
            case 1: // tous les perso mort, fin de la partie
                afficheFinDePartie();
                return;
            default:
                System.out.println("deroulementPartie, erreur :retourDonjon incorecte : " + retourDonjon);
                break;
        }
        retourDonjon = deroulementDonjon(ordre, carte, 3);
        switch (retourDonjon) {
            case 0: // tous les monstres sont mort, donjon suivant
                affichePartieTerminee();
                return;
            case 1: // tous les perso mort, fin de la partie
                afficheFinDePartie();
                return;
            default:
                System.out.println("deroulementPartie, erreur :retourDonjon incorecte : " + retourDonjon);
                break;
        }
    }
}
