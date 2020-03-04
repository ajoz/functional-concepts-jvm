package io.github.ajoz.illegal.css;

import io.github.ajoz.util.Try;

/*
  Instead of a single CSS type we could group it logically
  into separate distinct types. This can be achieved through
  a sealed type hierarchy, this way we will have a complete
  control over what types extend our CSSColor type.
 */
public abstract class CSSColor3 {
    public static final class Named extends CSSColor3 {
        private final ColorName value;

        private Named(final ColorName value) {
            this.value = value;
        }
    }

    public static final class RGB extends CSSColor3 {
        private final RGBComponent red;
        private final RGBComponent green;
        private final RGBComponent blue;

        private RGB(final RGBComponent red,
                    final RGBComponent green,
                    final RGBComponent blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    }

    public static final class RGBA extends CSSColor3 {
        private final RGBComponent red;
        private final RGBComponent green;
        private final RGBComponent blue;
        private final Alpha alpha;

        private RGBA(final RGBComponent red,
                     final RGBComponent green,
                     final RGBComponent blue,
                     final Alpha alpha) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }
    }

    /*
      It is still possible to pass null arguments, but this can
      be fixed by controlling the arguments when CSSColor type:
     */
    public static Try<CSSColor3> tryRGB(final RGBComponent red,
                                        final RGBComponent green,
                                        final RGBComponent blue) {
        if (null == red)
            return Try.failure(new IllegalArgumentException("Red component cannot be null!"));

        if (null == green)
            return Try.failure(new IllegalArgumentException("Green component cannot be null!"));

        if (null == blue)
            return Try.failure(new IllegalArgumentException("Blue component cannot be null!"));

        return Try.success(new RGB(red, green, blue));
    }
    /*
      Methods like the one above can be created for other possible cases of CSSColor.

      It seems like a large amount of code for a thing that is fairly simple, this is
      due to the baroque's nature of Java as a language. In a language like Kotlin
      the code could be much much shorter and simpler.
     */
}
