package dnd.partie;
import dnd.Asset;
import java.util.ArrayList;

public class Case
{
    // class members
    private ArrayList<Asset> m_contenu;
    private Position m_position;

    // ctor
    public Case(Position position)
    {
        if (position == null)
            throw new IllegalArgumentException("Erreur : la position ne peut pas être null");
        m_contenu = new ArrayList<>();
        this.m_position = position;
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

    public Position getPosition()
    {
        return this.m_position;
    }

    @Override
    public String toString()
    {
        return "Case(m_contenu=" + this.m_contenu.toString() + ", m_position=" + this.m_position.toString() + ")";
    }
}
