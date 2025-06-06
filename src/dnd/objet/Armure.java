package dnd.objet;

import dnd.Type;

public abstract class Armure extends Item
{
    // class members
    private int m_armure;

    public Armure(String name, int armure)
    {
        super(name, Type.ARMURE);
        if (armure < 1)
            throw new IllegalArgumentException("Erreur : les points d'armures doivent être supérieurs à 0");
        this.m_armure = armure;
    }

    public int getArmure()
    {
        return this.m_armure;
    }

    public abstract void appliqueBonusArmure();
}
