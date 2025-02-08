package dev.kaique.luan.chess.Check;

import dev.kaique.luan.chess.Enums.Color;

public class Check {

    private Boolean check;
    private Color player;

    public Check(Boolean check, Color player) {
        this.check = check;
        this.player = player;
    }

    public void updateCheck(Boolean check, Color player) {
        this.check = check;
        this.player = player;
    }

    public Boolean getCheck() {
        return check;
    }

    public Color getPlayer() {
        return player;
    }
}
