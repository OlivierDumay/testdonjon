package dnd.objet;

import dnd.objet.arme.Arme;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
