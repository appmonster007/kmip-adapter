# KMIP 2.0 — Enumerations

Total: **58** enumeration types

## Adjustment Type

| Name      | Hex        | Description |
| --------- | ---------- | ----------- |
| Increment | 0x00000001 | Add the Adjustment Parameter to the value. Applies to Integer, Long Integers, Big Integer, Interval, Date Time, and Date Time Extended. The default is parameter is 1 for numeric types, 1 second for Date Time, and 1 microsecond for Date Time Extended. |
| Decrement | 0x00000002 | Subtract the Adjustment Parameter to the value. Applies to Integer, Long Integers, Big Integer, Interval, Date Time, and Date Time Extended. The default is parameter is 1 for numeric types, 1 second for Date Time, and 1 microsecond for Date Time Extended. |
| Negate    | 0x00000003 | Negate the value. Applies to Integer, Long Integers, Big Integer and Boolean types. |

## Alternative Name Type

| Name                      | Hex        | Description |
| ------------------------- | ---------- | ----------- |
| Uninterpreted Text String | 0x00000001 |  |
| URI                       | 0x00000002 |  |
| Object Serial Number      | 0x00000003 |  |
| Email Address             | 0x00000004 |  |
| DNS Name                  | 0x00000005 |  |
| X.500 Distinguished Name  | 0x00000006 |  |
| IP Address                | 0x00000007 |  |

## Asynchronous Indicator

| Name       | Hex        | Description |
| ---------- | ---------- | ----------- |
| Mandatory  | 0x00000001 | The server SHALL process all batch items in the request asynchronously (returning an Asynchronous Correlation Value for each batch item). |
| Optional   | 0x00000002 | The server MAY process each batch item in the request either asynchronously (returning an Asynchronous Correlation Value for a batch item) or synchronously. The method or policy by which the server determines whether or not to process an individual batch item asynchronously is a decision of the server and is outside of the scope of this protocol. |
| Prohibited | 0x00000003 | The server SHALL NOT process any batch item asynchronously. All batch items SHALL be processed synchronously. |

## Attestation Type

| Name                 | Hex        | Description |
| -------------------- | ---------- | ----------- |
| TPM Quote            | 0x00000001 |  |
| TCG Integrity Report | 0x00000002 |  |
| SAML Assertion       | 0x00000003 |  |

## Batch Error Continuation Option

| Name     | Hex        | Description |
| -------- | ---------- | ----------- |
| Continue | 0x00000001 | Return an error for the failed operation, and continue processing subsequent operations in the request. Batch item fails and Result Status is set to Operation Failed. Batch items that had been processed have been undone and their responses are returned with Undone result status. |
| Stop     | 0x00000002 | If an operation fails, then the server SHALL NOT continue processing subsequent operations in the request. Completed operations SHALL NOT be undone. Batch item fails and Result Status is set to Operation Failed. Responses to other batch items are returned normally. |
| Undo     | 0x00000003 | If any operation in the request fails, then the server SHALL undo all the previous operations. Batch item fails and Result Status is set to Operation Failed. Responses to batch items that have already been processed are returned normally. Responses to batch items that have not been processed are not returned. |

## Block Cipher Mode

| Name              | Hex        | Description |
| ----------------- | ---------- | ----------- |
| CBC               | 0x00000001 |  |
| ECB               | 0x00000002 |  |
| PCBC              | 0x00000003 |  |
| CFB               | 0x00000004 |  |
| OFB               | 0x00000005 |  |
| CTR               | 0x00000006 |  |
| CMAC              | 0x00000007 |  |
| CCM               | 0x00000008 |  |
| GCM               | 0x00000009 |  |
| CBC-MAC           | 0x0000000A |  |
| XTS               | 0x0000000B |  |
| AESKeyWrapPadding | 0x0000000C |  |
| NISTKeyWrap       | 0x0000000D |  |
| X9.102 AESKW      | 0x0000000E |  |
| X9.102 TDKW       | 0x0000000F |  |
| X9.102 AKW1       | 0x00000010 |  |
| X9.102 AKW2       | 0x00000011 |  |
| AEAD              | 0x00000012 |  |

## Cancellation Result

| Name             | Hex        | Description |
| ---------------- | ---------- | ----------- |
| Canceled         | 0x00000001 | The cancel operation succeeded in canceling the pending operation. |
| Unable to Cancel | 0x00000002 | The cancel operation is unable to cancel the pending operation. |
| Completed        | 0x00000003 | The pending operation completed successfully before the cancellation operation was able to cancel it. |
| Failed           | 0x00000004 | The pending operation completed with a failure before the cancellation operation was able to cancel it. |
| Unavailable      | 0x00000005 | Unavailable – The specified correlation value did not match any recently pending or completed asynchronous operations. |

## Certificate Request Type

| Name    | Hex        | Description |
| ------- | ---------- | ----------- |
| CRMF    | 0x00000001 |  |
| PKCS#10 | 0x00000002 |  |
| PEM     | 0x00000003 |  |

## Certificate Type

