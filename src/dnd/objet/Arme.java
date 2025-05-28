package dnd.objet;

public abstract class Arme extends Item
{
    private int m_nb_de;
    private int m_nb_face;
    private int m_portee;

    public Arme(String name, int nb_de, int nb_face, int m_portee)
    {
        super(name);
        this.m_nb_de = nb_de;
        this.m_nb_face = nb_face;
        this.m_portee = m_portee;
    }

    protected int getnbDe()
    {
        return this.m_nb_de;
    }

    protected int getnbFace()
    {
        return this.m_nb_face;
    }

    public abstract void appliqueBonusArme();// pour arme lourde etc
    public abstract int jetDegat();

}
