package dnd.partie.donjon;

import dnd.Asset;
import dnd.des.De;
import dnd.gameobject.*;
import dnd.gameobject.personnage.Personnage;
import dnd.objet.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Carte
{
    // class members
    private int m_max_x;
    private int m_max_y;
    private Case m_grille[][];
    private HashMap<GameObject, Case> m_emplacementObjet;
    private List<Asset> m_asset;


    // ctor
    public Carte(int x, int y)
    {
        if (x < 0 && y < 0)
            throw new IllegalArgumentException("Erreur : les dimensions de la carte doivent être supérieures à 0");

        this.m_max_x = x;
        this.m_max_y = y;
        this.m_grille = new Case[this.m_max_x][this.m_max_y];
        this.m_emplacementObjet = new HashMap<>();
        this.m_asset = new ArrayList<>();

        //init de chaque cases
        for (int i = 0; i<this.m_max_x; i++)
            for(int j = 0; j<this.m_max_y; j++)
            {
                m_grille[i][j] = new Case(i, j);
            }
    }

    public GameObject getQuelGameObjectEstIci(int x, int y)
    {
        return this.m_grille[x][y].getGameObject();
    }

    public Item getQuelItemEstIci(int x, int y)
    {
        return this.m_grille[x][y].getItem();
    }

    public Case OuEstGameObjet(GameObject quoi)
    {
        return m_emplacementObjet.get(quoi);
    }

    public void ajouterItem(Item item, int x, int y)
    {
        if (item == null)
        { throw new IllegalArgumentException("Erreur : l'item ne peut pas être null");}
        if (!this.m_grille[x][y].getObstacle() && this.m_grille[x][y].getItem() == null ) // si il n'y a pas d'obstacle, ni d'item
        {
            this.m_grille[x][y].setItem(item); // ajout dans la case
        }
    }
    public void ajouterGameObject(GameObject gameObject, int x, int y) throws IllegalArgumentException
    {
        if (gameObject == null)
        {throw new IllegalArgumentException("Erreur : le gameObject ne peut pas être null");}
        if (this.m_grille[x][y].getGameObject() == null && !this.m_grille[x][y].getObstacle()) // si il n'y a pas de perso ou de monstre ou d'obstaclesur la case
        {
            this.m_grille[x][y].setGameObject(gameObject);     // ajout dans la case
            gameObject.setPosition(x,y);
            this.m_emplacementObjet.put(gameObject, this.m_grille[x][y]); // ajout dans la hashmap m_emplacementObjet
        }
    }
    public void ajouterObstacle(int x, int y)
    {
        this.m_grille[x][y].setObstacle(true);
    }

    public void retirerItem(Item item,int x, int y)
    {
        //manque le test: 2 item ou 2 gameObjet sur meme case impossible ou si il y a un obstacle
        if (item == null)
        { throw new IllegalArgumentException("Erreur : l'item ne peut pas être null");}
        if (this.m_grille[x][y].getItem() == item ) // test si l'item est present dans la case
        {
            this.m_grille[x][y].setItem(null); // ajout dans la case
        }
    }
    public void retirerGameObject(GameObject gameObject, int x, int y)
    {
        //manque le test: 2 item ou 2 gameObjet sur meme case impossible ou si il y a un obstacle
        if (gameObject == null)
        {throw new IllegalArgumentException("Erreur : le gameObject ne peut pas être null");}
        if (this.m_grille[x][y].getGameObject() == gameObject) // test si l'objet a enlever est bien present
        {
            this.m_grille[x][y].setGameObject(null); // ajout dans la case
            Case cse = this.m_grille[x][y];
            this.m_emplacementObjet.remove(gameObject, cse); // supression dans la hashmap m_emplacementObjet
        }

    }


    public String getEtiquetteDeLaCase(int x, int y)
    {
        if (this.m_grille[x][y].getObstacle()) { return "[ ]";} // si il y a un obstacle
        if (this.m_grille[x][y].getGameObject() != null) // si un monstre ou perso est dans la case
        {
            return this.m_grille[x][y].getGameObject().getEtiquette();
        }
        if (this.m_grille[x][y].getItem() != null) // si il y a un item
        {
            return " * ";
        }
        else // sinon affiche un vide
        {
            return " . ";
        }
    }

    public Case getCase(int x, int y)
    {
        return m_grille[x][y];
    }
    public int getMaxX() {return this.m_max_x;}
    public int getMaxY() {return this.m_max_y;}

    // les actions

    public int seDeplacer(int x, int y, GameObject gameObject)
    {

        int[] postionGameObject =  gameObject.getPosition();
        Case caseGameObject = this.getCase(postionGameObject[0], postionGameObject[1]);

        float distance = caseGameObject.calculDistance(this.getCase(x,y));
        if (distance <= (float)gameObject.getVitesse())
        {
            this.retirerGameObject(gameObject ,postionGameObject[0], postionGameObject[1]);
            this.ajouterGameObject(gameObject, x, y);
            return 0;
        } else
        {
            System.out.println("Case hors de portée, choisissez une autre case");
            return 1;
        }// erreur distance trop grande


    }

    public int attaquer(GameObject attaquant, GameObject defenseur)
    {
        // retour:
        //      0 si l'attaque est réussie et le defenseur est encore vivant
        //      1 si l'attaque est ratée
        //      2 si le défenseur est mort
        //      3 si cible hors de portée


        // test portée
        int[] postionAttaquant =  attaquant.getPosition();
        int[] postionDefenseur =  defenseur.getPosition();
        Case caseAttaquant = this.getCase(postionAttaquant[0], postionAttaquant[1]);
        Case caseDefenseur = this.getCase(postionDefenseur[0], postionDefenseur[1]);

        float distance = caseAttaquant.calculDistance(caseDefenseur);
        System.out.println("Carte.attaquer: test: distance: " + distance + ", portée: " +attaquant.getPortee());
        if (distance > (float)attaquant.getPortee()) // si distance est plus grande que la portée de l'attaquant
        {
            return 3;
        }

        // l'attaque

        int resultatDeAttaque = De.lancerDe(1,20);
        int armureDefenseur = defenseur.getArmure();
        int bonusAttaque = attaquant.getBonusAttaque();

        if (resultatDeAttaque + bonusAttaque >= armureDefenseur)
        {
            // attaque réussie, application des dégats

            int[] deDegat = attaquant.getAttaque();
            int resultatDegat = De.lancerDe(deDegat[0],deDegat[1]);

            if (!defenseur.setPV(defenseur.getPV() - resultatDegat)) // si renvoie faux, le defenseur est mort
            {
                return 2; //le defenseur est mort
            }
            else {return 0;}  //l'attaque est réussie et le defenseur est encore vivant

        }
        else {return 1;} // l'attaque a échoué
    }

    public void prendre (Personnage perso, Item item)
    {
        int oxy[] = perso.getPosition();
        perso.getInventaire().addItem(this.getQuelItemEstIci(oxy[0], oxy[1]));
        this.retirerItem(this.getQuelItemEstIci(oxy[0], oxy[1]), oxy[0], oxy[1]);
    }


}
