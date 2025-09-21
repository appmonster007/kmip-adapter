# KMIP v3.0 Specification Enumerations

Extracted from HTML specification document.

## Adjustment Type Enumeration

TheAdjustment Typeenumerations are:

| Name | Value |
| --- | --- |
| Increment | 00000001 |
| Decrement | 00000002 |
| Negate | 00000003 |
| Extensions | 8XXXXXXX |

---

## Alternative Name Type Enumeration

Table529: Alternative Name Type Enumeration

| Name | Value |
| --- | --- |
| Uninterpreted Text String | 00000001 |
| URI | 00000002 |
| Object Serial Number | 00000003 |
| Email Address | 00000004 |
| DNS Name | 00000005 |
| X.500 Distinguished Name | 00000006 |
| IP Address | 00000007 |
| Extensions | 8XXXXXXX |

---

## Asynchronous Indicator Enumeration

Asynchronous Indicatorenumerations are:

| Name | Value |
| --- | --- |
| Mandatory | 00000001 |
| Optional | 00000002 |
| Prohibited | 00000003 |
| Extensions | 8XXXXXXX |

---

## Attestation Type Enumeration

Table532: Attestation Type Enumeration

| Name | Value |
| --- | --- |
| TPM Quote | 00000001 |
| TCG Integrity Report | 00000002 |
| SAML Assertion | 00000003 |
| Extensions | 8XXXXXXX |

---

## Batch Error Continuation Option Enumeration

Batch Error Continuation Optionenumerations are:

| Name | Value |
| --- | --- |
| Continue | 00000001 |
| Stop | 00000002 |
| Undo | 00000003 |
| Extensions | 8XXXXXXX |

---

## Block Cipher Mode Enumeration

Table535: Block Cipher Mode Enumeration

| Name | Value |
| --- | --- |
| CBC | 00000001 |
| ECB | 00000002 |
| PCBC | 00000003 |
| CFB | 00000004 |
| OFB | 00000005 |
| CTR | 00000006 |
| CMAC | 00000007 |
| CCM | 00000008 |
| GCM | 00000009 |
| CBC-MAC | 0000000A |
| XTS | 0000000B |
| AESKeyWrapPadding | 0000000C |
| NISTKeyWrap | 0000000D |
| X9.102 AESKW | 0000000E |
| X9.102 TDKW | 0000000F |
| X9.102 AKW1 | 00000010 |
| X9.102 AKW2 | 00000011 |
| AEAD | 00000012 |
| Extensions | 8XXXXXXX |

---

## Cancellation Result Enumeration

ACancellation Resultenumerationsare:

| Name | Value |
| --- | --- |
| Canceled | 00000001 |
| Unable to Cancel | 00000002 |
| Completed | 00000003 |
| Failed | 00000004 |
| Unavailable | 00000005 |
| Extensions | 8XXXXXXX |

---

## Certificate Request Type Enumeration

Table538: Certificate Request Type Enumeration

| Name | Value |
| --- | --- |
| CRMF | 00000001 |
| PKCS#10 | 00000002 |
| PEM | 00000003 |
| (Reserved) | 00000004 |
| Extensions | 8XXXXXXX |

---

## Certificate Type Enumeration

Table539: Certificate Type Enumeration

| Name | Value |
| --- | --- |
| X.509 | 00000001 |
| PGP | 00000002 |
| Extensions | 8XXXXXXX |

---

## Client Registration Method Enumeration

Client Registration Methodenumerations are:

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| ServerPre-Generated | 00000002 |
| Server On-Demand | 00000003 |
| Client Generated | 00000004 |
| Client Registered | 00000005 |
| Extensions | 8XXXXXXX |

---

## Credential Type Enumeration

Table542: Credential Type Enumeration

| Name | Value |
| --- | --- |
| Username and Password | 00000001 |
| Device | 00000002 |
| Attestation | 00000003 |
| One Time Password | 00000004 |
| Hashed Password | 00000005 |
| Ticket | 00000006 |
| Password | 00000007 |
| Certificate | 00000008 |
| Extensions | 8XXXXXXX |

---

## Cryptographic Algorithm Enumeration

Table543: Cryptographic Algorithm Enumeration

| Name | Value |
| --- | --- |
| DES | 00000001 |
| 3DES | 00000002 |
| AES | 00000003 |
| RSA | 00000004 |
| DSA | 00000005 |
| ECDSA | 00000006 |
| HMAC-SHA1 | 00000007 |
| HMAC-SHA224 | 00000008 |
| HMAC-SHA256 | 00000009 |
| HMAC-SHA384 | 0000000A |
| HMAC-SHA512 | 0000000B |
| HMAC-MD5 | 0000000C |
| DH | 0000000D |
| ECDH | 0000000E |
| ECMQV | 0000000F |
| Blowfish | 00000010 |
| Camellia | 00000011 |
| CAST5 | 00000012 |
| IDEA | 00000013 |
| MARS | 00000014 |
| RC2 | 00000015 |
| RC4 | 00000016 |
| RC5 | 00000017 |
| SKIPJACK | 00000018 |
| Twofish | 00000019 |
| EC | 0000001A |
| One Time Pad | 0000001B |
| ChaCha20 | 0000001C |
| Poly1305 | 0000001D |
| ChaCha20Poly1305 | 0000001E |
| SHA3-224 | 0000001F |
| SHA3-256 | 00000020 |
| SHA3-384 | 00000021 |
| SHA3-512 | 00000022 |
| HMAC-SHA3-224 | 00000023 |
| HMAC-SHA3-256 | 00000024 |
| HMAC-SHA3-384 | 00000025 |
| HMAC-SHA3-512 | 00000026 |
| SHAKE-128 | 00000027 |
| SHAKE-256 | 00000028 |
| ARIA | 00000029 |
| SEED | 0000002A |
| SM2 | 0000002B |
| SM3 | 0000002C |
| SM4 | 0000002D |
| GOST R 34.10-2012 | 0000002E |
| GOST R 34.11-2012 | 0000002F |
| GOST R 34.13-2015 | 00000030 |
| GOST 28147-89 | 00000031 |
| XMSS | 00000032 |
| SPHINCS-256 | 00000033 |
| McEliece | 00000034 |
| McEliece-6960119 | 00000035 |
| McEliece-8192128 | 00000036 |
| Ed25519 | 00000037 |
| Ed448 | 00000038 |
| ML-KEM-512 | 00000039 |
| ML-KEM-768 | 0000003A |
| ML-KEM-1024 | 0000003B |
| ML-DSA-44 | 0000003C |
| ML-DSA-65 | 0000003D |
| ML-DSA-87 | 0000003E |
| SLH-DSA-SHA2-128s | 0000003F |
| SLH-DSA-SHA2-128f | 00000040 |
| SLH-DSA-SHA2-192s | 00000041 |
| SLH-DSA-SHA2-192f | 00000042 |
| SLH-DSA-SHA2-256s | 00000043 |
| SLH-DSA-SHA2-256f | 00000044 |
| SLH-DSA-SHAKE-128s | 00000045 |
| SLH-DSA-SHAKE-128f | 00000046 |
| SLH-DSA-SHAKE-192s | 00000047 |
| SLH-DSA-SHAKE-192f | 00000048 |
| SLH-DSA-SHAKE-256s | 00000049 |
| SLH-DSA-SHAKE-256f | 0000004A |
| Extensions | 8XXXXXXX |

