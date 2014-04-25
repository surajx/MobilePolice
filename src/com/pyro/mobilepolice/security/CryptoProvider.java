package com.pyro.mobilepolice.security;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class CryptoProvider {

	private static final String CRYPTO_KEY = "PYROCRYPT_RAISING_HELL";
	private static final String PBKDF2_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static String DELIMITER = "]";

	private static int KEY_LENGTH = 256;
	// minimum values recommended by PKCS#5, increase as necessary
	private static int ITERATION_COUNT = 1000;
	private static final int PKCS5_SALT_LENGTH = 8;

	private static SecureRandom random = new SecureRandom();

	private static CryptoProvider mCryptoProvider;

	private static final String TAG = "CryptoProvider";

	private SecretKey keyGenerator(byte[] salt) {
		try {
			KeySpec keySpec = new PBEKeySpec(CRYPTO_KEY.toCharArray(), salt,
					ITERATION_COUNT, KEY_LENGTH);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(PBKDF2_DERIVATION_ALGORITHM);
			byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
			SecretKey result = new SecretKeySpec(keyBytes, "AES");
			return result;
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (byte b : bytes) {
			buff.append(String.format("%02X", b));
		}
		return buff.toString();
	}

	private static byte[] generateSalt() {
		byte[] b = new byte[PKCS5_SALT_LENGTH];
		random.nextBytes(b);
		return b;
	}

	private static byte[] generateIv(int length) {
		byte[] b = new byte[length];
		random.nextBytes(b);
		return b;
	}

	private static String toBase64(byte[] bytes) {
		return Base64.encodeToString(bytes, Base64.NO_WRAP);
	}

	public static byte[] fromBase64(String base64) {
		return Base64.decode(base64, Base64.NO_WRAP);
	}

	private CryptoProvider() {
	}

	public static synchronized CryptoProvider getInstance() {
		if (mCryptoProvider == null)
			mCryptoProvider = new CryptoProvider();
		return mCryptoProvider;
	}

	public String encrypt(String plaintext) {
		byte[] salt = generateSalt();
		SecretKey key = keyGenerator(salt);
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

			byte[] iv = generateIv(cipher.getBlockSize());
			IvParameterSpec ivParams = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
			byte[] cipherText = cipher.doFinal(plaintext.getBytes("UTF-8"));

			if (salt != null) {
				return String.format("%s%s%s%s%s", toBase64(salt), DELIMITER,
						toBase64(iv), DELIMITER, toBase64(cipherText));
			}

			return String.format("%s%s%s", toBase64(iv), DELIMITER,
					toBase64(cipherText));
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public String decrypt(String ciphertext) {
		String[] fields = ciphertext.split(DELIMITER);
		if (fields.length != 3) {
			throw new IllegalArgumentException("Invalid encypted text format");
		}

		byte[] salt = fromBase64(fields[0]);
		byte[] iv = fromBase64(fields[1]);
		byte[] cipherBytes = fromBase64(fields[2]);
		SecretKey key = keyGenerator(salt);
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec ivParams = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
			byte[] plaintext = cipher.doFinal(cipherBytes);
			String plainrStr = new String(plaintext, "UTF-8");
			return plainrStr;
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
