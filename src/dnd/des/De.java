package dnd.des;

import dnd.affichage.Affichage;

import java.util.ArrayList;
import java.util.Random;

public class De
{
    private static final Random rand = new Random();

    public static int lancerDe(int nbDe, int nbFace, String pourquoi)
    {
        if (nbFace < 1)
            throw new IllegalArgumentException("Erreur : le nombre de faces doit être supérieur à 1");
        if (nbDe < 0)
            throw new IllegalArgumentException("Erreur : le nombre de dé doit être supérieur à 0");



        int n = 0;
        for (int i = 0 ; i < nbDe ; i++)
        {
            n += rand.nextInt(nbFace) + 1;
        }
        Affichage.afficheLancerDeDe(nbDe, nbFace, n, pourquoi);

        return n;
    }
}
