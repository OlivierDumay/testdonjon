package dnd.gameobject.personnage;
import dnd.objet.Arme;
import dnd.objet.Armure;
import dnd.objet.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Inventaire
{
    // class members
    private ArrayList<Item> m_items;
    private ArrayList<Arme> m_armes;
    private ArrayList<Armure> m_armures;

    // ctor
    public Inventaire()
    {
        this.m_items = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("Erreur : l'item ne peut pas être nul");
        this.m_items.add(item);
    }

    public Item remove(int n_item)
    {
        if (n_item < 0 || n_item >= this.m_items.size())
            throw new IllegalArgumentException("Erreur : l'item ne peut pas être inférieur à 0");

        Item tmp = this.m_items.get(n_item);

        this.m_items.remove(n_item);

        return tmp;
    }

    public int count()
    {
        return this.m_items.size();
    }
}
