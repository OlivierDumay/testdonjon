package dnd;

import static dnd.Type.OBSTACLE;

public class Obstacle implements Asset
{
    private static final Type m_type = OBSTACLE;

    public Obstacle()
    {
    }

    @Override
    public String getEtiquette()
    {
        return "[ ]";
    }

    @Override
    public Type getType() {
        return m_type;
    }
}
