package dnd.objet;

public abstract class Arme extends Item
{
    private int m_deDegat;
    private int m_nbDe;
    private int m_portee;

    public Arme(String name, int m_deDegat, int m_nbDe, int m_portee)
    {
        super(name);
        this.m_deDegat = m_deDegat;
        this.m_nbDe = m_nbDe;
        this.m_portee = m_portee;
    }

    public void appliqueBonusArme() // pour arme lourde etc
    {}

    public int jetDegat()
    {}
}
