package dev.kaique.luan.chess.Check;

import dev.kaique.luan.chess.Enums.Color;

public class Check {

    private Boolean check;
    private Color player;
    private boolean checkMate;

    public Check(Boolean check, Color player) {
        this.check = check;
        this.player = player;
        this.checkMate = false;
    }

    public void updateCheck(Boolean check, Color player) {
        this.check = check;
        this.player = player;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

    public Boolean getCheck() {
        return check;
    }

    public Color getPlayer() {
        return player;
    }
}
