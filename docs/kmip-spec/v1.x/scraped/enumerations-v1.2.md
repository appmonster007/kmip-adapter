# KMIP 1.2 — Enumerations

Total: **35** enumeration types

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

## Cancellation Result

| Name             | Hex        | Description |
| ---------------- | ---------- | ----------- |
| Canceled         | 0x00000001 | The cancel operation succeeded in canceling the pending operation. |
| Unable to Cancel | 0x00000002 | The cancel operation is unable to cancel the pending operation. |
| Completed        | 0x00000003 | The pending operation completed successfully before the cancellation operation was able to cancel it. |
| Failed           | 0x00000004 | The pending operation completed with a failure before the cancellation operation was able to cancel it. |
| Unavailable      | 0x00000005 | Unavailable – The specified correlation value did not match any recently pending or completed asynchronous operations. |

## Certificate Request Type

| Name    | Hex                   | Description |
| ------- | --------------------- | ----------- |
| CRMF    | 0x00000001            |  |
| PKCS#10 | 0x00000002            |  |
| PEM     | 0x00000003            |  |
| PGP     | 00000004 (deprecated) |  |

## Certificate Type

| Name  | Hex                   | Description |
| ----- | --------------------- | ----------- |
| X.509 | 0x00000001            |  |
| PGP   | 00000002 (deprecated) |  |

## Credential Type

| Name                  | Hex        | Description |
| --------------------- | ---------- | ----------- |
| Username and Password | 0x00000001 |  |
| Device                | 0x00000002 |  |
| Attestation           | 0x00000003 |  |

## Cryptographic Algorithm

| Name        | Hex        | Description |
| ----------- | ---------- | ----------- |
| DES         | 0x00000001 |  |
| 3DES        | 0x00000002 |  |
| AES         | 0x00000003 |  |
| RSA         | 0x00000004 |  |
| DSA         | 0x00000005 |  |
| ECDSA       | 0x00000006 |  |
| HMAC-SHA1   | 0x00000007 |  |
| HMAC-SHA224 | 0x00000008 |  |
| HMAC-SHA256 | 0x00000009 |  |
| HMAC-SHA384 | 0x0000000A |  |
| HMAC-SHA512 | 0x0000000B |  |
| HMAC-MD5    | 0x0000000C |  |
| DH          | 0x0000000D |  |
| ECDH        | 0x0000000E |  |
| ECMQV       | 0x0000000F |  |
| Blowfish    | 0x00000010 |  |
| Camellia    | 0x00000011 |  |
| CAST5       | 0x00000012 |  |
| IDEA        | 0x00000013 |  |
| MARS        | 0x00000014 |  |
| RC2         | 0x00000015 |  |
| RC4         | 0x00000016 |  |
| RC5         | 0x00000017 |  |
| SKIPJACK    | 0x00000018 |  |
| Twofish     | 0x00000019 |  |
| EC          | 0x0000001A |  |

## Derivation Method

| Name            | Hex        | Description |
| --------------- | ---------- | ----------- |
| PBKDF2          | 0x00000001 |  |
| HASH            | 0x00000002 |  |
| HMAC            | 0x00000003 |  |
| ENCRYPT         | 0x00000004 |  |
| NIST800-108-C   | 0x00000005 |  |
| NIST800-108-F   | 0x00000006 |  |
| NIST800-108-DPI | 0x00000007 |  |

## Digital Signature Algorithm

