package dnd.objet;

public class Arme extends Item
{
    private int m_degat;

    public Arme(String name, int degat)
    {
        super(name);
        if (degat < 1)
            throw new IllegalArgumentException("Erreur : les dégâts de l'arme doivent être supérieurs à 0 !");
    }
}