---

## Data Enumeration

Table544: Data Enumeration

| Name | Value |
| --- | --- |
| Decrypt | 00000001 |
| Encrypt | 00000002 |
| Hash | 00000003 |
| MACMACData | 00000004 |
| RNG Retrieve | 00000005 |
| Sign Signature Data | 00000006 |
| Signature Verify | 00000007 |
| Extensions | 8XXXXXXX |

---

## Deactivation Reason Code Enumeration

Table545: Deactivation Reason Code Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| Deactivation Date | 00000002 |
| Protect Stop Date | 00000003 |
| Usage Limit | 00000004 |
| Extensions | 8XXXXXXX |

---

## Derivation Method Enumeration

TheDerivation Methodenumerations are:

| Name | Value |
| --- | --- |
| PBKDF2 | 00000001 |
| HASH | 00000002 |
| HMAC | 00000003 |
| ENCRYPT | 00000004 |
| NIST800-108-C | 00000005 |
| NIST800-108-F | 00000006 |
| NIST800-108-DPI | 00000007 |
| Asymmetric Key | 00000008 |
| AWS Signature Version 4 | 00000009 |
| HKDF | 0000000A |
| Extensions | 8XXXXXXX |

---

## Destroy Action Enumeration

Table548: Destroy Action Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| Key Material Deleted | 00000002 |
| Key Material Shredded | 00000003 |
| Meta Data Deleted | 00000004 |
| Meta Data Shredded | 00000005 |
| Deleted | 00000006 |
| Shredded | 00000007 |
| Extensions | 8XXXXXXX |

---

## Digital Signature Algorithm Enumeration

Table549: Digital Signature Algorithm Enumeration

| Name | Value |
| --- | --- |
| MD2 with RSA Encryption | 00000001 |
| MD5 with RSA Encryption | 00000002 |
| SHA-1 with RSA Encryption | 00000003 |
| SHA-224 with RSA Encryption | 00000004 |
| SHA-256 with RSA Encryption | 00000005 |
| SHA-384 with RSA Encryption | 00000006 |
| SHA-512 with RSA Encryption | 00000007 |
| RSASSA-PSS | 00000008 |
| DSA with SHA-1 | 00000009 |
| DSA with SHA224 | 0000000A |
| DSA with SHA256 | 0000000B |
| ECDSA with SHA-1 | 0000000C |
| ECDSA with SHA224 | 0000000D |
| ECDSA with SHA256 | 0000000E |
| ECDSA with SHA384 | 0000000F |
| ECDSA with SHA512 | 00000010 |
| SHA3-256 with RSA Encryption | 00000011 |
| SHA3-384 with RSA Encryption | 00000012 |
| SHA3-512 with RSA Encryption | 00000013 |
| Extensions | 8XXXXXXX |

---

## DRBG Algorithm Enumeration

Table550: DRGB Algorithm Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| Dual-EC | 00000002 |
| Hash | 00000003 |
| HMAC | 00000004 |
| CTR | 00000005 |
| Extensions | 8XXXXXXX |

---

## Encoding Option Enumeration

The following encoding options are currently defined:

| Name | Value |
| --- | --- |
| No Encoding | 00000001 |
| TTLV Encoding | 00000002 |
| Extensions | 8XXXXXXX |

---

## Endpoint Role Enumeration

The following endpoint roles are currently defined:

| Name | Value |
| --- | --- |
| Client | 00000001 |
| Server | 00000002 |
| Extensions | 8XXXXXXX |

---

## Ephemeral Enumeration

The following ephemeral options are currently defined:

| Name | Value |
| --- | --- |
| Data | 00000001 |
| Empty | 00000002 |
| Unique Identifier | 00000003 |
| Extensions | 8XXXXXXX |

---

## FIPS186 Variation Enumeration

Table557: FIPS186 Variation Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| GP x-Original | 00000002 |
| GP x-Change Notice | 00000003 |
| x-Original | 00000004 |
| x-Change Notice | 00000005 |
| k-Original | 00000006 |
| k-Change Notice | 00000007 |
| Extensions | 8XXXXXXX |

---

## Hashing Algorithm Enumeration

Table558: Hashing Algorithm Enumeration

| Name | Value |
| --- | --- |
| MD2 | 00000001 |
| MD4 | 00000002 |
| MD5 | 00000003 |
| SHA-1 | 00000004 |
| SHA-224 | 00000005 |
| SHA-256 | 00000006 |
| SHA-384 | 00000007 |
| SHA-512 | 00000008 |
| RIPEMD-160 | 00000009 |
| Tiger | 0000000A |
| Whirlpool | 0000000B |
| SHA-512/224 | 0000000C |
| SHA-512/256 | 0000000D |
| SHA3-224 | 0000000E |
| SHA3-256 | 0000000F |
| SHA3-384 | 00000010 |
| SHA3-512 | 00000011 |
| Extensions | 8XXXXXXX |

---

## Interop Function Enumeration

Interop Functionenumerations are:

| Name | Value |
| --- | --- |
| Begin | 00000001 |
| End | 00000002 |
| Reset | 00000003 |
| Extensions | 8XXXXXXX |

---

## Item Type Enumeration

Item Typeenumerations are:

| Name | Value |
| --- | --- |
| Structure | 00000001 |
| Integer | 00000002 |
| Long Integer | 00000003 |
| Big Integer | 00000004 |
| Enumeration | 00000005 |
| Boolean | 00000006 |
| Text String | 00000007 |
| Byte String | 00000008 |
| Date Time | 00000009 |
| Interval | 0000000A |
| Date Time Extended | 0000000B |
| Identifier | 0000000C |
| Reference | 0000000D |
| Name Reference | 0000000E |

---

## Key Compression Type Enumeration

Table563: Key Compression Type Enumeration values

| Name | Value |
| --- | --- |
| EC Public Key Type Uncompressed | 00000001 |
| EC Public Key Type X9.62 Compressed Prime | 00000002 |
| EC Public Key Type X9.62 Compressed Char2 | 00000003 |
| EC Public Key Type X9.62 Hybrid | 00000004 |
| Extensions | 8XXXXXXX |

---

## Key Format Type Enumeration

AKey Blockcontains a Key Value of one of the followingKey Format Types:

