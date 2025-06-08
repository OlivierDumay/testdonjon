package dnd.affichage;


import dnd.gameobject.Action;
import dnd.gameobject.GameObject;
import dnd.gameobject.ennemi.EspeceMonstre;
import dnd.gameobject.ennemi.Monstre;
import dnd.gameobject.personnage.Inventaire;
import dnd.gameobject.personnage.Personnage;
import dnd.objet.Item;
import dnd.objet.arme.ArmeADistance;
import dnd.objet.arme.ArmeCourante;
import dnd.objet.arme.ArmeGuerre;
import dnd.objet.armure.ArmureLegere;
import dnd.objet.armure.ArmureLourde;
import dnd.partie.Ordre;
import dnd.partie.donjon.*;
import java.util.Scanner;

public class Affichage
{

    public static final Scanner scanner = new Scanner(System.in);

    public static void fermerScanner()
    {
        scanner.close();
    }

    // affichage pendant partie
    public static void afficherTour(Carte carte, Personnage personnage, int n_tour, int n_donjon)
    {
        afficherInfoDonjon(carte, personnage, n_tour, n_donjon);
        afficherCarte(carte);
        afficherInfoPersos(personnage);

    }

    public static void afficherCarte(Carte carte)
    {
        int maxX = carte.getMaxX(); // Colonnes
        int maxY = carte.getMaxY(); // Lignes

        // 1. En-tête des colonnes (1, 2, 3, ...)
        System.out.print("     ");
        for (int x = 0; x < maxX; x++)
        {
            System.out.printf("%-3d", x);
        }
        System.out.println();

        // 2. Ligne du haut
        System.out.print("   *");
        for (int x = 0; x < maxX * 3; x++)
        {
            System.out.print("-");
        }
        System.out.println("*");

        // 3. Affichage des lignes
        for (int y = 0; y < maxY; y++)
        {
            System.out.printf("%-3d|", y);
            for (int x = 0; x < maxX; x++)
            {
                String etiquette = carte.getEtiquetteDeLaCase(x, y);
                System.out.printf("%-3s", etiquette);
            }
            System.out.println("|");
        }

        // 4. Ligne du bas
        System.out.print("   *");
        for (int x = 0; x < maxX * 3; x++)
        {
            System.out.print("-");
        }
        System.out.println("*");

        // 5. Légende
        System.out.println("    * Equipement   |   [ ] Obstacle  |");
    }

    public static void afficherInfoDonjon(Carte carte, Personnage personnage, int n_tour, int n_donjon)
    {
        System.out.println("********************************************************************************\nDonjon " + n_donjon + ":");


        System.out.println("                                    " +
                personnage.getNom() + " (" + personnage.getRace().toString() + " " + personnage.getClasse().toString() +")");

        System.out.println("\n********************************************************************************");

        System.out.println("Tour " + n_tour + ":");
    }

    public static void afficherInfoPersos (Personnage personnage)
    {
        String armure;
        try
        {
            armure = personnage
                    .getEquipement()
                    .getArmure()
                    .getNom();
        }
        catch (Exception e)
        {
            armure = " aucune";
        }

        String arme;
        try
        {
            arme = personnage
                    .getEquipement()
                    .getArme()
                    .getNom() +
                    " (degat: " +
                    personnage
                            .getEquipement()
                            .getArme()
                            .getnbDe() +
                    "d" +
                    personnage
                            .getEquipement()
                            .getArme()
                            .getnbFace() +
                    ", portée: " +
                    personnage
                            .getEquipement()
                            .getArme()
                            .getPortee() +
                    ")";
        }
        catch (Exception e)
        {
            arme = " aucune";
        }

        int nb_items_inventaire = personnage.getInventaire().size();

        String inventaire = "";

        if (nb_items_inventaire > 0)
        {
            for (int i = 0 ; i < nb_items_inventaire ; i++)
            {
                inventaire += personnage
                        .getInventaire()
                        .getInventaire()
                        .get(i)
                        .getNom();
                if (i < nb_items_inventaire - 1)
                    inventaire += ", ";
            }
        }
        else
        {
            inventaire = "";
        }

        System.out.println(personnage.getNom() +
                "\n  Vie : " +
                personnage.getPV() +
                "/" + personnage.getPVMax() +
                "\n  Armure : " +
                armure +
                "\n  Arme : " +
                arme +
                "\n  Inventaire : [" +
                nb_items_inventaire +
                "] " +
                inventaire +
                "\n  Force : " +
                personnage
                        .getCaracteristique()
                        .getForce() +
                "\n  Dextérité : " +
                personnage
                        .getCaracteristique()
                        .getDexterite() +
                "\n  Vitesse : " +
                personnage
                        .getCaracteristique()
                        .getVitesse()
        );
    }

