package dnd.objet;
import dnd.Asset;
import dnd.Type;
import dnd.gameobject.ennemi.Monstre;
import dnd.gameobject.personnage.Personnage;

import java.util.NoSuchElementException;

public class Item implements Asset
{
    // class members
    private final String m_nom;
    private final Type m_type;

    // ctor
    public Item(String nom, Type type)
    {
        if (type == null)
            throw new IllegalArgumentException("Erreur : le type ne peut pas être null !");

        this.m_type = type;

        if (!nom.isEmpty())
            this.m_nom = nom;
        else
            throw new IllegalArgumentException("Erreur : l'item ne peut pas avoir un nom vide !");
    }


    public String getNom()
    {
        return this.m_nom;
    }

    public Type getType()
    {
        return this.m_type;
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
