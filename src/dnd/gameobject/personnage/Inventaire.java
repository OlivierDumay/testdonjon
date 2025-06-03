package dnd.gameobject.personnage;
import dnd.objet.Arme;
import dnd.objet.Armure;
import dnd.objet.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Inventaire
{
    // class members
    private ArrayList<Arme> m_armes;
    private ArrayList<Armure> m_armures;

    // ctor
    public Inventaire()
    {
        this.m_armes = new ArrayList<>();
        this.m_armures = new ArrayList<>();
    }

    public void addArme(Arme arme)
    {
        if (arme == null)
            throw new IllegalArgumentException("Erreur : l'arme ne peut pas être null");
        this.m_armes.add(arme);
    }

    public void addArmure(Armure armure)
    {
        if (armure == null)
            throw new IllegalArgumentException("Erreur : l'armure ne peut pas être null");
        this.m_armures.add(armure);
    }

    public Arme removeArme(int n_arme)
    {
        if (n_arme < 0 || n_arme >= this.m_armes.size())
            throw new IllegalArgumentException("Erreur : l'item ne peut pas être inférieur à 0");
        Arme tmp = this.m_armes.get(n_arme);

        this.m_armes.remove(n_arme);

        return tmp;
    }

    public Armure removeArmure(int n_armure)
    {
        if (n_armure < 0 || n_armure >= this.m_armures.size())
            throw new IllegalArgumentException("Erreur : l'item ne peut pas être inférieur à 0");
        Armure tmp = this.m_armures.get(n_armure);

        this.m_armures.remove(n_armure);

        return tmp;
    }

    public int count_armes()
    {
        return this.m_armes.size();
    }

    public int count_armures()
    {
        return this.m_armures.size();
    }

}
