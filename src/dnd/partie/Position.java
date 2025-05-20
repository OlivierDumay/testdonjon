package dnd.partie;

public class Position
{
    // class members
    private int m_x;
    private int m_y;

    public Position(int x, int y)
    {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("Erreur : position invalide !");
        // TODO : Ajouter une vérification de si c'est bien dans la carte ici jsp (bounds checking)
        this.m_x = x;
        this.m_y = y;
    }

    private int getX()
    {
        return this.m_x;
    }

    private int getY()
    {
        return this.m_y;
    }

    public int calculDistance(Position pos)
    {
        if (this.equals(pos))
            return 0;
        // TODO : olivier fait ça nb : oui oui aller king kong
        return 0;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Position other = (Position)obj;
        return this.m_x == other.getX() && this.m_y == other.getY();
    }

    @Override
    public int hashCode()
    {
        return 25 * this.m_x + this.m_y;
    }
}
