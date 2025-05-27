package dnd.gameobject.personnage;

import dnd.objet.Item;

import java.util.ArrayList;

public class EquipementPersonnage
{
    // class members
    private ArrayList<Item> m_equipement;

    public EquipementPersonnage()
    {
        this.m_equipement = new ArrayList<Item>();
    }

    public void addEquipement(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("Erreur : l'item ne peut pas être null");
        this.m_equipement.add(item);
    }

    public ArrayList<Item> getEquipement()
    {
        return this.m_equipement;
    }
}
