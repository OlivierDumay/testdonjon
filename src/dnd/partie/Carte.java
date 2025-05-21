package dnd.partie;

import java.util.ArrayList;

public class Carte
{
    // class members
    private ArrayList<Case> m_carte;
    private int m_max_x;
    private int m_max_y;


    // ctor
    public Carte(int x, int y)
    {
        if (x < 0 && y < 0)
            throw new IllegalArgumentException("Erreur : les dimensions de la carte doivent être supérieures à 0");

        this.m_max_x = x;
        this.m_max_y = y;
    }
}
