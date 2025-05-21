package dnd.gameobject;
import dnd.objet.Item;
import dnd.partie.Position;

public interface GameObject
{
    void deplacement(Position destination);

    void attaque(GameObject defenseur);

    void prendre(Item objet);

    String getEtiquette();
}