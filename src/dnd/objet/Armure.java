package dnd.objet;

public class Armure extends Item
{
    private int m_armure;

    public Armure(String name, int armure)
    {
        super(name);
        if (armure < 1)
            throw new IllegalArgumentException("Erreur : les dégâts de l'arme doivent être supérieurs à 0 !");
        this.m_armure = armure;
    }
}
