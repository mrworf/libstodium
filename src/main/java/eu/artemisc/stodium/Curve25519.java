package eu.artemisc.stodium;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

/**
 * Curve25519 wraps calls to crypto_scalarmult*.
 *
 * @author Jan van de Molengraft [jan@artemisc.eu]
 */
public final class Curve25519 {
    static {
        // Require sodium_init();
        Stodium.StodiumInit();
    }

    // block the constructor
    private Curve25519() {}

    // constants
    public static final int BYTES        = StodiumJNI.crypto_scalarmult_curve25519_bytes();
    public static final int SCALAR_BYTES = StodiumJNI.crypto_scalarmult_curve25519_scalarbytes();

    public static final @NotNull String PRIMITIVE = StodiumJNI.crypto_scalarmult_primitive();

    // wrappers

    //
    // scalar_mult*
    //

    /**
     *
     * @param dst
     * @param src
     * @param groupElement
     * @throws ConstraintViolationException
     * @throws StodiumException
     */
    public static void scalarMult(final @NotNull ByteBuffer dst,
                                  final @NotNull ByteBuffer src,
                                  final @NotNull ByteBuffer groupElement)
            throws StodiumException {
        Stodium.checkSize(dst.remaining(), SCALAR_BYTES, "Curve25519.SCALAR_BYTES");
        Stodium.checkSize(src.remaining(), SCALAR_BYTES, "Curve25519.SCALAR_BYTES");
        Stodium.checkSize(groupElement.remaining(), SCALAR_BYTES, "Curve25519.SCALAR_BYTES");
        Stodium.checkStatus(StodiumJNI.crypto_scalarmult_curve25519(
                Stodium.ensureUsableByteBuffer(dst.slice()),
                Stodium.ensureUsableByteBuffer(src.slice()),
                Stodium.ensureUsableByteBuffer(groupElement.slice())));
    }

    /**
     *
     * @param dst
     * @param src
     * @throws ConstraintViolationException
     * @throws StodiumException
     */
    public static void scalarMultBase(final @NotNull ByteBuffer dst,
                                      final @NotNull ByteBuffer src)
            throws StodiumException {
        Stodium.checkSize(dst.remaining(), SCALAR_BYTES, "Curve25519.SCALAR_BYTES");
        Stodium.checkSize(src.remaining(), SCALAR_BYTES, "Curve25519.SCALAR_BYTES");
        Stodium.checkStatus(StodiumJNI.crypto_scalarmult_curve25519_base(
                Stodium.ensureUsableByteBuffer(dst.slice()),
                Stodium.ensureUsableByteBuffer(src.slice())));
    }

    //
    // convert curve
    //

    /**
     *
     * @param dstPublic
     * @param srcPrivate
     * @throws ConstraintViolationException
     * @throws StodiumException
     */
    public static void x25519PrivateToPublic(final @NotNull ByteBuffer dstPublic,
                                             final @NotNull ByteBuffer srcPrivate)
            throws StodiumException {
        scalarMultBase(dstPublic, srcPrivate);
    }
}
