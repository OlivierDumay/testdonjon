package dnd.objet;

import dnd.Type;

public abstract class Arme extends Item
{
    private int m_nb_de;
    private int m_nb_face;
    private int m_portee;

    public Arme(String name, int nb_de, int nb_face, int portee)
    {
        super(name, Type.ARME);
        if (nb_de < 1)
            throw new IllegalArgumentException("Erreur : le nombre de dés doit être supérieur à 0");
        this.m_nb_de = nb_de;
        if (nb_face < 1)
            throw new IllegalArgumentException("Erreur : le nombre de faces doit être supérieur à 0");
        this.m_nb_face = nb_face;
        if (portee < 1)
            throw new IllegalArgumentException("Erreur : la portée doit être supérieure à 0");

        this.m_portee = portee;
    }

    public int getnbDe()
    {
        return this.m_nb_de;
    }

    public int getnbFace()
    {
        return this.m_nb_face;
    }

    public int getPortee()
    {
        return this.m_portee;
    }

    public abstract void appliqueBonusArme();// pour arme lourde etc
    public abstract int jetDegat();

    public abstract int getBonusAttaque(int force, int dex);
}