| Name  | Hex        | Description |
| ----- | ---------- | ----------- |
| X.509 | 0x00000001 |  |
| (PGP  | 0x00000002 |  |

## Client Registration Method

| Name                 | Hex        | Description |
| -------------------- | ---------- | ----------- |
| Unspecified          | 0x00000001 |  |
| Server Pre-Generated | 0x00000002 | The server has pre-generated the client’s private key. The returned PKCS#12 is protected with HEX(SHA256(Username || Password)). |
| Server On-Demand     | 0x00000003 | The server generates the client’s private key on demand. The returned PKCS#12 is protected with HEX(SHA256(Username || Password)). |
| Client Generated     | 0x00000004 | The client generates the private key and sends a Certificate Signing Request to the server to generate the certificate. The returned PKCS#12 is protected with HEX(SHA256(Username || Password)). |
| Client Registered    | 0x00000005 | The client generates the private key and the certificates and registers the certificate with the server. |

## Credential Type

| Name                  | Hex        | Description |
| --------------------- | ---------- | ----------- |
| Username and Password | 0x00000001 |  |
| Device                | 0x00000002 |  |
| Attestation           | 0x00000003 |  |
| One Time Password     | 0x00000004 |  |
| Hashed Password       | 0x00000005 |  |
| Ticket                | 0x00000006 |  |

## Cryptographic Algorithm

| Name              | Hex        | Description |
| ----------------- | ---------- | ----------- |
| DES               | 0x00000001 |  |
| 3DES              | 0x00000002 |  |
| AES               | 0x00000003 |  |
| RSA               | 0x00000004 |  |
| DSA               | 0x00000005 |  |
| ECDSA             | 0x00000006 |  |
| HMAC-SHA1         | 0x00000007 |  |
| HMAC-SHA224       | 0x00000008 |  |
| HMAC-SHA256       | 0x00000009 |  |
| HMAC-SHA384       | 0x0000000A |  |
| HMAC-SHA512       | 0x0000000B |  |
| HMAC-MD5          | 0x0000000C |  |
| DH                | 0x0000000D |  |
| ECDH              | 0x0000000E |  |
| ECMQV             | 0x0000000F |  |
| Blowfish          | 0x00000010 |  |
| Camellia          | 0x00000011 |  |
| CAST5             | 0x00000012 |  |
| IDEA              | 0x00000013 |  |
| MARS              | 0x00000014 |  |
| RC2               | 0x00000015 |  |
| RC4               | 0x00000016 |  |
| RC5               | 0x00000017 |  |
| SKIPJACK          | 0x00000018 |  |
| Twofish           | 0x00000019 |  |
| EC                | 0x0000001A |  |
| One Time Pad      | 0x0000001B |  |
| ChaCha20          | 0x0000001C |  |
| Poly1305          | 0x0000001D |  |
| ChaCha20Poly1305  | 0x0000001E |  |
| SHA3-224          | 0x0000001F |  |
| SHA3-256          | 0x00000020 |  |
| SHA3-384          | 0x00000021 |  |
| SHA3-512          | 0x00000022 |  |
| HMAC-SHA3-224     | 0x00000023 |  |
| HMAC-SHA3-256     | 0x00000024 |  |
| HMAC-SHA3-384     | 0x00000025 |  |
| HMAC-SHA3-512     | 0x00000026 |  |
| SHAKE-128         | 0x00000027 |  |
| SHAKE-256         | 0x00000028 |  |
| ARIA              | 0x00000029 |  |
| SEED              | 0x0000002A |  |
| SM2               | 0x0000002B |  |
| SM3               | 0x0000002C |  |
| SM4               | 0x0000002D |  |
| GOST R 34.10-2012 | 0x0000002E |  |
| GOST R 34.11-2012 | 0x0000002F |  |
| GOST R 34.13-2015 | 0x00000030 |  |
| GOST 28147-89     | 0x00000031 |  |
| XMSS              | 0x00000032 |  |
| SPHINCS-256       | 0x00000033 |  |
| McEliece          | 0x00000034 |  |
| McEliece-6960119  | 0x00000035 |  |
| McEliece-8192128  | 0x00000036 |  |
| Ed25519           | 0x00000037 |  |
| Ed448             | 0x00000038 |  |

## DRBG Algorithm

| Name        | Hex        | Description |
| ----------- | ---------- | ----------- |
| Unspecified | 0x00000001 |  |
| Dual-EC     | 0x00000002 |  |
| Hash        | 0x00000003 |  |
| HMAC        | 0x00000004 |  |
| CTR         | 0x00000005 |  |

## Data

| Name                | Hex        | Description |
| ------------------- | ---------- | ----------- |
| Decrypt             | 0x00000001 |  |
| Encrypt             | 0x00000002 |  |
| Hash                | 0x00000003 |  |
| MAC MAC Data        | 0x00000004 |  |
| RNG Retrieve        | 0x00000005 |  |
| Sign Signature Data | 0x00000006 |  |
| Signature Verify    | 0x00000007 |  |

## Derivation Method

| Name                    | Hex                                                                                           | Description |
| ----------------------- | --------------------------------------------------------------------------------------------- | ----------- |
| HASH                    | This method derives a key by computing a hash over the derivation key or the derivation data. |  |
| HMAC                    | This method derives a key by computing an HMAC over the derivation data.                      |  |
| ENCRYPT                 | This method derives a key by encrypting the derivation data.                                  |  |
| NIST800-108-C           | This method derives a key by computing the KDF in Counter Mode                                |  |
| NIST800-108-F           | This method derives a key by computing the KDF in Feedback Mode                               |  |
| NIST800-108-DPI         | This method derives a key by computing the KDF in Double-Pipeline Iteration Mode              |  |
| Asymmetric Key          | This method derives a key using asymmetric key agreement between a private and public key.    |  |
| AWS Signature Version 4 | As defined in Amazon Web Services Signature Version 4.                                        |  |
| HKDF                    | HMAC-based Extract-and-Expand Key Derivation Function                                         |  |

## Destroy Action

| Name                  | Hex        | Description |
| --------------------- | ---------- | ----------- |
| Unspecified           | 0x00000001 |  |
| Key Material Deleted  | 0x00000002 |  |
| Key Material Shredded | 0x00000003 |  |
| Meta Data Deleted     | 0x00000004 |  |
| Meta Data Shredded    | 0x00000005 |  |
| Deleted               | 0x00000006 |  |
| Shredded              | 0x00000007 |  |

## Digital Signature Algorithm

| Name                         | Hex        | Description |
| ---------------------------- | ---------- | ----------- |
| MD2 with RSA Encryption      | 0x00000001 |  |
| MD5 with RSA Encryption      | 0x00000002 |  |
| SHA-1 with RSA Encryption    | 0x00000003 |  |
| SHA-224 with RSA Encryption  | 0x00000004 |  |
| SHA-256 with RSA Encryption  | 0x00000005 |  |
| SHA-384 with RSA Encryption  | 0x00000006 |  |
| SHA-512 with RSA Encryption  | 0x00000007 |  |
| RSASSA-PSS                   | 0x00000008 |  |
| DSA with SHA-1               | 0x00000009 |  |
| DSA with SHA224              | 0x0000000A |  |
| DSA with SHA256              | 0x0000000B |  |
| ECDSA with SHA-1             | 0x0000000C |  |
| ECDSA with SHA224            | 0x0000000D |  |
| ECDSA with SHA256            | 0x0000000E |  |
| ECDSA with SHA384            | 0x0000000F |  |
| ECDSA with SHA512            | 0x00000010 |  |
| SHA3-256 with RSA Encryption | 0x00000011 |  |
| SHA3-384 with RSA Encryption | 0x00000012 |  |
| SHA3-512 with RSA Encryption | 0x00000013 |  |

## Encoding Option

| Name          | Hex        | Description |
| ------------- | ---------- | ----------- |
| No Encoding   | 0x00000001 | the wrapped un-encoded value of the Byte String Key Material field in the Key Value structure |
| TTLV Encoding | 0x00000002 | the wrapped TTLV-encoded Key Value structure |

## Endpoint Role

| Name   | Hex        | Description |
| ------ | ---------- | ----------- |
| Client | 0x00000001 | The endpoint that sends requests and receives responses. |
| Server | 0x00000002 | The endpoint that receives requests and sends responses. |

## FIPS186 Variation

| Name               | Hex        | Description |
| ------------------ | ---------- | ----------- |
| Unspecified        | 0x00000001 |  |
| GP x-Original      | 0x00000002 |  |
| GP x-Change Notice | 0x00000003 |  |
| x-Original         | 0x00000004 |  |
| x-Change Notice    | 0x00000005 |  |
| k-Original         | 0x00000006 |  |
| k-Change Notice    | 0x00000007 |  |

## Hashing Algorithm

| Name        | Hex        | Description |
| ----------- | ---------- | ----------- |
| MD2         | 0x00000001 |  |
| MD4         | 0x00000002 |  |
| MD5         | 0x00000003 |  |
| SHA-1       | 0x00000004 |  |
| SHA-224     | 0x00000005 |  |
| SHA-256     | 0x00000006 |  |
| SHA-384     | 0x00000007 |  |
| SHA-512     | 0x00000008 |  |
| RIPEMD-160  | 0x00000009 |  |
| Tiger       | 0x0000000A |  |
| Whirlpool   | 0x0000000B |  |
| SHA-512/224 | 0x0000000C |  |
| SHA-512/256 | 0x0000000D |  |
| SHA3-224    | 0x0000000E |  |
| SHA3-256    | 0x0000000F |  |
| SHA3-384    | 0x00000010 |  |
| SHA3-512    | 0x00000011 |  |

## Interop Function

| Name  | Hex                                                                                  | Description |
| ----- | ------------------------------------------------------------------------------------ | ----------- |
| End   | A specified test has ended                                                           |  |
| Reset | Resets the server to the state it would be in at the beginning of an interop session |  |

## Item Type

| Name               | Hex        | Description |
| ------------------ | ---------- | ----------- |
| Structure          | 0x00000001 | The ordered concatenation of items. |
| Integer            | 0x00000002 | Four-byte long (32 bit) signed numbers |
| Long Integer       | 0x00000003 | Eight-byte long (64 bit) signed numbers. |
| Big Integer        | 0x00000004 | A sequence of eight-bit bytes |
| Enumeration        | 0x00000005 | Four-byte long (32 bit) unsigned numbers |
| Boolean            | 0x00000006 | The value True or False. |
| Text String        | 0x00000007 | Sequences of character values. |
| Byte String        | 0x00000008 | Sequences of bytes containing individual unspecified eight-bit binary values |
| Date Time          | 0x00000009 | Eight-byte long (64 bit) POSIX Time values in seconds. . |
| Interval           | 0x0000000A | Four-byte long (32 bit) unsigned numbers in seconds |
| Date Time Extended | 0x0000000B | Eight-byte long (64 bit) POSIX Time values in micro-seconds. |

## Key Compression Type

| Name                                      | Hex        | Description |
| ----------------------------------------- | ---------- | ----------- |
| EC Public Key Type Uncompressed           | 0x00000001 |  |
| EC Public Key Type X9.62 Compressed Prime | 0x00000002 |  |
| EC Public Key Type X9.62 Compressed Char2 | 0x00000003 |  |
| EC Public Key Type X9.62 Hybrid           | 0x00000004 |  |

## Key Format Type

| Name                        | Hex        | Description |
| --------------------------- | ---------- | ----------- |
| Raw                         | 0x00000001 | A key that contains only cryptographic key material, encoded as a string of bytes. |
| Opaque                      | 0x00000002 | an encoded key for which the encoding is unknown to the key management system. It is encoded as a string of bytes. |
| PKCS#1                      | 0x00000003 |  |
| PKCS#8                      | 0x00000004 |  |
| X.509                       | 0x00000005 | An encoded object, expressed as a DER-encoded ASN.1 X.509 object. |
| ECPrivateKey                | 0x00000006 | An ASN.1 encoded elliptic curve private key. |
| Transparent Symmetric Key   | 0x00000007 |  |
| Transparent DSA Private Key | 0x00000008 |  |
| Transparent DSA Public Key  | 0x00000009 |  |
| Transparent RSA Private Key | 0x0000000A |  |
| Transparent RSA Public Key  | 0x0000000B |  |
| Transparent DH Private Key  | 0x0000000C |  |
| Transparent DH Public Key   | 0x0000000D |  |
| Transparent EC Private Key  | 0x00000014 |  |
| Transparent EC Public Key   | 0x00000015 |  |
| PKCS#12                     | 0x00000016 |  |
| PKCS#10                     | 0x00000017 |  |

## Key Role Type

| Name     | Hex        | Description |
| -------- | ---------- | ----------- |
| BDK      | 0x00000001 |  |
| CVK      | 0x00000002 |  |
| DEK      | 0x00000003 |  |
| MKAC     | 0x00000004 |  |
| MKSMC    | 0x00000005 |  |
| MKSMI    | 0x00000006 |  |
| MKDAC    | 0x00000007 |  |
| MKDN     | 0x00000008 |  |
| MKCP     | 0x00000009 |  |
| MKOTH    | 0x0000000A |  |
| KEK      | 0x0000000B |  |
| MAC16609 | 0x0000000C |  |
| MAC97971 | 0x0000000D |  |
| MAC97972 | 0x0000000E |  |
| MAC97973 | 0x0000000F |  |
| MAC97974 | 0x00000010 |  |
| MAC97975 | 0x00000011 |  |
| ZPK      | 0x00000012 |  |
| PVKIBM   | 0x00000013 |  |
| PVKPVV   | 0x00000014 |  |
| PVKOTH   | 0x00000015 |  |
| DUKPT    | 0x00000016 |  |
| IV       | 0x00000017 |  |
| TRKBK    | 0x00000018 |  |

## Key Value Location Type

| Name                      | Hex        | Description |
| ------------------------- | ---------- | ----------- |
| Uninterpreted Text String | 0x00000001 |  |
| URI                       | 0x00000002 |  |

## Key Wrap Type

| Name          | Hex        | Description |
| ------------- | ---------- | ----------- |
| Not Wrapped   | 0x00000001 |  |
| As Registered | 0x00000002 |  |

## Link Type

| Name                        | Hex        | Description |
| --------------------------- | ---------- | ----------- |
| Certificate Link            | 0x00000101 | For Certificate objects: the parent certificate for a certificate in a certificate chain. For Public Key objects: the corresponding certificate(s), containing the same public key. |
| Public Key Link             | 0x00000102 | For a Private Key object: the public key corresponding to the private key. For a Certificate object: the public key contained in the certificate. |
| Private Key Link            | 0x00000103 | For a Public Key object: the private key corresponding to the public key. |
| Derivation Base Object Link | 0x00000104 | For a derived Symmetric Key or Secret Data object: the object(s) from which the current symmetric key was derived. |
| Derived Key Link            | 0x00000105 | The symmetric key(s) or Secret Data object(s) that were derived from the current object. |
| Replacement Object Link     | 0x00000106 | For a Symmetric Key, an Asymmetric Private Key, or an Asymmetric Public Key object: the key that resulted from the re-key of the current key. For a Certificate object: the certificate that resulted from the re-certify. Note that there SHALL be only one such replacement object per Managed Object. |
| Replaced Object Link        | 0x00000107 | For a Symmetric Key, an Asymmetric Private Key, or an Asymmetric Public Key object: the key that was re-keyed to obtain the current key. For a Certificate object: the certificate that was re-certified to obtain the current certificate. |
| Parent Link                 | 0x00000108 | For all object types: the container or other parent object corresponding to the object. |
| Child Link                  | 0x00000109 | For all object types: the subordinate, derived or other child object corresponding to the object. |
| Previous Link               | 0x0000010A | For all object types: the previous object to this object. |
| Next Link                   | 0x0000010B | For all object types: the next object to this object. |
| PKCS#12 Certificate Link    | 0x0000010C |  |
| PKCS#12 Password Link       | 0x0000010D |  |
| Wrapping Key Link           | 0x0000010E | For wrapped objects: the object that was used to wrap this object. |

## Mask Generator

| Name | Hex        | Description |
| ---- | ---------- | ----------- |
| MFG1 | 0x00000001 |  |

## NIST Key Type

| Name                                   | Hex        | Description |
| -------------------------------------- | ---------- | ----------- |
| Private signature key                  | 0x00000001 |  |
| Public signature verification key      | 0x00000002 |  |
| Symmetric authentication key           | 0x00000003 |  |
| Private authentication key             | 0x00000004 |  |
| Public authentication key              | 0x00000005 |  |
| Symmetric data encryption key          | 0x00000006 |  |
| Symmetric key wrapping key             | 0x00000007 |  |
| Symmetric random number generation key | 0x00000008 |  |
| Symmetric master key                   | 0x00000009 |  |
| Private key transport key              | 0x0000000A |  |
| Public key transport key               | 0x0000000B |  |
| Symmetric key agreement key            | 0x0000000C |  |
| Private static key agreement key       | 0x0000000D |  |
| Public static key agreement key        | 0x0000000E |  |
| Private ephemeral key agreement key    | 0x0000000F |  |
| Public ephemeral key agreement key     | 0x00000010 |  |
| Symmetric authorization key            | 0x00000011 |  |
| Private authorization key              | 0x00000012 |  |
| Public authorization key               | 0x00000013 |  |

## Name Type

| Name                      | Hex        | Description |
| ------------------------- | ---------- | ----------- |
| Uninterpreted Text String | 0x00000001 |  |
| URI                       | 0x00000002 |  |

## Object Group Member

| Name                 | Hex        | Description |
| -------------------- | ---------- | ----------- |
| Group Member Fresh   | 0x00000001 |  |
| Group Member Default | 0x00000002 |  |

## Object Type

| Name                | Hex        | Description |
| ------------------- | ---------- | ----------- |
| Certificate         | 0x00000001 |  |
| Symmetric Key       | 0x00000002 |  |
| Public Key          | 0x00000003 |  |
| Private Key         | 0x00000004 |  |
| Split Key           | 0x00000005 |  |
| Secret Data         | 0x00000007 |  |
| Opaque Object       | 0x00000008 |  |
| PGP Key             | 0x00000009 |  |
| Certificate Request | 0x0000000A |  |

## Operation

| Name                 | Hex        | Description |
| -------------------- | ---------- | ----------- |
| Create               | 0x00000001 |  |
| Create Key Pair      | 0x00000002 |  |
| Register             | 0x00000003 |  |
| Re-key               | 0x00000004 |  |
| Derive Key           | 0x00000005 |  |
| Certify              | 0x00000006 |  |
| Re-certify           | 0x00000007 |  |
| Locate               | 0x00000008 |  |
| Check                | 0x00000009 |  |
| Get                  | 0x0000000A |  |
| Get Attributes       | 0x0000000B |  |
| Get Attribute List   | 0x0000000C |  |
| Add Attribute        | 0x0000000D |  |
| Modify Attribute     | 0x0000000E |  |
| Delete Attribute     | 0x0000000F |  |
| Obtain Lease         | 0x00000010 |  |
| Get Usage Allocation | 0x00000011 |  |
| Activate             | 0x00000012 |  |
| Revoke               | 0x00000013 |  |
| Destroy              | 0x00000014 |  |
| Archive              | 0x00000015 |  |
| Recover              | 0x00000016 |  |
| Validate             | 0x00000017 |  |
| Query                | 0x00000018 |  |
| Cancel               | 0x00000019 |  |
| Poll                 | 0x0000001A |  |
| Notify               | 0x0000001B |  |
| Put                  | 0x0000001C |  |
| Re-key Key Pair      | 0x0000001D |  |
| Discover Versions    | 0x0000001E |  |
| Encrypt              | 0x0000001F |  |
| Decrypt              | 0x00000020 |  |
| Sign                 | 0x00000021 |  |
| Signature Verify     | 0x00000022 |  |
| MAC                  | 0x00000023 |  |
| MAC Verify           | 0x00000024 |  |
| RNG Retrieve         | 0x00000025 |  |
| RNG Seed             | 0x00000026 |  |
| Hash                 | 0x00000027 |  |
| Create Split Key     | 0x00000028 |  |
| Join Split Key       | 0x00000029 |  |
| Import               | 0x0000002A |  |
| Export               | 0x0000002B |  |
| Log                  | 0x0000002C |  |
| Login                | 0x0000002D |  |
| Logout               | 0x0000002E |  |
| Delegated Login      | 0x0000002F |  |
| Adjust Attribute     | 0x00000030 |  |
| Set Attribute        | 0x00000031 |  |
| Set Endpoint Role    | 0x00000032 |  |
| PKCS#11              | 0x00000033 |  |
| Interop              | 0x00000034 |  |
| Re-Provision         | 0x00000035 |  |

## Padding Method

| Name       | Hex        | Description |
| ---------- | ---------- | ----------- |
| None       | 0x00000001 |  |
| OAEP       | 0x00000002 |  |
| PKCS5      | 0x00000003 |  |
| SSL3       | 0x00000004 |  |
| Zeros      | 0x00000005 |  |
| ANSI X9.23 | 0x00000006 |  |
| ISO 10126  | 0x00000007 |  |
| PKCS1 v1.5 | 0x00000008 |  |
| X9.31      | 0x00000009 |  |
| PSS        | 0x0000000A |  |

## Profile Name

| Name                                            | Hex        | Description |
| ----------------------------------------------- | ---------- | ----------- |
| Complete Server Basic                           | 0x00000104 |  |
| Complete Server TLS v1.2                        | 0x00000105 |  |
| Tape Library Client                             | 0x00000106 |  |
| Tape Library Server                             | 0x00000107 |  |
| Symmetric Key Lifecycle Client                  | 0x00000108 |  |
| Symmetric Key Lifecycle Server                  | 0x00000109 |  |
| Asymmetric Key Lifecycle Client                 | 0x0000010A |  |
| Asymmetric Key Lifecycle Server                 | 0x0000010B |  |
| Basic Cryptographic Client                      | 0x0000010C |  |
| Basic Cryptographic Server                      | 0x0000010D |  |
| Advanced Cryptographic Client                   | 0x0000010E |  |
| Advanced Cryptographic Server                   | 0x0000010F |  |
| RNG Cryptographic Client                        | 0x00000110 |  |
| RNG Cryptographic Server                        | 0x00000111 |  |
| Basic Symmetric Key Foundry Client              | 0x00000112 |  |
| Intermediate Symmetric Key Foundry Client       | 0x00000113 |  |
| Advanced Symmetric Key Foundry Client           | 0x00000114 |  |
| Symmetric Key Foundry Server                    | 0x00000115 |  |
| Opaque Managed Object Store Client              | 0x00000116 |  |
| Opaque Managed Object Store Server              | 0x00000117 |  |
| Storage Array with Self Encrypting Drive Client | 0x0000011C |  |
| Storage Array with Self Encrypting Drive Server | 0x0000011D |  |
| HTTPS Client                                    | 0x0000011E |  |
| HTTPS Server                                    | 0x0000011F |  |
| JSON Client                                     | 0x00000120 |  |
| JSON Server                                     | 0x00000121 |  |
| XML Client                                      | 0x00000122 |  |
| XML Server                                      | 0x00000123 |  |
| AES XTS Client                                  | 0x00000124 |  |
| AES XTS Server                                  | 0x00000125 |  |
| Quantum Safe Client                             | 0x00000126 |  |
| Quantum Safe Server                             | 0x00000127 |  |
| PKCS#11 Client                                  | 0x00000128 |  |
| PKCS#11 Server                                  | 0x00000129 |  |
| Baseline Client                                 | 0x0000012A |  |
| Baseline Server                                 | 0x0000012B |  |
| Complete Server                                 | 0x0000012C |  |

## Protection Level

| Name | Hex        | Description |
| ---- | ---------- | ----------- |
| High | 0x00000001 |  |
| Low  | 0x00000002 |  |

## Put Function

| Name    | Hex        | Description |
| ------- | ---------- | ----------- |
| New     | 0x00000001 |  |
| Replace | 0x00000002 |  |

## Query Function

| Name                              | Hex        | Description |
| --------------------------------- | ---------- | ----------- |
| Query Operations                  | 0x00000001 |  |
| Query Objects                     | 0x00000002 |  |
| Query Server Information          | 0x00000003 |  |
| Query Application Namespaces      | 0x00000004 |  |
| Query Extension List              | 0x00000005 |  |
| Query Extension Map               | 0x00000006 |  |
| Query Attestation Types           | 0x00000007 |  |
| Query RNGs                        | 0x00000008 |  |
| Query Validations                 | 0x00000009 |  |
| Query Profiles                    | 0x0000000A |  |
| Query Capabilities                | 0x0000000B |  |
| Query Client Registration Methods | 0x0000000C |  |
| Query Defaults Information        | 0x0000000D |  |
| Query Storage Protection Masks    | 0x0000000E |  |

## RNG Algorithm

| Name        | Hex        | Description |
| ----------- | ---------- | ----------- |
| Unspecified | 0x00000001 |  |
| FIPS 186-2  | 0x00000002 |  |
| DRBG        | 0x00000003 |  |
| NRBG        | 0x00000004 |  |
| ANSI X9.31  | 0x00000005 |  |
| ANSI X9.62  | 0x00000006 |  |

## RNG Mode

| Name                     | Hex        | Description |
| ------------------------ | ---------- | ----------- |
| Unspecified              | 0x00000001 |  |
| Shared Instantiation     | 0x00000002 |  |
| Non-Shared Instantiation | 0x00000003 |  |

## Recommended Curve

| Name             | Hex        | Description |
| ---------------- | ---------- | ----------- |
| P-192            | 0x00000001 |  |
| K-163            | 0x00000002 |  |
| B-163            | 0x00000003 |  |
| P-224            | 0x00000004 |  |
| K-233            | 0x00000005 |  |
| B-233            | 0x00000006 |  |
| P-256            | 0x00000007 |  |
| K-283            | 0x00000008 |  |
| B-283            | 0x00000009 |  |
| P-384            | 0x0000000A |  |
| K-409            | 0x0000000B |  |
| B-409            | 0x0000000C |  |
| P-521            | 0x0000000D |  |
| K-571            | 0x0000000E |  |
| B-571            | 0x0000000F |  |
| SECP112R1        | 0x00000010 |  |
| SECP112R2        | 0x00000011 |  |
| SECP128R1        | 0x00000012 |  |
| SECP128R2        | 0x00000013 |  |
| SECP160K1        | 0x00000014 |  |
| SECP160R1        | 0x00000015 |  |
| SECP160R2        | 0x00000016 |  |
| SECP192K1        | 0x00000017 |  |
| SECP224K1        | 0x00000018 |  |
| SECP256K1        | 0x00000019 |  |
| SECT113R1        | 0x0000001A |  |
| SECT113R2        | 0x0000001B |  |
| SECT131R1        | 0x0000001C |  |
| SECT131R2        | 0x0000001D |  |
| SECT163R1        | 0x0000001E |  |
| SECT193R1        | 0x0000001F |  |
| SECT193R2        | 0x00000020 |  |
| SECT239K1        | 0x00000021 |  |
| ANSIX9P192V2     | 0x00000022 |  |
| ANSIX9P192V3     | 0x00000023 |  |
| ANSIX9P239V1     | 0x00000024 |  |
| ANSIX9P239V2     | 0x00000025 |  |
| ANSIX9P239V3     | 0x00000026 |  |
| ANSIX9C2PNB163V1 | 0x00000027 |  |
| ANSIX9C2PNB163V2 | 0x00000028 |  |
| ANSIX9C2PNB163V3 | 0x00000029 |  |
| ANSIX9C2PNB176V1 | 0x0000002A |  |
| ANSIX9C2TNB191V1 | 0x0000002B |  |
| ANSIX9C2TNB191V2 | 0x0000002C |  |
| ANSIX9C2TNB191V3 | 0x0000002D |  |
| ANSIX9C2PNB208W1 | 0x0000002E |  |
| ANSIX9C2TNB239V1 | 0x0000002F |  |
| ANSIX9C2TNB239V2 | 0x00000030 |  |
| ANSIX9C2TNB239V3 | 0x00000031 |  |
| ANSIX9C2PNB272W1 | 0x00000032 |  |
| ANSIX9C2PNB304W1 | 0x00000033 |  |
| ANSIX9C2TNB359V1 | 0x00000034 |  |
| ANSIX9C2PNB368W1 | 0x00000035 |  |
| ANSIX9C2TNB431R1 | 0x00000036 |  |
| BRAINPOOLP160R1  | 0x00000037 |  |
| BRAINPOOLP160T1  | 0x00000038 |  |
| BRAINPOOLP192R1  | 0x00000039 |  |
| BRAINPOOLP192T1  | 0x0000003A |  |
| BRAINPOOLP224R1  | 0x0000003B |  |
| BRAINPOOLP224T1  | 0x0000003C |  |
| BRAINPOOLP256R1  | 0x0000003D |  |
| BRAINPOOLP256T1  | 0x0000003E |  |
| BRAINPOOLP320R1  | 0x0000003F |  |
| BRAINPOOLP320T1  | 0x00000040 |  |
| BRAINPOOLP384R1  | 0x00000041 |  |
| BRAINPOOLP384T1  | 0x00000042 |  |
| BRAINPOOLP512R1  | 0x00000043 |  |
| BRAINPOOLP512T1  | 0x00000044 |  |
| CURVE25519       | 0x00000045 |  |
| CURVE448         | 0x00000046 |  |

## Result Reason

| Name                                   | Hex        | Description |
| -------------------------------------- | ---------- | ----------- |
| Item Not Found                         | 0x00000001 | No object with the specified Unique Identifier exists |
| Response Too Large                     | 0x00000002 | Maximum Response Size has been exceeded |
| Authentication Not Successful          | 0x00000003 |  |
| Invalid Message                        | 0x00000004 | The request message was not syntactically understood by the server. For example - the invalid use of a known tag |
| Operation Not Supported                | 0x00000005 | The operation requested by the request message is not supported by the server |
| Missing Data                           | 0x00000006 |  |
| Invalid Field                          | 0x00000007 | The request is syntactically valid but some data in the request (other than an attribute value) has an invalid value |
| Feature Not Supported                  | 0x00000008 | The operation is supported, but not a specific feature specified in the request is not supported |
| Operation Canceled By Requester        | 0x00000009 |  |
| Cryptographic Failure                  | 0x0000000A | The operation failed due to a cryptographic error |
| Permission Denied                      | 0x0000000C | Client is not allowed to perform the specified operation |
| Object Archived                        | 0x0000000D | The object SHALL be recovered from the archive before performing the operation |
| Application Namespace Not Supported    | 0x0000000F | The particular Application Namespace is not supported, and the server was not able to generate the Application Data field of an Application Specific Information attribute if the field was omitted from the client request |
| Key Format Type Not Supported          | 0x00000010 | The object exists, but the server is unable to provide it in the desired Key Format Type |
| Key Compression Type Not Supported     | 0x00000011 | The object exists, but the server is unable to provide it in the desired Key Compression Type |
| Encoding Option Error                  | 0x00000012 | The Encoding Option is not supported as specified by the Encoding Option Enumeration |
| Key Value Not Present                  | 0x00000013 | A meta data only object. The key value is not present on the server |
| Attestation Required                   | 0x00000014 | Operation requires attestation data which was not provided by the client, and the client has set the Attestation Capable indicator to True |
| Attestation Failed                     | 0x00000015 | Operation requires attestation data and the attestation data provided by the client does not validate |
| Sensitive                              | 0x00000016 | Sensitive keys may not be retrieved unwrapped |
| Not Extractable                        | 0x00000017 | Object is not Extractable |
| Object Already Exists                  | 0x00000018 | for operations such as Import that require that no object with a specific unique identifier exists on a server |
| Invalid Ticket                         | 0x00000019 | The ticket was invalid |
| Usage Limit Exceeded                   | 0x0000001A | The usage limits or request count has been exceeded |
| Numeric Range                          | 0x0000001B | An operation produced a number that is to large or too small to be stored in the specified data type |
| Invalid Data Type                      | 0x0000001C | A data type was invalid for the requested operation |
| Read Only Attribute                    | 0x0000001D | Attempt to set a Read Only Attribute |
| Multi Valued Attribute                 | 0x0000001E | Attempt to Set or Adjust an attribute that has multiple values |
| Unsupported Attribute                  | 0x0000001F | Attribute is valid in the specification but unsupported by the Server |
| Attribute Instance Not Found           | 0x00000020 | A referenced attribute was found, but the specific instance was not found |
| Attribute Not Found                    | 0x00000021 | A referenced attribute was not found at all on an object |
| Attribute Read Only                    | 0x00000022 | Attempt to set a Read Only Attribute |
| Attribute Single Valued                | 0x00000023 |  |
| Bad Cryptographic Parameters           | 0x00000024 | Bad Cryptographic Parameters |
| Bad Password                           | 0x00000025 | Key Format Type is PKCS#12, but missing or multiple PKCS#12 Password Links, or not Secret Data, or not Active |
| Codec Error                            | 0x00000026 | The low level TTLV, XML, JSON etc. was badly formed and not understood by the server.TTLV connections should be closed as future requests might not be correctly separated |
| Illegal Object Type                    | 0x00000028 | Check cannot be performed on this object type |
| Incompatible Cryptographic Usage Mask  | 0x00000029 | The cryptographic algorithm or other parameters is not valid for the requested operation |
| Internal Server Error                  | 0x0000002A | The server had an internal error and could not process the request at this time. |
| Invalid Asynchronous Correlation Value | 0x0000002B | No outstanding operation with the specified Asynchronous Correlation Value exists |
| Invalid Attribute                      | 0x0000002C | An attribute is invalid for this object for this operation |
| Invalid Attribute Value                | 0x0000002D | The value supplied for an attribute is invalid |
| Invalid Correlation Value              | 0x0000002E | For streaming cryptographic operations |
| Invalid CSR                            | 0x0000002F | Invalid Certifcate Signing Request |
| Invalid Object Type                    | 0x00000030 | Specificed object is not valid for the requested operation |
| Key Wrap Type Not Supported            | 0x00000032 | Key Wrap Type Type is not supported by the server |
| Missing Initialization Vector          | 0x00000034 | Missing IV when required for crypto operation |
| Non Unique Name Attribute              | 0x00000035 | Trying to perform an operation that requests the server to break the constraint on Name attribute being unique |
| Object Destroyed                       | 0x00000036 | Object exists, but has already been destroyed |
| Object Not Found                       | 0x00000037 | A requested managed object was not found or did not exist |
| Not Authorised                         | 0x00000039 |  |
| Server Limit Exceeded                  | 0x0000003A | Some limit on the server such as database size has been exceeded |
| Unknown Enumeration                    | 0x0000003B | An enumerated value is not known by the server |
| Unknown Message Extension              | 0x0000003C | The server does not support the supplied Message Extension |
| Unknown Tag                            | 0x0000003D | A tag is not known by the server |
| Unsupported Cryptographic Parameters   | 0x0000003E | Cryptographic Parameters are valid in the specification but unsupported by the Server |
| Unsupported Protocol Version           | 0x0000003F | The operation cannot be performed with the provided protocol version |
| Wrapping Object Archived               | 0x00000040 | Wrapping Object is archived |
| Wrapping Object Destroyed              | 0x00000041 | The object exists, but is destroyed |
| Wrapping Object Not Found              | 0x00000042 | Wrapping object does not exist |
| Wrong Key Lifecycle State              | 0x00000043 | The key lifecycle state is invalid for the operation, for example not Active for an Encrypt operation |
| Protection Storage Unavailable         | 0x00000044 |  |
| PKCS#11 Codec Error                    | 0x00000045 | There is a Codec error in the Input parameter |
| PKCS#11 Invalid Function               | 0x00000046 | The PKCS function is not in the interface |
| PKCS#11 Invalid Interface              | 0x00000047 | The interface is unknown or unavailable in the server |
| Private Protection Storage Unavailable | 0x00000048 |  |
| Public Protection Storage Unavailable  | 0x00000049 |  |
| General Failure                        | 0x00000100 |  |

## Result Status

| Name              | Hex        | Description |
| ----------------- | ---------- | ----------- |
| Success           | 0x00000000 |  |
| Operation Failed  | 0x00000001 |  |
| Operation Pending | 0x00000002 |  |
| Operation Undone  | 0x00000003 |  |

## Revocation Reason Code

| Name                   | Hex        | Description |
| ---------------------- | ---------- | ----------- |
| Unspecified            | 0x00000001 |  |
| Key Compromise         | 0x00000002 |  |
| CA Compromise          | 0x00000003 |  |
| Affiliation Changed    | 0x00000004 |  |
| Superseded             | 0x00000005 |  |
| Cessation of Operation | 0x00000006 |  |
| Privilege Withdrawn    | 0x00000007 |  |

## Secret Data Type

| Name     | Hex        | Description |
| -------- | ---------- | ----------- |
| Password | 0x00000001 |  |
| Seed     | 0x00000002 |  |

## Shredding Algorithm

| Name          | Hex        | Description |
| ------------- | ---------- | ----------- |
| Unspecified   | 0x00000001 |  |
| Cryptographic | 0x00000002 |  |
| Unsupported   | 0x00000003 |  |

## Split Key Method

| Name                           | Hex        | Description |
| ------------------------------ | ---------- | ----------- |
| XOR                            | 0x00000001 |  |
| Polynomial Sharing GF (216)    | 0x00000002 |  |
| Polynomial Sharing Prime Field | 0x00000003 |  |
| Polynomial Sharing GF (28)     | 0x00000004 |  |

## State

| Name                  | Hex        | Description |
| --------------------- | ---------- | ----------- |
| Pre-Active            | 0x00000001 |  |
| Active                | 0x00000002 |  |
| Deactivated           | 0x00000003 |  |
| Compromised           | 0x00000004 |  |
| Destroyed             | 0x00000005 |  |
| Destroyed Compromised | 0x00000006 |  |

## Ticket Type

| Name  | Hex        | Description |
| ----- | ---------- | ----------- |
| Login | 0x00000001 |  |

## Unique Identifier

| Name                        | Hex        | Description |
| --------------------------- | ---------- | ----------- |
| ID Placeholder              | 0x00000001 |  |
| Certify                     | 0x00000002 |  |
| Create                      | 0x00000003 |  |
| Create Key Pair             | 0x00000004 |  |
| Create Key Pair Private Key | 0x00000005 |  |
| Create Key Pair Public Key  | 0x00000006 |  |
| Create Split Key            | 0x00000007 |  |
| Derive Key                  | 0x00000008 |  |
| Import                      | 0x00000009 |  |
| Join Split Key              | 0x0000000A |  |
| Locate                      | 0x0000000B |  |
| Register                    | 0x0000000C |  |
| Re-key                      | 0x0000000D |  |
| Re-certify                  | 0x0000000E |  |
| Re-key Key Pair             | 0x0000000F |  |
| Re-key Key Pair Private Key | 0x00000010 |  |
| Re-key Key Pair Public Key  | 0x00000011 |  |

## Unwrap Mode

| Name          | Hex        | Description |
| ------------- | ---------- | ----------- |
| Unspecified   | 0x00000001 |  |
| Processed     | 0x00000002 |  |
| Not Processed | 0x00000003 |  |

## Usage Limits Unit

| Name   | Hex        | Description |
| ------ | ---------- | ----------- |
| Byte   | 0x00000001 |  |
| Object | 0x00000002 |  |

## Validation Authority Type

| Name            | Hex        | Description |
| --------------- | ---------- | ----------- |
| Unspecified     | 0x00000001 |  |
| NIST CMVP       | 0x00000002 |  |
| Common Criteria | 0x00000003 |  |

## Validation Type

| Name        | Hex        | Description |
| ----------- | ---------- | ----------- |
| Unspecified | 0x00000001 |  |
| Hardware    | 0x00000002 |  |
| Software    | 0x00000003 |  |
| Firmware    | 0x00000004 |  |
| Hybrid      | 0x00000005 |  |

## Validity Indicator

| Name    | Hex        | Description |
| ------- | ---------- | ----------- |
| Valid   | 0x00000001 |  |
| Invalid | 0x00000002 |  |
| Unknown | 0x00000003 |  |

## Wrapping Method

| Name                  | Hex        | Description |
| --------------------- | ---------- | ----------- |
| Encrypt               | 0x00000001 |  |
| MAC/sign              | 0x00000002 |  |
| Encrypt then MAC/sign | 0x00000003 |  |
| MAC/sign then encrypt | 0x00000004 |  |
| TR-31                 | 0x00000005 |  |