| Name | Value |
| --- | --- |
| Raw | 00000001 |
| Opaque | 00000002 |
| PKCS#1 | 00000003 |
| PKCS#8 | 00000004 |
| X.509 | 00000005 |
| ECPrivateKey | 00000006 |
| Transparent Symmetric Key | 00000007 |
| Transparent DSA Private Key | 00000008 |
| Transparent DSA Public Key | 00000009 |
| Transparent RSA Private Key | 0000000A |
| Transparent RSA Public Key | 0000000B |
| Transparent DH Private Key | 0000000C |
| Transparent DH Public Key | 0000000D |
| (Reserved) | 0000000E |
| (Reserved) | 0000000F |
| (Reserved) | 00000010 |
| (Reserved) | 00000011 |
| (Reserved) | 00000012 |
| (Reserved) | 00000013 |
| Transparent EC Private Key | 00000014 |
| Transparent EC Public Key | 00000015 |
| PKCS#12 | 00000016 |
| PKCS#10 | 00000017 |
| Extensions | 8XXXXXXX |

---

## Key Role Type Enumeration

Table566: Key Role Type Enumeration

| Name | Value |
| --- | --- |
| BDK | 00000001 |
| CVK | 00000002 |
| DEK | 00000003 |
| MKAC | 00000004 |
| MKSMC | 00000005 |
| MKSMI | 00000006 |
| MKDAC | 00000007 |
| MKDN | 00000008 |
| MKCP | 00000009 |
| MKOTH | 0000000A |
| KEK | 0000000B |
| MAC16609 | 0000000C |
| MAC97971 | 0000000D |
| MAC97972 | 0000000E |
| MAC97973 | 0000000F |
| MAC97974 | 00000010 |
| MAC97975 | 00000011 |
| ZPK | 00000012 |
| PVKIBM | 00000013 |
| PVKPVV | 00000014 |
| PVKOTH | 00000015 |
| DUKPT | 00000016 |
| IV | 00000017 |
| TRKBK | 00000018 |
| Extensions | 8XXXXXXX |

---

## Key Value Location Type Enumeration

Table567: Key Value Location Type Enumeration

| Name | Value |
| --- | --- |
| Uninterpreted Text String | 00000001 |
| URI | 00000002 |
| Extensions | 8XXXXXXX |

---

## Key Wrap Type Enumeration

Table568: Key Wrap Enumeration

| Name | Value |
| --- | --- |
| Not Wrapped | 00000001 |
| As Registered | 00000002 |
| Extensions | 8XXXXXXX |

---

## Mask Generator Enumeration

Table569: Name Type Enumeration

| Name | Value |
| --- | --- |
| MFG1 | 00000001 |
| Extensions | 8XXXXXXX |

---

## NIST Key Type Enumeration

Table570:NIST Key Type Enumeration

| Name | Value |
| --- | --- |
| Private signature key | 00000001 |
| Public signature verification key | 00000002 |
| Symmetric authentication key | 00000003 |
| Private authentication key | 00000004 |
| Public authentication key | 00000005 |
| Symmetric data encryption key | 00000006 |
| Symmetric key wrapping key | 00000007 |
| Symmetric random number generation key | 00000008 |
| Symmetric master key | 00000009 |
| Private key transport key | 0000000A |
| Public key transport key | 0000000B |
| Symmetric key agreement key | 0000000C |
| Private static key agreement key | 0000000D |
| Public static key agreement key | 0000000E |
| Private ephemeral key agreement key | 0000000F |
| Public ephemeral key agreement key | 00000010 |
| Symmetric authorization key | 00000011 |
| Private authorization key | 00000012 |
| Public authorization key | 00000013 |
| Extensions | 8XXXXXXX |

---

## Object Class Enumeration

Table571: Object Class Enumeration

| Name | Value |
| --- | --- |
| User | 00000001 |
| System | 00000002 |
| Extensions | 8XXXXXXX |

---

## Object Type Enumeration

Table572: Object Type Enumeration

| Name | Value |
| --- | --- |
| Certificate | 00000001 |
| Symmetric Key | 00000002 |
| Public Key | 00000003 |
| Private Key | 00000004 |
| Split Key | 00000005 |
| (Reserved) | 00000006 |
| Secret Data | 00000007 |
| Opaque Object | 00000008 |
| PGP Key | 00000009 |
| Certificate Request | 0000000A |
| User | 0000000B |
| Group | 0000000C |
| Password Credential | 0000000D |
| Device Credential | 0000000E |
| One Time Password Credential | 0000000F |
| Hashed Password Credential | 00000010 |
| Extensions | 8XXXXXXX |

---

## Opaque Data Type Enumeration

Table573: Opaque Data Type Enumeration

| Name | Value |
| --- | --- |
| Extensions | 8XXXXXXX |

---

## Operation Enumeration

Table574: Operation Enumeration

| Name | Value |
| --- | --- |
| Create | 00000001 |
| Create Key Pair | 00000002 |
| Register | 00000003 |
| Re-key | 00000004 |
| Derive Key | 00000005 |
| Certify | 00000006 |
| Re-certify | 00000007 |
| Locate | 00000008 |
| Check | 00000009 |
| Get | 0000000A |
| Get Attributes | 0000000B |
| Get Attribute List | 0000000C |
| Add Attribute | 0000000D |
| Modify Attribute | 0000000E |
| Delete Attribute | 0000000F |
| Obtain Lease | 00000010 |
| Get Usage Allocation | 00000011 |
| Activate | 00000012 |
| Revoke | 00000013 |
| Destroy | 00000014 |
| Archive | 00000015 |
| Recover | 00000016 |
| Validate | 00000017 |
| Query | 00000018 |
| Cancel | 00000019 |
| Poll | 0000001A |
| Notify | 0000001B |
| Put | 0000001C |
| Re-key Key Pair | 0000001D |
| Discover Versions | 0000001E |
| Encrypt | 0000001F |
| Decrypt | 00000020 |
| Sign | 00000021 |
| Signature Verify | 00000022 |
| MAC | 00000023 |
| MAC Verify | 00000024 |
| RNG Retrieve | 00000025 |
| RNG Seed | 00000026 |
| Hash | 00000027 |
| Create Split Key | 00000028 |
| Join Split Key | 00000029 |
| Import | 0000002A |
| Export | 0000002B |
| Log | 0000002C |
| Login | 0000002D |
| Logout | 0000002E |
| Delegated Login | 0000002F |
| Adjust Attribute | 00000030 |
| Set Attribute | 00000031 |
| Set Endpoint Role | 00000032 |
| PKCS#11 | 00000033 |
| Interop | 00000034 |
| Re-Provision | 00000035 |
| Set Defaults | 00000036 |
| Set Constraints | 00000037 |
| Get Constraints | 00000038 |
| Query Asynchronous Requests | 00000039 |
| Process | 0000003A |
| Ping | 0000003B |
| Create Group | 0000003C |
| Obliterate | 0000003D |
| Create User | 0000003E |
| Create Credential | 0000003F |
| Deactivate | 00000040 |
| Extensions | 8XXXXXXX |

