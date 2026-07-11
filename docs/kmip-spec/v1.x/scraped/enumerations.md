# KMIP 1.2 — Enumerations

Total: **35** enumeration types

## Credential Type

| Value Name            | Hex      |
|-----------------------|----------|
| Username and Password | 00000001 |
| Device                | 00000002 |
| Attestation           | 00000003 |

## Key Compression Type

| Value Name                                | Hex      |
|-------------------------------------------|----------|
| EC Public Key Type Uncompressed           | 00000001 |
| EC Public Key Type X9.62 Compressed Prime | 00000002 |
| EC Public Key Type X9.62 Compressed Char2 | 00000003 |
| EC Public Key Type X9.62 Hybrid           | 00000004 |

## Key Format Type

| Value Name                    | Hex      |
|-------------------------------|----------|
| Raw                           | 00000001 |
| Opaque                        | 00000002 |
| PKCS#1                        | 00000003 |
| PKCS#8                        | 00000004 |
| X.509                         | 00000005 |
| ECPrivateKey                  | 00000006 |
| Transparent Symmetric Key     | 00000007 |
| Transparent DSA Private Key   | 00000008 |
| Transparent DSA Public Key    | 00000009 |
| Transparent RSA Private Key   | 0000000A |
| Transparent RSA Public Key    | 0000000B |
| Transparent DH Private Key    | 0000000C |
| Transparent DH Public Key     | 0000000D |
| Transparent ECDSA Private Key | 0000000E |
| Transparent ECDSA Public Key  | 0000000F |
| Transparent ECDH Private Key  | 00000010 |
| Transparent ECDH Public Key   | 00000011 |
| Transparent ECMQV Private Key | 00000012 |
| Transparent ECMQV Public Key  | 00000013 |

## Wrapping Method

| Value Name            | Hex      |
|-----------------------|----------|
| Encrypt               | 00000001 |
| MAC/sign              | 00000002 |
| Encrypt then MAC/sign | 00000003 |
| MAC/sign then encrypt | 00000004 |
| TR-31                 | 00000005 |

## Recommended Curve

| Value Name       | Hex      |
|------------------|----------|
| P-192            | 00000001 |
| K-163            | 00000002 |
| B-163            | 00000003 |
| P-224            | 00000004 |
| K-233            | 00000005 |
| B-233            | 00000006 |
| P-256            | 00000007 |
| K-283            | 00000008 |
| B-283            | 00000009 |
| P-384            | 0000000A |
| K-409            | 0000000B |
| B-409            | 0000000C |
| P-521            | 0000000D |
| K-571            | 0000000E |
| B-571            | 0000000F |
| SECP112R1        | 00000010 |
| SECP112R2        | 00000011 |
| SECP128R1        | 00000012 |
| SECP128R2        | 00000013 |
| SECP160K1        | 00000014 |
| SECP160R1        | 00000015 |
| SECP160R2        | 00000016 |
| SECP192K1        | 00000017 |
| SECP224K1        | 00000018 |
| SECP256K1        | 00000019 |
| SECT113R1        | 0000001A |
| SECT113R2        | 0000001B |
| SECT131R1        | 0000001C |
| SECT131R2        | 0000001D |
| SECT163R1        | 0000001E |
| SECT193R1        | 0000001F |
| SECT193R2        | 00000020 |
| SECT239K1        | 00000021 |
| ANSIX9P192V2     | 00000022 |
| ANSIX9P192V3     | 00000023 |
| ANSIX9P239V1     | 00000024 |
| ANSIX9P239V2     | 00000025 |
| ANSIX9P239V3     | 00000026 |
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
| BRAINPOOLP160R1  | 00000037 |
| BRAINPOOLP160T1  | 00000038 |
| BRAINPOOLP192R1  | 00000039 |
| BRAINPOOLP192T1  | 0000003A |
| BRAINPOOLP224R1  | 0000003B |
| BRAINPOOLP224T1  | 0000003C |
| BRAINPOOLP256R1  | 0000003D |
| BRAINPOOLP256T1  | 0000003E |
| BRAINPOOLP320R1  | 0000003F |
| BRAINPOOLP320T1  | 00000040 |
| BRAINPOOLP384R1  | 00000041 |
| BRAINPOOLP384T1  | 00000042 |
| BRAINPOOLP512R1  | 00000043 |
| BRAINPOOLP512T1  | 00000044 |

