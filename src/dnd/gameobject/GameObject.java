package dnd.gameobject;
import dnd.Asset;
import dnd.objet.Item;
import dnd.partie.donjon.Case;

public interface GameObject extends Asset
{
    public void setPosition(int x , int y);

    public int[] getPosition();

    public int getVitesse();

    public String getNom();
    /*
    int getPv() // faire les autres caractéristique au besoin
         */

}
