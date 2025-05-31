package dnd.partie.donjon;

import dnd.Asset;
import dnd.objet.Item;
import dnd.gameobject.*;

import java.util.ArrayList;
import java.util.List;

public class Case
{
    // class members
    private final int m_x;
    private final int m_y;
    private ArrayList<Item> m_item;
    private GameObject m_gameObject;
    private boolean m_obstacle;


    // ctor
    public Case(int x, int y)
    {
        if (x<0 || y<0)
            throw new IllegalArgumentException("Erreur : la position ne peut pas être inférieur à zero");
        this.m_x = x;
        this.m_y = y;
        this.m_item = new ArrayList<>();
        this.m_gameObject = null;
        this.m_obstacle = false;
    }


    public ArrayList<Item> getItem() { return this.m_item;}
    public String getNomsItem()
    {
        String ret = "";
        for (Item item : this.m_item)
        {
            ret += item.toString();
        }
        return ret;
    }
    public GameObject getGameObject() { return this.m_gameObject;}
    public void setGameObject(GameObject gameObject) { this.m_gameObject = gameObject;}
    public boolean getObstacle() { return this.m_obstacle;}
    public void setObstacle(boolean val) { this.m_obstacle = val;}

    @Override
    public String toString()
    {
        return ("m_x= " + this.m_x +
                ", m_y= " + this.m_y +
                ", m_gameObject= " + this.m_gameObject.toString() +
                ", m_item= " + this.getNomsItem() +
                ", m_obstacle = " + (this.m_obstacle ? "oui" : "non"));
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
