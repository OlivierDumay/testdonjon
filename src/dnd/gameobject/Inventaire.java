package dnd.gameobject;
import dnd.objet.Item;
import java.util.ArrayList;

public class Inventaire
{
    // class members
    private ArrayList<Item> m_items;

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

    public void removeItem(Item item)
    {
        for (Item i : this.m_items)
            if (i.equals(item))
                this.m_items.remove(item);
    }
}
