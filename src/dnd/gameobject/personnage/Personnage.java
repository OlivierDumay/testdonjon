package dnd.gameobject.personnage;
import dnd.Asset;
import dnd.Type;
import dnd.gameobject.Caracteristique;
import dnd.gameobject.GameObject;
import dnd.gameobject.personnage.classe.*;
import dnd.gameobject.personnage.race.*;
import dnd.objet.Arme;
import dnd.objet.Armure;
import dnd.objet.Item;
import dnd.objet.arme.ArmeADistance;
import dnd.objet.arme.ArmeCourante;
import dnd.objet.arme.ArmeGuerre;
import dnd.objet.armure.ArmureLegere;
import dnd.objet.armure.ArmureLourde;

import static dnd.Type.PERSONNAGE;
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
    private int[] m_position;
    private static Type m_type = PERSONNAGE;


    public Personnage(String nom, EnumClasse classe, EnumRace race)
    {
        if (nom == null || classe == null || race == null)
            throw new IllegalArgumentException("Erreur : impossible de traiter une référence null !");

        if (nom.isEmpty())
            throw new IllegalArgumentException("Erreur : le nom du personnage ne peut pas être vide !");


        // Mise en place du nom
        this.m_nom = nom;
        // Vivant
        this.m_etat = true;
        // Instanciation de l'inventaire
        this.m_inventaire = new Inventaire();
        // Instanciation de la classe
        switch (classe)
        {
            // TODO : définir ici tous les trucs spécifiques (pv etc) puis ajouter des choses dans l'instanciation de la race
            case CLERC:
                this.m_classe = new Clerc();
                this.m_caracteristique = new Caracteristique(lancerDe(4, 4) + this.m_classe.bonusCreation() + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3); // TODO : olivier vérifie si c bien
                this.m_equipement = new EquipementPersonnage();
                this.m_inventaire.addItem(new ArmeCourante("Masse d'armes", 1, 6, 1));
                this.m_inventaire.addItem(new ArmeADistance("Arbalète légère", 1, 8, 16));
                this.m_inventaire.addItem(new ArmureLegere("Armure d'écailles", 9));
                break;
            case GUERRIER:
                this.m_classe = new Guerrier();
                this.m_caracteristique = new Caracteristique(lancerDe(4, 4) + this.m_classe.bonusCreation() + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3);
                this.m_equipement = new EquipementPersonnage();
                this.m_inventaire.addItem(new ArmureLourde("Cotte de mailles", 11));
                this.m_inventaire.addItem(new ArmeGuerre("Épée longue", 1, 8, 1));
                this.m_inventaire.addItem(new ArmeADistance("Arbalète légère", 1, 8, 16));
                break;
            case MAGICIEN:
                this.m_classe = new Magicien();
                this.m_caracteristique = new Caracteristique(lancerDe(4, 4) + this.m_classe.bonusCreation() + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3);
                this.m_equipement = new EquipementPersonnage();
                this.m_inventaire.addItem(new ArmeCourante("Baton", 1, 6, 1));
                this.m_inventaire.addItem(new ArmeADistance("Fronde", 1, 4, 6));
                break;
            case ROUBLARD:
                this.m_classe = new Roublard();
                this.m_caracteristique = new Caracteristique(lancerDe(4, 4) + this.m_classe.bonusCreation() + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3, lancerDe(4, 4) + 3);
                this.m_equipement = new EquipementPersonnage();
                this.m_inventaire.addItem(new ArmeGuerre("Rapière", 1, 8, 1));
                this.m_inventaire.addItem(new ArmeADistance("Arc court", 1, 6, 16));
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

        //Init position
        this.m_position = new int[2];

    }


    public void equiper(int n_equipement)
    {
        Item item = this.m_inventaire.removeItem(n_equipement);

        switch (item.getType()) {
            case ARME:
                this.m_equipement.equiperArme((Arme)item);
                break;
            case ARMURE:
                this.m_equipement.equiperArmure((Armure)item);
                break;
            default:
                throw new IllegalArgumentException("Erreur : cet item n'est ni une arme ni une armure !");
        }
    }



    public Item desequiper(int n_equipement)
    {
        if (n_equipement < 0 || n_equipement > 2)
            throw new IllegalArgumentException("Erreur : numéro d'équipement invalide");
        return this.m_equipement.desequiperArme();
    }

    @Override
    public void setPosition(int x , int y)
    {
        this.m_position[0] = x;
        this.m_position[1] = y;
    }

    @Override
    public int[] getPosition()
    {
        return this.m_position;
    }

    public String getEtiquette()
    {
        return this.m_nom.substring(0, 3);
    }

    @Override
    public Type getType() {
        return m_type;
    }

    public int getPV()
    {
        return this.m_caracteristique.getPV();
    }

    public int getVitesse()
    {
        return this.m_caracteristique.getVitesse();
    }

    public Inventaire getInventaire()
    {
        return this.m_inventaire;
    }

    public void getString ()
    {
        System.out.println("Nom : " + this.m_nom);
        System.out.println(" Classe : " + this.m_classe.toString());
        System.out.println("Race : " + this.m_race.toString());
        System.out.println("Vivant : " + this.m_etat);
        System.out.println("Pv : " + this.m_caracteristique.getPV());
        System.out.println("Force : " + this.m_caracteristique.getForce());
        System.out.println("Dexterité : " + this.m_caracteristique.getDexterite());
        System.out.println("Vitesse : " + this.m_caracteristique.getVitesse());
        System.out.println("Inventaire : " + this.m_inventaire.toString());
        System.out.println("Equipement : " + this.m_equipement.toString());
    }
}
