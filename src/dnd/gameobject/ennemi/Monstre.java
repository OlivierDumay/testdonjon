package dnd.gameobject.ennemi;

import dnd.Asset;
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
    private int m_PV;
    private int m_vitesse;
    private String m_etiquette;

    // ctor
    // voir TODO.md
    public Monstre(String espece, int nDe, int nFace, int armure, int pv, int vitesse, String etiquette)
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
        this.m_PV = pv;
        this.m_vitesse = vitesse;
        this.m_etiquette = etiquette;
    }

    public void deplacement(Case destination)
    {

    }

    // À EFFACER
    @Override
    public void deplacement(int x, int y) {

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
}