| Name                                      | Hex        | Description |
| ----------------------------------------- | ---------- | ----------- |
| MD2 with RSA Encryption (PKCS#1 v1.5)     | 0x00000001 |  |
| MD5 with RSA Encryption (PKCS#1 v1.5)     | 0x00000002 |  |
| SHA-1 with RSA Encryption (PKCS#1 v1.5)   | 0x00000003 |  |
| SHA-224 with RSA Encryption (PKCS#1 v1.5) | 0x00000004 |  |
| SHA-256 with RSA Encryption (PKCS#1 v1.5) | 0x00000005 |  |
| SHA-384 with RSA Encryption (PKCS#1 v1.5) | 0x00000006 |  |
| SHA-512 with RSA Encryption (PKCS#1 v1.5) | 0x00000007 |  |
| RSASSA-PSS (PKCS#1 v2.1)                  | 0x00000008 |  |
| DSA with SHA-1                            | 0x00000009 |  |
| DSA with SHA224                           | 0x0000000A |  |
| DSA with SHA256                           | 0x0000000B |  |
| ECDSA with SHA-1                          | 0x0000000C |  |
| ECDSA with SHA224                         | 0x0000000D |  |
| ECDSA with SHA256                         | 0x0000000E |  |
| ECDSA with SHA384                         | 0x0000000F |  |
| ECDSA with SHA512                         | 0x00000010 |  |

## Encoding Option

| Name          | Hex        | Description |
| ------------- | ---------- | ----------- |
| No Encoding   | 0x00000001 | the wrapped un-encoded value of the Byte String Key Material field in the Key Value structure |
| TTLV Encoding | 0x00000002 | the wrapped TTLV-encoded Key Value structure |

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

## Key Compression Type

| Name                                      | Hex        | Description |
| ----------------------------------------- | ---------- | ----------- |
| EC Public Key Type Uncompressed           | 0x00000001 |  |
| EC Public Key Type X9.62 Compressed Prime | 0x00000002 |  |
| EC Public Key Type X9.62 Compressed Char2 | 0x00000003 |  |
| EC Public Key Type X9.62 Hybrid           | 0x00000004 |  |

## Key Format Type

| Name                          | Hex        | Description |
| ----------------------------- | ---------- | ----------- |
| Raw                           | 0x00000001 | A key that contains only cryptographic key material, encoded as a string of bytes. |
| Opaque                        | 0x00000002 | an encoded key for which the encoding is unknown to the key management system. It is encoded as a string of bytes. |
| PKCS#1                        | 0x00000003 |  |
| PKCS#8                        | 0x00000004 |  |
| X.509                         | 0x00000005 | An encoded object, expressed as a DER-encoded ASN.1 X.509 object. |
| ECPrivateKey                  | 0x00000006 | An ASN.1 encoded elliptic curve private key. |
| Transparent Symmetric Key     | 0x00000007 |  |
| Transparent DSA Private Key   | 0x00000008 |  |
| Transparent DSA Public Key    | 0x00000009 |  |
| Transparent RSA Private Key   | 0x0000000A |  |
| Transparent RSA Public Key    | 0x0000000B |  |
| Transparent DH Private Key    | 0x0000000C |  |
| Transparent DH Public Key     | 0x0000000D |  |
| Transparent ECDSA Private Key | 0x0000000E |  |
| Transparent ECDSA Public Key  | 0x0000000F |  |
| Transparent ECDH Private Key  | 0x00000010 |  |
| Transparent ECDH Public Key   | 0x00000011 |  |
| Transparent ECMQV Private Key | 0x00000012 |  |
| Transparent ECMQV Public Key  | 0x00000013 |  |

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

## Key Value Location Type

| Name                      | Hex        | Description |
| ------------------------- | ---------- | ----------- |
| Uninterpreted Text String | 0x00000001 |  |
| URI                       | 0x00000002 |  |

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

| Name          | Hex        | Description |
| ------------- | ---------- | ----------- |
| Certificate   | 0x00000001 |  |
| Symmetric Key | 0x00000002 |  |
| Public Key    | 0x00000003 |  |
| Private Key   | 0x00000004 |  |
| Split Key     | 0x00000005 |  |
| Template      | 0x00000006 |  |
| Secret Data   | 0x00000007 |  |
| Opaque Object | 0x00000008 |  |
| PGP Key       | 0x00000009 |  |

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

## Put Function

| Name    | Hex        | Description |
| ------- | ---------- | ----------- |
| New     | 0x00000001 |  |
| Replace | 0x00000002 |  |

## Query Function

| Name                         | Hex        | Description |
| ---------------------------- | ---------- | ----------- |
| Query Operations             | 0x00000001 |  |
| Query Objects                | 0x00000002 |  |
| Query Server Information     | 0x00000003 |  |
| Query Application Namespaces | 0x00000004 |  |
| Query Extension List         | 0x00000005 |  |
| Query Extension Map          | 0x00000006 |  |
| Query Attestation Types      | 0x00000007 |  |

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

## Result Reason

| Name                                | Hex        | Description |
| ----------------------------------- | ---------- | ----------- |
| Item Not Found                      | 0x00000001 | No object with the specified Unique Identifier exists |
| Response Too Large                  | 0x00000002 | Maximum Response Size has been exceeded |
| Authentication Not Successful       | 0x00000003 |  |
| Invalid Message                     | 0x00000004 | The request message was not syntactically understood by the server. For example - the invalid use of a known tag |
| Operation Not Supported             | 0x00000005 | The operation requested by the request message is not supported by the server |
| Missing Data                        | 0x00000006 |  |
| Invalid Field                       | 0x00000007 | The request is syntactically valid but some data in the request (other than an attribute value) has an invalid value |
| Feature Not Supported               | 0x00000008 | The operation is supported, but not a specific feature specified in the request is not supported |
| Operation Canceled By Requester     | 0x00000009 |  |
| Cryptographic Failure               | 0x0000000A | The operation failed due to a cryptographic error |
| Illegal Operation                   | 0x0000000B |  |
| Permission Denied                   | 0x0000000C | Client is not allowed to perform the specified operation |
| Object archived                     | 0x0000000D |  |
| Index Out of Bounds                 | 0x0000000E |  |
| Application Namespace Not Supported | 0x0000000F | The particular Application Namespace is not supported, and the server was not able to generate the Application Data field of an Application Specific Information attribute if the field was omitted from the client request |
| Key Format Type Not Supported       | 0x00000010 | The object exists, but the server is unable to provide it in the desired Key Format Type |
| Key Compression Type Not Supported  | 0x00000011 | The object exists, but the server is unable to provide it in the desired Key Compression Type |
| Encoding Option Error               | 0x00000012 | The Encoding Option is not supported as specified by the Encoding Option Enumeration |
| Key Value Not Present               | 0x00000013 | A meta data only object. The key value is not present on the server |
| Attestation Required                | 0x00000014 | Operation requires attestation data which was not provided by the client, and the client has set the Attestation Capable indicator to True |
| Attestation Failed                  | 0x00000015 | Operation requires attestation data and the attestation data provided by the client does not validate |
| General Failure                     | 0x00000100 |  |

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

## Usage Limits Unit

| Name   | Hex        | Description |
| ------ | ---------- | ----------- |
| Byte   | 0x00000001 |  |
| Object | 0x00000002 |  |

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