## Certificate Type

| Value Name | Hex                   |
|------------|-----------------------|
| X.509      | 00000001              |
| PGP        | 00000002 (deprecated) |

## Digital Signature Algorithm

| Value Name                                | Hex      |
|-------------------------------------------|----------|
| MD2 with RSA Encryption (PKCS#1 v1.5)     | 00000001 |
| MD5 with RSA Encryption (PKCS#1 v1.5)     | 00000002 |
| SHA-1 with RSA Encryption (PKCS#1 v1.5)   | 00000003 |
| SHA-224 with RSA Encryption (PKCS#1 v1.5) | 00000004 |
| SHA-256 with RSA Encryption (PKCS#1 v1.5) | 00000005 |
| SHA-384 with RSA Encryption (PKCS#1 v1.5) | 00000006 |
| SHA-512 with RSA Encryption (PKCS#1 v1.5) | 00000007 |
| RSASSA-PSS (PKCS#1 v2.1)                  | 00000008 |
| DSA with SHA-1                            | 00000009 |
| DSA with SHA224                           | 0000000A |
| DSA with SHA256                           | 0000000B |
| ECDSA with SHA-1                          | 0000000C |
| ECDSA with SHA224                         | 0000000D |
| ECDSA with SHA256                         | 0000000E |
| ECDSA with SHA384                         | 0000000F |
| ECDSA with SHA512                         | 00000010 |

## Split Key Method

| Value Name                     | Hex      |
|--------------------------------|----------|
| XOR                            | 00000001 |
| Polynomial Sharing GF (2 16 )  | 00000002 |
| Polynomial Sharing Prime Field | 00000003 |
| Polynomial Sharing GF (2 8 )   | 00000004 |

## Secret Data Type

| Value Name | Hex      |
|------------|----------|
| Password   | 00000001 |
| Seed       | 00000002 |

## Name Type

| Value Name                | Hex      |
|---------------------------|----------|
| Uninterpreted Text String | 00000001 |
| URI                       | 00000002 |

## Object Type

| Value Name    | Hex      |
|---------------|----------|
| Certificate   | 00000001 |
| Symmetric Key | 00000002 |
| Public Key    | 00000003 |
| Private Key   | 00000004 |
| Split Key     | 00000005 |
| Template      | 00000006 |
| Secret Data   | 00000007 |
| Opaque Object | 00000008 |
| PGP Key       | 00000009 |

## Cryptographic Algorithm

| Value Name  | Hex      |
|-------------|----------|
| DES         | 00000001 |
| 3DES        | 00000002 |
| AES         | 00000003 |
| RSA         | 00000004 |
| DSA         | 00000005 |
| ECDSA       | 00000006 |
| HMAC-SHA1   | 00000007 |
| HMAC-SHA224 | 00000008 |
| HMAC-SHA256 | 00000009 |
| HMAC-SHA384 | 0000000A |
| HMAC-SHA512 | 0000000B |
| HMAC-MD5    | 0000000C |
| DH          | 0000000D |
| ECDH        | 0000000E |
| ECMQV       | 0000000F |
| Blowfish    | 00000010 |
| Camellia    | 00000011 |
| CAST5       | 00000012 |
| IDEA        | 00000013 |
| MARS        | 00000014 |
| RC2         | 00000015 |
| RC4         | 00000016 |
| RC5         | 00000017 |
| SKIPJACK    | 00000018 |
| Twofish     | 00000019 |
| EC          | 0000001A |

## Block Cipher Mode

| Value Name        | Hex      |
|-------------------|----------|
| CBC               | 00000001 |
| ECB               | 00000002 |
| PCBC              | 00000003 |
| CFB               | 00000004 |
| OFB               | 00000005 |
| CTR               | 00000006 |
| CMAC              | 00000007 |
| CCM               | 00000008 |
| GCM               | 00000009 |
| CBC-MAC           | 0000000A |
| XTS               | 0000000B |
| AESKeyWrapPadding | 0000000C |
| NISTKeyWrap       | 0000000D |
| X9.102 AESKW      | 0000000E |
| X9.102 TDKW       | 0000000F |
| X9.102 AKW1       | 00000010 |
| X9.102 AKW2       | 00000011 |

## Padding Method

| Value Name | Hex      |
|------------|----------|
| None       | 00000001 |
| OAEP       | 00000002 |
| PKCS5      | 00000003 |
| SSL3       | 00000004 |
| Zeros      | 00000005 |
| ANSI X9.23 | 00000006 |
| ISO 10126  | 00000007 |
| PKCS1 v1.5 | 00000008 |
| X9.31      | 00000009 |
| PSS        | 0000000A |

## Hashing Algorithm

| Value Name  | Hex      |
|-------------|----------|
| MD2         | 00000001 |
| MD4         | 00000002 |
| MD5         | 00000003 |
| SHA-1       | 00000004 |
| SHA-224     | 00000005 |
| SHA-256     | 00000006 |
| SHA-384     | 00000007 |
| SHA-512     | 00000008 |
| RIPEMD-160  | 00000009 |
| Tiger       | 0000000A |
| Whirlpool   | 0000000B |
| SHA-512/224 | 0000000C |
| SHA-512/256 | 0000000D |

## Key Role Type

| Value Name | Hex      |
|------------|----------|
| BDK        | 00000001 |
| CVK        | 00000002 |
| DEK        | 00000003 |
| MKAC       | 00000004 |
| MKSMC      | 00000005 |
| MKSMI      | 00000006 |
| MKDAC      | 00000007 |
| MKDN       | 00000008 |
| MKCP       | 00000009 |
| MKOTH      | 0000000A |
| KEK        | 0000000B |
| MAC16609   | 0000000C |
| MAC97971   | 0000000D |
| MAC97972   | 0000000E |
| MAC97973   | 0000000F |
| MAC97974   | 00000010 |
| MAC97975   | 00000011 |
| ZPK        | 00000012 |
| PVKIBM     | 00000013 |
| PVKPVV     | 00000014 |
| PVKOTH     | 00000015 |

## State

| Value Name            | Hex      |
|-----------------------|----------|
| Pre-Active            | 00000001 |
| Active                | 00000002 |
| Deactivated           | 00000003 |
| Compromised           | 00000004 |
| Destroyed             | 00000005 |
| Destroyed Compromised | 00000006 |

## Revocation Reason Code

| Value Name             | Hex      |
|------------------------|----------|
| Unspecified            | 00000001 |
| Key Compromise         | 00000002 |
| CA Compromise          | 00000003 |
| Affiliation Changed    | 00000004 |
| Superseded             | 00000005 |
| Cessation of Operation | 00000006 |
| Privilege Withdrawn    | 00000007 |

## Link Type

| Value Name                  | Hex      |
|-----------------------------|----------|
| Certificate Link            | 00000101 |
| Public Key Link             | 00000102 |
| Private Key Link            | 00000103 |
| Derivation Base Object Link | 00000104 |
| Derived Key Link            | 00000105 |
| Replacement Object Link     | 00000106 |
| Replaced Object Link        | 00000107 |
| Parent Link                 | 00000108 |
| Child Link                  | 00000109 |
| Previous Link               | 0000010A |
| Next Link                   | 0000010B |

## Derivation Method

| Value Name      | Hex      |
|-----------------|----------|
| PBKDF2          | 00000001 |
| HASH            | 00000002 |
| HMAC            | 00000003 |
| ENCRYPT         | 00000004 |
| NIST800-108-C   | 00000005 |
| NIST800-108-F   | 00000006 |
| NIST800-108-DPI | 00000007 |

## Certificate Request Type

| Value Name | Hex                   |
|------------|-----------------------|
| CRMF       | 00000001              |
| PKCS#10    | 00000002              |
| PEM        | 00000003              |
| PGP        | 00000004 (deprecated) |

## Validity Indicator

| Value Name | Hex      |
|------------|----------|
| Valid      | 00000001 |
| Invalid    | 00000002 |
| Unknown    | 00000003 |

## Query Function

| Value Name                   | Hex      |
|------------------------------|----------|
| Query Operations             | 00000001 |
| Query Objects                | 00000002 |
| Query Server Information     | 00000003 |
| Query Application Namespaces | 00000004 |
| Query Extension List         | 00000005 |
| Query Extension Map          | 00000006 |
| Query Attestation Types      | 00000007 |

## Cancellation Result

| Value Name       | Hex      |
|------------------|----------|
| Canceled         | 00000001 |
| Unable to Cancel | 00000002 |
| Completed        | 00000003 |
| Failed           | 00000004 |
| Unavailable      | 00000005 |

## Put Function

| Value Name | Hex      |
|------------|----------|
| New        | 00000001 |
| Replace    | 00000002 |

## Operation

| Value Name           | Hex      |
|----------------------|----------|
| Create               | 00000001 |
| Create Key Pair      | 00000002 |
| Register             | 00000003 |
| Re-key               | 00000004 |
| Derive Key           | 00000005 |
| Certify              | 00000006 |
| Re-certify           | 00000007 |
| Locate               | 00000008 |
| Check                | 00000009 |
| Get                  | 0000000A |
| Get Attributes       | 0000000B |
| Get Attribute List   | 0000000C |
| Add Attribute        | 0000000D |
| Modify Attribute     | 0000000E |
| Delete Attribute     | 0000000F |
| Obtain Lease         | 00000010 |
| Get Usage Allocation | 00000011 |
| Activate             | 00000012 |
| Revoke               | 00000013 |
| Destroy              | 00000014 |
| Archive              | 00000015 |
| Recover              | 00000016 |
| Validate             | 00000017 |
| Query                | 00000018 |
| Cancel               | 00000019 |
| Poll                 | 0000001A |
| Notify               | 0000001B |
| Put                  | 0000001C |
| Re-key Key Pair      | 0000001D |
| Discover Versions    | 0000001E |
| Encrypt              | 0000001F |
| Decrypt              | 00000020 |
| Sign                 | 00000021 |
| Signature Verify     | 00000022 |
| MAC                  | 00000023 |
| MAC Verify           | 00000024 |
| RNG Retrieve         | 00000025 |
| RNG Seed             | 00000026 |
| Hash                 | 00000027 |
| Create Split Key     | 00000028 |
| Join Split Key       | 00000029 |

## Result Status

| Value Name        | Hex      |
|-------------------|----------|
| Success           | 00000000 |
| Operation Failed  | 00000001 |
| Operation Pending | 00000002 |
| Operation Undone  | 00000003 |

## Result Reason

| Value Name                          | Hex      |
|-------------------------------------|----------|
| Item Not Found                      | 00000001 |
| Response Too Large                  | 00000002 |
| Authentication Not Successful       | 00000003 |
| Invalid Message                     | 00000004 |
| Operation Not Supported             | 00000005 |
| Missing Data                        | 00000006 |
| Invalid Field                       | 00000007 |
| Feature Not Supported               | 00000008 |
| Operation Canceled By Requester     | 00000009 |
| Cryptographic Failure               | 0000000A |
| Illegal Operation                   | 0000000B |
| Permission Denied                   | 0000000C |
| Object archived                     | 0000000D |
| Index Out of Bounds                 | 0000000E |
| Application Namespace Not Supported | 0000000F |
| Key Format Type Not Supported       | 00000010 |
| Key Compression Type Not Supported  | 00000011 |
| Encoding Option Error               | 00000012 |
| Key Value Not Present               | 00000013 |
| Attestation Required                | 00000014 |
| Attestation Failed                  | 00000015 |
| General Failure                     | 00000100 |

## Batch Error Continuation Option

| Value Name | Hex      |
|------------|----------|
| Continue   | 00000001 |
| Stop       | 00000002 |
| Undo       | 00000003 |

## Usage Limits Unit

| Value Name | Hex      |
|------------|----------|
| Byte       | 00000001 |
| Object     | 00000002 |

## Encoding Option

| Value Name    | Hex      |
|---------------|----------|
| No Encoding   | 00000001 |
| TTLV Encoding | 00000002 |

## Object Group Member

| Value Name           | Hex      |
|----------------------|----------|
| Group Member Fresh   | 00000001 |
| Group Member Default | 00000002 |

## Alternative Name Type

| Value Name                | Hex      |
|---------------------------|----------|
| Uninterpreted Text String | 00000001 |
| URI                       | 00000002 |
| Object Serial Number      | 00000003 |
| Email Address             | 00000004 |
| DNS Name                  | 00000005 |
| X.500 Distinguished Name  | 00000006 |
| IP Address                | 00000007 |

## Key Value Location Type

| Value Name                | Hex      |
|---------------------------|----------|
| Uninterpreted Text String | 00000001 |
| URI                       | 00000002 |

## Attestation Type

| Value Name           | Hex      |
|----------------------|----------|
| TPM Quote            | 00000001 |
| TCG Integrity Report | 00000002 |
| SAML Assertion       | 00000003 |

