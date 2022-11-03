package dungeonmania.goals;

import java.io.Serializable;

import dungeonmania.Game;

public abstract class Goal implements Serializable {
    public abstract boolean achieved(Game game);

    public abstract String toString(Game game);

}