---

## OTP Algorithm Enumeration

The following One-Time Password algorithms are currently defined:

| Name | Value |
| --- | --- |
| HOTP | 00000001 |
| TOTP | 00000002 |
| Extensions | 8xxxxxxx |

---

## Padding Method Enumeration

Table577: Padding Method Enumeration

| Name | Value |
| --- | --- |
| None | 00000001 |
| OAEP | 00000002 |
| PKCS5 | 00000003 |
| SSL3 | 00000004 |
| Zeros | 00000005 |
| ANSI X9.23 | 00000006 |
| ISO 10126 | 00000007 |
| PKCS1 v1.5 | 00000008 |
| X9.31 | 00000009 |
| PSS | 0000000A |
| Extensions | 8XXXXXXX |

---

## PKCS#11 Function Enumeration

The PKCS#11 Function enumerations are the 1-based offset count of the function in the CK_FUNCTION_LIST_3_0 structure as specified in [PKCS#11]


---

## PKCS#11 Return Code Enumeration

The PKCS#11 Return Codes enumerations representing PKCS#11 return codes as specified in the CK_RV values in [PKCS#11]


---

## Processing Stage Enumeration

Table578: Processing Stage Enumeration

| Name | Value |
| --- | --- |
| Submitted | 00000001 |
| In Process | 00000002 |
| Completed | 00000003 |
| Extensions | 8XXXXXXX |

---

## Profile Name Enumeration

Table579: Profile Name Enumeration

| Name | Value |
| --- | --- |
| (Reserved) | 00000001-00000103 |
| Complete Server Basic | 00000104 |
| Complete Server TLS v1.2 | 00000105 |
| Tape Library Client | 00000106 |
| Tape Library Server | 00000107 |
| Symmetric Key Lifecycle Client | 00000108 |
| Symmetric Key Lifecycle Server | 00000109 |
| Asymmetric Key Lifecycle Client | 0000010A |
| Asymmetric Key Lifecycle Server | 0000010B |
| Basic Cryptographic Client | 0000010C |
| Basic Cryptographic Server | 0000010D |
| Advanced Cryptographic Client | 0000010E |
| Advanced Cryptographic Server | 0000010F |
| RNG Cryptographic Client | 00000110 |
| RNG Cryptographic Server | 00000111 |
| Basic Symmetric Key Foundry Client | 00000112 |
| Intermediate Symmetric Key Foundry Client | 00000113 |
| Advanced Symmetric Key Foundry Client | 00000114 |
| Symmetric Key Foundry Server | 00000115 |
| Opaque Managed Object Store Client | 00000116 |
| Opaque Managed Object Store Server | 00000117 |
| (Reserved) | 00000118 |
| (Reserved) | 00000119 |
| (Reserved) | 0000011A |
| (Reserved) | 0000011B |
| Storage Array with Self Encrypting Drive Client | 0000011C |
| Storage Array with Self Encrypting Drive Server | 0000011D |
| HTTPS Client | 0000011E |
| HTTPS Server | 0000011F |
| JSON Client | 00000120 |
| JSON Server | 00000121 |
| XML Client | 00000122 |
| XML Server | 00000123 |
| AES XTS Client | 00000124 |
| AES XTS Server | 00000125 |
| Quantum Safe Client | 00000126 |
| Quantum Safe Server | 00000127 |
| PKCS#11 Client | 00000128 |
| PKCS#11 Server | 00000129 |
| Baseline Client | 0000012A |
| Baseline Server | 0000012B |
| Complete Server | 0000012C |
| Extensions | 8XXXXXXX |

---

## Protection Level Enumeration

Table580: Protection Level Enumeration

| Name | Value |
| --- | --- |
| High | 00000001 |
| Low | 00000002 |
| Extensions | 8XXXXXXX |

---

## Put Function Enumeration

Table581: Put Function Enumeration

| Name | Value |
| --- | --- |
| New | 00000001 |
| Replace | 00000002 |
| Extensions | 8XXXXXXX |

---

## Query Function Enumeration

Table582: Query Function Enumeration

| Name | Value |
| --- | --- |
| Query Operations | 00000001 |
| Query Objects | 00000002 |
| Query Server Information | 00000003 |
| Query Application Namespaces | 00000004 |
| Query Extension List | 00000005 |
| Query Extension Map | 00000006 |
| Query Attestation Types | 00000007 |
| Query RNGs | 00000008 |
| Query Validations | 00000009 |
| Query Profiles | 0000000A |
| Query Capabilities | 0000000B |
| Query Client Registration Methods | 0000000C |
| Query Defaults Information | 0000000D |
| Query Storage Protection Masks | 0000000E |
| Query Credential Information | 0000000F |
| Extensions | 8XXXXXXX |

---

## Recommended Curve Enumeration

Table583: Recommended Curve Enumeration for ECDSA, ECDH, and ECMQV

| Name | Value |
| --- | --- |
| P-192 | 00000001 |
| K-163 | 00000002 |
| B-163 | 00000003 |
| P-224 | 00000004 |
| K-233 | 00000005 |
| B-233 | 00000006 |
| P-256 | 00000007 |
| K-283 | 00000008 |
| B-283 | 00000009 |
| P-384 | 0000000A |
| K-409 | 0000000B |
| B-409 | 0000000C |
| P-521 | 0000000D |
| K-571 | 0000000E |
| B-571 | 0000000F |
| SECP112R1 | 00000010 |
| SECP112R2 | 00000011 |
| SECP128R1 | 00000012 |
| SECP128R2 | 00000013 |
| SECP160K1 | 00000014 |
| SECP160R1 | 00000015 |
| SECP160R2 | 00000016 |
| SECP192K1 | 00000017 |
| SECP224K1 | 00000018 |
| SECP256K1 | 00000019 |
| SECT113R1 | 0000001A |
| SECT113R2 | 0000001B |
| SECT131R1 | 0000001C |
| SECT131R2 | 0000001D |
| SECT163R1 | 0000001E |
| SECT193R1 | 0000001F |
| SECT193R2 | 00000020 |
| SECT239K1 | 00000021 |
| ANSIX9P192V2 | 00000022 |
| ANSIX9P192V3 | 00000023 |
| ANSIX9P239V1 | 00000024 |
| ANSIX9P239V2 | 00000025 |
| ANSIX9P239V3 | 00000026 |
| ANSIX9C2PNB163V1 | 00000027 |
| ANSIX9C2PNB163V2 | 00000028 |
| ANSIX9C2PNB163V3 | 00000029 |
| ANSIX9C2PNB176V1 | 0000002A |
| ANSIX9C2TNB191V1 | 0000002B |
| ANSIX9C2TNB191V2 | 0000002C |
| ANSIX9C2TNB191V3 | 0000002D |
| ANSIX9C2PNB208W1 | 0000002E |
| ANSIX9C2TNB239V1 | 0000002F |
| ANSIX9C2TNB239V2 | 00000030 |
| ANSIX9C2TNB239V3 | 00000031 |
| ANSIX9C2PNB272W1 | 00000032 |
| ANSIX9C2PNB304W1 | 00000033 |
| ANSIX9C2TNB359V1 | 00000034 |
| ANSIX9C2PNB368W1 | 00000035 |
| ANSIX9C2TNB431R1 | 00000036 |
| BRAINPOOLP160R1 | 00000037 |
| BRAINPOOLP160T1 | 00000038 |
| BRAINPOOLP192R1 | 00000039 |
| BRAINPOOLP192T1 | 0000003A |
| BRAINPOOLP224R1 | 0000003B |
| BRAINPOOLP224T1 | 0000003C |
| BRAINPOOLP256R1 | 0000003D |
| BRAINPOOLP256T1 | 0000003E |
| BRAINPOOLP320R1 | 0000003F |
| BRAINPOOLP320T1 | 00000040 |
| BRAINPOOLP384R1 | 00000041 |
| BRAINPOOLP384T1 | 00000042 |
| BRAINPOOLP512R1 | 00000043 |
| BRAINPOOLP512T1 | 00000044 |
| CURVE25519 | 00000045 |
| CURVE448 | 00000046 |
| Extensions | 8XXXXXXX |

