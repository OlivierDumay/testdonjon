package dnd.objet.armure;
import dnd.objet.Item;

public class Armure extends Item
{
    // class members
    private int m_armure;

    // ctor
    public Armure(String name, int armure)
    {
        super(name);
        if (armure < 1)
            throw new IllegalArgumentException("Erreur : l'armure doit avoir une défense supérieure à 0");
        this.m_armure = armure;
    }
}
