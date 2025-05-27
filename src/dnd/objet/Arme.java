package dnd.objet;

public abstract class Arme extends Item
{
    protected int m_deDegat;
    protected int m_nbDe;
    protected int m_portee;

    public Arme(String name, int m_deDegat, int m_nbDe, int m_portee)
    {
        super(name);
        this.m_deDegat = m_deDegat;
        this.m_nbDe = m_nbDe;
        this.m_portee = m_portee;
    }

    public abstract void appliqueBonusArme();// pour arme lourde etc
    public abstract int jetDegat();

}
