package draw;

public class Canvas {
    private final int width;
    private final int height;
    private Character[][] pixels;

    public static Canvas create(int width, int height) {
        if (width <= 0 || height <= 0) {
            return null;
        }
        return new Canvas(width, height);
    }

    private Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = this.initPixels(width, height);
    }

    public void set(Point point, Character color) {
        if (this.isInside(point)) {
            this.pixels[point.y][point.x] = color;
        }
    }

    public Character get(Point point) {
        if (this.isInside(point)) {
            return this.pixels[point.y][point.x];
        }
        return null;
    }

    public Character[][] output() {
        return this.pixels;
    }

    private boolean isInside(Point point) {
        return point.x > 0 && point.x < width - 1 && point.y > 0 && point.y < height - 1;
    }

    private Character[][] initPixels(int width, int height) {
        int framedHeight = height + 2;
        int framedWidth = width + 2;
        Character[][] pixels = new Character[framedHeight][framedWidth];
        for (int row = 0; row < framedHeight; row++) {
            for (int col = 0; col < framedWidth; col++) {
                Character c = row == 0 || row == height - 1 ? '-' :
                    col == 0 || col == width - 1 ? '|' : ' ';
                pixels[row][col] = c;
            }
        }
        return pixels;
    }
}
