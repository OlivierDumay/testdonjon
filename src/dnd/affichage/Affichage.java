package dnd.affichage;


import dnd.Asset;

public class Affichage {

    // Methode d'affichage de la partie


    // Methodes d'affichage de la création de la partie

    public static String[] afficherCreaPerso()
    {
        //test
        String[] res = new String[3];
        res[0] = "Kingonk";
        res[1] = "guerrier";
        res[2] = "elfe";
        return res;
    }

    public static int[] afficherCreaMonstreObjet()
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
        return ret;
    }

    public static Asset afficheCreaMonstre()
    {

    }

    public static Asset afficheCreaEquipement()
    {

    }
}