---

## Result Reason Enumeration

Following are the Result Reason enumerations.

| Name | Value |
| --- | --- |
| Item Not Found | 00000001 |
| Response Too Large | 00000002 |
| Authentication Not Successful | 00000003 |
| Invalid Message | 00000004 |
| Operation Not Supported | 00000005 |
| Missing Data | 00000006 |
| Invalid Field | 00000007 |
| Feature Not Supported | 00000008 |
| Operation CanceledByRequester | 00000009 |
| Cryptographic Failure | 0000000A |
| (Reserved) | 0000000B |
| Permission Denied | 0000000C |
| Object Archived | 0000000D |
| (Reserved) | 0000000E |
| Application Namespace Not Supported | 0000000F |
| Key Format Type Not Supported | 00000010 |
| Key Compression Type Not Supported | 00000011 |
| Encoding Option Error | 00000012 |
| Key Value Not Present | 00000013 |
| Attestation Required | 00000014 |
| Attestation Failed | 00000015 |
| Sensitive | 00000016 |
| Not Extractable | 00000017 |
| Object Already Exists | 00000018 |
| Invalid Ticket | 00000019 |
| Usage Limit Exceeded | 0000001A |
| Numeric Range | 0000001B |
| Invalid Data Type | 0000001C |
| Read Only Attribute | 0000001D |
| Multi Valued Attribute | 0000001E |
| Unsupported Attribute | 0000001F |
| Attribute Instance Not Found | 00000020 |
| Attribute Not Found | 00000021 |
| Attribute Read Only | 00000022 |
| Attribute Single Valued | 00000023 |
| Bad Cryptographic Parameters | 00000024 |
| Bad Password | 00000025 |
| Codec Error | 00000026 |
| (Reserved) | 00000027 |
| Illegal Object Type | 00000028 |
| Incompatible Cryptographic Usage Mask | 00000029 |
| Internal Server Error | 0000002A |
| Invalid Asynchronous Correlation Value | 0000002B |
| Invalid Attribute | 0000002C |
| Invalid Attribute Value | 0000002D |
| Invalid Correlation Value | 0000002E |
| Invalid CSR | 0000002F |
| Invalid Object Type | 00000030 |
| (Reserved) | 00000031 |
| Key Wrap Type Not Supported | 00000032 |
| (Reserved) | 00000033 |
| Missing Initialization Vector | 00000034 |
| Non UniqueName Attribute | 00000035 |
| Object Destroyed | 00000036 |
| Object Not Found | 00000037 |
| (Reserved) | 00000038 |
| NotAuthorised | 00000039 |
| Server Limit Exceeded | 0000003A |
| Unknown Enumeration | 0000003B |
| Unknown Message Extension | 0000003C |
| Unknown Tag | 0000003D |
| Unsupported Cryptographic Parameters | 0000003E |
| Unsupported Protocol Version | 0000003F |
| Wrapping Object Archived | 00000040 |
| Wrapping Object Destroyed | 00000041 |
| Wrapping Object Not Found | 00000042 |
| Wrong Key Lifecycle State | 00000043 |
| Protection Storage Unavailable | 00000044 |
| PKCS#11 Codec Error | 00000045 |
| PKCS#11 Invalid Function | 00000046 |
| PKCS#11 Invalid Interface | 00000047 |
| Private Protection Storage Unavailable | 00000048 |
| Public Protection Storage Unavailable | 00000049 |
| (Reserved) | 0000004A |
| Constraint Violation | 0000004B |
| Duplicate Process Request | 0000004C |
| Circular Link Error | 0000004D |
| General Failure | 00000100 |
| Extensions | 8XXXXXXX |

---

## Result Status Enumeration

Table586: Result Status Enumeration

| Name | Value |
| --- | --- |
| Success | 00000000 |
| Operation Failed | 00000001 |
| Operation Pending | 00000002 |
| Operation Undone | 00000003 |
| Extensions | 8XXXXXXX |

---

## Revocation Reason Code Enumeration

Table587: Revocation Reason Code Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| Key Compromise | 00000002 |
| CA Compromise | 00000003 |
| Affiliation Changed | 00000004 |
| Superseded | 00000005 |
| Cessation of Operation | 00000006 |
| Privilege Withdrawn | 00000007 |
| Extensions | 8XXXXXXX |

---

## RNG Algorithm Enumeration

Table588: RNG Algorithm Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| FIPS 186-2 | 00000002 |
| DRBG | 00000003 |
| NRBG | 00000004 |
| ANSI X9.31 | 00000005 |
| ANSI X9.62 | 00000006 |
| Extensions | 8XXXXXXX |

---

## RNG Mode Enumeration

Table589: RNG Mode Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| Shared Instantiation | 00000002 |
| Non-Shared Instantiation | 00000003 |
| Extensions | 8XXXXXXX |

---

## Secret Data Type Enumeration

Table590: Secret Data Type Enumeration

| Name | Value |
| --- | --- |
| Password | 00000001 |
| Seed | 00000002 |
| Extensions | 8XXXXXXX |

---

## Shredding Algorithm Enumeration

Table591: Shredding Algorithm Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| Cryptographic | 00000002 |
| Unsupported | 00000003 |
| Extensions | 8XXXXXXX |

---

## Split Key Method Enumeration

Table592: Split Key Method Enumeration

| Name | Value |
| --- | --- |
| XOR | 00000001 |
| Polynomial Sharing GF (216) | 00000002 |
| Polynomial Sharing Prime Field | 00000003 |
| Polynomial Sharing GF (28) | 00000004 |
| Extensions | 8XXXXXXX |

