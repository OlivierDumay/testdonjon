package dnd;

import dnd.Type;
import dnd.gameobject.ennemi.Monstre;
import dnd.gameobject.personnage.Personnage;
import dnd.objet.Item;

public interface Asset
{
    public String getEtiquette();

    public Type getType();
}
