package dnd.objet;

public class Item
{
    // class members
    private String m_nom;
    private String m_etiquette;

    // ctor
    public Item(String nom)
    {
        if (!nom.isEmpty())
            this.m_nom = nom;
        else
            throw new IllegalArgumentException("Erreur : l'item ne peut pas avoir un nom vide !");
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Item other = (Item)obj;
        return this.m_nom.equals(other.getNom()) && this.m_etiquette.equals(other.getEtiquette());
    }

    private String getEtiquette()
    {
        return this.m_nom.substring(0, 4);
    }

    public String getNom()
    {
        return this.m_nom;
    }
}
