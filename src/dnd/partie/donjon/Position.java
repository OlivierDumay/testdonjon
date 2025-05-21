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


}
