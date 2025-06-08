package dnd.gameobject.personnage;

import dnd.objet.Arme;
import dnd.objet.Armure;
import dnd.objet.Item;
import dnd.objet.arme.ArmeCourante;
import dnd.objet.armure.ArmureLegere;

import java.util.NoSuchElementException;
import java.util.Optional;

public class EquipementPersonnage
{
    // class members
    private static final Arme m_arme_defaut = new ArmeCourante("Poings", 1, 1, 1);
    private static final Armure m_armure_defaut = new ArmureLegere("Aucune armure", 1);
    private Optional<Arme> m_arme; // TODO : à changer : fait
    private Optional<Armure> m_armure; // TODO : à changer : fait

    public EquipementPersonnage()
    {
        this.m_arme = Optional.empty();
        this.m_armure = Optional.empty();
    }

    public void equiperArme(Arme arme)
    {
        if (arme == null)
            throw new IllegalArgumentException("Erreur : l'arme ne peut pas être null");
        if (this.m_arme.isPresent())
            throw new NoSuchElementException("Erreur : il y a une arme déjà équipée");
        this.m_arme = Optional.of(arme);
    }

    public void equiperArmure(Armure armure)
    {
        if (armure == null)
            throw new NoSuchElementException("Erreur : l'armure ne peut pas être null");
        if (this.m_armure.isPresent())
            throw new NoSuchElementException("Erreur : il y a une armure déjà équipée");
        this.m_armure = Optional.of(armure);
    }

    public Arme getArme()
    {
        return this.m_arme.orElse(m_arme_defaut);
    }

    public Armure getArmure()
    {
        return this.m_armure.orElse(m_armure_defaut);
    }

    public void retirerArme()
    {
        this.m_arme = Optional.empty();
    }

    public void retirerArmure()
    {
        this.m_armure = Optional.empty();
    }
}
