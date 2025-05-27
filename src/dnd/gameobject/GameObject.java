package dnd.gameobject;
import dnd.objet.Item;
import dnd.partie.donjon.Case;

public interface GameObject
{
    void deplacement(Case destination);

    void attaque(GameObject defenseur);

    void prendre(Item objet);
}