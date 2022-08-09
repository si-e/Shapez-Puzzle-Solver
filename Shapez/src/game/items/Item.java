package game.items;

public abstract class Item {

    private final String shortcode;

    public Item(String shortcode) {
        this.shortcode = shortcode;
    }

    public static Item fromShortCode(String shortcode) {
        if (shortcode.startsWith("C") ||
                shortcode.startsWith("R") ||
                shortcode.startsWith("S") ||
                shortcode.startsWith("W") ||
                shortcode.startsWith("--")
        ) {
            return new Shape(shortcode);
        } else {
            return new Color(shortcode);
        }
    }

    @Override
    public String toString() {
        return this.shortcode;
    }

}
