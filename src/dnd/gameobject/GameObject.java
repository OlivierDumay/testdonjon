package dnd.gameobject;
import dnd.Asset;
import dnd.objet.Item;
import dnd.partie.donjon.Case;

public interface GameObject extends Asset
{
    void deplacement(int x, int y);

    void attaque(GameObject defenseur);

    void prendre(Item objet);
}