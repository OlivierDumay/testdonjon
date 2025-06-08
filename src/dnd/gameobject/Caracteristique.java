package dnd.gameobject;

public class Caracteristique
{
    // class members
    private final int m_pvmax;
    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;

    // ctor
    public Caracteristique(int pv, int force, int dexterite, int vitesse, int initiative)
    {
        if (!(pv > 0 && force > 0 && dexterite > 0 && vitesse > 0 && initiative > 0))
            throw new IllegalArgumentException("Les caractéristiques doivent être supérieures à 0");

        this.m_pvmax = pv;
        this.m_pv = pv;
        this.m_force = force;
        this.m_dexterite = dexterite;
        this.m_vitesse = vitesse;
        this.m_initiative = initiative;
    }

    public int getPV()
    {
        return this.m_pv;
    }

    public int getForce()
    {
        return this.m_force;
    }

    public int getDexterite()
    {
        return this.m_dexterite;
    }

    public int getVitesse()
    {
        return this.m_vitesse;
    }

    public int getPVMax()
    {
        return this.m_pvmax;
    }
    public void subPV(int pv)
    {
        if (pv < 0 || pv > getPV())
            throw new IllegalArgumentException("Erreur : impossible de retirer un nombre de points de vie nul ou supérieur aux points de vie actuels");
        setPV(getPV()-pv);
    }

    public void setPV(int pv)
    {
        if (pv < 0)
            this.m_pv = 0;
        else
            this.m_pv = pv;
    }
}
