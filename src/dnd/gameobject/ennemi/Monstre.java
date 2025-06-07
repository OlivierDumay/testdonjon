package dnd.gameobject.ennemi;

import dnd.Asset;
import dnd.Type;
import dnd.gameobject.Caracteristique;
import dnd.gameobject.GameObject;
import dnd.gameobject.personnage.Personnage;
import dnd.objet.Item;
import dnd.partie.donjon.Case;

import java.util.NoSuchElementException;

import static dnd.Type.MONSTRE;

public class Monstre implements GameObject, Asset
{
    // class members
    private int m_current_id;
    private int m_id;
    private String m_espece;
    private int[] m_attaque;
    private int m_classeArmure;
    private Caracteristique m_caracteristique;
    private String m_etiquette;
    private int[] m_position;
    private static final Type m_type = MONSTRE;

    // ctor
    // voir TODO.md
    public Monstre(String espece, int nDe, int nFace, int armure, int pv, int vitesse, String etiquette,int id)
    {
        if (espece.isEmpty())
            throw new IllegalArgumentException("Erreur : le nom du monstre ne doit pas être vide");
        if (nDe < 1 || nFace < 1)
            throw new IllegalArgumentException("Erreur l'attaque d'un monstre doit être supérieure à 0");
        if (etiquette.isEmpty() || etiquette.length() > 3)
        {
            throw new IllegalArgumentException("Erreur: l'etiquette du monstre doit faire entre 1 et 3 caractères");
        }
        this.m_id = id;
        this.m_espece = espece;
        this.m_attaque = new int[2];
        this.m_attaque[0] = nDe;
        this.m_attaque[1] = nFace;
        this.m_classeArmure = armure;
        this.m_caracteristique = new Caracteristique(pv, 1, 1, vitesse, 1);
        this.m_etiquette = etiquette;
        //Init position
        this.m_position = new int[2];
    }

    @Override
    public String getEtiquette()
    {
        return m_etiquette;
    }

    @Override
    public Type getType()
    {
        return m_type;
    }

    public void setPosition(int x , int y)
    {
        this.m_position[0] = x;
        this.m_position[1] = y;
    }

    public String getNom() {return (m_espece+m_id);}

    public int[] getPosition()
    {
        return this.m_position;
    }

    public int getVitesse()
    {
        return this.m_caracteristique.getVitesse();
    }

    public int getArmure()
    {
        return this.m_classeArmure;
    }

    public int getPortee() { return 1;}

    public int[] getAttaque()
    {
        int[] retour = {0,0};
        retour[0] = this.m_attaque[0];
        retour[1] = this.m_attaque[1];
        return retour;
    }

    public int getBonusAttaque() {return this.m_caracteristique.getForce();}

    public int getPV()
    {
        return this.m_caracteristique.getPV();
    }

    public boolean setPV(int pv)
    {
        this.m_caracteristique.setPV(pv);
        return this.testEtatVie();

    }

    public boolean testEtatVie()
    {
        if (this.m_caracteristique.getPV()<=0)
        {return false;}
        return true;
    }

    public int getAttaque1()
    {return this.m_attaque[0];}

    public int getAttaque2()
    {return this.m_attaque[1];}

    public int getID()
    {return this.m_id;}
}