---

## Split Key Polynomial Enumeration

Table593: Split Key Polynomial Enumeration

| Name | Value |
| --- | --- |
| Polynomial-283 | 00000001 |
| Polynomial-285 | 00000002 |
| Extensions | 8XXXXXXX |

---

## State Enumeration

Table594: State Enumeration

| Name | Value |
| --- | --- |
| Pre-Active | 00000001 |
| Active | 00000002 |
| Deactivated | 00000003 |
| Compromised | 00000004 |
| Destroyed | 00000005 |
| Destroyed Compromised | 00000006 |
| Extensions | 8XXXXXXX |

---

## Tag Enumeration

All tags SHALL contain either the value 42 in hex or the value 54 in hex as the first byte of a three (3) byte enumeration value. Tags defined by this specification contain hex 42 in the first byte. Extensions contain the value 54 hex in the first byte.

| Name | Value |
| --- | --- |
| (Unused) | 000000 - 420000 |
| Activation Date | 420001 |
| Application Data | 420002 |
| Application Namespace | 420003 |
| Application Specific Information | 420004 |
| Archive Date | 420005 |
| Asynchronous Correlation Value | 420006 |
| Asynchronous Indicator | 420007 |
| Attribute | 420008 |
| (Reserved) | 420009 |
| Attribute Name | 42000A |
| Attribute Value | 42000B |
| Authentication | 42000C |
| (Reserved) | 42000D |
| Batch Error Continuation Option | 42000E |
| Batch Item | 42000F |
| (Reserved) | 420010 |
| Block Cipher Mode | 420011 |
| Cancellation Result | 420012 |
| Certificate | 420013 |
| (Reserved) | 420014 |
| (Reserved) | 420015 |
| (Reserved) | 420016 |
| (Reserved) | 420017 |
| Certificate Request | 420018 |
| Certificate Request Type | 420019 |
| (Reserved) | 42001A |
| (Reserved) | 42001B |
| (Reserved) | 42001C |
| Certificate Type | 42001D |
| Certificate Value | 42001E |
| (Reserved) | 42001F |
| CompromiseDate | 420020 |
| Compromise Occurrence Date | 420021 |
| Contact Information | 420022 |
| Credential | 420023 |
| Credential Type | 420024 |
| Credential Value | 420025 |
| Criticality Indicator | 420026 |
| CRT Coefficient | 420027 |
| Cryptographic Algorithm | 420028 |
| Cryptographic Domain Parameters | 420029 |
| Cryptographic Length | 42002A |
| Cryptographic Parameters | 42002B |
| Cryptographic Usage Mask | 42002C |
| (Reserved) | 42002D |
| D | 42002E |
| Deactivation Date | 42002F |
| Derivation Data | 420030 |
| Derivation Method | 420031 |
| Derivation Parameters | 420032 |
| Destroy Date | 420033 |
| Digest | 420034 |
| Digest Value | 420035 |
| Encryption Key Information | 420036 |
| G | 420037 |
| Hashing Algorithm | 420038 |
| Initial Date | 420039 |
| Initialization Vector | 42003A |
| (Reserved) | 42003B |
| Iteration Count | 42003C |
| IV/Counter/Nonce | 42003D |
| J | 42003E |
| Key | 42003F |
| Key Block | 420040 |
| Key Compression Type | 420041 |
| Key Format Type | 420042 |
| Key Material | 420043 |
| Key Part Identifier | 420044 |
| Key Value | 420045 |
| Key Wrapping Data | 420046 |
| Key Wrapping Specification | 420047 |
| Last Change Date | 420048 |
| Lease Time | 420049 |
| (Reserved) | 42004A |
| (Reserved) | 42004B |
| (Reserved) | 42004C |
| MAC/Signature | 42004D |
| MAC/Signature Key Information | 42004E |
| Maximum Items | 42004F |
| Maximum Response Size | 420050 |
| Message Extension | 420051 |
| Modulus | 420052 |
| Name | 420053 |
| (Reserved) | 420054 |
| (Reserved) | 420055 |
| (Reserved) | 420056 |
| Object Type | 420057 |
| Offset | 420058 |
| Opaque Data Type | 420059 |
| Opaque Data Value | 42005A |
| Opaque Object | 42005B |
| Operation | 42005C |
| (Reserved) | 42005D |
| P | 42005E |
| Padding Method | 42005F |
| Prime Exponent P | 420060 |
| Prime Exponent Q | 420061 |
| Prime Field Size | 420062 |
| Private Exponent | 420063 |
| Private Key | 420064 |
| (Reserved) | 420065 |
| Private Key Unique Identifier | 420066 |
| Process Start Date | 420067 |
| Protect Stop Date | 420068 |
| Protocol Version | 420069 |
| Protocol Version Major | 42006A |
| Protocol Version Minor | 42006B |
| Public Exponent | 42006C |
| Public Key | 42006D |
| (Reserved) | 42006E |
| Public Key Unique Identifier | 42006F |
| Put Function | 420070 |
| Q | 420071 |
| Q String | 420072 |
| Qlength | 420073 |
| Query Function | 420074 |
| Recommended Curve | 420075 |
| Replaced Unique Identifier | 420076 |
| Request Header | 420077 |
| Request Message | 420078 |
| Request Payload | 420079 |
| Response Header | 42007A |
| Response Message | 42007B |
| Response Payload | 42007C |
| Result Message | 42007D |
| Result Reason | 42007E |
| Result Status | 42007F |
| Revocation Message | 420080 |
| Revocation Reason | 420081 |
| Revocation Reason Code | 420082 |
| Key Role Type | 420083 |
| Salt | 420084 |
| Secret Data | 420085 |
| Secret Data Type | 420086 |
| (Reserved) | 420087 |
| Server Information | 420088 |
| Split Key | 420089 |
| Split Key Method | 42008A |
| Split Key Parts | 42008B |
| Split Key Threshold | 42008C |
| State | 42008D |
| Storage Status Mask | 42008E |
| Symmetric Key | 42008F |
| (Reserved) | 420090 |
| (Reserved) | 420091 |
| Time Stamp | 420092 |
| (Reserved) | 420093 |
| Unique Identifier | 420094 |
| Usage Limits | 420095 |
| Usage Limits Count | 420096 |
| Usage Limits Total | 420097 |
| Usage Limits Unit | 420098 |
| Username | 420099 |
| Validity Date | 42009A |
| Validity Indicator | 42009B |
| Vendor Extension | 42009C |
| Vendor Identification | 42009D |
| Wrapping Method | 42009E |
| X | 42009F |
| Y | 4200A0 |
| Password | 4200A1 |
| Device Identifier | 4200A2 |
| Encoding Option | 4200A3 |
| Extension Information | 4200A4 |
| Extension Name | 4200A5 |
| Extension Tag | 4200A6 |
| Extension Type | 4200A7 |
| Fresh | 4200A8 |
| Machine Identifier | 4200A9 |
| Media Identifier | 4200AA |
| Network Identifier | 4200AB |
| (Reserved) | 4200AC |
| Certificate Length | 4200AD |
| Digital Signature Algorithm | 4200AE |
| Certificate Serial Number | 4200AF |
| Device Serial Number | 4200B0 |
| Issuer Alternative Name | 4200B1 |
| Issuer Distinguished Name | 4200B2 |
| Subject Alternative Name | 4200B3 |
| Subject Distinguished Name | 4200B4 |
| X.509 Certificate Identifier | 4200B5 |
| X.509 Certificate Issuer | 4200B6 |
| X.509 Certificate Subject | 4200B7 |
| Key Value Location | 4200B8 |
| Key Value Location Value | 4200B9 |
| Key Value Location Type | 4200BA |
| Key Value Present | 4200BB |
| Original Creation Date | 4200BC |
| PGP Key | 4200BD |
| PGP Key Version | 4200BE |
| Alternative Name | 4200BF |
| Alternative Name Value | 4200C0 |
| Alternative Name Type | 4200C1 |
| Data | 4200C2 |
| Signature Data | 4200C3 |
| Data Length | 4200C4 |
| Random IV | 4200C5 |
| MAC Data | 4200C6 |
| Attestation Type | 4200C7 |
| Nonce | 4200C8 |
| Nonce ID | 4200C9 |
| Nonce Value | 4200CA |
| Attestation Measurement | 4200CB |
| Attestation Assertion | 4200CC |
| IV Length | 4200CD |
| Tag Length | 4200CE |
| Fixed Field Length | 4200CF |
| Counter Length | 4200D0 |
| Initial Counter Value | 4200D1 |
| Invocation Field Length | 4200D2 |
| Attestation Capable Indicator | 4200D3 |
| Offset Items | 4200D4 |
| Located Items | 4200D5 |
| Correlation Value | 4200D6 |
| Init Indicator | 4200D7 |
| Final Indicator | 4200D8 |
| RNG Parameters | 4200D9 |
| RNG Algorithm | 4200DA |
| DRBG Algorithm | 4200DB |
| FIPS186 Variation | 4200DC |
| Prediction Resistance | 4200DD |
| Random Number Generator | 4200DE |
| Validation Information | 4200DF |
| Validation Authority Type | 4200E0 |
| Validation Authority Country | 4200E1 |
| Validation Authority URI | 4200E2 |
| Validation Version Major | 4200E3 |
| Validation Version Minor | 4200E4 |
| Validation Type | 4200E5 |
| Validation Level | 4200E6 |
| Validation Certificate Identifier | 4200E7 |
| Validation Certificate URI | 4200E8 |
| Validation Vendor URI | 4200E9 |
| Validation Profile | 4200EA |
| Profile Information | 4200EB |
| Profile Name | 4200EC |
| Server URI | 4200ED |
| Server Port | 4200EE |
| Streaming Capability | 4200EF |
| Asynchronous Capability | 4200F0 |
| Attestation Capability | 4200F1 |
| Unwrap Mode | 4200F2 |
| Destroy Action | 4200F3 |
| Shredding Algorithm | 4200F4 |
| RNG Mode | 4200F5 |
| Client Registration Method | 4200F6 |
| Capability Information | 4200F7 |
| Key Wrap Type | 4200F8 |
| Batch Undo Capability | 4200F9 |
| Batch Continue Capability | 4200FA |
| PKCS#12 Friendly Name | 4200FB |
| Description | 4200FC |
| Comment | 4200FD |
| AuthenticatedEncryption Additional Data | 4200FE |
| Authenticated Encryption Tag | 4200FF |
| Salt Length | 420100 |
| Mask Generator | 420101 |
| Mask Generator Hashing Algorithm | 420102 |
| P Source | 420103 |
| Trailer Field | 420104 |
| Client Correlation Value | 420105 |
| Server Correlation Value | 420106 |
| Digested Data | 420107 |
| Certificate Subject CN | 420108 |
| Certificate Subject O | 420109 |
| Certificate Subject OU | 42010A |
| Certificate Subject Email | 42010B |
| Certificate Subject C | 42010C |
| Certificate Subject ST | 42010D |
| Certificate Subject L | 42010E |
| Certificate Subject UID | 42010F |
| Certificate Subject Serial Number | 420110 |
| Certificate Subject Title | 420111 |
| Certificate Subject DC | 420112 |
| Certificate Subject DN Qualifier | 420113 |
| Certificate Issuer CN | 420114 |
| Certificate Issuer O | 420115 |
| Certificate Issuer OU | 420116 |
| Certificate Issuer Email | 420117 |
| Certificate Issuer C | 420118 |
| Certificate Issuer ST | 420119 |
| Certificate Issuer L | 42011A |
| Certificate Issuer UID | 42011B |
| Certificate Issuer Serial Number | 42011C |
| Certificate Issuer Title | 42011D |
| Certificate Issuer DC | 42011E |
| Certificate Issuer DN Qualifier | 42011F |
| Sensitive | 420120 |
| Always Sensitive | 420121 |
| Extractable | 420122 |
| Never Extractable | 420123 |
| Replace Existing | 420124 |
| Attributes | 420125 |
| Common Attributes | 420126 |
| Private Key Attributes | 420127 |
| Public Key Attributes | 420128 |
| Extension Enumeration | 420129 |
| Extension Attribute | 42012A |
| Extension Parent Structure Tag | 42012B |
| Extension Description | 42012C |
| Server Name | 42012D |
| Server Serial Number | 42012E |
| Server Version | 42012F |
| Server Load | 420130 |
| Product Name | 420131 |
| Build Level | 420132 |
| Build Date | 420133 |
| Cluster Info | 420134 |
| Alternate Failover Endpoints | 420135 |
| Short Unique Identifier | 420136 |
| Reserved | 420137 |
| Tag | 420138 |
| Certificate Request Unique Identifier | 420139 |
| NIST Key Type | 42013A |
| Attribute Reference | 42013B |
| Current Attribute | 42013C |
| New Attribute | 42013D |
| (Reserved) | 42013E |
| (Reserved) | 42013F |
| Certificate Request Value | 420140 |
| Log Message | 420141 |
| Profile Version | 420142 |
| Profile Version Major | 420143 |
| Profile Version Minor | 420144 |
| Protection Level | 420145 |
| Protection Period | 420146 |
| Quantum Safe | 420147 |
| Quantum Safe Capability | 420148 |
| Ticket | 420149 |
| Ticket Type | 42014A |
| Ticket Value | 42014B |
| Request Count | 42014C |
| Rights | 42014D |
| Objects | 42014E |
| Operations | 42014F |
| Right | 420150 |
| Endpoint Role | 420151 |
| Defaults Information | 420152 |
| Object Defaults | 420153 |
| Ephemeral | 420154 |
| Server Hashed Password | 420155 |
| One Time Password | 420156 |
| Hashed Password | 420157 |
| Adjustment Type | 420158 |
| PKCS#11 Interface | 420159 |
| PKCS#11 Function | 42015A |
| PKCS#11 Input Parameters | 42015B |
| PKCS#11 Output Parameters | 42015C |
| PKCS#11 Return Code | 42015D |
| Protection Storage Mask | 42015E |
| Protection Storage Masks | 42015F |
| Interop Function | 420160 |
| Interop Identifier | 420161 |
| Adjustment Value | 420162 |
| Common Protection Storage Masks | 420163 |
| Private Protection Storage Masks | 420164 |
| Public Protection Storage Masks | 420165 |
| Object Groups | 420166 |
| Object Types | 420167 |
| Constraints | 420168 |
| Constraint | 420169 |
| Rotate Interval | 0x42016A |
| Rotate Automatic | 0x42016B |
| Rotate Offset | 0x42016C |
| Rotate Date | 0x42016D |
| Rotate Generation | 0x42016E |
| Rotate Name | 0x42016F |
| (Reserved) | 0x420170 |
| (Reserved) | 0x420171 |
| Rotate Latest | 0x420172 |
| Asynchronous Request | 0x420173 |
| Submission Date | 0x420174 |
| Processing Stage | 0x420175 |
| Asynchronous Correlation Values | 0x420176 |
| Certificate Link | 0x420190 |
| Child Link | 0x420191 |
| Derivation Object Link | 0x420192 |
| Derived Object Link | 0x420193 |
| Next Link | 0x420194 |
| Parent Link | 0x420195 |
| PKCS#12 Certificate Link | 0x420196 |
| PKCS#12 Password Link | 0x420197 |
| Previous Link | 0x420198 |
| Private Key Link | 0x420199 |
| Public Key Link | 0x42019A |
| Replaced Object Link | 0x42019B |
| Replacement Object Link | 0x42019C |
| Wrapping Key Link | 0x42019D |
| Object Class | 0x42019E |
| Object Class Mask | 0x42019F |
| Credential Link | 0x4201A0 |
| Password Credential | 0x4201A1 |
| Password Salt | 0x4201A2 |
| Password Salt Algorithm | 0x4201A3 |
| Salted Password | 0x4201A4 |
| Password Link | 0x4201A5 |
| Device Credential | 0x4201A6 |
| OTP Credential | 0x4201A7 |
| OTP Algorithm | 0x4201A8 |
| OTP Digest | 0x4201A9 |
| OTP Serial | 0x4201AA |
| OTP Seed | 0x4201AB |
| OTP Interval | 0x4201AC |
| OTP Digits | 0x4201AD |
| OTP Counter | 0x4201AE |
| Hashed Password Credential | 0x4201AF |
| Hashed Username Password | 0x4201B0 |
| Hashed Password Username | 0x4201B1 |
| Credential Information | 0x4201B2 |
| Group Link | 0x4201B3 |
| Split Key Base Link | 0x4201B4 |
| Joined Split Key Parts Link | 0x4201B5 |
| Split Key Polynomial | 0x4201B6 |
| Deactivation Message | 0x4201B7 |
| Deactivation Reason | 0x4201B8 |
| Deactivation Reason Code | 0x4201B9 |
| Certificate Subject DN | 0x4201BA |
| Certificate Issuer DN | 0x4201BB |
| Certificate Request Link | 0x4201BC |
| Certify Counter | 0x4201BD |
| Decrypt Counter | 0x4201BE |
| Encrypt Counter | 0x4201BF |
| Sign Counter | 0x4201C0 |
| Signature Verify Counter | 0x4201C1 |
| NIST Security Category | 0x4201C2 |
| (Reserved) | 420XXX – 42FFFF |
| (Unused) | 430000 – 53FFFF |
| Extensions | 540000 – 54FFFF |
| (Unused) | 550000 - FFFFFF |

