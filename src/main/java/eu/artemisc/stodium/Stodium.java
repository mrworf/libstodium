package eu.artemisc.stodium;

import org.abstractj.kalium.Sodium;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;

import javax.crypto.AEADBadTagException;

/**
 * Stodium is an abstract class with static methods. It is an attempt to
 * simplify the API generated by SWIG to a more Java-ish version, as well as add
 * some proper JavaDocs to the methods.
 *
 * All method calls are wrappers around calls to JNI implemented methods. The
 * library is aimed specifically at the android platform.
 *
 * @author Jan van de Molengraft [jan@artemisc.eu]
 */
public final class Stodium {
    // Block constructor
    private Stodium() {}

    /**
     *
     * @param status
     * @throws StodiumException
     */
    public static void checkStatus(final int status)
            throws StodiumException {
        if (status == 0) {
            return;
        }
        throw new StodiumException(
                String.format(Locale.ENGLISH, "Stodium: operation returned non-zero status %d", status));
    }

    /**
     *
     * @param status
     * @param methodDescription
     * @throws AEADBadTagException If the status value does not equal 0,
     *         indicating an invalid authentication tag was encountered.
     * @throws StodiumException If the API level does not support
     *         AEADBadTagException, the method will call
     *         {@link #checkStatus(int)} instead.
     */
    public static void checkStatusSealOpen(         final int    status,
                                           final @NotNull String methodDescription)
            throws AEADBadTagException, StodiumException {
        if (status == 0) {
            return;
        }

        // FIXME: 9-7-16 On android, this API is too new. In JAR, this can be used. For now just defer to checkStatus
        //throw new AEADBadTagException(
        //        methodDescription + ": cannot open sealed box (invalid tag?)");
        checkStatus(status);
    }

    /**
     *
     * @param src
     * @param expected
     * @param constant
     * @throws ConstraintViolationException
     */
    public static void checkSize(         final int    src,
                                          final int    expected,
                                 final @NotNull String constant)
            throws ConstraintViolationException {
        if (src == expected) {
            return;
        }
        throw new ConstraintViolationException(
                String.format(Locale.ENGLISH, "Check size failed on [%s] [expected: %d, real: %d]",
                        constant, expected, src));
    }

    /**
     *
     * @param src
     * @param lower
     * @param upper
     * @param lowerC
     * @param upperC
     * @throws ConstraintViolationException
     */
    public static void checkSize(final int src,
                                 final int lower,
                                 final int upper,
                                 final @NotNull String lowerC,
                                 final @NotNull String upperC)
            throws ConstraintViolationException {
        if (src <= upper && src >= lower) {
            return;
        }
        throw new ConstraintViolationException(
                String.format(Locale.ENGLISH, "CheckSize failed on bounds [%s, %s] [lower: %d, upper: %d, real: %d]",
                        lowerC, upperC, lower, upper, src));
    }

    /**
     *
     * @param src
     * @throws ConstraintViolationException
     */
    public static void checkPositive(final int src)
            throws ConstraintViolationException {
        if (src >= 0) {
            return;
        }
        throw new ConstraintViolationException(
                String.format(Locale.ENGLISH, "checkPositive failed [real: %d]", src));
    }

    /**
     * checkOffsetParams is a shorthand for the combined verification calls
     * required when using an API based on the (in, offset, len) format.
     *
     * @param dataLen
     * @param offset
     * @param len
     */
    public static void checkOffsetParams(final int dataLen,
                                         final int offset,
                                         final int len)
            throws ConstraintViolationException {
        Stodium.checkSize(offset, 0, dataLen, "0", "dataLen");
        Stodium.checkSize(offset + len, 0, dataLen, "0", "dataLen");
        Stodium.checkPositive(len);
    }

    /**
     * checkPow2 checks whether the given integer src is a power of 2, and
     * throws an exception otherwise.
     * @param src
     * @param descr
     * @throws ConstraintViolationException
     */
    public static void checkPow2(final int src,
                                 final @NotNull String descr)
            throws ConstraintViolationException {
        if ((src > 0) && ((src & (~src + 1)) == src)) {
            return;
        }
        throw new ConstraintViolationException(
                String.format(Locale.ENGLISH, "checkPow2 failed on [%s: %d]", descr, src));
    }

    /**
     *
     * @param src
     * @param descr
     * @throws ConstraintViolationException
     */
    public static void checkPow2(final long src,
                                 final @NotNull String descr)
            throws ConstraintViolationException {
        if ((src > 0) && ((src & (~src + 1)) == src)) {
            return;
        }
        throw new ConstraintViolationException(
                String.format(Locale.ENGLISH, "checkPow2 failed on [%s: %d]", descr, src));
    }

