package FileIndexer.GlyphicalLib;

public class ArrayOperations {

    // ___Add operations___\\

    public static int[] add(int[] a, int[] b) {
        assert a.length == b.length;
        int[] c = new int[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public static long[] add(long[] a, long[] b) {
        assert a.length == b.length;
        long[] c = new long[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public static float[] add(float[] a, float[] b) {
        assert a.length == b.length;
        float[] c = new float[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public static double[] add(double[] a, double[] b) {
        assert a.length == b.length;
        double[] c = new double[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    // ___Subtract Operations___\\

    public static int[] subtract(int[] a, int[] b) {
        assert a.length == b.length;
        int[] c = new int[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    public static long[] subtract(long[] a, long[] b) {
        assert a.length == b.length;
        long[] c = new long[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    public static float[] subtract(float[] a, float[] b) {
        assert a.length == b.length;
        float[] c = new float[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    public static double[] subtract(double[] a, double[] b) {
        assert a.length == b.length;
        double[] c = new double[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    // ___Multiply Operations___\\

    public static int[] multiply(int[] a, int[] b) {
        assert a.length == b.length;
        int[] c = new int[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] * b[i];
        }
        return c;
    }

    public static long[] multiply(long[] a, long[] b) {
        assert a.length == b.length;
        long[] c = new long[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] * b[i];
        }
        return c;
    }

    public static float[] multiply(float[] a, float[] b) {
        assert a.length == b.length;
        float[] c = new float[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] * b[i];
        }
        return c;
    }

    public static double[] multiply(double[] a, double[] b) {
        assert a.length == b.length;
        double[] c = new double[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] * b[i];
        }
        return c;
    }

    // ___Concatenate Operations___\\

    public static Object[] concatenate(Object[] a, Object[] b) {
        Object[] c = new Object[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static int[] concatenate(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static long[] concatenate(long[] a, long[] b) {
        long[] c = new long[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static float[] concatenate(float[] a, float[] b) {
        float[] c = new float[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static double[] concatenate(double[] a, double[] b) {
        double[] c = new double[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static byte[] concatenate(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static boolean[] concatenate(boolean[] a, boolean[] b) {
        boolean[] c = new boolean[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
}