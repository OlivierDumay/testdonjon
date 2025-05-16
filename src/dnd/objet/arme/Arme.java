package dnd.objet.arme;
import dnd.objet.Item;

public class Arme extends Item
{
    // class members
    private int m_degat;

    // ctor
    public Arme(String name, int degat)
    {
        super(name);
        if (degat < 1)
            throw new IllegalArgumentException("Erreur : l'arme doit avoir des dégâts supérieurs à 0");
        this.m_degat = degat;
    }
}
