package dnd.objet;
import dnd.Asset;

public class Item implements Asset
{
    // class members
    private final String m_nom;

    // ctor
    public Item(String nom)
    {
        if (!nom.isEmpty())
            this.m_nom = nom;
        else
            throw new IllegalArgumentException("Erreur : l'item ne peut pas avoir un nom vide !");
    }


    public String getNom()
    {
        return this.m_nom;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Item other = (Item)obj;
        return this.m_nom.equals(other.getNom());
    }

    @Override
    public String getEtiquette()
    {
        return " * ";
    }

    @Override
    public String toString()
    {
        return "Item()"; // TODO : à compléter
    }
}
