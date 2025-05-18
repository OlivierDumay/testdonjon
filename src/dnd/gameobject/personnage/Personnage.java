package dnd.gameobject.personnage;
import dnd.gameobject.GameObject;
import dnd.gameobject.Inventaire;
import dnd.objet.Item;
import dnd.partie.Position;

public class Personnage implements GameObject
{
    // class members
    private String m_nom;
    private boolean m_etat;
    private String m_etiquette;
    private Inventaire m_inventaire;

    @Override
    public void deplacement(Position destination)
    {

    }

    @Override
    public void attaque(GameObject defenseur)
    {

    }

    @Override
    public void prendre(Item objet)
    {

    }

    @Override
    public String getEtiquette()
    {
        return "";
    }
}
