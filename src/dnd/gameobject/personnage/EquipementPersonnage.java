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
        this.m_arme = Optional.of(arme);
    }

    public void equiperArmure(Armure armure)
    {
        if (armure == null)
            throw new NoSuchElementException("Erreur : l'armure ne peut pas être null");
        this.m_armure = Optional.of(armure);
    }

    public Arme desequiperArme()
    {
        if (this.m_arme.isEmpty())
            throw new NoSuchElementException("Erreur : pas d'arme équipée");
        Arme tmp = this.m_arme.get();
        this.m_arme = Optional.empty(); // TODO : à changer : perfecto
        return tmp;
    }

    public Armure desequiperArmure()
    {
        if (this.m_armure.isEmpty())
            throw new RuntimeException("Erreur : pas d'armure équipée");
        Armure tmp = this.m_armure.get();
        this.m_armure = Optional.empty(); // TODO : à changer : perfecto
        return tmp;
    }
}
