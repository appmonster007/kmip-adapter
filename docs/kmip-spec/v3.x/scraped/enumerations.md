# KMIP 3.0 — Enumerations

Total: **61** enumeration types

## Adjustment Type

| Value     | Description                                                                                                              |
|-----------|--------------------------------------------------------------------------------------------------------------------------|
| Increment | Add the Adjustment Parameter to the value. Applies to Integer, Long Integers, Big Integer, Interval, Date Time, and Date |
| Decrement | Subtract the Adjustment Parameter to the value. Applies to Integer, Long Integers, Big Integer, Interval, Date Time, and |
| Negate    | Negate the value. Applies to Integer, Long Integers, Big Integer and Boolean types.                                      |

## Alternative Name Type

| Value                     | Description |
|---------------------------|-------------|
| Uninterpreted Text String |             |
| URI                       |             |
| Object Serial Number      |             |
| Email Address             |             |
| DNS Name                  |             |
| X.500 Distinguished Name  |             |
| IP Address                |             |

## Asynchronous Indicator

| Value      | Description                                                                                                              |
|------------|--------------------------------------------------------------------------------------------------------------------------|
| Mandatory  | The server SHALL process all batch items in the request asynchronously (returning an Asynchronous Correlation Value for  |
| Optional   | The server MAY process each batch item in the request either asynchronously (returning an Asynchronous Correlation Value |
| Prohibited | The server SHALL NOT process any batch item asynchronously. All batch items SHALL be processed synchronously.            |

## Attestation Type

| Value                | Description |
|----------------------|-------------|
| TPM Quote            |             |
| TCG Integrity Report |             |
| SAML Assertion       |             |

## Batch Error Continuation Option

| Value    | Description                                                                                                              |
|----------|--------------------------------------------------------------------------------------------------------------------------|
| Undo     | If any operation in the request fails, then the server SHALL undo all the previous operations. Batch item fails and Resu |
| Stop     | If an operation fails, then the server SHALL NOT continue processing subsequent operations in the request. Completed ope |
| Continue | Return an error for the failed operation, and continue processing subsequent operations in the request. Batch item fails |

## Block Cipher Mode

| Value             | Description |
|-------------------|-------------|
| CBC               |             |
| ECB               |             |
| PCBC              |             |
| CFB               |             |
| OFB               |             |
| CTR               |             |
| CMAC              |             |
| CCM               |             |
| GCM               |             |
| CBC-MAC           |             |
| XTS               |             |
| AESKeyWrapPadding |             |
| NISTKeyWrap       |             |
| X9.102 AESKW      |             |
| X9.102 TDKW       |             |
| X9.102 AKW1       |             |
| X9.102 AKW2       |             |
| AEAD              |             |

## Cancellation Result

| Value            | Description                                                                                                            |
|------------------|------------------------------------------------------------------------------------------------------------------------|
| Canceled         | The cancel operation succeeded in canceling the pending operation.                                                     |
| Unable to Cancel | The cancel operation is unable to cancel the pending operation.                                                        |
| Completed        | The pending operation completed successfully before the cancellation operation was able to cancel it.                  |
| Failed           | The pending operation completed with a failure before the cancellation operation was able to cancel it.                |
| Unavailable      | Unavailable – The specified correlation value did not match any recently pending or completed asynchronous operations. |

## Certificate Request Type

| Value      | Description |
|------------|-------------|
| CRMF       |             |
| PKCS#10    |             |
| PEM        |             |
| (Reserved) |             |

## Certificate Type

| Value | Description |
|-------|-------------|
| X.509 |             |
| PGP   |             |

## Client Registration Method

| Value                | Description                                                                                                              |
|----------------------|--------------------------------------------------------------------------------------------------------------------------|
| Server Pre-Generated | The server has pre-generated the client’s private key. The returned PKCS#12 is protected with HEX( SHA256(Username || Pa |
| Server On-Demand     | The server generates the client’s private key on demand. The returned PKCS#12 is protected with HEX( SHA256(Username ||  |
| Client Generated     | The client generates the private key and sends a Certificate Signing Request to the server to generate the certificate.  |
| Client Registered    | The client generates the private key and the certificates and registers the certificate with the server.                 |

## Credential Type

| Value                 | Description |
|-----------------------|-------------|
| Username and Password |             |
| Device                |             |
| Attestation           |             |
| One Time Password     |             |
| Hashed Password       |             |
| Ticket                |             |
| Password              |             |
| Certificate           |             |

## Cryptographic Algorithm

| Value              | Description |
|--------------------|-------------|
| DES                |             |
| 3DES               |             |
| AES                |             |
| RSA                |             |
| DSA                |             |
| ECDSA              |             |
| HMAC-SHA1          |             |
| HMAC-SHA224        |             |
| HMAC-SHA256        |             |
| HMAC-SHA384        |             |
| HMAC-SHA512        |             |
| HMAC-MD5           |             |
| DH                 |             |
| ECDH               |             |
| ECMQV              |             |
| Blowfish           |             |
| Camellia           |             |
| CAST5              |             |
| IDEA               |             |
| MARS               |             |
| RC2                |             |
| RC4                |             |
| RC5                |             |
| SKIPJACK           |             |
| Twofish            |             |
| EC                 |             |
| One Time Pad       |             |
| ChaCha20           |             |
| Poly1305           |             |
| ChaCha20Poly1305   |             |
| SHA3-224           |             |
| SHA3-256           |             |
| SHA3-384           |             |
| SHA3-512           |             |
| HMAC-SHA3-224      |             |
| HMAC-SHA3-256      |             |
| HMAC-SHA3-384      |             |
| HMAC-SHA3-512      |             |
| SHAKE-128          |             |
| SHAKE-256          |             |
| ARIA               |             |
| SEED               |             |
| SM2                |             |
| SM3                |             |
| SM4                |             |
| GOST R 34.10-2012  |             |
| GOST R 34.11-2012  |             |
| GOST R 34.13-2015  |             |
| GOST 28147-89      |             |
| XMSS               |             |
| SPHINCS-256        |             |
| McEliece           |             |
| McEliece-6960119   |             |
| McEliece-8192128   |             |
| Ed25519            |             |
| Ed448              |             |
| ML-KEM-512         |             |
| ML-KEM-768         |             |
| ML-KEM-1024        |             |
| ML-DSA-44          |             |
| ML-DSA-65          |             |
| ML-DSA-87          |             |
| SLH-DSA-SHA2-128s  |             |
| SLH-DSA-SHA2-128f  |             |
| SLH-DSA-SHA2-192s  |             |
| SLH-DSA-SHA2-192f  |             |
| SLH-DSA-SHA2-256s  |             |
| SLH-DSA-SHA2-256f  |             |
| SLH-DSA-SHAKE-128s |             |
| SLH-DSA-SHAKE-128f |             |
| SLH-DSA-SHAKE-192s |             |
| SLH-DSA-SHAKE-192f |             |
| SLH-DSA-SHAKE-256s |             |
| SLH-DSA-SHAKE-256f |             |

## Data

| Value               | Description |
|---------------------|-------------|
| Decrypt             |             |
| Encrypt             |             |
| Hash                |             |
| MAC MAC Data        |             |
| RNG Retrieve        |             |
| Sign Signature Data |             |
| Signature Verify    |             |

## Deactivation Reason Code

| Value             | Description |
|-------------------|-------------|
| Unspecified       |             |
| Deactivation Date |             |
| Protect Stop Date |             |
| Usage Limit       |             |

## Derivation Method

| Value                   | Description |
|-------------------------|-------------|
| PBKDF2                  |             |
| HASH                    |             |
| HMAC                    |             |
| ENCRYPT                 |             |
| NIST800-108-C           |             |
| NIST800-108-F           |             |
| NIST800-108-DPI         |             |
| Asymmetric Key          |             |
| AWS Signature Version 4 |             |
| HKDF                    |             |

## Destroy Action

| Value                 | Description |
|-----------------------|-------------|
| Unspecified           |             |
| Key Material Deleted  |             |
| Key Material Shredded |             |
| Meta Data Deleted     |             |
| Meta Data Shredded    |             |
| Deleted               |             |
| Shredded              |             |

## Digital Signature Algorithm

| Value                        | Description |
|------------------------------|-------------|
| MD2 with RSA Encryption      |             |
| MD5 with RSA Encryption      |             |
| SHA-1 with RSA Encryption    |             |
| SHA-224 with RSA Encryption  |             |
| SHA-256 with RSA Encryption  |             |
| SHA-384 with RSA Encryption  |             |
| SHA-512 with RSA Encryption  |             |
| RSASSA-PSS                   |             |
| DSA with SHA-1               |             |
| DSA with SHA224              |             |
| DSA with SHA256              |             |
| ECDSA with SHA-1             |             |
| ECDSA with SHA224            |             |
| ECDSA with SHA256            |             |
| ECDSA with SHA384            |             |
| ECDSA with SHA512            |             |
| SHA3-256 with RSA Encryption |             |
| SHA3-384 with RSA Encryption |             |
| SHA3-512 with RSA Encryption |             |

## DRBG Algorithm

| Value       | Description |
|-------------|-------------|
| Unspecified |             |
| Dual-EC     |             |
| Hash        |             |
| HMAC        |             |
| CTR         |             |

## Encoding Option

| Value         | Description                                                                                   |
|---------------|-----------------------------------------------------------------------------------------------|
| No Encoding   | the wrapped un-encoded value of the Byte String Key Material field in the Key Value structure |
| TTLV Encoding | the wrapped TTLV-encoded Key Value structure                                                  |

## Endpoint Role

| Value  | Description                                              |
|--------|----------------------------------------------------------|
| Client | The endpoint that sends requests and receives responses. |
| Server | The endpoint that receives requests and sends responses. |

## Ephemeral

| Value             | Description                                                                      |
|-------------------|----------------------------------------------------------------------------------|
| Data              | Only the Data tag is omitted from the Response Payload                           |
| Empty             | The Response Payload is returned as empty (all fields are omitted)               |
| Unique Identifier | All fields in the Response Payload other than the Unique Identifier are omitted. |

## FIPS186 Variation

| Value              | Description |
|--------------------|-------------|
| Unspecified        |             |
| GP x-Original      |             |
| GP x-Change Notice |             |
| x-Original         |             |
| x-Change Notice    |             |
| k-Original         |             |
| k-Change Notice    |             |

## Hashing Algorithm

| Value       | Description |
|-------------|-------------|
| MD2         |             |
| MD4         |             |
| MD5         |             |
| SHA-1       |             |
| SHA-224     |             |
| SHA-256     |             |
| SHA-384     |             |
| SHA-512     |             |
| RIPEMD-160  |             |
| Tiger       |             |
| Whirlpool   |             |
| SHA-512/224 |             |
| SHA-512/256 |             |
| SHA3-224    |             |
| SHA3-256    |             |
| SHA3-384    |             |
| SHA3-512    |             |

## Interop Function

| Value | Description |
|-------|-------------|
| Begin |             |
| End   |             |
| Reset |             |

## Item Type

| Value              | Description                                                                  |
|--------------------|------------------------------------------------------------------------------|
| Structure          | The ordered concatenation of items.                                          |
| Integer            | Four-byte long (32 bit) signed numbers                                       |
| Long Integer       | Eight-byte long (64 bit) signed numbers.                                     |
| Big Integer        | A sequence of eight-bit bytes                                                |
| Enumeration        | Four-byte long (32 bit) unsigned numbers                                     |
| Boolean            | The value True or False.                                                     |
| Text String        | Sequences of character values.                                               |
| Byte String        | Sequences of bytes containing individual unspecified eight-bit binary values |
| Date Time          | Eight-byte long (64 bit) POSIX Time values in seconds. .                     |
| Interval           | Four-byte long (32 bit) unsigned numbers in seconds                          |
| Date Time Extended | Eight-byte long (64 bit) POSIX Time values in micro-seconds.                 |
| Identifier         | Sequences of character values.                                               |
| Reference          | Sequences of character values.                                               |
| Name Reference     | Sequence of character values.                                                |

## Key Compression Type

| Value                                     | Description |
|-------------------------------------------|-------------|
| EC Public Key Type Uncompressed           |             |
| EC Public Key Type X9.62 Compressed Prime |             |
| EC Public Key Type X9.62 Compressed Char2 |             |
| EC Public Key Type X9.62 Hybrid           |             |

## Key Format Type

| Value                         | Description                                                                                                              |
|-------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Raw                           | A key that contains only cryptographic key material, encoded as a string of bytes.                                       |
| Opaque                        | an encoded key for which the encoding is unknown to the key management system. It is encoded as a string of bytes.       |
| PKCS1                         | an encoded private key, expressed as a DER-encoded ASN.1 PKCS#1 object.                                                  |
| PKCS8                         | An encoded private key, expressed as a DER-encoded ASN.1 PKCS#8 object, supporting both the RSAPrivateKey syntax and Enc |
| X.509                         | An encoded object, expressed as a DER-encoded ASN.1 X.509 object.                                                        |
| ECPrivateKey                  | An ASN.1 encoded elliptic curve private key.                                                                             |
| Several Transparent Key types | algorithm-specific structures containing defined values for the various key types.                                       |

## Key Role Type

| Value    | Description |
|----------|-------------|
| BDK      |             |
| CVK      |             |
| DEK      |             |
| MKAC     |             |
| MKSMC    |             |
| MKSMI    |             |
| MKDAC    |             |
| MKDN     |             |
| MKCP     |             |
| MKOTH    |             |
| KEK      |             |
| MAC16609 |             |
| MAC97971 |             |
| MAC97972 |             |
| MAC97973 |             |
| MAC97974 |             |
| MAC97975 |             |
| ZPK      |             |
| PVKIBM   |             |
| PVKPVV   |             |
| PVKOTH   |             |
| DUKPT    |             |
| IV       |             |
| TRKBK    |             |

## Key Value Location Type

| Value                     | Description |
|---------------------------|-------------|
| Uninterpreted Text String |             |
| URI                       |             |

## Key Wrap Type

| Value         | Description |
|---------------|-------------|
| Not Wrapped   |             |
| As Registered |             |

## Mask Generator

| Value | Description |
|-------|-------------|
| MFG1  |             |

## NIST Key Type

| Value                                  | Description |
|----------------------------------------|-------------|
| Private signature key                  |             |
| Public signature verification key      |             |
| Symmetric authentication key           |             |
| Private authentication key             |             |
| Public authentication key              |             |
| Symmetric data encryption key          |             |
| Symmetric key wrapping key             |             |
| Symmetric random number generation key |             |
| Symmetric master key                   |             |
| Private key transport key              |             |
| Public key transport key               |             |
| Symmetric key agreement key            |             |
| Private static key agreement key       |             |
| Public static key agreement key        |             |
| Private ephemeral key agreement key    |             |
| Public ephemeral key agreement key     |             |
| Symmetric authorization key            |             |
| Private authorization key              |             |
| Public authorization key               |             |

## Object Class

| Value  | Description |
|--------|-------------|
| User   |             |
| System |             |

## Object Type

| Value                        | Description |
|------------------------------|-------------|
| Certificate                  |             |
| Symmetric Key                |             |
| Public Key                   |             |
| Private Key                  |             |
| Split Key                    |             |
| (Reserved)                   |             |
| Secret Data                  |             |
| Opaque Object                |             |
| PGP Key                      |             |
| Certificate Request          |             |
| User                         |             |
| Group                        |             |
| Password Credential          |             |
| Device Credential            |             |
| One Time Password Credential |             |
| Hashed Password Credential   |             |

## Operation

| Value                       | Description |
|-----------------------------|-------------|
| Create                      |             |
| Create Key Pair             |             |
| Register                    |             |
| Re-key                      |             |
| Derive Key                  |             |
| Certify                     |             |
| Re-certify                  |             |
| Locate                      |             |
| Check                       |             |
| Get                         |             |
| Get Attributes              |             |
| Get Attribute List          |             |
| Add Attribute               |             |
| Modify Attribute            |             |
| Delete Attribute            |             |
| Obtain Lease                |             |
| Get Usage Allocation        |             |
| Activate                    |             |
| Revoke                      |             |
| Destroy                     |             |
| Archive                     |             |
| Recover                     |             |
| Validate                    |             |
| Query                       |             |
| Cancel                      |             |
| Poll                        |             |
| Notify                      |             |
| Put                         |             |
| Re-key Key Pair             |             |
| Discover Versions           |             |
| Encrypt                     |             |
| Decrypt                     |             |
| Sign                        |             |
| Signature Verify            |             |
| MAC                         |             |
| MAC Verify                  |             |
| RNG Retrieve                |             |
| RNG Seed                    |             |
| Hash                        |             |
| Create Split Key            |             |
| Join Split Key              |             |
| Import                      |             |
| Export                      |             |
| Log                         |             |
| Login                       |             |
| Logout                      |             |
| Delegated Login             |             |
| Adjust Attribute            |             |
| Set Attribute               |             |
| Set Endpoint Role           |             |
| PKCS#11                     |             |
| Interop                     |             |
| Re-Provision                |             |
| Set Defaults                |             |
| Set Constraints             |             |
| Get Constraints             |             |
| Query Asynchronous Requests |             |
| Process                     |             |
| Ping                        |             |
| Create Group                |             |
| Obliterate                  |             |
| Create User                 |             |
| Create Credential           |             |
| Deactivate                  |             |

## OTP Algorithm

| Value | Description                                      |
|-------|--------------------------------------------------|
| HOTP  | HMAC-Based One-Time Password Algorithm [RFC4226] |
| TOTP  | Time-Based One-Time Password Algorithm [RFC6238] |

## Padding Method

| Value      | Description |
|------------|-------------|
| None       |             |
| OAEP       |             |
| PKCS5      |             |
| SSL3       |             |
| Zeros      |             |
| ANSI X9.23 |             |
| ISO 10126  |             |
| PKCS1 v1.5 |             |
| X9.31      |             |
| PSS        |             |

## Processing Stage

| Value      | Description |
|------------|-------------|
| Submitted  |             |
| In Process |             |
| Completed  |             |

## Profile Name

| Value                                           | Description |
|-------------------------------------------------|-------------|
| (Reserved)                                      |             |
| Complete Server Basic                           |             |
| Complete Server TLS v1.2                        |             |
| Tape Library Client                             |             |
| Tape Library Server                             |             |
| Symmetric Key Lifecycle Client                  |             |
| Symmetric Key Lifecycle Server                  |             |
| Asymmetric Key Lifecycle Client                 |             |
| Asymmetric Key Lifecycle Server                 |             |
| Basic Cryptographic Client                      |             |
| Basic Cryptographic Server                      |             |
| Advanced Cryptographic Client                   |             |
| Advanced Cryptographic Server                   |             |
| RNG Cryptographic Client                        |             |
| RNG Cryptographic Server                        |             |
| Basic Symmetric Key Foundry Client              |             |
| Intermediate Symmetric Key Foundry Client       |             |
| Advanced Symmetric Key Foundry Client           |             |
| Symmetric Key Foundry Server                    |             |
| Opaque Managed Object Store Client              |             |
| Opaque Managed Object Store Server              |             |
| (Reserved)                                      |             |
| (Reserved)                                      |             |
| (Reserved)                                      |             |
| (Reserved)                                      |             |
| Storage Array with Self Encrypting Drive Client |             |
| Storage Array with Self Encrypting Drive Server |             |
| HTTPS Client                                    |             |
| HTTPS Server                                    |             |
| JSON Client                                     |             |
| JSON Server                                     |             |
| XML Client                                      |             |
| XML Server                                      |             |
| AES XTS Client                                  |             |
| AES XTS Server                                  |             |
| Quantum Safe Client                             |             |
| Quantum Safe Server                             |             |
| PKCS#11 Client                                  |             |
| PKCS#11 Server                                  |             |
| Baseline Client                                 |             |
| Baseline Server                                 |             |
| Complete Server                                 |             |

## Protection Level

| Value | Description |
|-------|-------------|
| High  |             |
| Low   |             |

## Put Function

| Value   | Description |
|---------|-------------|
| New     |             |
| Replace |             |

## Query Function

| Value                             | Description |
|-----------------------------------|-------------|
| Query Operations                  |             |
| Query Objects                     |             |
| Query Server Information          |             |
| Query Application Namespaces      |             |
| Query Extension List              |             |
| Query Extension Map               |             |
| Query Attestation Types           |             |
| Query RNGs                        |             |
| Query Validations                 |             |
| Query Profiles                    |             |
| Query Capabilities                |             |
| Query Client Registration Methods |             |
| Query Defaults Information        |             |
| Query Storage Protection Masks    |             |
| Query Credential Information      |             |

## Recommended Curve

| Value            | Description |
|------------------|-------------|
| P-192            |             |
| K-163            |             |
| B-163            |             |
| P-224            |             |
| K-233            |             |
| B-233            |             |
| P-256            |             |
| K-283            |             |
| B-283            |             |
| P-384            |             |
| K-409            |             |
| B-409            |             |
| P-521            |             |
| K-571            |             |
| B-571            |             |
| SECP112R1        |             |
| SECP112R2        |             |
| SECP128R1        |             |
| SECP128R2        |             |
| SECP160K1        |             |
| SECP160R1        |             |
| SECP160R2        |             |
| SECP192K1        |             |
| SECP224K1        |             |
| SECP256K1        |             |
| SECT113R1        |             |
| SECT113R2        |             |
| SECT131R1        |             |
| SECT131R2        |             |
| SECT163R1        |             |
| SECT193R1        |             |
| SECT193R2        |             |
| SECT239K1        |             |
| ANSIX9P192V2     |             |
| ANSIX9P192V3     |             |
| ANSIX9P239V1     |             |
| ANSIX9P239V2     |             |
| ANSIX9P239V3     |             |
| ANSIX9C2PNB163V1 |             |
| ANSIX9C2PNB163V2 |             |
| ANSIX9C2PNB163V3 |             |
| ANSIX9C2PNB176V1 |             |
| ANSIX9C2TNB191V1 |             |
| ANSIX9C2TNB191V2 |             |
| ANSIX9C2TNB191V3 |             |
| ANSIX9C2PNB208W1 |             |
| ANSIX9C2TNB239V1 |             |
| ANSIX9C2TNB239V2 |             |
| ANSIX9C2TNB239V3 |             |
| ANSIX9C2PNB272W1 |             |
| ANSIX9C2PNB304W1 |             |
| ANSIX9C2TNB359V1 |             |
| ANSIX9C2PNB368W1 |             |
| ANSIX9C2TNB431R1 |             |
| BRAINPOOLP160R1  |             |
| BRAINPOOLP160T1  |             |
| BRAINPOOLP192R1  |             |
| BRAINPOOLP192T1  |             |
| BRAINPOOLP224R1  |             |
| BRAINPOOLP224T1  |             |
| BRAINPOOLP256R1  |             |
| BRAINPOOLP256T1  |             |
| BRAINPOOLP320R1  |             |
| BRAINPOOLP320T1  |             |
| BRAINPOOLP384R1  |             |
| BRAINPOOLP384T1  |             |
| BRAINPOOLP512R1  |             |
| BRAINPOOLP512T1  |             |
| CURVE25519       |             |
| CURVE448         |             |

## Result Reason

| Value                                                                                                         | Description                                                                                                              |
|---------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Application Namespace Not Supported                                                                           | The particular Application Namespace is not supported, and the server was not able to generate the Application Data fiel |
| Attestation Failed                                                                                            | Operation requires attestation data and the attestation data provided by the client does not validate                    |
| Attestation Required                                                                                          | Operation requires attestation data which was not provided by the client, and the client has set the Attestation Capable |
| Attribute Instance Not Found                                                                                  | A referenced attribute was found, but the specific instance was not found                                                |
| Attribute Not Found                                                                                           | A referenced attribute was not found at all on an object                                                                 |
| Attribute Read Only                                                                                           | Attempt to set a Read Only Attribute                                                                                     |
| Attribute Single Instance                                                                                     | Attempt to provide multiple values for a single instance attribute                                                       |
| Authentication not successful                                                                                 | The authentication information in the request could not be validated, or was not found                                   |
| Bad Cryptographic Parameters                                                                                  | Bad Cryptographic Parameters                                                                                             |
| Bad Password                                                                                                  | Key Format Type is PKCS#12, but missing or multiple PKCS#12 Password Links, or not Secret Data, or not Active            |
| Circular Link Error                                                                                           | A ParentLink sets up a directed acyclic relationship. Detection of a cycle in the relationship graph results in this rea |
| Codec Error                                                                                                   | The low level TTLV, XML, JSON etc. was badly formed and not understood by the server.TTLV connections should be closed a |
| Constraint Violation                                                                                          | The request failed because one or more constraints were violated                                                         |
| Cryptographic Failure                                                                                         | The operation failed due to a cryptographic error                                                                        |
| Duplicate Process Request                                                                                     | The asynchronous request specified was already processed                                                                 |
| Encoding Option Error                                                                                         | The Encoding Option is not supported as specified by the Encoding Option Enumeration                                     |
| Feature Not Supported                                                                                         | The operation is supported, but not a specific feature specified in the request is not supported                         |
| General failure                                                                                               | The request failed for a reason other than the defined reasons above                                                     |
| Illegal Object Type                                                                                           | Check cannot be performed on this object type                                                                            |
| Incompatible Cryptographic Usage Mask                                                                         | The cryptographic algorithm or other parameters is not valid for the requested operation                                 |
| Internal Server Error                                                                                         | The server had an internal error and could not process the request at this time.                                         |
| Invalid Asynchronous Correlation Value                                                                        | No outstanding operation with the specified Asynchronous Correlation Value exists                                        |
| Invalid Attribute                                                                                             | An attribute is invalid for this object for this operation                                                               |
| Invalid Attribute Value                                                                                       | The value supplied for an attribute is invalid                                                                           |
| Invalid Correlation Value                                                                                     | For streaming cryptographic operations                                                                                   |
| Invalid CSR                                                                                                   | Invalid Certificate Signing Request                                                                                      |
| Invalid Data Type                                                                                             | A data type was invalid for the requested operation                                                                      |
| Invalid Field                                                                                                 | The request is syntactically valid but some data in the request (other than an attribute value) has an invalid value     |
| Invalid Message                                                                                               | The request message was not syntactically understood by the server. For example - the invalid use of a known tag         |
| Invalid Object Type                                                                                           | Specified object is not valid for the requested operation                                                                |
| Invalid Password                                                                                              |                                                                                                                          |
| Invalid Ticket                                                                                                | The ticket was invalid                                                                                                   |
| Item Not Found                                                                                                | No object with the specified Unique Identifier exists                                                                    |
| Key Compression Type Not Supported                                                                            | The object exists, but the server is unable to provide it in the desired Key Compression Type                            |
| Key Format Type Not Supported                                                                                 | The object exists, but the server is unable to provide it in the desired Key Format Type                                 |
| Key Value Not Present                                                                                         | A meta data only object. The key value is not present on the server                                                      |
| Key Wrap Type Not Supported                                                                                   | Key Wrap Type Type is not supported by the server                                                                        |
| Missing data                                                                                                  | The operation REQUIRED additional information in the request, which was not present                                      |
| Missing Initialization Vector                                                                                 | Missing IV when required for crypto operation                                                                            |
| Multi Valued Attribute                                                                                        | Attempt to Set or Adjust an attribute that has multiple values                                                           |
| Non Unique Name Attribute                                                                                     | Trying to perform an operation that requests the server to break the constraint on Name attribute being unique           |
| Not Extractable                                                                                               | Object is not Extractable                                                                                                |
| Numeric Range                                                                                                 | An operation produced a number that is too large or too small to be stored in the specified data type                    |
| Object Already Exists                                                                                         | for operations such as Import that require that no object with a specific unique identifier exists on a server           |
| Object Archived                                                                                               | The object SHALL be recovered from the archive before performing the operation                                           |
| Object Destroyed                                                                                              | Object exists, but has already been destroyed                                                                            |
| Object Not Found                                                                                              | A requested managed object was not found or did not exist                                                                |
| Object Type                                                                                                   | Invalid object type for the operation                                                                                    |
| Operation canceled by requester                                                                               | The operation was asynchronous, and the operation was canceled by the Cancel operation before it completed successfully  |
| Operation Not Supported                                                                                       | The operation requested by the request message is not supported by the server                                            |
| Permission Denied                                                                                             | Client is not allowed to perform the specified operation                                                                 |
| PKCS#11 Codec Error                                                                                           | There is a Codec error in the Input parameter                                                                            |
| PKCS#11 Invalid Function                                                                                      | The PKCS function is not in the interface                                                                                |
| PKCS#11 Invalid Interface                                                                                     | The interface is unknown or unavailable in the server                                                                    |
| Protection Storage Unavailable, Private Protection Storage Unavailable, Public Protection Storage Unavailable | The operation could not be completed with the protections requested (or defaulted).                                      |
| Read Only Attribute                                                                                           | Attempt to set a Read Only Attribute                                                                                     |
| Response Too Large                                                                                            | Maximum Response Size has been exceeded                                                                                  |
| Sensitive                                                                                                     | Sensitive keys may not be retrieved unwrapped                                                                            |
| Server Limit Exceeded                                                                                         | Some limit on the server such as database size has been exceeded                                                         |
| Unknown Enumeration                                                                                           | An enumerated value is not known by the server                                                                           |
| Unknown Message Extension                                                                                     | The server does not support the supplied Message Extension                                                               |
| Unknown Tag                                                                                                   | A tag is not known by the server                                                                                         |
| Unsupported Attribute                                                                                         | Attribute is valid in the specification but unsupported by the Server                                                    |
| Unsupported Cryptographic Parameters                                                                          | Cryptographic Parameters are valid in the specification but unsupported by the Server                                    |
| Unsupported Protocol Version                                                                                  | The operation cannot be performed with the provided protocol version                                                     |
| Usage Limit Exceeded                                                                                          | The usage limits or request count has been exceeded                                                                      |
| Wrapping Object Archived                                                                                      | Wrapping Object is archived                                                                                              |
| Wrapping Object Destroyed                                                                                     | The object exists, but is destroyed                                                                                      |
| Wrapping Object Not Found                                                                                     | Wrapping object does not exist                                                                                           |
| Wrong Key Lifecycle State                                                                                     | The key lifecycle state is invalid for the operation, for example not Active for an Encrypt operation                    |
| General failure                                                                                               | The request failed for a reason other than any other reason enumeration value.                                           |

## Result Status

| Value             | Description |
|-------------------|-------------|
| Success           |             |
| Operation Failed  |             |
| Operation Pending |             |
| Operation Undone  |             |

## Revocation Reason Code

| Value                  | Description |
|------------------------|-------------|
| Unspecified            |             |
| Key Compromise         |             |
| CA Compromise          |             |
| Affiliation Changed    |             |
| Superseded             |             |
| Cessation of Operation |             |
| Privilege Withdrawn    |             |

## RNG Algorithm

| Value       | Description |
|-------------|-------------|
| Unspecified |             |
| FIPS 186-2  |             |
| DRBG        |             |
| NRBG        |             |
| ANSI X9.31  |             |
| ANSI X9.62  |             |

## RNG Mode

| Value                    | Description |
|--------------------------|-------------|
| Unspecified              |             |
| Shared Instantiation     |             |
| Non-Shared Instantiation |             |

## Secret Data Type

| Value    | Description |
|----------|-------------|
| Password |             |
| Seed     |             |

## Shredding Algorithm

| Value         | Description |
|---------------|-------------|
| Unspecified   |             |
| Cryptographic |             |
| Unsupported   |             |

## Split Key Method

| Value                          | Description |
|--------------------------------|-------------|
| XOR                            |             |
| Polynomial Sharing GF (2 16 )  |             |
| Polynomial Sharing Prime Field |             |
| Polynomial Sharing GF (2 8 )   |             |

## Split Key Polynomial

| Value          | Description |
|----------------|-------------|
| Polynomial-283 |             |
| Polynomial-285 |             |

## State

| Value                 | Description |
|-----------------------|-------------|
| Pre-Active            |             |
| Active                |             |
| Deactivated           |             |
| Compromised           |             |
| Destroyed             |             |
| Destroyed Compromised |             |

## Ticket Type

| Value | Description |
|-------|-------------|
| Login |             |

## Unique Identifier

| Value                       | Description |
|-----------------------------|-------------|
| ID Placeholder              |             |
| Certify                     |             |
| Create                      |             |
| Create Key Pair             |             |
| Create Key Pair Private Key |             |
| Create Key Pair Public Key  |             |
| Create Split Key            |             |
| Derive Key                  |             |
| Import                      |             |
| Join Split Key              |             |
| Locate                      |             |
| Register                    |             |
| Re-key                      |             |
| Re-certify                  |             |
| Re-key Key Pair             |             |
| Re-key Key Pair Private Key |             |
| Re-key Key Pair Public Key  |             |
| Re-Provision                |             |
| Create User                 |             |
| Create Group                |             |
| Create Credential           |             |

## Unwrap Mode

| Value         | Description |
|---------------|-------------|
| Unspecified   |             |
| Processed     |             |
| Not Processed |             |

## Usage Limits Unit

| Value  | Description |
|--------|-------------|
| Byte   |             |
| Object |             |

## Validity Indicator

| Value   | Description |
|---------|-------------|
| Valid   |             |
| Invalid |             |
| Unknown |             |

## Wrapping Method

| Value                  | Description                                                                                                  |
|------------------------|--------------------------------------------------------------------------------------------------------------|
| Encrypt only           | encryption using a symmetric key or public key, or authenticated encryption algorithms that use a single key |
| MAC/sign only          | either MACing the Key Value with a symmetric key, or signing the Key Value with a private key                |
| Encrypt then MAC/sign  |                                                                                                              |
| MAC/sign then encrypt. |                                                                                                              |
| TR-31                  |                                                                                                              |

## Validation Authority Type

| Value           | Description |
|-----------------|-------------|
| Unspecified     |             |
| NIST CMVP       |             |
| Common Criteria |             |

## Validation Type

| Value       | Description |
|-------------|-------------|
| Unspecified |             |
| Hardware    |             |
| Software    |             |
| Firmware    |             |
| Hybrid      |             |

