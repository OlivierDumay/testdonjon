package dnd.objet;

import dnd.objet.arme.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest
{
    @Test
    void ItemVide()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            new ArmeCourante("", 10, 10, 1);
        });
    }

    @Test
    void ArmeValide()
    {
        assertDoesNotThrow(()->{
            Item eppe = new ArmeGuerre("Éppé longue", 1, 6, 1);
        });
    }

    @Test
    void ArmeSansDe()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            new ArmeCourante("Éppé longue", 0, 6, 1);
        });
    }

    @Test
    void ArmeDegatsNegatifs()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            new ArmeGuerre("Éppé longue", -1, 4, 10);
        });
    }

    @Test
    void ArmeGuerreValide()
    {
        Arme massue = new ArmeGuerre("Massue", 1, 4, 1);
        assertEquals(true, massue instanceof ArmeGuerre);
    }

    @Test
    void ArmePortee0()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            new ArmeCourante("Épée longue", 1, 6, 0);
        });
    }
}