    public static void afficherCommentaire(String debut)
    {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer votre commentaire sans retour à la ligne, et appuyer sur entrer");
        String comment = scanner.nextLine();
        System.out.println(debut + comment);
        //scanner.close();
    }

    public static void afficherObjetSurCase(Carte carte)
    {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Sur quelle case est l'objet qui vous interrese?\n x: ");
        int x = scanner.nextInt();
        while (x < 0 || x > carte.getMaxX())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxX());
            x = scanner.nextInt();
        }
        System.out.println("y: ");
        int y = scanner.nextInt();
        while (y < 0 || y > carte.getMaxY())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxY());
            y = scanner.nextInt();
        }

        if (carte.getQuelItemEstIci(x,y) != null)
        {
            carte.getQuelItemEstIci(x,y).toString();
        }
        else
        {
            System.out.println("Il n'y as rien dans cette case");
        }
        //scanner.close();
    }

    public static boolean afficherAttaquer (Carte carte, GameObject gameObject, Ordre ordre)
    {
        // renvoie true si defenseur tué, pour faire un testFinPartie
        boolean retourbool = false;
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Sur quelle case se porte votre attaque?\t\t\t\t\t\t\tTapez -1 pour annuler l'attaque\n x: ");
        int x = scanner.nextInt();
        while (x < -1 || x > carte.getMaxX())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxX() + " ou -1 (pour annuler)");
            x = scanner.nextInt();
        }

        if (x==-1)
        { System.out.println("Attaque annulée");
            return false;}

        System.out.println("y: ");
        int y = scanner.nextInt();
        while (y < 0 || y > carte.getMaxY())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxY());
            y = scanner.nextInt();
        }
        // recup cible
        GameObject cible = carte.getQuelGameObjectEstIci(x, y);
        while (cible == null)
        {
            System.out.println("Personne sur cette case, choisissez en une autre\n x: ");
            x = scanner.nextInt();
            while (x < 0 || x > carte.getMaxX())
            {
                System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxX());
                x = scanner.nextInt();
            }
            System.out.println("y: ");
            y = scanner.nextInt();
            while (y < 0 || y > carte.getMaxY())
            {
                System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxY());
                y = scanner.nextInt();
            }
            cible = carte.getQuelGameObjectEstIci(x, y);
        }

        // retour:
        //      0 si l'attaque est réussie et le defenseur est encore vivant
        //      1 si l'attaque est ratée
        //      2 si le défenseur est mort
        //      3 si cible hors de portée
        int retour;
        retour = carte.attaquer(gameObject, cible, ordre);
        while (retour != -1)
        {
            switch (retour)
            {
                case 0 : // attaque est réussie et le defenseur est encore vivant
                    retour = -1;
                    System.out.println("Attaque réussie : " + gameObject.getNom() + " à toucher " + cible.getNom());
                    break;
                case 1 : // attaque est ratée
                    retour = -1;
                    System.out.println("Attaque raté : " + gameObject.getNom() + " à raté " + cible.getNom());
                    break;
                case 2 : // le défenseur est mort
                    retour = -1;
                    System.out.println("Attaque réussie : " + gameObject.getNom() + " à tuer " + cible.getNom());
                    retourbool = true;
                    break;
                case 3 : // cible hors de portée
                    System.out.println("Cible hors de portée, choisissez en une case. (Votre portée :  " + gameObject.getPortee() + ")\n x: ");
                    x = scanner.nextInt();
                    while (x < 0 || x > carte.getMaxX())
                    {
                        System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxX());
                        x = scanner.nextInt();
                    }
                    System.out.println("y: ");
                    y = scanner.nextInt();
                    while (y < 0 || y > carte.getMaxY())
                    {
                        System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxY());
                        y = scanner.nextInt();
                    }
                    cible = carte.getQuelGameObjectEstIci(x, y);
                    retour = carte.attaquer(gameObject, cible, ordre);
                    break;
            }
        }
        System.out.println("afficherAttaquer: test: (0 si defenseur tué) retourbool: " + retourbool);
        return retourbool;
        //scanner.close();
    }


    public static void afficherSeDeplacer(Carte carte, GameObject gameObject)
    {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Sur quelle case voulez vous vous deplacer?\n x: ");
        int x = scanner.nextInt();
        while (x < 0 || x > carte.getMaxX())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxX());
            x = scanner.nextInt();
        }
        System.out.println("y: ");
        int y = scanner.nextInt();
        while (y < 0 || y > carte.getMaxY())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxY());
            y = scanner.nextInt();
        }

        // appel à seDeplacer
        int retour = 1;
        retour = carte.seDeplacer(x, y, gameObject);
        while (retour == 1)
        {
            System.out.println("x: ");
            x = scanner.nextInt();
            while (x < 0 || x > carte.getMaxX())
            {
                System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxX());
                x = scanner.nextInt();
            }
            System.out.println("y: ");
            y = scanner.nextInt();
            while (y < 0 || y > carte.getMaxY())
            {
                System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxY());
                y = scanner.nextInt();
            }
            retour = carte.seDeplacer(x, y, gameObject);
        }

        System.out.println(gameObject.getNom() + " s'est déplacé à la case " + x + ", " + y);
        //scanner.close();
    }

    public static void afficherSEquiper(Carte carte, Personnage perso)
    {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Voici de quoi est équippé "+ perso.getNom());
        String armure;
        try
        {
            armure = perso
                    .getEquipement()
                    .getArmure()
                    .getNom();
        }
        catch (Exception e)
        {
            armure = " aucune";
        }
        String arme;
        try
        {
            arme = perso
                    .getEquipement()
                    .getArme()
                    .getNom() +
                    " (degat: " +
                    perso
                            .getEquipement()
                            .getArme()
                            .getnbDe() +
                    "d" +
                    perso
                            .getEquipement()
                            .getArme()
                            .getnbFace() +
                    ", portée: " +
                    perso
                            .getEquipement()
                            .getArme()
                            .getPortee() +
                    ")";
        }
        catch (Exception e)
        {
            arme = " aucune";
        }
        System.out.println("Arme: " + arme + ", armure:  " + armure +"\n");

        System.out.println("Voici le contenu de l'inventaire de "+ perso.getNom());
        int nb_items_inventaire = perso.getInventaire().size();
        String inventaire = "";
        if (nb_items_inventaire > 0)
        {
            for (int i = 0 ; i < nb_items_inventaire ; i++)
            {
                inventaire += i + ". " + perso
                        .getInventaire()
                        .getInventaire()
                        .get(i)
                        .getNom();
                if (i < nb_items_inventaire - 1)
                    inventaire += ", ";
            }
        }
        else
        {
            inventaire = "";
        }
        System.out.println(inventaire+"\n");

        System.out.println("Entrer le numero de l'objet à équiper");
        int x = scanner.nextInt();
        while (x<0 || x>perso.getInventaire().size())
        {
            System.out.println("Indiquer une valeur entre 0 et " + (perso.getInventaire().size()-1));
            x = scanner.nextInt();
        }
        Item item = perso.getInventaire().getInventaire().get(x);

        if (armure != "aucune")
        {
            Action.desequiper(perso, item.getType());
        }
        Action.equiper(perso, x);
        System.out.println(perso.getNom() + " est maintenant équipé de :");
        String armure1;
        try
        {
            armure1 = perso
                    .getEquipement()
                    .getArmure()
                    .getNom();
        }
        catch (Exception e)
        {
            armure1 = " aucune";
        }
        String arme1;
        try
        {
            arme1 = perso
                    .getEquipement()
                    .getArme()
                    .getNom() +
                    " (degat: " +
                    perso
                            .getEquipement()
                            .getArme()
                            .getnbDe() +
                    "d" +
                    perso
                            .getEquipement()
                            .getArme()
                            .getnbFace() +
                    ", portée: " +
                    perso
                            .getEquipement()
                            .getArme()
                            .getPortee() +
                    ")";
        }
        catch (Exception e)
        {
            arme1 = " aucune";
        }
        System.out.println("Arme: " + arme1 + ", armure:  " + armure1 +"\n");
        //scanner.close();
    }

    public static void afficherPrendre(Carte carte, Personnage perso)
    {
        int oxy[] = perso.getPosition();

        carte.prendre(perso, carte.getQuelItemEstIci(oxy[0], oxy[1]));

        System.out.println( perso.getNom() + " met dans son inventaire " + carte.getQuelItemEstIci(oxy[0], oxy[1]).getNom());

    }

    public static boolean afficherActionPerso(Carte carte, Ordre ordre, Personnage perso, int nAction, int nTour, int ndonjon)
    {
        // retourne true pour arreter le tour en cours dans l'appel par TourDeJeu
        boolean retour = false;

        // ajouter action, voir quel est l'object sur cette case
        afficherTour(carte, perso, nTour, ndonjon);
        /*
        "Caelynn il vous reste 2 actions que souhaitez vous faire ?
                - laisser le maître du jeu commenter l'action précédente (mj <texte>)
            - commenter action précédente (com <texte>)
            - attaquer (att <Case>)
            - se déplacer (dep <Case>)
            - s'équiper (equ <numero equipement>)"
         */
        //Scanner scanner = new Scanner(System.in);
        afficherTour(carte, perso, nTour, ndonjon);
        System.out.print(" \n" + perso.getNom() + " il vous reste " + nAction + " action, que souhaitez vous faire? Entrez le nuemro de l'action que vous volez réaliser\n" +
                "\t\t\t 1. laisser le maître du jeu commenter l'action précédente (ne consomme pas d'action)\n" +
                "\t\t\t 2. commenter action précédente (ne consomme pas d'action)\n" +
                "\t\t\t 3. Voir les caracteristique d'un objet sur une case (ne consomme pas d'action)\n" +
                "\t\t\t 4. Attaquer\n" +
                "\t\t\t 5. Vous déplacer\n" +
                "\t\t\t 6. S'équiper \n" +
                "\t\t\t 7. Prendre un objet par terre");
        int reponse = scanner.nextInt();
        while (reponse < 1 || reponse > 7)
        {
            System.out.println("Tapez un chiffre entre 1 et 7");
            reponse = scanner.nextInt();
        }
        switch (reponse)
        {
            case 1:
                afficherCommentaire("Maitre du Jeu - ");
                afficherActionPerso(carte, ordre, perso, nAction, nTour, ndonjon);
                break;
            case 2:
                afficherCommentaire(perso.getNom() + " - ");
                afficherActionPerso(carte, ordre, perso, nAction, nTour, ndonjon);
                break;
            case 3:
                afficherObjetSurCase(carte);
                break;
            case 4:
                if (afficherAttaquer(carte, perso, ordre))
                {
                    switch (ordre.testFinDePartie())
                    {
                        case 0:
                            break;
                        case 1:
                            retour = true;
                            break;
                        case 2:
                            retour = true;
                            break;
                    }
                }
                break;
            case 5:
                afficherSeDeplacer(carte, perso);
                break;
            case 6:
                afficherSEquiper(carte, perso);
                break;
            case 7:
                afficherPrendre(carte, perso);
                break;
            default:
                break;
        }
        return retour;
        //scanner.close();
    }

    public static boolean afficherActionMonstre(Carte carte, Ordre ordre, Monstre monstre, int nAction, int nTour, int ndonjon)
    {

        // ajouter action, voir quel est l'object sur cette case
        // trouver une methode pour ajouter        afficherTour(carte, perso, nTour, ndonjon);

        boolean retour = false;
        afficherCarte(carte);
        // Scanner scanner = new Scanner(System.in);
        System.out.print(monstre.getNom() + " il vous reste " + nAction + " action, que souhaitez vous faire? Entrez le numero de l'action que vous voulez réaliser\n" +
                "\t\t\t 1. commenter action précédente (ne consomme pas d'action)\n" +
                "\t\t\t 2. Attaquer\n" +
                "\t\t\t 3. Vous déplacer\n");

        int reponse = scanner.nextInt();
        while (reponse < 1 || reponse > 6)
        {
            System.out.println("Tapez un chiffre entre 1 et 6");
            reponse = scanner.nextInt();
        }
        switch (reponse)
        {
            case 1:
                afficherCommentaire("Maitre du Jeu - ");
                afficherActionMonstre(carte, ordre, monstre, nAction, nTour, ndonjon);
                break;
            case 2:
                if (afficherAttaquer(carte, monstre, ordre))
                {
                    switch (ordre.testFinDePartie())
                    {
                        case 0:
                            break;
                        case 1:
                            retour = true;
                            break;
                        case 2:
                            retour = true;
                            break;
                    }
                }
                break;
            case 3:
                afficherSeDeplacer(carte, monstre);
                break;
            default:
                break;
        }
        return retour;
        //scanner.close();
    }


    // Methodes d'affichage de la création de la partie

    public static String[] afficherCreaPerso()
    {
        //test
        String[] res = new String[3];

        //Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez un nom pour le personnage : \n");
        // Nettoyer le buffer avant la première vraie lecture
        if (scanner.hasNextLine()) scanner.nextLine();
        res[0] = scanner.nextLine();
        System.out.println("Choisissez sa classe : \n1. Clerc\n2. Guerrier\n3. Magicien\n4. Roublard\n");
        res[1] = scanner.nextLine();
        //System.out.println("afficherCreaPerso: test, res[1]: "+ res[1]);
        while (!res[1].equals("1") && !res[1].equals("2") && !res[1].equals("3") && !res[1].equals("4"))
        {
            System.out.println("Erreur : Entrez 1, 2, 3 ou 4");
            res[1] = scanner.nextLine();
        }

        System.out.println("Choisissez sa race : \n1. Elfe\n2. Halflin\n3. Humain\n4. Nain\n");
        res[2] = scanner.nextLine();
        while (!res[1].equals("1") && !res[1].equals("2") && !res[1].equals("3") && !res[1].equals("4"))
        {
            System.out.println("Erreur : Entrez 1, 2, 3 ou 4");
            res[2] = scanner.nextLine();
        }

        //scanner.close();
        return res;
    }

    public static int[] afficherCreaMonstreObjet(Carte carte) throws Exception // enlever les deux dernoer argment, on s'occupera de la position dans afficheCreaTruc
    {
        // boucle de creation pour MJ: 3 choix: créer monstre, placer monstre, creer equipement, placer equipement, placer obstacle
        // renvoie trois int
        // 1er int code:
        //              0 : fin de creation monstre etc
        //              1: veut creer monstre
        //              2: veut creer un equipement
        //              3: veut placer un obstacle
        //              4: placer un monstre,
        //              5: placer un equipement
        // 2eme et 3eme pour emplacement
        int[] ret = new int[3];

        //Scanner scanner = new Scanner(System.in);
        System.out.println("-Mise en place- \n");
        afficherCarte(carte);
        System.out.println("Que voulez faire ??\n\n1. Creer un monstre, 2. Creer et placer un item, 3. Placer un obstacle, 4. Placer un monstre\n0. Plus rien à placer\n");
        ret[0] = (int) scanner.nextInt();
        while (ret[0] < 0 || ret[0] > 4)
        {
            System.out.println("Erreur : Entrez 1, 2, 3, 4 ou 0");
            ret[0] = (int) scanner.nextInt();
        }
        if (ret[0] == 2 || ret[0] == 3|| ret[0] == 4) // si l'utilisateur veut palcer quqchose
        {
            Affichage.afficherCarte(carte);
            System.out.println("A quelle position?\n .x : ");

            ret[1] = (int) scanner.nextInt();
            if (ret[1] > carte.getMaxX()) // si x hors de la carte
            {
                throw new Exception("Erreur : x en dehors de la carte");
            }

            System.out.println(".y : ");
            ret[2] = (int) scanner.nextInt();
            if (ret[2] > carte.getMaxY()) // si y hors de la carte
            {
                throw new Exception("Erreur : y en dehors de la carte");
            }
        }
        //scanner.close();
        return ret;
    }

    public static void  afficheCreaMonstre()
    {
        //creer un monstre et le met dans la liste des espece de monstre
        String[] resS = new String[2];
        int[] resI = new int[5];
        boolean reponse = false;
        //Scanner scanner = new Scanner(System.in);

        while (!reponse)
        {
            // Nettoyer le buffer avant la première vraie lecture
            if (scanner.hasNextLine()) scanner.nextLine();

            System.out.println("Entrez le nom de l'espèce du monstre : \n");
            resS[0] = scanner.nextLine();
            System.out.println("Entrez son attaque: nombre de face du dés : \n");
            resI[0] = scanner.nextInt();
            System.out.println("Entrez son attaque: nombre de dés lancés : \n");
            resI[1] = scanner.nextInt();
            System.out.println("Entrez sa classe d'armure : \n");
            resI[2] = scanner.nextInt();
            System.out.println("Entrez son nombre de points de vie: \n");
            resI[3] = scanner.nextInt();
            System.out.println("Entrez sa vitesse: \n");
            resI[4] = scanner.nextInt();
            if (scanner.hasNextLine()) scanner.nextLine();
            System.out.println("Entrez les 3 caractere de son affichage sur la carte : \n");
            resS[1] = scanner.nextLine();
            while (resS[1].length() != 3) {
                System.out.println("Erreur : Entrez exactement 3 caractere ");
                resS[1] = scanner.nextLine();
            }

            Monstre monstre = new Monstre(resS[0], resI[1], resI[0], resI[2], resI[3], resI[4], resS[1], 0);
            reponse = EspeceMonstre.ajouterEspeceMonstre(monstre);
            if (!reponse) {
                System.out.println("On recommence la création du monstre \n");
            }
        }
       //scanner.close();
    }

    public static void AffichageAjoutMonstreCarte(Carte carte, Ordre ordre,int x, int y)
    {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Choississez un monstre à mettre sur carte parmis les espèce crées :\n");

        for (int i = 0; i < EspeceMonstre.getListeEspece().size(); i++)
        {
            System.out.println(i + " - " + EspeceMonstre.getListeEspece().get(i).getEspece());
        }
        int ret = scanner.nextInt();
        while (ret < 0 || ret > EspeceMonstre.getListeEspece().size())
        {
            System.out.println("Indiquer une valeur entre 0 et " + EspeceMonstre.getListeEspece().size());
            ret = scanner.nextInt();
        }
        System.out.println("Test affichageAjoutMonstreCarte, EspeceMonstre.getListeEspece().get(ret).getEspece(): "+ EspeceMonstre.getListeEspece().get(ret).getEspece()+ "\n" );
        Monstre monstre = EspeceMonstre.creerMonstreEspeceExistante(EspeceMonstre.getListeEspece().get(ret).getEspece());


        carte.ajouterGameObject(monstre, x, y);
        ordre.ajouterGameObject(monstre);
    }

    public static Item afficheCreaItem() {

        //creer l'item et le renvoie
        Item retour;
        String[] resS = new String[2];
        int[] resI = new int[5];
        boolean reponse = false;

        System.out.println("Quel genre d'item voulez vous creer? : \n 0.Arme courante  1. Arme de Guerre  2. Arme a distance  3.Armure legère  4.Armure lourde");
        resI[0] = scanner.nextInt();
        while (resI[0] < 0 || resI[0] > 4)
        {
            System.out.println("Erreur : Entrez 1, 2, 3, 4 ou 0");
            resI[0] =  scanner.nextInt();
        }

        switch (resI[0])
        {
            case 0:
                    // Nettoyer le buffer avant la première vraie lecture
                    if (scanner.hasNextLine()) scanner.nextLine();

                    System.out.println("Entrez le nom de l'arme courante ");
                    resS[0] = scanner.nextLine();
                    System.out.println("Entrez son attaque: nombre de face du dés : ");
                    resI[1] = scanner.nextInt();
                    System.out.println("Entrez son attaque: nombre de dés lancés : ");
                    resI[2] = scanner.nextInt();

                    retour = new ArmeCourante(resS[0], resI[2], resI[1],  1);
                    return retour;
            case 1:
                // Nettoyer le buffer avant la première vraie lecture
                if (scanner.hasNextLine()) scanner.nextLine();

                System.out.println("Entrez le nom de l'arme de guerre ");
                resS[0] = scanner.nextLine();
                System.out.println("Entrez son attaque: nombre de face du dés : ");
                resI[0] = scanner.nextInt();
                System.out.println("Entrez son attaque: nombre de dés lancés : ");
                resI[1] = scanner.nextInt();

                retour = new ArmeGuerre(resS[0], resI[2], resI[1],  1);
                return retour;
            case 2:
                // Nettoyer le buffer avant la première vraie lecture
                if (scanner.hasNextLine()) scanner.nextLine();

                System.out.println("Entrez le nom de l'arme à distance ");
                resS[0] = scanner.nextLine();
                System.out.println("Entrez son attaque: nombre de face du dés : ");
                resI[1] = scanner.nextInt();
                System.out.println("Entrez son attaque: nombre de dés lancés : ");
                resI[2] = scanner.nextInt();
                System.out.println("Entrez sa portée : ");
                resI[3] = scanner.nextInt();

                retour = new ArmeADistance(resS[0], resI[2], resI[1],  resI[3]);
                return retour;
            case 3:
                // Nettoyer le buffer avant la première vraie lecture
                if (scanner.hasNextLine()) scanner.nextLine();

                System.out.println("Entrez le nom de l'armure légère ");
                resS[0] = scanner.nextLine();
                System.out.println("Entrez sa classe d'armure : ");
                resI[1] = scanner.nextInt();

                retour = new ArmureLegere(resS[0], resI[1]);
                return retour;
            case 4:
                // Nettoyer le buffer avant la première vraie lecture
                if (scanner.hasNextLine()) scanner.nextLine();

                System.out.println("Entrez le nom de l'armure lourde ");
                resS[0] = scanner.nextLine();
                System.out.println("Entrez sa classe d'armure : ");
                resI[1] = scanner.nextInt();

                retour = new ArmureLourde(resS[0], resI[1]);
                return retour;
        }

        return null;
    }

    public static int[] afficheDemandeEmplacement(Carte carte)
    {
        int[] res = new int[2];
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Indiquer une case : \nx: ");
        int x = scanner.nextInt();
        while (x < 0 || x > carte.getMaxX())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxX());
            x = scanner.nextInt();
        }

        System.out.println("y: ");
        int y = scanner.nextInt();
        while (y < 0 || y > carte.getMaxY())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxY());
            y = scanner.nextInt();
        }

        //scanner.close();
        res[0] = x;
        res[1] = y;
        return res;
    }

    public static void afficherEquipement(Personnage personnage)
    {
        Inventaire inventaire_perso = personnage.getInventaire();
        for (int i = 0 ; i < inventaire_perso.getInventaire().size() ; i++)
        {
            Item current_item = inventaire_perso.getInventaire().get(i);
            System.out.println(i + " - " + current_item.getNom());
        }
    }

    public static void afficheFinDePartie()
    {
        System.out.print("Malédiction! Le maitre du donjon vous a vaincu!!");

    }

    public static int[] afficheDonjonSuivant(int nDonjon)
    {
        // retourne la taille du prochain donjon
        System.out.print("Bravo! vous êtes venu à bout du donjon n°" + nDonjon +"\n\n\tPréparez vous pour le prochaine donjon!");

        //Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle est la taille du prochain donjon?\n x: ");
        int x = scanner.nextInt();
        while (x < 0)
        {
            System.out.println("Indiquer une valeur supérieur à 0");
            x = scanner.nextInt();
        }
        System.out.println("y: ");
        int y = scanner.nextInt();
        while (y < 0 )
        {
            System.out.println("Indiquer une valeur supérieur à 0");
            y = scanner.nextInt();
        }
         int[] res = new int[2];
        res[0] = x;
        res[1] = y;
        return res;
    }

    public static void affichePartieTerminee()
    {
        System.out.print("Bravo! vous êtes venu à bout des trois donjon!!!");
    }
}
