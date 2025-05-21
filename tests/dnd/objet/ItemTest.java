package dnd.objet;

import dnd.objet.arme.Arme;
import dnd.objet.arme.ArmeGuerre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest
{
    @Test
    void ItemVide()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            new Arme("", 10);
        });
    }

    @Test
    void ArmeValide()
    {
        assertDoesNotThrow(()->{
            Item eppe = new Arme("Éppé longue", 15);
        });
    }

    @Test
    void ArmeSansDegats()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            new Arme("Éppé longue", 0);
        });
    }

    @Test
    void ArmeDegatsNegatifs()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            new Arme("Éppé longue", -10);
        });
    }

    @Test
    void ArmeGuerreValide()
    {
        Arme massue = new ArmeGuerre("Massue", 50);
        assertEquals(massue instanceof ArmeGuerre, true);
    }
}
