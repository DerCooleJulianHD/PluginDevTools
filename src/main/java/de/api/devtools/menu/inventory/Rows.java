package de.api.devtools.menu.inventory;

public enum Rows {

    ONE(9),
    TWO(18),
    THREE(27),
    FOUR(36),
    FIVE(45);

    private final int size;

    Rows(int size) {
        this.size = size;
    }

    public final int getSize() {
        return size;
    }
}
