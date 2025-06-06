package dnd.affichage;


import dnd.Asset;
import dnd.gameobject.GameObject;
import dnd.gameobject.personnage.Inventaire;
import dnd.gameobject.personnage.Personnage;
import dnd.objet.Arme;
import dnd.objet.Item;
import dnd.partie.donjon.*;
import java.util.Scanner;

public class Affichage
{

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
        for (int x = 1; x <= maxX; x++)
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
            System.out.printf("%-3d|", y + 1);
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

    public static void afficherActionPerso(Carte carte, Personnage perso, int nAction)
    {
        // ajouter action, voir quel est l'object sur cette case

        /*
        "Caelynn il vous reste 2 actions que souhaitez vous faire ?
                - laisser le maître du jeu commenter l'action précédente (mj <texte>)
            - commenter action précédente (com <texte>)
            - attaquer (att <Case>)
            - se déplacer (dep <Case>)
            - s'équiper (equ <numero equipement>)"
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print(perso.getNom() + " il vous reste " + nAction + " action, que souhaitez vous faire? Entrez le nuemro de l'action que vous volez réaliser\n" +
                "\t\t\t 1. laisser le maître du jeu commenter l'action précédente (ne consomme pas d'action)\n" +
                "\t\t\t 2. commenter action précédente (ne consomme pas d'action)\n" +
                "\t\t\t 3. Attaquer\n" +
                "\t\t\t 4. Vous déplacer\n" +
                "\t\t\t 5. S'équiper\n" +
                "\t\t\t 6. Prendre un objet par terre");
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
                afficherActionPerso(carte, perso, nAction);
                break;
            case 2:
                afficherCommentaire(perso.getNom() + " - ");
                afficherActionPerso(carte, perso, nAction);
                break;
            case 3:
                afficherAttaquer(carte, perso);
                break;
            case 4:
                afficherSeDeplacer(carte, perso);
                break;
            case 5:
                afficherSEquiper(carte, perso);
                break;
            case 6:
                afficherPrendre(carte, perso);
                break;
            default:
                break;
        }
        scanner.close();
    }

    public static void afficherCommentaire(String debut)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer votre commentaire sans retour à la ligne, et appuyer sur entrer");
        String comment = scanner.nextLine();
        System.out.println(debut + comment);
        scanner.close();
    }

    public static void afficherAttaquer (Carte carte, GameObject gameObject)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sur quelle case se porte votre attaque?\n x: ");
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

        int retour;
        retour = carte.attaquer(gameObject, cible);
        while (retour != -1)
        {
            switch (retour)
            {
                case 0 : // attaque est réussie et le defenseur est encore vivant

                case 1 : // attaque est ratée

                case 2 : // le défenseur est mort

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
                    retour = carte.attaquer(gameObject, cible);
                    break;
            }


        }

        scanner.close();
    }


    public static void afficherSeDeplacer(Carte carte, GameObject gameObject)
    {
        Scanner scanner = new Scanner(System.in);
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
        scanner.close();
    }

    public static void afficherSEquiper(Carte carte, Personnage perso)
    {
        Scanner scanner = new Scanner(System.in);
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
        while (x<1 || x>perso.getInventaire().size())
        {
            System.out.println("Indiquer une valeur entre 1 et " + perso.getInventaire().size());
            x = scanner.nextInt();
        }

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

        scanner.close();
    }

    public static void afficherPrendre(Carte carte, Personnage perso)
    {
        int oxy[] = perso.getPosition();

        carte.prendre(perso, carte.getQuelItemEstIci(oxy[0], oxy[1]));

        System.out.println( perso.getNom() + " met dans son inventaire " + carte.getQuelItemEstIci(oxy[0], oxy[1]).getNom());

    }

    // Methodes d'affichage de la création de la partie

    public static String[] afficherCreaPerso()
    {
        //test
        String[] res = new String[3];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez un nom pour le personnage : \n");
        res[0] = scanner.nextLine();

        System.out.println("Choisissez sa classe : \n1. Clerc\n2. Guerrier\n3. Magicien\n4. Roublard\n");
        res[1] = scanner.nextLine();
        while (res[1] != "1" || res[1] != "2" || res[1] != "3" || res[1] != "4")
        {
            System.out.println("Erreur : Entrez 1, 2, 3 ou 4");
            res[1] = scanner.nextLine();
        }

        System.out.println("Choisissez sa race : \n1. Elfe\n2. Halflin\n3. Humain\n4. Nain\n");
        res[2] = scanner.nextLine();
        while (res[2] != "1" || res[2] != "2" || res[2] != "3" || res[2] != "4")
        {
            System.out.println("Erreur : Entrez 1, 2, 3 ou 4");
            res[2] = scanner.nextLine();
        }

        scanner.close();
        return res;
    }

    public static int[] afficherCreaMonstreObjet(Carte carte) throws Exception // enlever les deux dernoer argment, on s'occupera de la position dans afficheCreaTruc
    {
        // boucle de creation pour MJ: 3 choix: créer monstre, placer monstre, creer equipement, placer equipement, placer obstacle
        // renvoie trois int
        // 1er int code:
        //              0 : fin de creation monstre etc
        //              1: veut creer et placer un monstre, appel de creaMonstre
        //              2: veut creer et place un equipement
        //              3: veut placer un obstacle
        // 2eme et 3eme pour emplacement
        int[] ret = new int[3];

        Scanner scanner = new Scanner(System.in);
        System.out.println("-Mise en place- \nQue voulez placer sur la carte?\n\n1. Un monstre, 2. Un equipement, 3. Un obstacle\n0. Plus rien à placer\n");
        ret[0] = (int) scanner.nextInt();
        while (ret[0] != 1 || ret[0] != 2 || ret[0] != 3 || ret[0] != 0)
        {
            System.out.println("Erreur : Entrez 1, 2, 3 ou 0");
            ret[0] = (int) scanner.nextInt();

            if (ret[0] == 1 || ret[0] == 2 || ret[0] == 3) // si l'utilisateur veut palcer quqchose
            {
                System.out.println("A quelle position (x, y)?\n .x : ");

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
        }
        scanner.close();
        return ret;
    }

    public static GameObject afficheCreaMonstre()
    {
        return null;
    }

    public static Item afficheCreaEquipement()
    {
        return null;
    }

    public static int[] afficheDemandeEmplacement(Carte carte)
    {
        int[] res = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indiquer une case : \nx:\n");
        int x = scanner.nextInt();
        while (x < 0 || x > carte.getMaxX())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxX());
            x = scanner.nextInt();
        }

        System.out.println("y:\n");
        int y = scanner.nextInt();
        while (y < 0 || y > carte.getMaxY())
        {
            System.out.println("Indiquer une valeur entre 0 et " + carte.getMaxY());
            y = scanner.nextInt();
        }

        scanner.close();
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
}
