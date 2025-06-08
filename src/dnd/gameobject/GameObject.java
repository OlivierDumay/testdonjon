package dnd.gameobject;
import dnd.Asset;
import dnd.objet.Item;
import dnd.partie.donjon.Case;

public interface GameObject extends Asset
{
    public void setPosition(int x , int y);

    public int[] getPosition();

    public int getVitesse();

    public int getInitiative();

    public String getNom();

    public int getPortee();

    public int[] getAttaque();

    public int getArmure();

    public int getBonusAttaque();

    public int getPV();

    public boolean setPV(int pv); // modifie le nombre de pv, renvoie vrai si pv>0, faux sinon

    public boolean testEtatVie(); // renvoie vrai si pv>0


    /*
    int getPv() // faire les autres caractéristique au besoin
         */

}
