package dnd.gameobject.ennemi;

import dnd.gameobject.GameObject;
import dnd.objet.Item;
import dnd.partie.Position;

public class Monstre implements GameObject
{
    // class members
    private static int m_current_id = 0;
    private int m_id;
    private String m_espece;
    private int m_attaque;
    private String m_etiquette;

    // ctor
    // voir TODO.md
    public Monstre(String espece, int attaque)
    {
        if (espece.isEmpty())
            throw new IllegalArgumentException("Erreur : le nom du monstre ne doit pas être vide");
        if (attaque < 1)
            throw new IllegalArgumentException("Erreur l'attaque d'un monstre doit être supérieure à 0");
        this.m_espece = espece;
        this.m_attaque = attaque;
        this.m_id = m_current_id;
        m_current_id++;
    }

    @Override
    public void deplacement(Position destination)
    {

    }

    @Override
    public void attaque(GameObject defenseur)
    {

    }

    @Override
    public void prendre(Item objet) {

    }

    @Override
    public String getEtiquette()
    {
        return "";
    }
}
