package dnd.partie.donjon;

import dnd.Asset;
import dnd.gameobject.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Carte
{
    // class members
    private int m_max_x;
    private int m_max_y;
    private Case m_grille[][];
    private HashMap<Asset, Case> m_emplacementObjet;
    private List<Asset> m_asset;

    // ctor
    public Carte(int x, int y)
    {
        if (x < 0 && y < 0)
            throw new IllegalArgumentException("Erreur : les dimensions de la carte doivent être supérieures à 0");

        this.m_max_x = x;
        this.m_max_y = y;

        //init de chaque cases
        for (int i = 0; i<this.m_max_x; i++)
            for(int j = 0; j<this.m_max_y; j++)
            {
                m_grille[i][j] = new Case(i, j);
            }
    }

    public ArrayList<Asset> getQuoiEstIci(int x, int y)
    {
        return this.m_grille[x][y].getContenu();
    }

    public Case OuEstQuoi(Asset quoi)
    {
        return m_emplacementObjet.get(quoi);
    }

    
    public void ajouterAsset(Asset asset, Case cse)
    {
        //manque le test: 2 item ou 2 gameObjet sur meme case impossible ou si il y a un obstacle
        if (asset == null)
            throw new IllegalArgumentException("Erreur : l'asset ne peut pas être null");
        this.m_grille[cse.getX()][cse.getY()].m_contenu.add(asset); // ajout dans la case
        this.m_emplacementObjet.put(asset, cse); // ajout dans la hashmap m_emplacementObjet
    }

    public void retirerAsset(Asset asset, Case cse)
    {
        if (asset == null)
            throw new IllegalArgumentException("Erreur : l'asset ne peut pas être null");
        this.m_grille[cse.getX()][cse.getY()].m_contenu.remove(asset); // supresion dans la case
        this.m_emplacementObjet.remove(asset, cse); // supression dans la hashmap m_emplacementObjet
    }

    public Case getCase(int x, int y)
    {
        return m_grille[x][y];
    }
}
