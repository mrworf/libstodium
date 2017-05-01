/*
 * Copyright (c) 2016 Project ArteMisc
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package eu.artemisc.stodium;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

/**
 * StodiumJNI implements the java definitions of native methods for wrappers
 *
 * around Libsodium functions.
 * @author Jan van de Molengraft [jan@artemisc.eu]
 */
public class StodiumJNI {
    //
    // Library methods
    //
    public static native int stodium_init();
    public static native int sodium_init();

    //
    // Utility methods
    //
    public static native int randombytes_random();
    public static native int randombytes_uniform(int upper_bound);
    public static native void randombytes_buf(ByteBuffer dst);

    //
    // Core
    //
    public static native int crypto_core_hsalsa20_outputbytes();
    public static native int crypto_core_hsalsa20_inputbytes();
    public static native int crypto_core_hsalsa20_keybytes();
    public static native int crypto_core_hsalsa20_constbytes();
    public static native int crypto_core_hsalsa20(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer key,
            @NotNull ByteBuffer constant);

    //
    // AEAD
    //
    public static native int crypto_aead_chacha20poly1305_keybytes();
    public static native int crypto_aead_chacha20poly1305_nsecbytes();
    public static native int crypto_aead_chacha20poly1305_npubbytes();
    public static native int crypto_aead_chacha20poly1305_abytes();

    public static native int crypto_aead_chacha20poly1305_encrypt_detached(
            ByteBuffer dstCipher, ByteBuffer srcPlain, ByteBuffer ad, ByteBuffer nonce, ByteBuffer key);
    public static native int crypto_aead_chacha20poly1305_decrypt_detached(
            ByteBuffer dstPlain, ByteBuffer srcCipher, ByteBuffer ad, ByteBuffer nonce, ByteBuffer key);

    public static native int crypto_aead_xchacha20poly1305_encrypt_detached(
            ByteBuffer dstCipher, ByteBuffer srcPlain, ByteBuffer ad, ByteBuffer nonce, ByteBuffer key);
    public static native int crypto_aead_xchacha20poly1305_decrypt_detached(
            ByteBuffer dstPlain, ByteBuffer srcCipher, ByteBuffer ad, ByteBuffer nonce, ByteBuffer key);

    public static native int crypto_aead_xsalsa20poly1305_encrypt_detached(
            ByteBuffer dstCipher, ByteBuffer srcPlain, ByteBuffer ad, ByteBuffer nonce, ByteBuffer key);
    public static native int crypto_aead_xsalsa20poly1305_decrypt_detached(
            ByteBuffer dstPlain, ByteBuffer srcCipher, ByteBuffer ad, ByteBuffer nonce, ByteBuffer key);

    //
    // Box
    //
    public static native String crypto_box_primitive();

    public static native int crypto_box_seedbytes();
    public static native int crypto_box_publickeybytes();
    public static native int crypto_box_secretkeybytes();
    public static native int crypto_box_noncebytes();
    public static native int crypto_box_macbytes();
    public static native int crypto_box_beforenmbytes();
    public static native int crypto_box_sealbytes();

//    public static native int crypto_box_keypair(
//            ByteBuffer publicKey, ByteBuffer privateKey);
//    public static native int crypto_box_seed_keypair(
//            ByteBuffer publicKey, ByteBuffer privateKey, ByteBuffer seed);

    public static native int crypto_box_seal(
            ByteBuffer dstCipher, ByteBuffer srcPlain, ByteBuffer publicKey);
    public static native int crypto_box_seal_open(
            ByteBuffer dstPlain, ByteBuffer srcCipher, ByteBuffer publicKey, ByteBuffer privateKey);

    //
    // GenericHash Blake2b
    //
    public static native int crypto_generichash_blake2b_bytes();
    public static native int crypto_generichash_blake2b_bytes_min();
    public static native int crypto_generichash_blake2b_bytes_max();
    public static native int crypto_generichash_blake2b_keybytes();
    public static native int crypto_generichash_blake2b_keybytes_min();
    public static native int crypto_generichash_blake2b_keybytes_max();
    public static native int crypto_generichash_blake2b_personalbytes();
    public static native int crypto_generichash_blake2b_saltbytes();
    public static native int crypto_generichash_blake2b_statebytes();

    public static native int crypto_generichash_blake2b(
            @NotNull  ByteBuffer dst,
            @NotNull  ByteBuffer src,
            @Nullable ByteBuffer key);

    public static native int crypto_generichash_blake2b_salt_personal(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer key,
            @NotNull ByteBuffer salt,
            @NotNull ByteBuffer personal);

    public static native int crypto_generichash_blake2b_init(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer key,
                     int        outlen);
    public static native int crypto_generichash_blake2b_update(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer in);
    public static native int crypto_generichash_blake2b_final(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer out);

    //
    // PwHash
    //
    public static native String crypto_pwhash_primitive();

    public static native int crypto_pwhash_alg_default();
    public static native int crypto_pwhash_saltbytes();
    public static native int crypto_pwhash_strbytes();
    public static native int crypto_pwhash_opslimit_interactive();
    public static native int crypto_pwhash_memlimit_interactive();
    public static native int crypto_pwhash_opslimit_moderate();
    public static native int crypto_pwhash_memlimit_moderate();
    public static native int crypto_pwhash_opslimit_sensitive();
    public static native int crypto_pwhash_memlimit_sensitive();

    public static native int crypto_pwhash(
            ByteBuffer dst, ByteBuffer password, ByteBuffer salt, int opslimit, int memlimit);

    //
    // PwHash Scrypt
    //
    public static native int crypto_pwhash_scryptsalsa208sha256_saltbytes();
    public static native int crypto_pwhash_scryptsalsa208sha256_strbytes();
    public static native int crypto_pwhash_scryptsalsa208sha256_opslimit_interactive();
    public static native int crypto_pwhash_scryptsalsa208sha256_memlimit_interactive();
    public static native int crypto_pwhash_scryptsalsa208sha256_opslimit_sensitive();
    public static native int crypto_pwhash_scryptsalsa208sha256_memlimit_sensitive();

    public static native int crypto_pwhash_scryptsalsa208sha256(
            ByteBuffer dst, ByteBuffer password, ByteBuffer salt, int opslimit, int memlimit);

    //
    // ScalarMult
    //
    public static native String crypto_scalarmult_primitive();

    public static native int crypto_scalarmult_curve25519_bytes();
    public static native int crypto_scalarmult_curve25519_scalarbytes();
    public static native int crypto_scalarmult_curve25519(
            ByteBuffer dst, ByteBuffer src, ByteBuffer elm);
    public static native int crypto_scalarmult_curve25519_base(
            ByteBuffer dst, ByteBuffer src);

    //
    // SecretBox
    //
    public static native String crypto_secretbox_primitive();

    public static native int crypto_secretbox_keybytes();
    public static native int crypto_secretbox_macbytes();
    public static native int crypto_secretbox_noncebytes();
    public static native int crypto_secretbox_easy(
            ByteBuffer dst, ByteBuffer src, ByteBuffer nonce, ByteBuffer key);
    public static native int crypto_secretbox_open_easy(
            ByteBuffer dst, ByteBuffer src, ByteBuffer nonce, ByteBuffer key);
    public static native int crypto_secretbox_detached(
            ByteBuffer dst, ByteBuffer mac, ByteBuffer src, ByteBuffer nonce, ByteBuffer key);
    public static native int crypto_secretbox_open_detached(
            ByteBuffer dst, ByteBuffer src, ByteBuffer mac, ByteBuffer nonce, ByteBuffer key);
}
