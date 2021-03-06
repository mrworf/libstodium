/*
 * Copyright (c) 2017 Project ArteMisc
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package eu.artemisc.stodium;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

/**
 * StodiumJNI implements the java definitions of native methods for wrappers
 * around Libsodium functions.
 *
 * @author Jan van de Molengraft [jan@artemisc.eu]
 */
public class StodiumJNI {

    /**
     * NOERR is the constant value returned by native functions indicating that
     * no error has occured.
     */
    public static final int NOERR = 0;

    //
    // Library methods
    //
    public static native int stodium_init();
    public static native @NotNull String sodium_version_string();
    // TODO: 8-6-17 add constant time utility methods? like sodium_increment

    //
    // Utility methods
    //
    public static native int randombytes_random();
    public static native int randombytes_uniform(int upper_bound);
    public static native void randombytes_buf(@NotNull ByteBuffer dst);

    //
    // Core
    //

    //
    // HSalsa20
    //
    public static native int crypto_core_hsalsa20_outputbytes();
    public static native int crypto_core_hsalsa20_inputbytes();
    public static native int crypto_core_hsalsa20_keybytes();
    public static native int crypto_core_hsalsa20_constbytes();
    public static native int crypto_core_hsalsa20(
            @NotNull  ByteBuffer dst,
            @NotNull  ByteBuffer src,
            @NotNull  ByteBuffer key,
            @Nullable ByteBuffer constant);

    //
    // HChacha20
    //
    public static native int crypto_core_hchacha20_outputbytes();
    public static native int crypto_core_hchacha20_inputbytes();
    public static native int crypto_core_hchacha20_keybytes();
    public static native int crypto_core_hchacha20_constbytes();
    public static native int crypto_core_hchacha20(
            @NotNull  ByteBuffer dst,
            @NotNull  ByteBuffer src,
            @NotNull  ByteBuffer key,
            @Nullable ByteBuffer constant);

    //
    // AEAD - Chacha20Poly1305
    //
    public static native int crypto_aead_aes256gcm_is_available();
    public static native int crypto_aead_aes256gcm_keybytes();
    public static native int crypto_aead_aes256gcm_nsecbytes();
    public static native int crypto_aead_aes256gcm_npubbytes();
    public static native int crypto_aead_aes256gcm_abytes();

