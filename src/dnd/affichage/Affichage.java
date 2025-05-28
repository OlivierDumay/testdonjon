package dnd.affichage;


import dnd.Asset;

import java.util.Scanner;

public class Affichage {

    // Methode d'affichage de la partie




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

    public static int[] afficherCreaMonstreObjet(Carte carte) // enlever les deux dernoer argment, on s'occupera de la position dans afficheCreaTruc
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
        }
            if (ret[0] == 1 || ret[0] == 2 || ret[0] == 3 )
            {
                System.out.println("A quelle position (x, y)?\n .x : ");
                ret[1] = (int) scanner.nextInt();
                if (ret[1] > carte.getMaxX()) // x dans la carte
                { return Exception("Erreur : x en dehors de la carte);}
                System.out.println(".y : ");
                if (ret[2] > carte.getMaxY()) // y dans la carte
                { return Exception("Erreur : y en dehors de la carte");}
                ret[2] = (int) scanner.nextInt();
            }
        }
        scanner.close();
        return ret;
    }

    public static Asset afficheCreaMonstre()
    {

    }

    public static Asset afficheCreaEquipement()
    {

    }
}
