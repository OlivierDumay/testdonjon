package dnd.gameobject;
import dnd.Asset;
import dnd.objet.Item;
import dnd.partie.donjon.Case;

public interface GameObject extends Asset
{
    void deplacement(Case destination);

    void attaque(GameObject defenseur);

    void prendre(Item objet);
}