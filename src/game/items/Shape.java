package game.items;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Shape extends Item {

    static class Layer {
        final Corner[] corners;
        final String shortcode;

        Layer(String shortcode) {
            this.shortcode = shortcode;
            this.corners = new Corner[4];
            for (int i = 0; i < 4; i++) {
                String cornerCode = shortcode.substring(2 * i, 2 * i + 2);
                if (!cornerCode.equals("--")) {
                    this.corners[i] = new Corner(cornerCode);
                }
            }
        }

        Layer(Corner[] corners) {
            this.corners = corners;
            StringBuilder s = new StringBuilder();
            for (Corner corner : corners) {
                s.append((corner == null) ? "--" : corner);
            }
            this.shortcode = s.toString();
        }

        boolean isEmpty() {
            for (Corner corner : this.corners) {
                if (corner != null) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return this.shortcode;
        }
    }

    static class Corner {
        enum CornerShape {
            C, R, S, W;
        }

        enum CornerColor {
            u, r, g, b, y, p, c, w
        }

        final String shortcode;
        final CornerShape shape;
        final CornerColor color;

        Corner(String shortcode) {
            this.shortcode = shortcode;
            this.shape = CornerShape.valueOf(shortcode.substring(0, 1));
            this.color = CornerColor.valueOf(shortcode.substring(1, 2));
        }

        @Override
        public String toString() {
            return this.shortcode;
        }
    }

    Layer[] layers;

    public Shape(String shortCode) {
        super(shortCode);
        this.layers = new Layer[4];
        int i = 0;
        for (String layerCode : shortCode.split(":")) {
            Layer layer = new Layer(layerCode);
            if (!layer.isEmpty()) {
                this.layers[i] = layer;
                i++;
            }
        }
    }

    Shape(Layer[] layers) {
        super(Arrays.stream(layers).filter(Objects::nonNull).map(Layer::toString)
                .collect(Collectors.joining(":")));
        this.layers = new Layer[4];
        int i = 0;
        for (Layer layer : layers) {
            if (layer != null) {
                this.layers[i] = layer;
                i++;
                if (i == 4) {
                    break;
                }
            }
        }
    }

    boolean isEmpty() {
        for (Layer layer : this.layers) {
            if (layer != null) {
                return false;
            }
        }
        return true;
    }

    public Shape rotate(int clockwiseDegree) {
        int t = clockwiseDegree / 90;
        Layer[] newLayers = new Layer[4];
        int i = 0;
        for (Layer layer : this.layers) {
            if (layer != null) {
                Corner[] newCorners = new Corner[4];
                for (int j = 0; j < 4; j++) {
                    int j2 = (j + t) % 4;
                    newCorners[j2] = this.layers[i].corners[j];
                }
                Layer newLayer = new Layer(newCorners);
                if (!newLayer.isEmpty()) {
                    newLayers[i] = newLayer;
                    i++;
                }
            }
        }
        return new Shape(newLayers);
    }

    public Shape stackWith(Shape shape) {
        int depth = 0;
        TryDepth:
        while (depth < 4) {
            depth++;
            for (int i = 0; i < depth; i++) {
                Layer layer1 = this.layers[i + (4 - depth)];
                Layer layer2 = shape.layers[i];
                if (layer1 != null && layer2 != null) {
                    for (int j = 0; j < 4; j++) {
                        if (layer1.corners[j] != null && layer2.corners[j] != null) {
                            depth--;
                            break TryDepth;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        Layer[] newLayers = new Layer[4];
        StackWithDepth:
        for (int i = 0; i < 4; i++) {
            Corner[] newCorners = new Corner[4];
            for (int j = 0; j < 4; j++) {
                if (i < 4 - depth) {
                    newCorners[j] = this.layers[i].corners[j];
                } else {
                    Layer layer1 = this.layers[i];
                    Layer layer2 = shape.layers[i - (4 - depth)];
                    if (layer1 == null && layer2 == null) {
                        break StackWithDepth;
                    }
                    newCorners[j] = (layer2 == null) ? layer1.corners[j] :
                            (layer1 == null) ? layer2.corners[j] :
                            (layer2.corners[j] == null) ? layer1.corners[j] :
                                    layer2.corners[j];
                }
            }
            newLayers[i] = new Layer(newCorners);
        }
        return new Shape(newLayers);
    }

    public Shape getQuadrants(int... quadrants) {
        Layer[] newLayers = new Layer[4];
        int i = 0;
        for (Layer layer : this.layers) {
            if (layer != null) {
                Corner[] newCorners = new Corner[4];
                for (int q : quadrants) {
                    int j = q - 1;
                    newCorners[j] = layer.corners[j];
                }
                Layer newLayer = new Layer(newCorners);
                if (!newLayer.isEmpty()) {
                    newLayers[i] = newLayer;
                    i++;
                }
            }
        }
        return new Shape(newLayers);
    }
}
