package dnd.gameobject.personnage;
import dnd.Asset;
import dnd.gameobject.Caracteristique;
import dnd.gameobject.personnage.EquipementPersonnage;
import dnd.gameobject.GameObject;
import dnd.gameobject.personnage.classe.*;
import dnd.gameobject.personnage.race.*;
import dnd.objet.Item;
import dnd.objet.arme.ArmeCourante;
import dnd.objet.arme.ArmeGuerre;
import dnd.partie.donjon.Case;

import static dnd.des.De.lancerDe;
// import dnd.partie.Position;

public class Personnage implements GameObject, Asset
{
    // class members
    private String m_nom;
    private boolean m_etat;
    private Caracteristique m_caracteristique;
    private Inventaire m_inventaire;
    private EquipementPersonnage m_equipement;
    private Classe m_classe;
    private Race m_race;

    public Personnage(String nom, EnumClasse classe, EnumRace race)
    {
        if (nom == null || classe == null || race == null)
            throw new IllegalArgumentException("Erreur : impossible de traiter une référence null !");

        if (nom.isEmpty())
            throw new IllegalArgumentException("Erreur : le nom du personnage ne peut pas être vide !");


        // Mise en place du nom
        this.m_nom = nom;

        // Instanciation de la classe
        switch (classe)
        {
            // TODO : définir ici tous les trucs spécifiques (pv etc) puis ajouter des choses dans l'instanciation de la race
            case CLERC:
                this.m_classe = new Clerc();
                this.m_caracteristique = new Caracteristique(lancerDe(4, 4) + this.m_classe.bonusCreation() + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3); // TODO : olivier vérifie si c bien
                this.m_equipement = new EquipementPersonnage();
                this.m_equipement.addEquipement(new ArmeCourante("Masse d'armes", lancerDe(1, 6), 1));
                this.m_equipement.addEquipement(new ArmeGuerre("Masse d'armes", lancerDe(1, 6), 1));
                break;
            case GUERRIER:
                this.m_classe = new Guerrier();
                this.m_caracteristique = new Caracteristique(lancerDe(4, 4) + this.m_classe.bonusCreation() + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3);
                this.m_equipement = new EquipementPersonnage();
                break;
            case MAGICIEN:
                this.m_classe = new Magicien();
                this.m_caracteristique = new Caracteristique(lancerDe(4, 4) + this.m_classe.bonusCreation() + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3);
                this.m_equipement = new EquipementPersonnage();
                break;
            case ROUBLARD:
                this.m_classe = new Roublard();
                this.m_caracteristique = new Caracteristique(lancerDe(4, 4) + this.m_classe.bonusCreation() + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3); // TODO : Implémenter ici les d je ne sais combien pour les caractéristiques non définies par la race/classe
                this.m_equipement = new EquipementPersonnage();
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
    }

    public void deplacement(Case destination)
    {

    }

    public void attaque(GameObject defenseur)
    {

    }

    public void prendre(Item objet)
    {

    }

    public String getEtiquette()
    {
        return this.m_nom.substring(0, 3);
    }
}