---

## Ticket Type Enumeration

Table596: Ticket Type Enumeration

| Name | Value |
| --- | --- |
| Login | 00000001 |
| Extensions | 8XXXXXXX |

---

## Unique Identifier Enumeration

The following values may be specified in an operation request for a Unique Identifier: If multiple unique identifiers would be referenced then the operation is repeated for each of them as separate batch items. If an operation appears multiple times in a request, it is the most recent that is referred to.

| Name | Value |
| --- | --- |
| ID Placeholder | 00000001 |
| Certify | 00000002 |
| Create | 00000003 |
| Create Key Pair | 00000004 |
| Create Key Pair Private Key | 00000005 |
| Create Key Pair Public Key | 00000006 |
| Create Split Key | 00000007 |
| Derive Key | 00000008 |
| Import | 00000009 |
| Join Split Key | 0000000A |
| Locate | 0000000B |
| Register | 0000000C |
| Re-key | 0000000D |
| Re-certify | 0000000E |
| Re-key Key Pair | 0000000F |
| Re-key Key Pair Private Key | 00000010 |
| Re-key Key Pair Public Key | 00000011 |
| Re-Provision | 00000012 |
| Create User | 00000013 |
| Create Group | 00000014 |
| Create Credential | 00000015 |
| Extensions | 8XXXXXXX |

