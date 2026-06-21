## 9. Full Enumeration Reference (All Versions)

Complete value tables for every enumeration parsed across all versions. ⚠️ on a value means it was not present in the final version (`v3.0`).

### Adjustment Type  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Negate | `Negate the value. Applies to Integer, Long Integers, Big Integer and Boolean types.` | v2.0 | v3.0 |
| Decrement | `Subtract the Adjustment Parameter to the value. Applies to Integer, Long Integers, Big Integer, Interval, Date Time, and Date Time Extended. The default is parameter is 1 for numeric types, 1 second for Date Time, and 1 microsecond for Date Time Extended.` | v2.0 | v3.0 |

### Alternative Name Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Uninterpreted Text String | `0x00000001` | v1.2 | v3.0 |
| URI | `0x00000002` | v1.2 | v3.0 |
| Object Serial Number | `0x00000003` | v1.2 | v3.0 |
| Email Address | `0x00000004` | v1.2 | v3.0 |
| DNS Name | `0x00000005` | v1.2 | v3.0 |
| X.500 Distinguished Name | `0x00000006` | v1.2 | v3.0 |
| IP Address | `0x00000007` | v1.2 | v3.0 |

### Asynchronous Indicator  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Optional | `The server MAY process each batch item in the request either asynchronously (returning an Asynchronous Correlation Value for a batch item) or synchronously. The method or policy by which the server determines whether or not to process an individual batch item asynchronously is a decision of the server and is outside of the scope of this protocol.` | v2.0 | v3.0 |
| Prohibited | `The server SHALL NOT process any batch item asynchronously. All batch items SHALL be processed synchronously.` | v2.0 | v3.0 |

### Attestation Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| TPM Quote | `0x00000001` | v1.2 | v3.0 |
| TCG Integrity Report | `0x00000002` | v1.2 | v3.0 |
| SAML Assertion | `0x00000003` | v1.2 | v3.0 |

### Batch Error Continuation Option  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Continue | `0x00000001` | v1.2 | v3.0 |
| Stop | `0x00000002` | v1.2 | v3.0 |
| Undo | `0x00000003` | v1.2 | v1.4 ⚠️ |

### Block Cipher Mode  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| CBC | `0x00000001` | v1.2 | v3.0 |
| ECB | `0x00000002` | v1.2 | v3.0 |
| PCBC | `0x00000003` | v1.2 | v3.0 |
| CFB | `0x00000004` | v1.2 | v3.0 |
| OFB | `0x00000005` | v1.2 | v3.0 |
| CTR | `0x00000006` | v1.2 | v3.0 |
| CMAC | `0x00000007` | v1.2 | v3.0 |
| CCM | `0x00000008` | v1.2 | v3.0 |
| GCM | `0x00000009` | v1.2 | v3.0 |
| CBC-MAC | `0x0000000A` | v1.2 | v3.0 |
| XTS | `0x0000000B` | v1.2 | v3.0 |
| AESKeyWrapPadding | `0x0000000C` | v1.2 | v3.0 |
| NISTKeyWrap | `0x0000000D` | v1.2 | v3.0 |
| X9.102 AESKW | `0x0000000E` | v1.2 | v3.0 |
| X9.102 TDKW | `0x0000000F` | v1.2 | v3.0 |
| X9.102 AKW1 | `0x00000010` | v1.2 | v3.0 |
| X9.102 AKW2 | `0x00000011` | v1.2 | v3.0 |
| AEAD | `0x00000012` | v1.4 | v3.0 |

### Cancellation Result  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Canceled | `0x00000001` | v1.2 | v1.4 ⚠️ |
| Unable to Cancel | `0x00000002` | v1.2 | v3.0 |
| Completed | `0x00000003` | v1.2 | v3.0 |
| Failed | `0x00000004` | v1.2 | v3.0 |
| Unavailable | `0x00000005` | v1.2 | v3.0 |

### Certificate Request Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| PGP | `00000004 (deprecated)` | v1.2 | v1.4 ⚠️ |
| CRMF | `0x00000001` | v1.2 | v3.0 |
| PKCS#10 | `0x00000002` | v1.2 | v3.0 |
| PEM | `0x00000003` | v1.2 | v3.0 |

