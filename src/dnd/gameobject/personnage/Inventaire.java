package dnd.gameobject.personnage;

import dnd.objet.Item;


import java.util.ArrayList;

public class Inventaire
{
    // class members
//    private ArrayList<Arme> m_armes;
//    private ArrayList<Armure> m_armures;
    private ArrayList<Item> m_items;

    // ctor
    public Inventaire()
    {
//        this.m_armes = new ArrayList<>();
//        this.m_armures = new ArrayList<>();
        this.m_items = new ArrayList<>();
    }

    public ArrayList<Item> getInventaire()
    {
        return this.m_items;
    }

    public void addItem(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("Erreur : l'item ne peut pas être null");
        this.m_items.add(item);
    }

    public Item removeItem(int index)
    {
        if (index < 0 || index >= this.m_items.size())
            throw new IllegalArgumentException("Erreur : pas d'item avec l'index " + index);
        return this.m_items.remove(index);
    }

    public int size()
    {
        return this.m_items.size();
    }
}
