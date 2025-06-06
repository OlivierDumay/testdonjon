package dnd.gameobject.personnage.race;

public class Halflin extends Race
{
    @Override
    int bonusVie()
    {
        return 0;
    }

    @Override
    public int bonusForce()
    {
        return 0;
    }

    @Override
    public int bonusDexterite()
    {
        return 4;
    }

    @Override
    public int bonusVitesse()
    {
        return 2;
    }

    @Override
    public int bonusInitiative()
    {
        return 0;
    }

    @Override
    public String toString()
    {
        return "Halflin";
    }
}
