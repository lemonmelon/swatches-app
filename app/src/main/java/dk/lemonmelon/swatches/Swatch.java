package dk.lemonmelon.swatches;

import android.graphics.Color;

public class Swatch {
    private int r, b, g;

    public Swatch(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String getName() {
        return "(" + r + "," + g + "," + b + ")";
    }

    public int getColor() {
        return Color.rgb(r, g, b);
    }
}
