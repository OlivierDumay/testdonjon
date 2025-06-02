package dnd.gameobject.ennemi;

import dnd.Asset;
import dnd.gameobject.Caracteristique;
import dnd.gameobject.GameObject;
import dnd.objet.Item;
import dnd.partie.donjon.Case;

public class Monstre implements GameObject, Asset
{
    // class members
    private static int m_current_id = 0;
    private int m_id;
    private String m_espece;
    private int[] m_attaque;
    private int m_classeArmure;
    private Caracteristique m_caracteristique;
    private String m_etiquette;
    private int[] m_position;

    // ctor
    // voir TODO.md
    public Monstre(String espece, int nDe, int nFace, int armure, int pv, int force, int dexterite, int vitesse, int initiative, String etiquette)
    {
        if (espece.isEmpty())
            throw new IllegalArgumentException("Erreur : le nom du monstre ne doit pas être vide");
        if (nDe < 1 || nFace < 1)
            throw new IllegalArgumentException("Erreur l'attaque d'un monstre doit être supérieure à 0");
        if (etiquette.length() < 1 || etiquette.length() > 3)
        {
            throw new IllegalArgumentException("Erreur: l'etiquette du monstre doit faire entre 1 et 3 caractères");
        }
        this.m_id = m_current_id;
        m_current_id++;
        this.m_espece = espece;
        this.m_attaque[0] = nDe;
        this.m_attaque[1] = nFace;
        this.m_classeArmure = armure;
        this.m_caracteristique = new Caracteristique(pv, force, dexterite, vitesse, initiative);
        this.m_etiquette = etiquette;
        //Init position
        this.m_position = new int[2];
    }

    @Override
    public void deplacement(Case destination)
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
        return m_etiquette;
    }

    public void setPosition(int x , int y)
    {
        this.m_position[0] = x;
        this.m_position[1] = y;
    }

    public int[] getPosition()
    {
        return this.m_position;
    }

    public int getVitesse()
    {
        return this.m_caracteristique.getVitesse();
    }
}
