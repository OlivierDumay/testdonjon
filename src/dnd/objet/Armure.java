package dnd.objet;

public abstract class Armure extends Item
{
    private int m_armure;

    public Armure(String name, int armure)
    {
        super(name);
        if (armure < 1)
            throw new IllegalArgumentException("Erreur : la valeur de l'armure doit être supérieur à 0 !");
        this.m_armure = armure;
    }

    public abstract void appliqueBonusArmure();
}
