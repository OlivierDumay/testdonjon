package dnd.gameobject.personnage;

import dnd.objet.Arme;
import dnd.objet.Armure;
import dnd.objet.Item;

import java.util.NoSuchElementException;
import java.util.Optional;

public class EquipementPersonnage
{
    // class members
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
        return this.m_arme.orElseThrow(() ->
                new NoSuchElementException("Erreur : pas d'arme équipée"));
    }

    public Armure getArmure()
    {
        return this.m_armure.orElseThrow(() ->
                new NoSuchElementException("Erreur : pas d'armure équipée"));
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
