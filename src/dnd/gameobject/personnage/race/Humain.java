package dnd.gameobject.personnage.race;

public class Humain extends Race
{
    @Override
    int bonusVie()
    {
        return 2;
    }

    @Override
    public int bonusForce()
    {
        return 2;
    }

    @Override
    public int bonusDexterite()
    {
        return 2;
    }

    @Override
    public int bonusVitesse()
    {
        return 2;
    }

    @Override
    public int bonusInitiative()
    {
        return 2;
    }

    @Override
    public String toString() {
        return "Humain";
    }
}