    public static native int crypto_aead_aes256gcm_encrypt_detached(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer dstMac,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_aes256gcm_encrypt(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_aes256gcm_decrypt_detached(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer srcMac,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_aes256gcm_decrypt(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);

    //
    // AEAD - Chacha20Poly1305
    //
    public static native int crypto_aead_chacha20poly1305_keybytes();
    public static native int crypto_aead_chacha20poly1305_nsecbytes();
    public static native int crypto_aead_chacha20poly1305_npubbytes();
    public static native int crypto_aead_chacha20poly1305_abytes();

    public static native int crypto_aead_chacha20poly1305_encrypt_detached(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer dstMac,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_chacha20poly1305_encrypt(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_chacha20poly1305_decrypt_detached(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer srcMac,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_chacha20poly1305_decrypt(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);

    //
    // AEAD - Chacha20Poly1305 (ietf)
    //
    public static native int crypto_aead_chacha20poly1305_ietf_keybytes();
    public static native int crypto_aead_chacha20poly1305_ietf_nsecbytes();
    public static native int crypto_aead_chacha20poly1305_ietf_npubbytes();
    public static native int crypto_aead_chacha20poly1305_ietf_abytes();

    public static native int crypto_aead_chacha20poly1305_ietf_encrypt_detached(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer dstMac,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_chacha20poly1305_ietf_encrypt(
            @NotNull ByteBuffer dstCipher,
            int dstOffset,
            @NotNull ByteBuffer srcPlain,
            int srcOffset,
            int srcLimit,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_chacha20poly1305_ietf_decrypt_detached(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer srcMac,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_chacha20poly1305_ietf_decrypt(
            @NotNull ByteBuffer dstPlain,
            int dstOffset,
            @NotNull ByteBuffer srcCipher,
            int srcOffset,
            int srcLength,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);

    //
    // AEAD - XChacha20Poly1305 (ietf)
    //
    public static native int crypto_aead_xchacha20poly1305_ietf_keybytes();
    public static native int crypto_aead_xchacha20poly1305_ietf_nsecbytes();
    public static native int crypto_aead_xchacha20poly1305_ietf_npubbytes();
    public static native int crypto_aead_xchacha20poly1305_ietf_abytes();

    public static native int crypto_aead_xchacha20poly1305_ietf_encrypt_detached(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer dstMac,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_xchacha20poly1305_ietf_encrypt(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_xchacha20poly1305_ietf_decrypt_detached(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer srcMac,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_aead_xchacha20poly1305_ietf_decrypt(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer ad,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);

    //
    // Auth
    //
    public static native @NotNull String crypto_auth_primitive();

    //
    // Auth - HMAC-SHA-256
    //
    public static native int crypto_auth_hmacsha256_bytes();
    public static native int crypto_auth_hmacsha256_keybytes();
    public static native int crypto_auth_hmacsha256_statebytes();

    public static native int crypto_auth_hmacsha256(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha256_verify(
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha256_init(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha256_update(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in);
    public static native int crypto_auth_hmacsha256_final(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer dst);

    //
    // Auth - HMAC-SHA-512
    //
    public static native int crypto_auth_hmacsha512_bytes();
    public static native int crypto_auth_hmacsha512_keybytes();
    public static native int crypto_auth_hmacsha512_statebytes();

    public static native int crypto_auth_hmacsha512(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha512_verify(
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha512_init(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha512_update(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in);
    public static native int crypto_auth_hmacsha512_final(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer dst);

    //
    // Auth - HMAC-SHA-512/256
    //
    public static native int crypto_auth_hmacsha512256_bytes();
    public static native int crypto_auth_hmacsha512256_keybytes();
    public static native int crypto_auth_hmacsha512256_statebytes();

    public static native int crypto_auth_hmacsha512256(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha512256_verify(
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha512256_init(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer key);
    public static native int crypto_auth_hmacsha512256_update(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in);
    public static native int crypto_auth_hmacsha512256_final(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer dst);

    //
    // Box
    //
    public static native @NotNull String crypto_box_primitive();
    public static native int crypto_box_sealbytes();

    public static native int crypto_box_seal(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer publicKey);
    public static native int crypto_box_seal_open(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer publicKey,
            @NotNull ByteBuffer privateKey);

    // todo detached

    //
    // Box - Curve25519XSalsa20Poly1305
    //
    public static native int crypto_box_curve25519xsalsa20poly1305_seedbytes();
    public static native int crypto_box_curve25519xsalsa20poly1305_publickeybytes();
    public static native int crypto_box_curve25519xsalsa20poly1305_secretkeybytes();
    public static native int crypto_box_curve25519xsalsa20poly1305_beforenmbytes();
    public static native int crypto_box_curve25519xsalsa20poly1305_noncebytes();
    public static native int crypto_box_curve25519xsalsa20poly1305_zerobytes();
    public static native int crypto_box_curve25519xsalsa20poly1305_boxzerobytes();
    public static native int crypto_box_curve25519xsalsa20poly1305_macbytes();

    public static native int crypto_box_curve25519xsalsa20poly1305_seed_keypair(
            @NotNull ByteBuffer dstPublic,
            @NotNull ByteBuffer dstPrivate,
            @NotNull ByteBuffer seed);
    public static native int crypto_box_curve25519xsalsa20poly1305_keypair(
            @NotNull ByteBuffer dstPublic,
            @NotNull ByteBuffer dstPrivate);
    public static native int crypto_box_curve25519xsalsa20poly1305_beforenm(
            @NotNull ByteBuffer dstKey,
            @NotNull ByteBuffer srcPublic,
            @NotNull ByteBuffer srcPrivate);
    public static native int crypto_box_curve25519xsalsa20poly1305_afternm(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_box_curve25519xsalsa20poly1305_open_afternm(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_box_curve25519xsalsa20poly1305(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer publicKey,
            @NotNull ByteBuffer privateKey);
    public static native int crypto_box_curve25519xsalsa20poly1305_open(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer publicKey,
            @NotNull ByteBuffer privateKey);

    //
    // Box - X25519XChachaPoly1305
    //
    public static native int crypto_box_curve25519xchacha20poly1305_seedbytes();
    public static native int crypto_box_curve25519xchacha20poly1305_publickeybytes();
    public static native int crypto_box_curve25519xchacha20poly1305_secretkeybytes();
    public static native int crypto_box_curve25519xchacha20poly1305_beforenmbytes();
    public static native int crypto_box_curve25519xchacha20poly1305_noncebytes();
    public static native int crypto_box_curve25519xchacha20poly1305_macbytes();

    public static native int crypto_box_curve25519xchacha20poly1305_seed_keypair(
            @NotNull ByteBuffer dstPublic,
            @NotNull ByteBuffer dstPrivate,
            @NotNull ByteBuffer seed);
    public static native int crypto_box_curve25519xchacha20poly1305_keypair(
            @NotNull ByteBuffer dstPublic,
            @NotNull ByteBuffer dstPrivate);
    public static native int crypto_box_curve25519xchacha20poly1305_beforenm(
            @NotNull ByteBuffer dstKey,
            @NotNull ByteBuffer srcPublic,
            @NotNull ByteBuffer srcPrivate);
    public static native int crypto_box_curve25519xchacha20poly1305_easy_afternm(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_box_curve25519xchacha20poly1305_open_easy_afternm(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_box_curve25519xchacha20poly1305_easy(
            @NotNull ByteBuffer dstCipher,
            @NotNull ByteBuffer srcPlain,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer publicKey,
            @NotNull ByteBuffer privateKey);
    public static native int crypto_box_curve25519xchacha20poly1305_open_easy(
            @NotNull ByteBuffer dstPlain,
            @NotNull ByteBuffer srcCipher,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer publicKey,
            @NotNull ByteBuffer privateKey);

    // todo detached

    //
    // Codec
    //

    //
    // Codec Hex
    //
    public static native int sodium_bin2hex(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src);
    public static native int sodium_hex2bin(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src);

    //
    // Codec Base64
    //
    public static native int sodium_base64_variant_original();
    public static native int sodium_base64_variant_original_no_padding();
    public static native int sodium_base64_variant_urlsafe();
    public static native int sodium_base64_variant_urlsafe_no_padding();

    public static native int sodium_base64_encoded_len(
            int bin_len,
            int variant);

    public static native int sodium_bin2base64(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
                     int        variant);
    public static native int sodium_base642bin(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
                     int        variant);

    //
    // GenericHash
    //
    public static native @NotNull String crypto_generichash_primitive();

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
            @NotNull  ByteBuffer state,
            @Nullable ByteBuffer key,
                      int        outlen);
    public static native int crypto_generichash_blake2b_update(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer in);
    public static native int crypto_generichash_blake2b_final(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer out);

    //
    // Hash
    //
    public static native @NotNull String crypto_hash_primitive();

    //
    // Hash - SHA-256
    //
    public static native int crypto_hash_sha256_bytes();
    public static native int crypto_hash_sha256_statebytes();

    public static native int crypto_hash_sha256(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in);
    public static native int crypto_hash_sha256_init(
            @NotNull ByteBuffer dst);
    public static native int crypto_hash_sha256_update(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in);
    public static native int crypto_hash_sha256_final(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer dst);

    //
    // Hash - SHA-512
    //
    public static native int crypto_hash_sha512_bytes();
    public static native int crypto_hash_sha512_statebytes();

    public static native int crypto_hash_sha512(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in);
    public static native int crypto_hash_sha512_init(
            @NotNull ByteBuffer dst);
    public static native int crypto_hash_sha512_update(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in);
    public static native int crypto_hash_sha512_final(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer dst);

    //
    // Kdf
    //
    public static native @NotNull String crypto_kdf_primitive();

    //
    // Kdf - Blake2b
    //
    public static native int crypto_kdf_blake2b_bytes_min();
    public static native int crypto_kdf_blake2b_bytes_max();
    public static native int crypto_kdf_blake2b_contextbytes();
    public static native int crypto_kdf_blake2b_keybytes();

    public static native int crypto_kdf_blake2b_derive_from_key(
            @NotNull ByteBuffer subkey,
                     long       subId,
            @NotNull ByteBuffer context,
            @NotNull ByteBuffer key);

    //
    // Kx
    //
    public static native @NotNull String crypto_kx_primitive();

    public static native int crypto_kx_publickeybytes();
    public static native int crypto_kx_secretkeybytes();
    public static native int crypto_kx_seedbytes();
    public static native int crypto_kx_sessionkeybytes();

    public static native int crypto_kx_keypair(
            @NotNull ByteBuffer pk,
            @NotNull ByteBuffer sk);

    public static native int crypto_kx_seed_keypair(
            @NotNull ByteBuffer pk,
            @NotNull ByteBuffer sk,
            @NotNull ByteBuffer seed);

    public static native int crypto_kx_client_session_keys(
            @NotNull ByteBuffer rx,
            @NotNull ByteBuffer tx,
            @NotNull ByteBuffer clientPk,
            @NotNull ByteBuffer clientSk,
            @NotNull ByteBuffer serverPk);

    public static native int crypto_kx_server_session_keys(
            @NotNull ByteBuffer rx,
            @NotNull ByteBuffer tx,
            @NotNull ByteBuffer serverPk,
            @NotNull ByteBuffer serverSk,
            @NotNull ByteBuffer clientPk);

    //
    // OneTimeAuth
    //
    public static native @NotNull String crypto_onetimeauth_primitive();

    //
    // OneTimeAuth - Poly1305
    //
    public static native int crypto_onetimeauth_poly1305_bytes();
    public static native int crypto_onetimeauth_poly1305_keybytes();
    public static native int crypto_onetimeauth_poly1305_statebytes();

    public static native int crypto_onetimeauth_poly1305(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);
    public static native int crypto_onetimeauth_poly1305_verify(
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);
    public static native int crypto_onetimeauth_poly1305_init(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer key);
    public static native int crypto_onetimeauth_poly1305_update(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer in);
    public static native int crypto_onetimeauth_poly1305_final(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer dst);
    //
    // PwHash
    //
    public static native @NotNull String crypto_pwhash_primitive();

    //
    // PwHash - Argon2i
    //
    public static native long crypto_pwhash_argon2i_bytes_min();
    public static native long crypto_pwhash_argon2i_bytes_max();
    public static native long crypto_pwhash_argon2i_passwd_min();
    public static native long crypto_pwhash_argon2i_passwd_max();
    public static native int crypto_pwhash_argon2i_saltbytes();
    public static native int crypto_pwhash_argon2i_strbytes();
    public static native @NotNull String crypto_pwhash_argon2i_strprefix();
    public static native long crypto_pwhash_argon2i_opslimit_min();
    public static native long crypto_pwhash_argon2i_opslimit_max();
    public static native long crypto_pwhash_argon2i_memlimit_min();
    public static native long crypto_pwhash_argon2i_memlimit_max();
    public static native long crypto_pwhash_argon2i_opslimit_interactive();
    public static native long crypto_pwhash_argon2i_memlimit_interactive();
    public static native long crypto_pwhash_argon2i_opslimit_sensitive();
    public static native long crypto_pwhash_argon2i_memlimit_sensitive();

    public static native int crypto_pwhash_argon2i(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer password,
            @NotNull ByteBuffer salt,
                     long       opslimit,
                     long       memlimit);

    public static native int crypto_pwhash_argon2i_str(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer password,
                     long       opslimit,
                     long       memlimit);

    public static native int crypto_pwhash_argon2i_str_verify(
            @NotNull ByteBuffer str,
            @NotNull ByteBuffer password);

    //
    // PwHash Scrypt
    //
    public static native long crypto_pwhash_scryptsalsa208sha256_bytes_min();
    public static native long crypto_pwhash_scryptsalsa208sha256_bytes_max();
    public static native long crypto_pwhash_scryptsalsa208sha256_passwd_min();
    public static native long crypto_pwhash_scryptsalsa208sha256_passwd_max();
    public static native int crypto_pwhash_scryptsalsa208sha256_saltbytes();
    public static native int crypto_pwhash_scryptsalsa208sha256_strbytes();
    public static native @NotNull String crypto_pwhash_scryptsalsa208sha256_strprefix();
    public static native long crypto_pwhash_scryptsalsa208sha256_opslimit_min();
    public static native long crypto_pwhash_scryptsalsa208sha256_opslimit_max();
    public static native long crypto_pwhash_scryptsalsa208sha256_memlimit_min();
    public static native long crypto_pwhash_scryptsalsa208sha256_memlimit_max();
    public static native long crypto_pwhash_scryptsalsa208sha256_opslimit_interactive();
    public static native long crypto_pwhash_scryptsalsa208sha256_memlimit_interactive();
    public static native long crypto_pwhash_scryptsalsa208sha256_opslimit_sensitive();
    public static native long crypto_pwhash_scryptsalsa208sha256_memlimit_sensitive();

    public static native int crypto_pwhash_scryptsalsa208sha256(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer password,
            @NotNull ByteBuffer salt,
                     long       opslimit,
                     long       memlimit);

    public static native int crypto_pwhash_scryptsalsa208sha256_str(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer password,
            long       opslimit,
            long       memlimit);

    public static native int crypto_pwhash_scryptsalsa208sha256_str_verify(
            @NotNull ByteBuffer str,
            @NotNull ByteBuffer password);

    //
    // ScalarMult
    //
    public static native String crypto_scalarmult_primitive();

    //
    // ScalarMult Curve25519
    //
    public static native int crypto_scalarmult_curve25519_bytes();
    public static native int crypto_scalarmult_curve25519_scalarbytes();

    public static native int crypto_scalarmult_curve25519(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer elm);
    public static native int crypto_scalarmult_curve25519_base(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src);

    //
    // SecretBox
    //
    public static native String crypto_secretbox_primitive();

    //
    // SecretBox XSalsa20Poly1305
    //
    public static native int crypto_secretbox_xsalsa20poly1305_keybytes();
    public static native int crypto_secretbox_xsalsa20poly1305_macbytes();
    public static native int crypto_secretbox_xsalsa20poly1305_noncebytes();

    public static native int crypto_secretbox_xsalsa20poly1305_easy(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_secretbox_xsalsa20poly1305_open_easy(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_secretbox_xsalsa20poly1305_detached(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer mac,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_secretbox_xsalsa20poly1305_open_detached(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer mac,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);

    //
    // SecretBox XChacha20Poly1305
    //
    public static native int crypto_secretbox_xchacha20poly1305_keybytes();
    public static native int crypto_secretbox_xchacha20poly1305_macbytes();
    public static native int crypto_secretbox_xchacha20poly1305_noncebytes();

    public static native int crypto_secretbox_xchacha20poly1305_easy(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_secretbox_xchacha20poly1305_open_easy(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_secretbox_xchacha20poly1305_detached(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer mac,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);
    public static native int crypto_secretbox_xchacha20poly1305_open_detached(
            @NotNull ByteBuffer dst,
            @NotNull ByteBuffer src,
            @NotNull ByteBuffer mac,
            @NotNull ByteBuffer nonce,
            @NotNull ByteBuffer key);

    //
    // ShortHash
    //
    public static native @NotNull String crypto_shorthash_primitive();

    //
    // ShortHash SipHash24
    //
    public static native int crypto_shorthash_siphash24_bytes();
    public static native int crypto_shorthash_siphash24_keybytes();

    public static native int crypto_shorthash_siphash24(
            @NotNull ByteBuffer out,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);

    //
    // ShortHash SipHashx24
    //
    public static native int crypto_shorthash_siphashx24_bytes();
    public static native int crypto_shorthash_siphashx24_keybytes();

    public static native int crypto_shorthash_siphashx24(
            @NotNull ByteBuffer out,
            @NotNull ByteBuffer in,
            @NotNull ByteBuffer key);

    //
    // Sign
    //
    public static native @NotNull String crypto_sign_primitive();

    //
    // Sign - Ed25519
    //
    public static native int crypto_sign_ed25519_publickeybytes();
    public static native int crypto_sign_ed25519_secretkeybytes();
    public static native int crypto_sign_ed25519_bytes();
    public static native int crypto_sign_ed25519_seedbytes();
    public static native int crypto_sign_ed25519ph_statebytes();

    public static native int crypto_sign_ed25519_keypair(
            @NotNull ByteBuffer dstPub,
            @NotNull ByteBuffer dstPriv);
    public static native int crypto_sign_ed25519_seed_keypair(
            @NotNull ByteBuffer dstPub,
            @NotNull ByteBuffer dstPriv,
            @NotNull ByteBuffer srcSeed);
    public static native int crypto_sign_ed25519(
            @NotNull ByteBuffer dstSigned,
            @NotNull ByteBuffer srcMsg,
            @NotNull ByteBuffer priv);
    public static native int crypto_sign_ed25519_open(
            @NotNull ByteBuffer dstMsg,
            @NotNull ByteBuffer srcSigned,
            @NotNull ByteBuffer priv);
    public static native int crypto_sign_ed25519_detached(
            @NotNull ByteBuffer dstSig,
            @NotNull ByteBuffer srcMsg,
            @NotNull ByteBuffer priv);
    public static native int crypto_sign_ed25519_verify_detached(
            @NotNull ByteBuffer srcSig,
            @NotNull ByteBuffer srcMsg,
            @NotNull ByteBuffer priv);
    public static native int crypto_sign_ed25519ph_init(
            @NotNull ByteBuffer state);
    public static native int crypto_sign_ed25519ph_update(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer srcMsg);
    public static native int crypto_sign_ed25519ph_final_create(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer dstSig,
            @NotNull ByteBuffer priv);
    public static native int crypto_sign_ed25519ph_final_verify(
            @NotNull ByteBuffer state,
            @NotNull ByteBuffer srcSig,
            @NotNull ByteBuffer priv);

    /*
      Load the native library
     */
    static {
        try {
            Class.forName("android.Manifest");

            // Load the android JNI libs, as this is libstodium-android
            System.loadLibrary("stodiumjni");

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
            throw new RuntimeException("Cannot load libstodium native library");
        }

        if (StodiumJNI.stodium_init() != 0) {
            throw new RuntimeException("Stodium: could not initialize with stodium_init()");
        }
    }
}
