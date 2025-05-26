package dnd.partie.donjon;
import dnd.Asset;
import java.util.ArrayList;

public class Case
{
    // class members
    private final int m_x;
    private final int m_y;
    protected ArrayList<Asset> m_contenu;


    // ctor
    public Case(int x, int y)
    {
        if (x<0 || y<0)
            throw new IllegalArgumentException("Erreur : la position ne peut pas être inférieur à zero");
        m_x = x;
        m_y = y;
        m_contenu = new ArrayList<Asset>();
    }


    ArrayList<Asset> getContenu()
    {
        return this.m_contenu; // TODO : rendre la liste immuable
    }

    @Override
    public String toString()
    {
        return "Case(m_contenu=" + this.m_contenu.toString() + ", m_x=" + this.m_x + ", m_y=" + this.m_y + ")";
    }

    protected int getX()
    {
        return this.m_x;
    }

    protected int getY()
    {
        return this.m_y;
    }

    public float calculDistance(Case other)
    {
        if (this.equalsPosition(other))
        {
            return 0;
        }
    int ox = this.getX();
    int oy = this.getY();
    int dx = other.getX();
    int dy = other.getY();

    int diffx = Math.abs(ox - dx);
    int diffy = Math.abs(oy - dy);

    float distanceCarre = diffx * diffx + diffy * diffy;
    float distance = (float) Math.sqrt(distanceCarre);

    return distance;
    }

    public boolean equalsPosition(Case other)
    {
        return ((this.m_x == other.getX()) && (this.m_y == other.getY()));
    }

}