### Certificate Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| PGP | `00000002 (deprecated)` | v1.2 | v3.0 |
| X.509 | `0x00000001` | v1.2 | v3.0 |
| (PGP | `0x00000002` | v2.0 | v2.0 ⚠️ |

### Client Registration Method  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v1.4 ⚠️ |
| Server Pre-Generated | `0x00000002` | v1.3 | v1.4 ⚠️ |
| Server On-Demand | `0x00000003` | v1.3 | v3.0 |
| Client Generated | `0x00000004` | v1.3 | v3.0 |
| Client Registered | `0x00000005` | v1.3 | v3.0 |

### Credential Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Username and Password | `0x00000001` | v1.2 | v3.0 |
| Device | `0x00000002` | v1.2 | v3.0 |
| Attestation | `0x00000003` | v1.2 | v3.0 |
| One Time Password | `0x00000004` | v2.0 | v3.0 |
| Hashed Password | `0x00000005` | v2.0 | v3.0 |
| Ticket | `0x00000006` | v2.0 | v3.0 |
| Password | `0x00000007` | v3.0 | v3.0 |
| Certificate | `0x00000008` | v3.0 | v3.0 |

### Cryptographic Algorithm  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| DES | `0x00000001` | v1.2 | v3.0 |
| 3DES | `0x00000002` | v1.2 | v3.0 |
| AES | `0x00000003` | v1.2 | v3.0 |
| RSA | `0x00000004` | v1.2 | v3.0 |
| DSA | `0x00000005` | v1.2 | v3.0 |
| ECDSA | `0x00000006` | v1.2 | v3.0 |
| HMAC-SHA1 | `0x00000007` | v1.2 | v3.0 |
| HMAC-SHA224 | `0x00000008` | v1.2 | v3.0 |
| HMAC-SHA256 | `0x00000009` | v1.2 | v3.0 |
| HMAC-SHA384 | `0x0000000A` | v1.2 | v3.0 |
| HMAC-SHA512 | `0x0000000B` | v1.2 | v3.0 |
| HMAC-MD5 | `0x0000000C` | v1.2 | v3.0 |
| DH | `0x0000000D` | v1.2 | v3.0 |
| ECDH | `0x0000000E` | v1.2 | v3.0 |
| ECMQV | `0x0000000F` | v1.2 | v3.0 |
| Blowfish | `0x00000010` | v1.2 | v3.0 |
| Camellia | `0x00000011` | v1.2 | v3.0 |
| CAST5 | `0x00000012` | v1.2 | v3.0 |
| IDEA | `0x00000013` | v1.2 | v3.0 |
| MARS | `0x00000014` | v1.2 | v3.0 |
| RC2 | `0x00000015` | v1.2 | v3.0 |
| RC4 | `0x00000016` | v1.2 | v3.0 |
| RC5 | `0x00000017` | v1.2 | v3.0 |
| SKIPJACK | `0x00000018` | v1.2 | v3.0 |
| Twofish | `0x00000019` | v1.2 | v3.0 |
| EC | `0x0000001A` | v1.2 | v3.0 |
| One Time Pad | `0x0000001B` | v1.3 | v3.0 |
| ChaCha20 | `0x0000001C` | v1.4 | v3.0 |
| Poly1305 | `0x0000001D` | v1.4 | v3.0 |
| ChaCha20Poly1305 | `0x0000001E` | v1.4 | v3.0 |
| SHA3-224 | `0x0000001F` | v1.4 | v3.0 |
| SHA3-256 | `0x00000020` | v1.4 | v3.0 |
| SHA3-384 | `0x00000021` | v1.4 | v3.0 |
| SHA3-512 | `0x00000022` | v1.4 | v3.0 |
| HMAC-SHA3-224 | `0x00000023` | v1.4 | v3.0 |
| HMAC-SHA3-256 | `0x00000024` | v1.4 | v3.0 |
| HMAC-SHA3-384 | `0x00000025` | v1.4 | v3.0 |
| HMAC-SHA3-512 | `0x00000026` | v1.4 | v3.0 |
| SHAKE-128 | `0x00000027` | v1.4 | v3.0 |
| SHAKE-256 | `0x00000028` | v1.4 | v3.0 |
| ARIA | `0x00000029` | v2.0 | v3.0 |
| SEED | `0x0000002A` | v2.0 | v3.0 |
| SM2 | `0x0000002B` | v2.0 | v3.0 |
| SM3 | `0x0000002C` | v2.0 | v3.0 |
| SM4 | `0x0000002D` | v2.0 | v3.0 |
| GOST R 34.10-2012 | `0x0000002E` | v2.0 | v3.0 |
| GOST R 34.11-2012 | `0x0000002F` | v2.0 | v3.0 |
| GOST R 34.13-2015 | `0x00000030` | v2.0 | v3.0 |
| GOST 28147-89 | `0x00000031` | v2.0 | v3.0 |
| XMSS | `0x00000032` | v2.0 | v3.0 |
| SPHINCS-256 | `0x00000033` | v2.0 | v3.0 |
| McEliece | `0x00000034` | v2.0 | v3.0 |
| McEliece-6960119 | `0x00000035` | v2.0 | v3.0 |
| McEliece-8192128 | `0x00000036` | v2.0 | v3.0 |
| Ed25519 | `0x00000037` | v2.0 | v3.0 |
| Ed448 | `0x00000038` | v2.0 | v3.0 |
| ML-KEM-512 | `0x00000039` | v3.0 | v3.0 |
| ML-KEM-768 | `0x0000003A` | v3.0 | v3.0 |
| ML-KEM-1024 | `0x0000003B` | v3.0 | v3.0 |
| ML-DSA-44 | `0x0000003C` | v3.0 | v3.0 |
| ML-DSA-65 | `0x0000003D` | v3.0 | v3.0 |
| ML-DSA-87 | `0x0000003E` | v3.0 | v3.0 |
| SLH-DSA-SHA2-128s | `0x0000003F` | v3.0 | v3.0 |
| SLH-DSA-SHA2-128f | `0x00000040` | v3.0 | v3.0 |
| SLH-DSA-SHA2-192s | `0x00000041` | v3.0 | v3.0 |
| SLH-DSA-SHA2-192f | `0x00000042` | v3.0 | v3.0 |
| SLH-DSA-SHA2-256s | `0x00000043` | v3.0 | v3.0 |
| SLH-DSA-SHA2-256f | `0x00000044` | v3.0 | v3.0 |
| SLH-DSA-SHAKE-128s | `0x00000045` | v3.0 | v3.0 |
| SLH-DSA-SHAKE-128f | `0x00000046` | v3.0 | v3.0 |
| SLH-DSA-SHAKE-192s | `0x00000047` | v3.0 | v3.0 |
| SLH-DSA-SHAKE-192f | `0x00000048` | v3.0 | v3.0 |
| SLH-DSA-SHAKE-256s | `0x00000049` | v3.0 | v3.0 |
| SLH-DSA-SHAKE-256f | `0x0000004A` | v3.0 | v3.0 |

### DRBG Algorithm  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| Dual-EC | `0x00000002` | v1.3 | v3.0 |
| Hash | `0x00000003` | v1.3 | v3.0 |
| HMAC | `0x00000004` | v1.3 | v3.0 |
| CTR | `0x00000005` | v1.3 | v3.0 |

### Data  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Decrypt | `0x00000001` | v2.0 | v3.0 |
| Encrypt | `0x00000002` | v2.0 | v3.0 |
| Hash | `0x00000003` | v2.0 | v3.0 |
| MAC MAC Data | `0x00000004` | v2.0 | v3.0 |
| RNG Retrieve | `0x00000005` | v2.0 | v3.0 |
| Sign Signature Data | `0x00000006` | v2.0 | v3.0 |
| Signature Verify | `0x00000007` | v2.0 | v3.0 |

### Deactivation Reason Code  ✅  —  `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v3.0 | v3.0 |
| Deactivation Date | `0x00000002` | v3.0 | v3.0 |
| Protect Stop Date | `0x00000003` | v3.0 | v3.0 |
| Usage Limit | `0x00000004` | v3.0 | v3.0 |

### Derivation Method  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| PBKDF2 | `0x00000001` | v1.2 | v1.4 ⚠️ |
| HASH | `0x00000002` | v1.2 | v3.0 |
| HMAC | `0x00000003` | v1.2 | v3.0 |
| ENCRYPT | `0x00000004` | v1.2 | v3.0 |
| NIST800-108-C | `0x00000005` | v1.2 | v3.0 |
| NIST800-108-F | `0x00000006` | v1.2 | v3.0 |
| NIST800-108-DPI | `0x00000007` | v1.2 | v3.0 |
| Asymmetric Key | `0x00000008` | v1.4 | v3.0 |
| AWS Signature Version 4 | `As defined in Amazon Web Services Signature Version 4.` | v2.0 | v3.0 |
| HKDF | `HMAC-based Extract-and-Expand Key Derivation Function` | v2.0 | v3.0 |

### Destroy Action  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| Key Material Deleted | `0x00000002` | v1.3 | v3.0 |
| Key Material Shredded | `0x00000003` | v1.3 | v3.0 |
| Meta Data Deleted | `0x00000004` | v1.3 | v3.0 |
| Meta Data Shredded | `0x00000005` | v1.3 | v3.0 |
| Deleted | `0x00000006` | v1.3 | v3.0 |
| Shredded | `0x00000007` | v1.3 | v3.0 |

### Digital Signature Algorithm  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| MD2 with RSA Encryption (PKCS#1 v1.5) | `0x00000001` | v1.2 | v1.4 ⚠️ |
| MD2 with RSA Encryption | `0x00000001` | v2.0 | v3.0 |
| MD5 with RSA Encryption (PKCS#1 v1.5) | `0x00000002` | v1.2 | v1.4 ⚠️ |
| MD5 with RSA Encryption | `0x00000002` | v2.0 | v3.0 |
| SHA-1 with RSA Encryption (PKCS#1 v1.5) | `0x00000003` | v1.2 | v1.4 ⚠️ |
| SHA-1 with RSA Encryption | `0x00000003` | v2.0 | v3.0 |
| SHA-224 with RSA Encryption (PKCS#1 v1.5) | `0x00000004` | v1.2 | v1.4 ⚠️ |
| SHA-224 with RSA Encryption | `0x00000004` | v2.0 | v3.0 |
| SHA-256 with RSA Encryption (PKCS#1 v1.5) | `0x00000005` | v1.2 | v1.4 ⚠️ |
| SHA-256 with RSA Encryption | `0x00000005` | v2.0 | v3.0 |
| SHA-384 with RSA Encryption (PKCS#1 v1.5) | `0x00000006` | v1.2 | v1.4 ⚠️ |
| SHA-384 with RSA Encryption | `0x00000006` | v2.0 | v3.0 |
| SHA-512 with RSA Encryption (PKCS#1 v1.5) | `0x00000007` | v1.2 | v1.4 ⚠️ |
| SHA-512 with RSA Encryption | `0x00000007` | v2.0 | v3.0 |
| RSASSA-PSS (PKCS#1 v2.1) | `0x00000008` | v1.2 | v1.4 ⚠️ |
| RSASSA-PSS | `0x00000008` | v2.0 | v3.0 |
| DSA with SHA-1 | `0x00000009` | v1.2 | v3.0 |
| DSA with SHA224 | `0x0000000A` | v1.2 | v3.0 |
| DSA with SHA256 | `0x0000000B` | v1.2 | v3.0 |
| ECDSA with SHA-1 | `0x0000000C` | v1.2 | v3.0 |
| ECDSA with SHA224 | `0x0000000D` | v1.2 | v3.0 |
| ECDSA with SHA256 | `0x0000000E` | v1.2 | v3.0 |
| ECDSA with SHA384 | `0x0000000F` | v1.2 | v3.0 |
| ECDSA with SHA512 | `0x00000010` | v1.2 | v3.0 |
| SHA3-256 with RSA Encryption | `0x00000011` | v1.4 | v3.0 |
| SHA3-384 with RSA Encryption | `0x00000012` | v1.4 | v3.0 |
| SHA3-512 with RSA Encryption | `0x00000013` | v1.4 | v3.0 |

### Encoding Option  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| No Encoding | `0x00000001` | v1.2 | v1.4 ⚠️ |
| TTLV Encoding | `0x00000002` | v1.2 | v3.0 |

### Endpoint Role  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Server | `The endpoint that receives requests and sends responses.` | v2.0 | v3.0 |

### Ephemeral  ✅  —  `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unique Identifier | `All fields in the Response Payload other than the Unique Identifier are omitted.` | v3.0 | v3.0 |
| Empty | `The Response Payload is returned as empty (all fields are omitted)` | v3.0 | v3.0 |

### FIPS186 Variation  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| GP x-Original | `0x00000002` | v1.3 | v3.0 |
| GP x-Change Notice | `0x00000003` | v1.3 | v3.0 |
| x-Original | `0x00000004` | v1.3 | v3.0 |
| x-Change Notice | `0x00000005` | v1.3 | v3.0 |
| k-Original | `0x00000006` | v1.3 | v3.0 |
| k-Change Notice | `0x00000007` | v1.3 | v3.0 |

### Hashing Algorithm  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| MD2 | `0x00000001` | v1.2 | v3.0 |
| MD4 | `0x00000002` | v1.2 | v3.0 |
| MD5 | `0x00000003` | v1.2 | v3.0 |
| SHA-1 | `0x00000004` | v1.2 | v3.0 |
| SHA-224 | `0x00000005` | v1.2 | v3.0 |
| SHA-256 | `0x00000006` | v1.2 | v3.0 |
| SHA-384 | `0x00000007` | v1.2 | v3.0 |
| SHA-512 | `0x00000008` | v1.2 | v3.0 |
| RIPEMD-160 | `0x00000009` | v1.2 | v3.0 |
| Tiger | `0x0000000A` | v1.2 | v3.0 |
| Whirlpool | `0x0000000B` | v1.2 | v3.0 |
| SHA-512/224 | `0x0000000C` | v1.2 | v3.0 |
| SHA-512/256 | `0x0000000D` | v1.2 | v3.0 |
| SHA-3-224 | `0x0000000E` | v1.4 | v1.4 ⚠️ |
| SHA3-224 | `0x0000000E` | v2.0 | v3.0 |
| SHA-3-256 | `0x0000000F` | v1.4 | v1.4 ⚠️ |
| SHA3-256 | `0x0000000F` | v2.0 | v3.0 |
| SHA-3-384 | `0x00000010` | v1.4 | v1.4 ⚠️ |
| SHA3-384 | `0x00000010` | v2.0 | v3.0 |
| SHA-3-512 | `0x00000011` | v1.4 | v1.4 ⚠️ |
| SHA3-512 | `0x00000011` | v2.0 | v3.0 |

### Interop Function  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| End | `A specified test has ended` | v2.0 | v3.0 |
| Reset | `Resets the server to the state it would be in at the beginning of an interop session` | v2.0 | v3.0 |

### Item Type  ❌ **NOT IMPLEMENTED**  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Boolean | `0x0EAEEFAE` | v2.0 | v3.0 |
| Big Integer | `A sequence of eight-bit bytes` | v2.0 | v3.0 |
| Date Time Extended | `Eight-byte long (64 bit) POSIX Time values in micro-seconds.` | v2.0 | v3.0 |
| Date Time | `Eight-byte long (64 bit) POSIX Time values in seconds. .` | v2.0 | v3.0 |
| Long Integer | `Eight-byte long (64 bit) signed numbers.` | v2.0 | v3.0 |
| Integer | `Four-byte long (32 bit) signed numbers` | v2.0 | v3.0 |
| Enumeration | `Four-byte long (32 bit) unsigned numbers` | v2.0 | v3.0 |
| Interval | `Four-byte long (32 bit) unsigned numbers in seconds` | v2.0 | v3.0 |
| Name Reference | `Sequence of character values.` | v3.0 | v3.0 |
| Byte String | `Sequences of bytes containing individual unspecified eight-bit binary values` | v2.0 | v3.0 |
| Text String | `Sequences of character values.` | v2.0 | v3.0 |
| Identifier | `Sequences of character values.` | v3.0 | v3.0 |
| Reference | `Sequences of character values.` | v3.0 | v3.0 |

### Key Compression Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| EC Public Key Type Uncompressed | `0x00000001` | v1.2 | v3.0 |
| EC Public Key Type X9.62 Compressed Prime | `0x00000002` | v1.2 | v3.0 |
| EC Public Key Type X9.62 Compressed Char2 | `0x00000003` | v1.2 | v3.0 |
| EC Public Key Type X9.62 Hybrid | `0x00000004` | v1.2 | v3.0 |

### Key Format Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Raw | `0x00000001` | v1.2 | v1.4 ⚠️ |
| Opaque | `0x00000002` | v1.2 | v3.0 |
| PKCS#1 | `0x00000003` | v1.2 | v1.4 ⚠️ |
| PKCS#8 | `0x00000004` | v1.2 | v1.4 ⚠️ |
| X.509 | `0x00000005` | v1.2 | v3.0 |
| ECPrivateKey | `0x00000006` | v1.2 | v3.0 |
| Transparent Symmetric Key | `0x00000007` | v1.2 | v1.4 ⚠️ |
| Transparent DSA Private Key | `0x00000008` | v1.2 | v1.4 ⚠️ |
| Transparent DSA Public Key | `0x00000009` | v1.2 | v1.4 ⚠️ |
| Transparent RSA Private Key | `0x0000000A` | v1.2 | v1.4 ⚠️ |
| Transparent RSA Public Key | `0x0000000B` | v1.2 | v1.4 ⚠️ |
| Transparent DH Private Key | `0x0000000C` | v1.2 | v1.4 ⚠️ |
| Transparent DH Public Key | `0x0000000D` | v1.2 | v1.4 ⚠️ |
| Transparent ECDSA Private Key | `0x0000000E` | v1.2 | v1.4 ⚠️ |
| Transparent ECDSA Public Key | `0x0000000F` | v1.2 | v1.4 ⚠️ |
| Transparent ECDH Private Key | `0x00000010` | v1.2 | v1.4 ⚠️ |
| Transparent ECDH Public Key | `0x00000011` | v1.2 | v1.4 ⚠️ |
| Transparent ECMQV Private Key | `0x00000012` | v1.2 | v1.4 ⚠️ |
| Transparent ECMQV Public Key | `0x00000013` | v1.2 | v1.4 ⚠️ |
| Transparent EC Private Key | `0x00000014` | v1.3 | v1.4 ⚠️ |
| Transparent EC Public Key | `0x00000015` | v1.3 | v1.4 ⚠️ |
| PKCS#12 | `0x00000016` | v1.4 | v1.4 ⚠️ |
| PKCS8 | `An encoded private key, expressed as a DER-encoded ASN.1 PKCS#8 object, supporting both the RSAPrivateKey syntax and EncryptedPrivateKey` | v2.0 | v3.0 |
| Several Transparent Key types | `algorithm-specific structures containing defined values for the various key types.` | v2.0 | v3.0 |
| PKCS1 | `an encoded private key, expressed as a DER-encoded ASN.1 PKCS#1 object.` | v2.0 | v3.0 |

### Key Role Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| BDK | `0x00000001` | v1.2 | v3.0 |
| CVK | `0x00000002` | v1.2 | v3.0 |
| DEK | `0x00000003` | v1.2 | v3.0 |
| MKAC | `0x00000004` | v1.2 | v3.0 |
| MKSMC | `0x00000005` | v1.2 | v3.0 |
| MKSMI | `0x00000006` | v1.2 | v3.0 |
| MKDAC | `0x00000007` | v1.2 | v3.0 |
| MKDN | `0x00000008` | v1.2 | v3.0 |
| MKCP | `0x00000009` | v1.2 | v3.0 |
| MKOTH | `0x0000000A` | v1.2 | v3.0 |
| KEK | `0x0000000B` | v1.2 | v3.0 |
| MAC16609 | `0x0000000C` | v1.2 | v3.0 |
| MAC97971 | `0x0000000D` | v1.2 | v3.0 |
| MAC97972 | `0x0000000E` | v1.2 | v3.0 |
| MAC97973 | `0x0000000F` | v1.2 | v3.0 |
| MAC97974 | `0x00000010` | v1.2 | v3.0 |
| MAC97975 | `0x00000011` | v1.2 | v3.0 |
| ZPK | `0x00000012` | v1.2 | v3.0 |
| PVKIBM | `0x00000013` | v1.2 | v3.0 |
| PVKPVV | `0x00000014` | v1.2 | v3.0 |
| PVKOTH | `0x00000015` | v1.2 | v3.0 |
| DUKPT | `0x00000016` | v1.4 | v3.0 |
| IV | `0x00000017` | v1.4 | v3.0 |
| TRKBK | `0x00000018` | v1.4 | v3.0 |

### Key Value Location Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Uninterpreted Text String | `0x00000001` | v1.2 | v3.0 |
| URI | `0x00000002` | v1.2 | v3.0 |

### Key Wrap Type  ✅  —  `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Not Wrapped | `0x00000001` | v1.4 | v3.0 |
| As Registered | `0x00000002` | v1.4 | v3.0 |

### Link Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Certificate Link | `0x00000101` | v1.2 | v2.1 ⚠️ |
| Public Key Link | `0x00000102` | v1.2 | v2.1 ⚠️ |
| Private Key Link | `0x00000103` | v1.2 | v1.4 ⚠️ |
| Derivation Base Object Link | `0x00000104` | v1.2 | v2.1 ⚠️ |
| Derived Key Link | `0x00000105` | v1.2 | v2.1 ⚠️ |
| Replacement Object Link | `0x00000106` | v1.2 | v2.1 ⚠️ |
| Replaced Object Link | `0x00000107` | v1.2 | v2.1 ⚠️ |
| Parent Link | `0x00000108` | v1.2 | v2.1 ⚠️ |
| Child Link | `0x00000109` | v1.2 | v2.1 ⚠️ |
| Previous Link | `0x0000010A` | v1.2 | v2.1 ⚠️ |
| Next Link | `0x0000010B` | v1.2 | v2.1 ⚠️ |
| PKCS#12 Certificate Link | `0x0000010C` | v1.4 | v2.1 ⚠️ |
| PKCS#12 Password Link | `0x0000010D` | v1.4 | v2.1 ⚠️ |
| Wrapping Key Link | `For wrapped objects: the object that was used to wrap this object.` | v2.0 | v2.1 ⚠️ |

### Mask Generator  ✅  —  `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| MGF1 | `0x00000001` | v1.4 | v1.4 ⚠️ |
| MFG1 | `0x00000001` | v2.0 | v3.0 |

### NIST Key Type  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Private signature key | `0x00000001` | v2.0 | v3.0 |
| Public signature verification key | `0x00000002` | v2.0 | v3.0 |
| Symmetric authentication key | `0x00000003` | v2.0 | v3.0 |
| Private authentication key | `0x00000004` | v2.0 | v3.0 |
| Public authentication key | `0x00000005` | v2.0 | v3.0 |
| Symmetric data encryption key | `0x00000006` | v2.0 | v3.0 |
| Symmetric key wrapping key | `0x00000007` | v2.0 | v3.0 |
| Symmetric random number generation key | `0x00000008` | v2.0 | v3.0 |
| Symmetric master key | `0x00000009` | v2.0 | v3.0 |
| Private key transport key | `0x0000000A` | v2.0 | v3.0 |
| Public key transport key | `0x0000000B` | v2.0 | v3.0 |
| Symmetric key agreement key | `0x0000000C` | v2.0 | v3.0 |
| Private static key agreement key | `0x0000000D` | v2.0 | v3.0 |
| Public static key agreement key | `0x0000000E` | v2.0 | v3.0 |
| Private ephemeral key agreement key | `0x0000000F` | v2.0 | v3.0 |
| Public ephemeral key agreement key | `0x00000010` | v2.0 | v3.0 |
| Symmetric authorization key | `0x00000011` | v2.0 | v3.0 |
| Private authorization key | `0x00000012` | v2.0 | v3.0 |
| Public authorization key | `0x00000013` | v2.0 | v3.0 |

### Name Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Uninterpreted Text String | `0x00000001` | v1.2 | v2.1 ⚠️ |
| URI | `0x00000002` | v1.2 | v2.1 ⚠️ |

### OTP Algorithm  ✅  —  `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| TOTP | `Time-Based One-Time Password Algorithm [RFC6238]` | v3.0 | v3.0 |

### Object Class  ✅  —  `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| User | `0x00000001` | v3.0 | v3.0 |
| System | `0x00000002` | v3.0 | v3.0 |

### Object Group Member  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Group Member Fresh | `0x00000001` | v1.2 | v2.1 ⚠️ |
| Group Member Default | `0x00000002` | v1.2 | v2.1 ⚠️ |

### Object Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Certificate | `0x00000001` | v1.2 | v3.0 |
| Symmetric Key | `0x00000002` | v1.2 | v3.0 |
| Public Key | `0x00000003` | v1.2 | v3.0 |
| Private Key | `0x00000004` | v1.2 | v3.0 |
| Split Key | `0x00000005` | v1.2 | v3.0 |
| Template | `0x00000006` | v1.2 | v1.4 ⚠️ |
| Secret Data | `0x00000007` | v1.2 | v3.0 |
| Opaque Object | `0x00000008` | v1.2 | v3.0 |
| PGP Key | `0x00000009` | v1.2 | v3.0 |
| Certificate Request | `0x0000000A` | v2.0 | v3.0 |
| User | `0x0000000B` | v3.0 | v3.0 |
| Group | `0x0000000C` | v3.0 | v3.0 |
| Password Credential | `0x0000000D` | v3.0 | v3.0 |
| Device Credential | `0x0000000E` | v3.0 | v3.0 |
| One Time Password Credential | `0x0000000F` | v3.0 | v3.0 |
| Hashed Password Credential | `0x00000010` | v3.0 | v3.0 |

### Operation  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Create | `0x00000001` | v1.2 | v3.0 |
| Create Key Pair | `0x00000002` | v1.2 | v3.0 |
| Register | `0x00000003` | v1.2 | v3.0 |
| Re-key | `0x00000004` | v1.2 | v3.0 |
| Derive Key | `0x00000005` | v1.2 | v3.0 |
| Certify | `0x00000006` | v1.2 | v3.0 |
| Re-certify | `0x00000007` | v1.2 | v3.0 |
| Locate | `0x00000008` | v1.2 | v3.0 |
| Check | `0x00000009` | v1.2 | v3.0 |
| Get | `0x0000000A` | v1.2 | v3.0 |
| Get Attributes | `0x0000000B` | v1.2 | v3.0 |
| Get Attribute List | `0x0000000C` | v1.2 | v3.0 |
| Add Attribute | `0x0000000D` | v1.2 | v3.0 |
| Modify Attribute | `0x0000000E` | v1.2 | v3.0 |
| Delete Attribute | `0x0000000F` | v1.2 | v3.0 |
| Obtain Lease | `0x00000010` | v1.2 | v3.0 |
| Get Usage Allocation | `0x00000011` | v1.2 | v3.0 |
| Activate | `0x00000012` | v1.2 | v3.0 |
| Revoke | `0x00000013` | v1.2 | v3.0 |
| Destroy | `0x00000014` | v1.2 | v3.0 |
| Archive | `0x00000015` | v1.2 | v3.0 |
| Recover | `0x00000016` | v1.2 | v3.0 |
| Validate | `0x00000017` | v1.2 | v3.0 |
| Query | `0x00000018` | v1.2 | v3.0 |
| Cancel | `0x00000019` | v1.2 | v3.0 |
| Poll | `0x0000001A` | v1.2 | v3.0 |
| Notify | `0x0000001B` | v1.2 | v3.0 |
| Put | `0x0000001C` | v1.2 | v3.0 |
| Re-key Key Pair | `0x0000001D` | v1.2 | v3.0 |
| Discover Versions | `0x0000001E` | v1.2 | v3.0 |
| Encrypt | `0x0000001F` | v1.2 | v3.0 |
| Decrypt | `0x00000020` | v1.2 | v3.0 |
| Sign | `0x00000021` | v1.2 | v3.0 |
| Signature Verify | `0x00000022` | v1.2 | v3.0 |
| MAC | `0x00000023` | v1.2 | v3.0 |
| MAC Verify | `0x00000024` | v1.2 | v3.0 |
| RNG Retrieve | `0x00000025` | v1.2 | v3.0 |
| RNG Seed | `0x00000026` | v1.2 | v3.0 |
| Hash | `0x00000027` | v1.2 | v3.0 |
| Create Split Key | `0x00000028` | v1.2 | v3.0 |
| Join Split Key | `0x00000029` | v1.2 | v3.0 |
| Import | `0x0000002A` | v1.4 | v3.0 |
| Export | `0x0000002B` | v1.4 | v3.0 |
| Log | `0x0000002C` | v2.0 | v3.0 |
| Login | `0x0000002D` | v2.0 | v3.0 |
| Logout | `0x0000002E` | v2.0 | v3.0 |
| Delegated Login | `0x0000002F` | v2.0 | v3.0 |
| Adjust Attribute | `0x00000030` | v2.0 | v3.0 |
| Set Attribute | `0x00000031` | v2.0 | v3.0 |
| Set Endpoint Role | `0x00000032` | v2.0 | v3.0 |
| PKCS#11 | `0x00000033` | v2.0 | v3.0 |
| Interop | `0x00000034` | v2.0 | v3.0 |
| Re-Provision | `0x00000035` | v2.0 | v3.0 |
| Set Defaults | `0x00000036` | v2.1 | v3.0 |
| Set Constraints | `0x00000037` | v2.1 | v3.0 |
| Get Constraints | `0x00000038` | v2.1 | v3.0 |
| Query Asynchronous Requests | `0x00000039` | v2.1 | v3.0 |
| Process | `0x0000003A` | v2.1 | v3.0 |
| Ping | `0x0000003B` | v2.1 | v3.0 |
| Create Group | `0x0000003C` | v3.0 | v3.0 |
| Obliterate | `0x0000003D` | v3.0 | v3.0 |
| Create User | `0x0000003E` | v3.0 | v3.0 |
| Create Credential | `0x0000003F` | v3.0 | v3.0 |
| Deactivate | `0x00000040` | v3.0 | v3.0 |

### Padding Method  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| None | `0x00000001` | v1.2 | v3.0 |
| OAEP | `0x00000002` | v1.2 | v3.0 |
| PKCS5 | `0x00000003` | v1.2 | v3.0 |
| SSL3 | `0x00000004` | v1.2 | v3.0 |
| Zeros | `0x00000005` | v1.2 | v3.0 |
| ANSI X9.23 | `0x00000006` | v1.2 | v3.0 |
| ISO 10126 | `0x00000007` | v1.2 | v3.0 |
| PKCS1 v1.5 | `0x00000008` | v1.2 | v3.0 |
| X9.31 | `0x00000009` | v1.2 | v3.0 |
| PSS | `0x0000000A` | v1.2 | v3.0 |

### Processing Stage  ✅  —  `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Submitted | `0x00000001` | v2.1 | v3.0 |
| In Process | `0x00000002` | v2.1 | v3.0 |
| Completed | `0x00000003` | v2.1 | v3.0 |

### Profile Name  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Baseline Server Basic KMIP v1.2 | `0x00000001` | v1.3 | v1.4 ⚠️ |
| Baseline Server TLS v1.2 KMIP v1.2 | `0x00000002` | v1.3 | v1.4 ⚠️ |
| Baseline Client Basic KMIP v1.2 | `0x00000003` | v1.3 | v1.4 ⚠️ |
| Baseline Client TLS v1.2 KMIP v1.2 | `0x00000004` | v1.3 | v1.4 ⚠️ |
| Complete Server Basic KMIP v1.2 | `0x00000005` | v1.3 | v1.4 ⚠️ |
| Complete Server TLS v1.2 KMIP v1.2 | `0x00000006` | v1.3 | v1.4 ⚠️ |
| Tape Library Client KMIP v1.0 | `0x00000007` | v1.3 | v1.4 ⚠️ |
| Tape Library Client KMIP v1.1 | `0x00000008` | v1.3 | v1.4 ⚠️ |
| Tape Library Client KMIP v1.2 | `0x00000009` | v1.3 | v1.4 ⚠️ |
| Tape Library Server KMIP v1.0 | `0x0000000A` | v1.3 | v1.4 ⚠️ |
| Tape Library Server KMIP v1.1 | `0x0000000B` | v1.3 | v1.4 ⚠️ |
| Tape Library Server KMIP v1.2 | `0x0000000C` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Client KMIP v1.0 | `0x0000000D` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Client KMIP v1.1 | `0x0000000E` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Client KMIP v1.2 | `0x0000000F` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Server KMIP v1.0 | `0x00000010` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Server KMIP v1.1 | `0x00000011` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Server KMIP v1.2 | `0x00000012` | v1.3 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Client KMIP v1.0 | `0x00000013` | v1.3 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Client KMIP v1.1 | `0x00000014` | v1.3 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Client KMIP v1.2 | `0x00000015` | v1.3 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Server KMIP v1.0 | `0x00000016` | v1.3 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Server KMIP v1.1 | `0x00000017` | v1.3 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Server KMIP v1.2 | `0x00000018` | v1.3 | v1.4 ⚠️ |
| Basic Cryptographic Client KMIP v1.2 | `0x00000019` | v1.3 | v1.4 ⚠️ |
| Basic Cryptographic Server KMIP v1.2 | `0x0000001A` | v1.3 | v1.4 ⚠️ |
| Advanced Cryptographic Client KMIP v1.2 | `0x0000001B` | v1.3 | v1.4 ⚠️ |
| Advanced Cryptographic Server KMIP v1.2 | `0x0000001C` | v1.3 | v1.4 ⚠️ |
| RNG Cryptographic Client KMIP v1.2 | `0x0000001D` | v1.3 | v1.4 ⚠️ |
| RNG Cryptographic Server KMIP v1.2 | `0x0000001E` | v1.3 | v1.4 ⚠️ |
| Basic Symmetric Key Foundry Client KMIP v1.0 | `0x0000001F` | v1.3 | v1.4 ⚠️ |
| Intermediate Symmetric Key Foundry Client KMIP v1.0 | `0x00000020` | v1.3 | v1.4 ⚠️ |
| Advanced Symmetric Key Foundry Client KMIP v1.0 | `0x00000021` | v1.3 | v1.4 ⚠️ |
| Basic Symmetric Key Foundry Client KMIP v1.1 | `0x00000022` | v1.3 | v1.4 ⚠️ |
| Intermediate Symmetric Key Foundry Client KMIP v1.1 | `0x00000023` | v1.3 | v1.4 ⚠️ |
| Advanced Symmetric Key Foundry Client KMIP v1.1 | `0x00000024` | v1.3 | v1.4 ⚠️ |
| Basic Symmetric Key Foundry Client KMIP v1.2 | `0x00000025` | v1.3 | v1.4 ⚠️ |
| Intermediate Symmetric Key Foundry Client KMIP v1.2 | `0x00000026` | v1.3 | v1.4 ⚠️ |
| Advanced Symmetric Key Foundry Client KMIP v1.2 | `0x00000027` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Foundry Server KMIP v1.0 | `0x00000028` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Foundry Server KMIP v1.1 | `0x00000029` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Foundry Server KMIP v1.2 | `0x0000002A` | v1.3 | v1.4 ⚠️ |
| Opaque Managed Object Store Client KMIP v1.0 | `0x0000002B` | v1.3 | v1.4 ⚠️ |
| Opaque Managed Object Store Client KMIP v1.1 | `0x0000002C` | v1.3 | v1.4 ⚠️ |
| Opaque Managed Object Store Client KMIP v1.2 | `0x0000002D` | v1.3 | v1.4 ⚠️ |
| Opaque Managed Object Store Server KMIP v1.0 | `0x0000002E` | v1.3 | v1.4 ⚠️ |
| Opaque Managed Object Store Server KMIP v1.1 | `0x0000002F` | v1.3 | v1.4 ⚠️ |
| Opaque Managed Object Store Server KMIP v1.2 | `0x00000030` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_128 Client KMIP v1.0 | `0x00000031` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_128 Client KMIP v1.1 | `0x00000032` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_128 Client KMIP v1.2 | `0x00000033` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_128 Server KMIP v1.0 | `0x00000034` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_128 Server KMIP v1.1 | `0x00000035` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_128 Server KMIP v1.2 | `0x00000036` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_192 Client KMIP v1.0 | `0x00000037` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_192 Client KMIP v1.1 | `0x00000038` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_192 Client KMIP v1.2 | `0x00000039` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_192 Server KMIP v1.0 | `0x0000003A` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_192 Server KMIP v1.1 | `0x0000003B` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_192 Server KMIP v1.2 | `0x0000003C` | v1.3 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Client KMIP v1.0 | `0x0000003D` | v1.3 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Client KMIP v1.1 | `0x0000003E` | v1.3 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Client KMIP v1.2 | `0x0000003F` | v1.3 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Server KMIP v1.0 | `0x00000040` | v1.3 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Server KMIP v1.1 | `0x00000041` | v1.3 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Server KMIP v1.2 | `0x00000042` | v1.3 | v1.4 ⚠️ |
| HTTPS Client KMIP v1.0 | `0x00000043` | v1.3 | v1.4 ⚠️ |
| HTTPS Client KMIP v1.1 | `0x00000044` | v1.3 | v1.4 ⚠️ |
| HTTPS Client KMIP v1.2 | `0x00000045` | v1.3 | v1.4 ⚠️ |
| HTTPS Server KMIP v1.0 | `0x00000046` | v1.3 | v1.4 ⚠️ |
| HTTPS Server KMIP v1.1 | `0x00000047` | v1.3 | v1.4 ⚠️ |
| HTTPS Server KMIP v1.2 | `0x00000048` | v1.3 | v1.4 ⚠️ |
| JSON Client KMIP v1.0 | `0x00000049` | v1.3 | v1.4 ⚠️ |
| JSON Client KMIP v1.1 | `0x0000004A` | v1.3 | v1.4 ⚠️ |
| JSON Client KMIP v1.2 | `0x0000004B` | v1.3 | v1.4 ⚠️ |
| JSON Server KMIP v1.0 | `0x0000004C` | v1.3 | v1.4 ⚠️ |
| JSON Server KMIP v1.1 | `0x0000004D` | v1.3 | v1.4 ⚠️ |
| JSON Server KMIP v1.2 | `0x0000004E` | v1.3 | v1.4 ⚠️ |
| XML Client KMIP v1.0 | `0x0000004F` | v1.3 | v1.4 ⚠️ |
| XML Client KMIP v1.1 | `0x00000050` | v1.3 | v1.4 ⚠️ |
| XML Client KMIP v1.2 | `0x00000051` | v1.3 | v1.4 ⚠️ |
| XML Server KMIP v1.0 | `0x00000052` | v1.3 | v1.4 ⚠️ |
| XML Server KMIP v1.1 | `0x00000053` | v1.3 | v1.4 ⚠️ |
| XML Server KMIP v1.2 | `0x00000054` | v1.3 | v1.4 ⚠️ |
| Baseline Server Basic KMIP v1.3 | `0x00000055` | v1.3 | v1.4 ⚠️ |
| Baseline Server TLS v1.2 KMIP v1.3 | `0x00000056` | v1.3 | v1.4 ⚠️ |
| Baseline Client Basic KMIP v1.3 | `0x00000057` | v1.3 | v1.4 ⚠️ |
| Baseline Client TLS v1.2 KMIP v1.3 | `0x00000058` | v1.3 | v1.4 ⚠️ |
| Complete Server Basic KMIP v1.3 | `0x00000059` | v1.3 | v1.4 ⚠️ |
| Complete Server TLS v1.2 KMIP v1.3 | `0x0000005A` | v1.3 | v1.4 ⚠️ |
| Tape Library Client KMIP v1.3 | `0x0000005B` | v1.3 | v1.4 ⚠️ |
| Tape Library Server KMIP v1.3 | `0x0000005C` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Client KMIP v1.3 | `0x0000005D` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Server KMIP v1.3 | `0x0000005E` | v1.3 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Client KMIP v1.3 | `0x0000005F` | v1.3 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Server KMIP v1.3 | `0x00000060` | v1.3 | v1.4 ⚠️ |
| Basic Cryptographic Client KMIP v1.3 | `0x00000061` | v1.3 | v1.4 ⚠️ |
| Basic Cryptographic Server KMIP v1.3 | `0x00000062` | v1.3 | v1.4 ⚠️ |
| Advanced Cryptographic Client KMIP v1.3 | `0x00000063` | v1.3 | v1.4 ⚠️ |
| Advanced Cryptographic Server KMIP v1.3 | `0x00000064` | v1.3 | v1.4 ⚠️ |
| RNG Cryptographic Client KMIP v1.3 | `0x00000065` | v1.3 | v1.4 ⚠️ |
| RNG Cryptographic Server KMIP v1.3 | `0x00000066` | v1.3 | v1.4 ⚠️ |
| Basic Symmetric Key Foundry Client KMIP v1.3 | `0x00000067` | v1.3 | v1.4 ⚠️ |
| Intermediate Symmetric Key Foundry Client KMIP v1.3 | `0x00000068` | v1.3 | v1.4 ⚠️ |
| Advanced Symmetric Key Foundry Client KMIP v1.3 | `0x00000069` | v1.3 | v1.4 ⚠️ |
| Symmetric Key Foundry Server KMIP v1.3 | `0x0000006A` | v1.3 | v1.4 ⚠️ |
| Opaque Managed Object Store Client KMIP v1.3 | `0x0000006B` | v1.3 | v1.4 ⚠️ |
| Opaque Managed Object Store Server KMIP v1.3 | `0x0000006C` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_128 Client KMIP v1.3 | `0x0000006D` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_128 Server KMIP v1.3 | `0x0000006E` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_192 Client KMIP v1.3 | `0x0000006F` | v1.3 | v1.4 ⚠️ |
| Suite B minLOS_192 Server KMIP v1.3 | `0x00000070` | v1.3 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Client KMIP v1.3 | `0x00000071` | v1.3 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Server KMIP v1.3 | `0x00000072` | v1.3 | v1.4 ⚠️ |
| HTTPS Client KMIP v1.3 | `0x00000073` | v1.3 | v1.4 ⚠️ |
| HTTPS Server KMIP v1.3 | `0x00000074` | v1.3 | v1.4 ⚠️ |
| JSON Client KMIP v1.3 | `0x00000075` | v1.3 | v1.4 ⚠️ |
| JSON Server KMIP v1.3 | `0x00000076` | v1.3 | v1.4 ⚠️ |
| XML Client KMIP v1.3 | `0x00000077` | v1.3 | v1.4 ⚠️ |
| XML Server KMIP v1.3 | `0x00000078` | v1.3 | v1.4 ⚠️ |
| Baseline Server Basic KMIP v1.4 | `0x00000079` | v1.4 | v1.4 ⚠️ |
| Baseline Server TLS v1.2 KMIP v1.4 | `0x0000007A` | v1.4 | v1.4 ⚠️ |
| Baseline Client Basic KMIP v1.4 | `0x0000007B` | v1.4 | v1.4 ⚠️ |
| Baseline Client TLS v1.2 KMIP v1.4 | `0x0000007C` | v1.4 | v1.4 ⚠️ |
| Complete Server Basic KMIP v1.4 | `0x0000007D` | v1.4 | v1.4 ⚠️ |
| Complete Server TLS v1.2 KMIP v1.4 | `0x0000007E` | v1.4 | v1.4 ⚠️ |
| Tape Library Client KMIP v1.4 | `0x0000007F` | v1.4 | v1.4 ⚠️ |
| Tape Library Server KMIP v1.4 | `0x00000080` | v1.4 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Client KMIP v1.4 | `0x00000081` | v1.4 | v1.4 ⚠️ |
| Symmetric Key Lifecycle Server KMIP v1.4 | `0x00000082` | v1.4 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Client KMIP v1.4 | `0x00000083` | v1.4 | v1.4 ⚠️ |
| Asymmetric Key Lifecycle Server KMIP v1.4 | `0x00000084` | v1.4 | v1.4 ⚠️ |
| Basic Cryptographic Client KMIP v1.4 | `0x00000085` | v1.4 | v1.4 ⚠️ |
| Basic Cryptographic Server KMIP v1.4 | `0x00000086` | v1.4 | v1.4 ⚠️ |
| Advanced Cryptographic Client KMIP v1.4 | `0x00000087` | v1.4 | v1.4 ⚠️ |
| Advanced Cryptographic Server KMIP v1.4 | `0x00000088` | v1.4 | v1.4 ⚠️ |
| RNG Cryptographic Client KMIP v1.4 | `0x00000089` | v1.4 | v1.4 ⚠️ |
| RNG Cryptographic Server KMIP v1.4 | `0x0000008A` | v1.4 | v1.4 ⚠️ |
| Basic Symmetric Key Foundry Client KMIP v1.4 | `0x0000008B` | v1.4 | v1.4 ⚠️ |
| Intermediate Symmetric Key Foundry Client KMIP v1.4 | `0x0000008C` | v1.4 | v1.4 ⚠️ |
| Advanced Symmetric Key Foundry Client KMIP v1.4 | `0x0000008D` | v1.4 | v1.4 ⚠️ |
| Symmetric Key Foundry Server KMIP v1.4 | `0x0000008E` | v1.4 | v1.4 ⚠️ |
| Opaque Managed Object Store Client KMIP v1.4 | `0x0000008F` | v1.4 | v1.4 ⚠️ |
| Opaque Managed Object Store Server KMIP v1.4 | `0x00000090` | v1.4 | v1.4 ⚠️ |
| Suite B minLOS_128 Client KMIP v1.4 | `0x00000091` | v1.4 | v1.4 ⚠️ |
| Suite B minLOS_128 Server KMIP v1.4 | `0x00000092` | v1.4 | v1.4 ⚠️ |
| Suite B minLOS_192 Client KMIP v1.4 | `0x00000093` | v1.4 | v1.4 ⚠️ |
| Suite B minLOS_192 Server KMIP v1.4 | `0x00000094` | v1.4 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Client KMIP v1.4 | `0x00000095` | v1.4 | v1.4 ⚠️ |
| Storage Array with Self Encrypting Drive Server KMIP v1.4 | `0x00000096` | v1.4 | v1.4 ⚠️ |
| HTTPS Client KMIP v1.4 | `0x00000097` | v1.4 | v1.4 ⚠️ |
| HTTPS Server KMIP v1.4 | `0x00000098` | v1.4 | v1.4 ⚠️ |
| JSON Client KMIP v1.4 | `0x00000099` | v1.4 | v1.4 ⚠️ |
| JSON Server KMIP v1.4 | `0x0000009A` | v1.4 | v1.4 ⚠️ |
| XML Client KMIP v1.4 | `0x0000009B` | v1.4 | v1.4 ⚠️ |
| XML Server KMIP v1.4 | `0x0000009C` | v1.4 | v1.4 ⚠️ |
| Complete Server Basic | `0x00000104` | v2.0 | v3.0 |
| Complete Server TLS v1.2 | `0x00000105` | v2.0 | v3.0 |
| Tape Library Client | `0x00000106` | v2.0 | v3.0 |
| Tape Library Server | `0x00000107` | v2.0 | v3.0 |
| Symmetric Key Lifecycle Client | `0x00000108` | v2.0 | v3.0 |
| Symmetric Key Lifecycle Server | `0x00000109` | v2.0 | v3.0 |
| Asymmetric Key Lifecycle Client | `0x0000010A` | v2.0 | v3.0 |
| Asymmetric Key Lifecycle Server | `0x0000010B` | v2.0 | v3.0 |
| Basic Cryptographic Client | `0x0000010C` | v2.0 | v3.0 |
| Basic Cryptographic Server | `0x0000010D` | v2.0 | v3.0 |
| Advanced Cryptographic Client | `0x0000010E` | v2.0 | v3.0 |
| Advanced Cryptographic Server | `0x0000010F` | v2.0 | v3.0 |
| RNG Cryptographic Client | `0x00000110` | v2.0 | v3.0 |
| RNG Cryptographic Server | `0x00000111` | v2.0 | v3.0 |
| Basic Symmetric Key Foundry Client | `0x00000112` | v2.0 | v3.0 |
| Intermediate Symmetric Key Foundry Client | `0x00000113` | v2.0 | v3.0 |
| Advanced Symmetric Key Foundry Client | `0x00000114` | v2.0 | v3.0 |
| Symmetric Key Foundry Server | `0x00000115` | v2.0 | v3.0 |
| Opaque Managed Object Store Client | `0x00000116` | v2.0 | v3.0 |
| Opaque Managed Object Store Server | `0x00000117` | v2.0 | v3.0 |
| Storage Array with Self Encrypting Drive Client | `0x0000011C` | v2.0 | v3.0 |
| Storage Array with Self Encrypting Drive Server | `0x0000011D` | v2.0 | v3.0 |
| HTTPS Client | `0x0000011E` | v2.0 | v3.0 |
| HTTPS Server | `0x0000011F` | v2.0 | v3.0 |
| JSON Client | `0x00000120` | v2.0 | v3.0 |
| JSON Server | `0x00000121` | v2.0 | v3.0 |
| XML Client | `0x00000122` | v2.0 | v3.0 |
| XML Server | `0x00000123` | v2.0 | v3.0 |
| AES XTS Client | `0x00000124` | v2.0 | v3.0 |
| AES XTS Server | `0x00000125` | v2.0 | v3.0 |
| Quantum Safe Client | `0x00000126` | v2.0 | v3.0 |
| Quantum Safe Server | `0x00000127` | v2.0 | v3.0 |
| PKCS#11 Client | `0x00000128` | v2.0 | v3.0 |
| PKCS#11 Server | `0x00000129` | v2.0 | v3.0 |
| Baseline Client | `0x0000012A` | v2.0 | v3.0 |
| Baseline Server | `0x0000012B` | v2.0 | v3.0 |
| Complete Server | `0x0000012C` | v2.0 | v3.0 |

### Protection Level  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| High | `0x00000001` | v2.0 | v3.0 |
| Low | `0x00000002` | v2.0 | v3.0 |

### Put Function  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| New | `0x00000001` | v1.2 | v3.0 |
| Replace | `0x00000002` | v1.2 | v3.0 |

### Query Function  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Query Operations | `0x00000001` | v1.2 | v3.0 |
| Query Objects | `0x00000002` | v1.2 | v3.0 |
| Query Server Information | `0x00000003` | v1.2 | v3.0 |
| Query Application Namespaces | `0x00000004` | v1.2 | v3.0 |
| Query Extension List | `0x00000005` | v1.2 | v3.0 |
| Query Extension Map | `0x00000006` | v1.2 | v3.0 |
| Query Attestation Types | `0x00000007` | v1.2 | v3.0 |
| Query RNGs | `0x00000008` | v1.3 | v3.0 |
| Query Validations | `0x00000009` | v1.3 | v3.0 |
| Query Profiles | `0x0000000A` | v1.3 | v3.0 |
| Query Capabilities | `0x0000000B` | v1.3 | v3.0 |
| Query Client Registration Methods | `0x0000000C` | v1.3 | v3.0 |
| Query Defaults Information | `0x0000000D` | v2.0 | v3.0 |
| Query Storage Protection Masks | `0x0000000E` | v2.0 | v3.0 |
| Query Credential Information | `0x0000000F` | v3.0 | v3.0 |

### RNG Algorithm  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| FIPS 186-2 | `0x00000002` | v1.3 | v3.0 |
| DRBG | `0x00000003` | v1.3 | v3.0 |
| NRBG | `0x00000004` | v1.3 | v3.0 |
| ANSI X9.31 | `0x00000005` | v1.3 | v3.0 |
| ANSI X9.62 | `0x00000006` | v1.3 | v3.0 |

### RNG Mode  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| Shared Instantiation | `0x00000002` | v1.3 | v3.0 |
| Non-Shared Instantiation | `0x00000003` | v1.3 | v3.0 |

### Recommended Curve  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| P-192 | `0x00000001` | v1.2 | v3.0 |
| K-163 | `0x00000002` | v1.2 | v3.0 |
| B-163 | `0x00000003` | v1.2 | v3.0 |
| P-224 | `0x00000004` | v1.2 | v3.0 |
| K-233 | `0x00000005` | v1.2 | v3.0 |
| B-233 | `0x00000006` | v1.2 | v3.0 |
| P-256 | `0x00000007` | v1.2 | v3.0 |
| K-283 | `0x00000008` | v1.2 | v3.0 |
| B-283 | `0x00000009` | v1.2 | v3.0 |
| P-384 | `0x0000000A` | v1.2 | v3.0 |
| K-409 | `0x0000000B` | v1.2 | v3.0 |
| B-409 | `0x0000000C` | v1.2 | v3.0 |
| P-521 | `0x0000000D` | v1.2 | v3.0 |
| K-571 | `0x0000000E` | v1.2 | v3.0 |
| B-571 | `0x0000000F` | v1.2 | v3.0 |
| SECP112R1 | `0x00000010` | v1.2 | v3.0 |
| SECP112R2 | `0x00000011` | v1.2 | v3.0 |
| SECP128R1 | `0x00000012` | v1.2 | v3.0 |
| SECP128R2 | `0x00000013` | v1.2 | v3.0 |
| SECP160K1 | `0x00000014` | v1.2 | v3.0 |
| SECP160R1 | `0x00000015` | v1.2 | v3.0 |
| SECP160R2 | `0x00000016` | v1.2 | v3.0 |
| SECP192K1 | `0x00000017` | v1.2 | v3.0 |
| SECP224K1 | `0x00000018` | v1.2 | v3.0 |
| SECP256K1 | `0x00000019` | v1.2 | v3.0 |
| SECT113R1 | `0x0000001A` | v1.2 | v3.0 |
| SECT113R2 | `0x0000001B` | v1.2 | v3.0 |
| SECT131R1 | `0x0000001C` | v1.2 | v3.0 |
| SECT131R2 | `0x0000001D` | v1.2 | v3.0 |
| SECT163R1 | `0x0000001E` | v1.2 | v3.0 |
| SECT193R1 | `0x0000001F` | v1.2 | v3.0 |
| SECT193R2 | `0x00000020` | v1.2 | v3.0 |
| SECT239K1 | `0x00000021` | v1.2 | v3.0 |
| ANSIX9P192V2 | `0x00000022` | v1.2 | v3.0 |
| ANSIX9P192V3 | `0x00000023` | v1.2 | v3.0 |
| ANSIX9P239V1 | `0x00000024` | v1.2 | v3.0 |
| ANSIX9P239V2 | `0x00000025` | v1.2 | v3.0 |
| ANSIX9P239V3 | `0x00000026` | v1.2 | v3.0 |
| ANSIX9C2PNB163V1 | `0x00000027` | v1.2 | v3.0 |
| ANSIX9C2PNB163V2 | `0x00000028` | v1.2 | v3.0 |
| ANSIX9C2PNB163V3 | `0x00000029` | v1.2 | v3.0 |
| ANSIX9C2PNB176V1 | `0x0000002A` | v1.2 | v3.0 |
| ANSIX9C2TNB191V1 | `0x0000002B` | v1.2 | v3.0 |
| ANSIX9C2TNB191V2 | `0x0000002C` | v1.2 | v3.0 |
| ANSIX9C2TNB191V3 | `0x0000002D` | v1.2 | v3.0 |
| ANSIX9C2PNB208W1 | `0x0000002E` | v1.2 | v3.0 |
| ANSIX9C2TNB239V1 | `0x0000002F` | v1.2 | v3.0 |
| ANSIX9C2TNB239V2 | `0x00000030` | v1.2 | v3.0 |
| ANSIX9C2TNB239V3 | `0x00000031` | v1.2 | v3.0 |
| ANSIX9C2PNB272W1 | `0x00000032` | v1.2 | v3.0 |
| ANSIX9C2PNB304W1 | `0x00000033` | v1.2 | v3.0 |
| ANSIX9C2TNB359V1 | `0x00000034` | v1.2 | v3.0 |
| ANSIX9C2PNB368W1 | `0x00000035` | v1.2 | v3.0 |
| ANSIX9C2TNB431R1 | `0x00000036` | v1.2 | v3.0 |
| BRAINPOOLP160R1 | `0x00000037` | v1.2 | v3.0 |
| BRAINPOOLP160T1 | `0x00000038` | v1.2 | v3.0 |
| BRAINPOOLP192R1 | `0x00000039` | v1.2 | v3.0 |
| BRAINPOOLP192T1 | `0x0000003A` | v1.2 | v3.0 |
| BRAINPOOLP224R1 | `0x0000003B` | v1.2 | v3.0 |
| BRAINPOOLP224T1 | `0x0000003C` | v1.2 | v3.0 |
| BRAINPOOLP256R1 | `0x0000003D` | v1.2 | v3.0 |
| BRAINPOOLP256T1 | `0x0000003E` | v1.2 | v3.0 |
| BRAINPOOLP320R1 | `0x0000003F` | v1.2 | v3.0 |
| BRAINPOOLP320T1 | `0x00000040` | v1.2 | v3.0 |
| BRAINPOOLP384R1 | `0x00000041` | v1.2 | v3.0 |
| BRAINPOOLP384T1 | `0x00000042` | v1.2 | v3.0 |
| BRAINPOOLP512R1 | `0x00000043` | v1.2 | v3.0 |
| BRAINPOOLP512T1 | `0x00000044` | v1.2 | v3.0 |
| CURVE25519 | `0x00000045` | v2.0 | v3.0 |
| CURVE448 | `0x00000046` | v2.0 | v3.0 |

### Result Reason  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Invalid Password | `` | v2.0 | v3.0 |
| Item Not Found | `0x00000001` | v1.2 | v3.0 |
| Response Too Large | `0x00000002` | v1.2 | v3.0 |
| Authentication Not Successful | `0x00000003` | v1.2 | v1.4 ⚠️ |
| Invalid Message | `0x00000004` | v1.2 | v3.0 |
| Operation Not Supported | `0x00000005` | v1.2 | v3.0 |
| Missing Data | `0x00000006` | v1.2 | v1.4 ⚠️ |
| Invalid Field | `0x00000007` | v1.2 | v3.0 |
| Feature Not Supported | `0x00000008` | v1.2 | v3.0 |
| Operation Canceled By Requester | `0x00000009` | v1.2 | v1.4 ⚠️ |
| Cryptographic Failure | `0x0000000A` | v1.2 | v3.0 |
| Illegal Operation | `0x0000000B` | v1.2 | v1.4 ⚠️ |
| Permission Denied | `0x0000000C` | v1.2 | v3.0 |
| Object archived | `0x0000000D` | v1.2 | v1.4 ⚠️ |
| Index Out of Bounds | `0x0000000E` | v1.2 | v1.4 ⚠️ |
| Application Namespace Not Supported | `0x0000000F` | v1.2 | v1.4 ⚠️ |
| Key Format Type Not Supported | `0x00000010` | v1.2 | v3.0 |
| Key Compression Type Not Supported | `0x00000011` | v1.2 | v3.0 |
| Encoding Option Error | `0x00000012` | v1.2 | v3.0 |
| Key Value Not Present | `0x00000013` | v1.2 | v3.0 |
| Attestation Required | `0x00000014` | v1.2 | v3.0 |
| Attestation Failed | `0x00000015` | v1.2 | v3.0 |
| Sensitive | `0x00000016` | v1.4 | v3.0 |
| Not Extractable | `0x00000017` | v1.4 | v3.0 |
| Object Already Exists | `0x00000018` | v1.4 | v3.0 |
| General Failure | `0x00000100` | v1.2 | v1.4 ⚠️ |
| Unknown Tag | `0x00AABEEE` | v2.0 | v3.0 |
| Invalid Ticket | `0x00ECEAAD` | v2.0 | v3.0 |
| Wrapping Object Not Found | `0x0ABECDEE` | v2.0 | v3.0 |
| Wrapping Object Archived | `0xABECACED` | v2.0 | v3.0 |
| Missing Initialization Vector | `0xEEEDFCEA` | v2.0 | v3.0 |
| Invalid Correlation Value | `0xFEACACEA` | v2.0 | v3.0 |
| Unknown Object Group | `<insert>` | v2.1 | v2.1 ⚠️ |
| Circular Link Error | `A ParentLink sets up a directed acyclic relationship. Detection of a cycle in the relationship graph results in this reason code.` | v3.0 | v3.0 |
| Invalid Data Type | `A data type was invalid for the requested operation` | v2.0 | v3.0 |
| Attribute Instance Not Found | `A referenced attribute was found, but the specific instance was not found` | v2.0 | v3.0 |
| Attribute Not Found | `A referenced attribute was not found at all on an object` | v2.0 | v3.0 |
| Object Not Found | `A requested managed object was not found or did not exist` | v2.0 | v3.0 |
| Invalid Attribute | `An attribute is invalid for this object for this operation` | v2.0 | v3.0 |
| Unknown Enumeration | `An enumerated value is not known by the server` | v2.0 | v3.0 |
| Numeric Range | `An operation produced a number that is to large or too small to be stored in the specified data type` | v2.0 | v3.0 |
| Multi Valued Attribute | `Attempt to Set or Adjust an attribute that has multiple values` | v2.0 | v3.0 |
| Attribute Single Instance | `Attempt to provide multiple values for a single instance attribute` | v2.0 | v3.0 |
| Attribute Read Only | `Attempt to set a Read Only Attribute` | v2.0 | v3.0 |
| Read Only Attribute | `Attempt to set a Read Only Attribute` | v2.0 | v3.0 |
| Unsupported Attribute | `Attribute is valid in the specification but unsupported by the Server` | v2.0 | v3.0 |
| Bad Cryptographic Parameters | `Bad Cryptographic Parameters` | v2.0 | v3.0 |
| Illegal Object Type | `Check cannot be performed on this object type` | v2.0 | v3.0 |
| Unsupported Cryptographic Parameters | `Cryptographic Parameters are valid in the specification but unsupported by the Server` | v2.0 | v3.0 |
| Invalid CSR | `Invalid Certifcate Signing Request` | v2.0 | v3.0 |
| Object Type | `Invalid object type for the operation` | v2.0 | v3.0 |
| Bad Password | `Key Format Type is PKCS#12, but missing or multiple PKCS#12 Password Links, or not Secret Data, or not Active` | v2.0 | v3.0 |
| Key Wrap Type Not Supported | `Key Wrap Type Type is not supported by the server` | v2.0 | v3.0 |
| Invalid Asynchronous Correlation Value | `No outstanding operation with the specified Asynchronous Correlation Value exists` | v2.0 | v3.0 |
| Object Destroyed | `Object exists, but has already been destroyed` | v2.0 | v3.0 |
| Server Limit Exceeded | `Some limit on the server such as database size has been exceeded` | v2.0 | v3.0 |
| Invalid Object Type | `Specificed object is not valid for the requested operation` | v2.0 | v3.0 |
| PKCS#11 Invalid Function | `The PKCS function is not in the interface` | v2.0 | v3.0 |
| Duplicate Process Request | `The asynchronous request specified was already processed` | v2.1 | v3.0 |
| Authentication not successful | `The authentication information in the request could not be validated, or was not found` | v2.0 | v3.0 |
| Incompatible Cryptographic Usage Mask | `The cryptographic algorithm or other parameters is not valid for the requested operation` | v2.0 | v3.0 |
| PKCS#11 Invalid Interface | `The interface is unknown or unavailable in the server` | v2.0 | v3.0 |
| Wrong Key Lifecycle State | `The key lifecycle state is invalid for the operation, for example not Active for an Encrypt operation` | v2.0 | v3.0 |
| Codec Error | `The low level TTLV, XML, JSON etc. was badly formed and not understood by the server.TTLV connections should be closed as future requests might not be correctly separated` | v2.0 | v3.0 |
| Object Archived | `The object SHALL be recovered from the archive before performing the operation` | v2.0 | v3.0 |
| Wrapping Object Destroyed | `The object exists, but is destroyed` | v2.0 | v3.0 |
| Missing data | `The operation REQUIRED additional information in the request, which was not present` | v2.0 | v3.0 |
| Unsupported Protocol Version | `The operation cannot be performed with the provided protocol version` | v2.0 | v3.0 |
| Protection Storage Unavailable, Private Protection Storage Unavailable, Public Protection Storage Unavailable | `The operation could not be completed with the protections requested (or defaulted).` | v2.0 | v3.0 |
| Operation canceled by requester | `The operation was asynchronous, and the operation was canceled by the Cancel operation before it completed successfully` | v2.0 | v3.0 |
| Constraint Violation | `The request failed because one or more constraints were violated` | v2.1 | v3.0 |
| General failure | `The request failed for a reason other than the defined reasons above` | v2.0 | v3.0 |
| Unknown Message Extension | `The server does not support the supplied Message Extension` | v2.0 | v3.0 |
| Internal Server Error | `The server had an internal error and could not process the request at this time.` | v2.0 | v3.0 |
| Usage Limit Exceeded | `The usage limits or request count has been exceeded` | v2.0 | v3.0 |
| Invalid Attribute Value | `The value supplied for an attribute is invalid` | v2.0 | v3.0 |
| PKCS#11 Codec Error | `There is a Codec error in the Input parameter` | v2.0 | v3.0 |
| Non Unique Name Attribute | `Trying to perform an operation that requests the server to break the constraint on Name attribute being unique` | v2.0 | v3.0 |

### Result Status  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Success | `0x00000000` | v1.2 | v3.0 |
| Operation Failed | `0x00000001` | v1.2 | v3.0 |
| Operation Pending | `0x00000002` | v1.2 | v3.0 |
| Operation Undone | `0x00000003` | v1.2 | v3.0 |

### Revocation Reason Code  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.2 | v3.0 |
| Key Compromise | `0x00000002` | v1.2 | v3.0 |
| CA Compromise | `0x00000003` | v1.2 | v3.0 |
| Affiliation Changed | `0x00000004` | v1.2 | v3.0 |
| Superseded | `0x00000005` | v1.2 | v3.0 |
| Cessation of Operation | `0x00000006` | v1.2 | v3.0 |
| Privilege Withdrawn | `0x00000007` | v1.2 | v3.0 |

### Rotate Name Type  ✅  —  `v2.1`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Uninterpreted Text String | `0x00000001` | v2.1 | v2.1 ⚠️ |
| URI | `0x00000002` | v2.1 | v2.1 ⚠️ |

### Secret Data Type  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Password | `0x00000001` | v1.2 | v3.0 |
| Seed | `0x00000002` | v1.2 | v3.0 |

### Shredding Algorithm  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| Cryptographic | `0x00000002` | v1.3 | v3.0 |
| Unsupported | `0x00000003` | v1.3 | v3.0 |

### Split Key Method  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| XOR | `0x00000001` | v1.2 | v3.0 |
| Polynomial Sharing GF (216) | `0x00000002` | v1.2 | v3.0 |
| Polynomial Sharing Prime Field | `0x00000003` | v1.2 | v3.0 |
| Polynomial Sharing GF (28) | `0x00000004` | v1.2 | v3.0 |

### Split Key Polynomial  ✅  —  `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Polynomial-283 | `0x00000001` | v3.0 | v3.0 |
| Polynomial-285 | `0x00000002` | v3.0 | v3.0 |

### State  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Pre-Active | `0x00000001` | v1.2 | v3.0 |
| Active | `0x00000002` | v1.2 | v3.0 |
| Deactivated | `0x00000003` | v1.2 | v3.0 |
| Compromised | `0x00000004` | v1.2 | v3.0 |
| Destroyed | `0x00000005` | v1.2 | v3.0 |
| Destroyed Compromised | `0x00000006` | v1.2 | v3.0 |

### Ticket Type  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Login | `0x00000001` | v2.0 | v3.0 |

### Unique Identifier  ✅  —  `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| ID Placeholder | `0x00000001` | v2.0 | v3.0 |
| Certify | `0x00000002` | v2.0 | v3.0 |
| Create | `0x00000003` | v2.0 | v3.0 |
| Create Key Pair | `0x00000004` | v2.0 | v3.0 |
| Create Key Pair Private Key | `0x00000005` | v2.0 | v3.0 |
| Create Key Pair Public Key | `0x00000006` | v2.0 | v3.0 |
| Create Split Key | `0x00000007` | v2.0 | v3.0 |
| Derive Key | `0x00000008` | v2.0 | v3.0 |
| Import | `0x00000009` | v2.0 | v3.0 |
| Join Split Key | `0x0000000A` | v2.0 | v3.0 |
| Locate | `0x0000000B` | v2.0 | v3.0 |
| Register | `0x0000000C` | v2.0 | v3.0 |
| Re-key | `0x0000000D` | v2.0 | v3.0 |
| Re-certify | `0x0000000E` | v2.0 | v3.0 |
| Re-key Key Pair | `0x0000000F` | v2.0 | v3.0 |
| Re-key Key Pair Private Key | `0x00000010` | v2.0 | v3.0 |
| Re-key Key Pair Public Key | `0x00000011` | v2.0 | v3.0 |
| Re-Provision | `0x00000012` | v3.0 | v3.0 |
| Create User | `0x00000013` | v3.0 | v3.0 |
| Create Group | `0x00000014` | v3.0 | v3.0 |
| Create Credential | `0x00000015` | v3.0 | v3.0 |

### Unwrap Mode  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| Processed | `0x00000002` | v1.3 | v3.0 |
| Not Processed | `0x00000003` | v1.3 | v3.0 |

### Usage Limits Unit  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Byte | `0x00000001` | v1.2 | v3.0 |
| Object | `0x00000002` | v1.2 | v3.0 |

### Validation Authority Type  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| NIST CMVP | `0x00000002` | v1.3 | v3.0 |
| Common Criteria | `0x00000003` | v1.3 | v3.0 |

### Validation Type  ✅  —  `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Unspecified | `0x00000001` | v1.3 | v3.0 |
| Hardware | `0x00000002` | v1.3 | v3.0 |
| Software | `0x00000003` | v1.3 | v3.0 |
| Firmware | `0x00000004` | v1.3 | v3.0 |
| Hybrid | `0x00000005` | v1.3 | v3.0 |

### Validity Indicator  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| Valid | `0x00000001` | v1.2 | v3.0 |
| Invalid | `0x00000002` | v1.2 | v3.0 |
| Unknown | `0x00000003` | v1.2 | v3.0 |

### Wrapping Method  ✅  —  `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`

| Value Name | Hex | First Seen | Last Seen |
|---|---|---|---|
| MAC/sign then encrypt. | `` | v2.0 | v3.0 |
| Encrypt | `0x00000001` | v1.2 | v1.4 ⚠️ |
| MAC/sign | `0x00000002` | v1.2 | v1.4 ⚠️ |
| Encrypt then MAC/sign | `0x00000003` | v1.2 | v3.0 |
| MAC/sign then encrypt | `0x00000004` | v1.2 | v1.4 ⚠️ |
| TR-31 | `0x00000005` | v1.2 | v3.0 |
| MAC/sign only | `either MACing the Key Value with a symmetric key, or signing the Key Value with a private key` | v2.0 | v3.0 |

---

_End of document._
