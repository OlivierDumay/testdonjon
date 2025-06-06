package dnd.gameobject.personnage.race;

public class Nain extends Race
{
    @Override
    int bonusVie()
    {
        return 0;
    }

    @Override
    public int bonusForce()
    {
        return 6;
    }

    @Override
    public int bonusDexterite()
    {
        return 0;
    }

    @Override
    public int bonusVitesse()
    {
        return 0;
    }

    @Override
    public int bonusInitiative()
    {
        return 0;
    }

    @Override
    public String toString() {
        return "Nain";
    }
}