package dnd.gameobject.personnage.race;

public abstract class Race
{
    abstract int bonusVie();
    public abstract int bonusForce();
    public abstract int bonusDexterite();
    public abstract int bonusVitesse();
    public abstract int bonusInitiative();
    @Override
    public abstract String toString();
}