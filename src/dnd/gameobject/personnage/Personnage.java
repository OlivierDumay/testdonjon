package dnd.gameobject.personnage;
import dnd.Asset;
import dnd.gameobject.personnage.EquipementPersonnage;
import dnd.gameobject.GameObject;
import dnd.gameobject.personnage.classe.*;
import dnd.gameobject.personnage.race.*;
import dnd.objet.Item;
import dnd.partie.Position;

public class Personnage implements GameObject, Asset
{
    // class members
    private String m_nom;
    private boolean m_etat;
    private String m_etiquette;
    private Inventaire m_inventaire;
    private EquipementPersonnage m_equipement;
    private Classe m_classe;
    private Race m_race;

    public Personnage(String nom, EnumClasse classe, EnumRace race)
    {
        if (nom.isEmpty())
            throw new IllegalArgumentException("Erreur : le nom du personnage ne peut pas être vide !");

        // Instanciation de la classe
        switch (classe)
        {
            case CLERC:
                this.m_classe = new Clerc();
                break;
            case GUERRIER:
                this.m_classe = new Guerrier();
                break;
            case MAGICIEN:
                this.m_classe = new Magicien();
                break;
            case ROUBLARD:
                this.m_classe = new Roublard();
                break;
            default:
                throw new IllegalArgumentException("Erreur : classe invalide !");
        }

        // Instanciation de la race
        switch (race)
        {
            case ELFE:
                this.m_race = new Elfe();
                break;
            case HALFLIN:
                this.m_race = new Halflin();
                break;
            case HUMAIN:
                this.m_race = new Humain();
                break;
            case NAIN:
                this.m_race = new Nain();
                break;
            default:
                throw new IllegalArgumentException("Erreur : race invalide !");
        }

        // Vivant
        this.m_etat = true;

        this.m_inventaire = new Inventaire();
        this.m_equipement = new EquipementPersonnage();
    }

    @Override
    public void deplacement(Position destination)
    {

    }

    @Override
    public void attaque(GameObject defenseur)
    {

    }

    @Override
    public void prendre(Item objet)
    {

    }

    @Override
    public String getEtiquette()
    {
        return "";
    }
}