    /**
     * isEqual implements a Java-implementation of constant-time,
     * length-independent equality checking for sensitive values.
     *
     * @return true iff a == b
     */
    public static boolean isEqual(final @NotNull byte[] a,
                                  final @NotNull byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }
        return result == 0;
    }

    /**
     * TODO let this call a native comparator for direct buffers?
     * @param a
     * @param b
     * @return
     */
    public static boolean isEqual(final @NotNull ByteBuffer a,
                                  final @NotNull ByteBuffer b) {
        if (a.remaining() != b.remaining()) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.remaining(); i++) {
            result |= a.get(i) ^ b.get(i);
        }
        return result == 0;
    }

    /**
     *
     * @param a
     */
    public static void wipeBytes(final @NotNull byte[] a) {
        Arrays.fill(a, (byte) 0x00);
    }

    private static byte[] emptyBuffer = new byte[8];
    public static void wipeBytes(final @NotNull ByteBuffer a) {
        if (a.hasArray()) {
            wipeBytes(a.array());
            return;
        }

        if (a.isReadOnly()) {
            return; // ignore
        }

        while (a.hasRemaining()) {
            a.put(emptyBuffer, 0, a.remaining() < 8 ? a.remaining() : 8);
        }
    }

    /**
     * ensureUsableByteBuffer returns a ByteBuffer instance that is guaranteed
     * to work correctly with the implementation of stodium_buffers in the
     * native code.
     * <p>
     * If the passed buff argument represents a JNI usable ByteBuffer, it is
     * directly returned. Otherwise, the method allocates a direct buffer with
     * the size of {@code buff.remaining()}, and copies the contents of buff.
     * This copy is guaranteed to work with the native code (as it is a direct
     * buffer) and therefore is returned.
     *
     * @param buff the original buffer
     * @return a ByteBuffer that is guaranteed to function correctly in the
     *         native code.
     */
    @NotNull
    static ByteBuffer ensureUsableByteBuffer(final @NotNull ByteBuffer buff) {
        if (buff.isDirect() || (buff.hasArray() && !buff.isReadOnly())) {
            return buff;
        }

        final ByteBuffer direct = ByteBuffer.allocateDirect(buff.remaining());
        direct.mark();
        direct.put(buff);
        direct.reset();
        return direct;
    }

    /**
     * checkDestinationWritable throws an exception if the ByteBuffer passed to
     * it is backed by an array and is read-only. If this is the case, the
     * native code would not have a way to operate on the buffer's contents, and
     * copying to it from Java's side would also be impossible.
     *
     * @param buff The ByteBuffer that needs to be verified
     * @throws ReadOnlyBufferException if the buffer is incorrectly passed as a
     *         read-only buffer, even while being the output for an operation.
     */
    static void checkDestinationWritable(final @NotNull ByteBuffer buff,
                                         final @NotNull String     description) {
        if (buff.isDirect() || !buff.isReadOnly()) {
            return;
        }
        throw new ReadOnlyBufferException(
                String.format("Stodion: output for [%s] is readonly", description));
    }

    /**
     *
     */
    private static boolean initialized = false;

    /**
     * runInit wraps a call to sodium_init().
     */
    private synchronized static void runInit()
            throws RuntimeException {
        if (initialized) {
            return;
        }

        if (StodiumJNI.stodium_init() != 0) {
            throw new RuntimeException("StodiumInit: could not initialize with stodium_init()");
        }

        initialized = true;
    }

    /**
     * Load the native library
     */
    static {
        try {
            Class.forName("android.Manifest");

            // Load the android JNI libs, as this is libstodium-android
            System.loadLibrary("kaliumjni");

        } catch (final ClassNotFoundException e1) {
            /*// This is not android, extract pre-build library from jar
            File file;
            InputStream in = null;
            OutputStream out = null;

            String name = System.mapLibraryName("kaliumjni");

            try {
                in   = Stodium.class.getResourceAsStream("/eu/artemisc/stodium/libs/" + name);
                file = File.createTempFile("stodium", name);
                out  = new FileOutputStream(file);
                System.load(file.getAbsolutePath());

                System.loadLibrary("kaliumjni");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try { if (in  != null) { in.close();  } } catch (IOException e) { e.printStackTrace(); }
                try { if (out != null) { out.close(); } } catch (IOException e) { e.printStackTrace(); }
            }*/
            throw new RuntimeException("Cannot load libstorium native library");
        }
    }

    /**
     * Stodium constructor should be called once per application, or at least
     * before any class is used that requires the native methods to be
     * available. This ensures the library is loaded and initialized.
     */
    public static void StodiumInit() {
        runInit();
    }

    /**
     * SodiumVersionString returns the value of sodium_version_string().
     *
     * @return libsodium's version string
     */
    @NotNull
    public static String SodiumVersionString() {
        return Sodium.sodium_version_string();
    }
}

