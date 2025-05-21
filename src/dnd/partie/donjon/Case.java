package dnd.partie;
import dnd.Asset;
import java.util.ArrayList;

public class Case
{
    // class members
    private int m_x;
    private int m_y;
    private ArrayList<Asset> m_contenu;


    // ctor
    public Case(int x, int y)
    {
        if (x<0 || y<0)
            throw new IllegalArgumentException("Erreur : la position ne peut pas être inférieur à zero");
        m_contenu = new ArrayList<Asset>();
    }

    void ajouter(Asset asset)
    {
        if (asset == null)
            throw new IllegalArgumentException("Erreur : l'asset ne peut pas être null");
        this.m_contenu.add(asset);
    }

    void retirer(Asset asset)
    {
        if (asset == null)
            throw new IllegalArgumentException("Erreur : l'asset ne peut pas être null");
        this.m_contenu.remove(asset);
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

    private int getX()
    {
        return this.m_x;
    }

    private int getY()
    {
        return this.m_y;
    }

    public int calculDistance(int destinationX, int destinationY)
    {
        if (this.equalsPosition(grille[destinationX][destinationY]))
        {
            return 0;
        }

        // TODO : olivier fait ça nb : oui oui aller king kong
        return 0;
    }

    public boolean equalsPosition(Case case)    { return ((this.m_x == case.m_x) && (this.m_y == case.m_y)); }

}