---

## Unwrap Mode Enumeration

Table598:UnwrapModeEnumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| Processed | 00000002 |
| Not Processed | 00000003 |
| Extensions | 8XXXXXXX |

---

## Usage Limits Unit Enumeration

Table599: Usage Limits UnitEnumeration

| Name | Value |
| --- | --- |
| Byte | 00000001 |
| Object | 00000002 |
| Extensions | 8XXXXXXX |

---

## Validity Indicator Enumeration

Table600: Validity Indicator Enumeration

| Name | Value |
| --- | --- |
| Valid | 00000001 |
| Invalid | 00000002 |
| Unknown | 00000003 |
| Extensions | 8XXXXXXX |

---

## Wrapping Method Enumeration

The following wrapping methods are currently defined:

| Name | Value |
| --- | --- |
| Encrypt | 00000001 |
| MAC/sign | 00000002 |
| Encrypt then MAC/sign | 00000003 |
| MAC/sign then encrypt | 00000004 |
| TR-31 | 00000005 |
| Extensions | 8XXXXXXX |

---

## Validation Authority Type Enumeration

Table603: Validation Authority Type Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| NIST CMVP | 00000002 |
| Common Criteria | 00000003 |
| Extensions | 8XXXXXXX |

---

## Validation Type Enumeration

Table604: Validation Type Enumeration

| Name | Value |
| --- | --- |
| Unspecified | 00000001 |
| Hardware | 00000002 |
| Software | 00000003 |
| Firmware | 00000004 |
| Hybrid | 00000005 |
| Extensions | 8XXXXXXX |

---
