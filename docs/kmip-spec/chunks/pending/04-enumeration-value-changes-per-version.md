## 4. Enumeration Value Changes Per Version

### v1.2

**Added values:**

| Enumeration | Impl? | New Values |
|---|---|---|
| Alternative Name Type | ✅ | DNS Name, Email Address, IP Address, Object Serial Number, URI, Uninterpreted Text String, X.500 Distinguished Name |
| Attestation Type | ✅ | SAML Assertion, TCG Integrity Report, TPM Quote |
| Batch Error Continuation Option | ✅ | Continue, Stop, Undo |
| Block Cipher Mode | ✅ | AESKeyWrapPadding, CBC, CBC-MAC, CCM, CFB, CMAC, CTR, ECB, GCM, NISTKeyWrap, OFB, PCBC, X9.102 AESKW, X9.102 AKW1, X9.102 AKW2, X9.102 TDKW, XTS |
| Cancellation Result | ✅ | Canceled, Completed, Failed, Unable to Cancel, Unavailable |
| Certificate Request Type | ✅ | CRMF, PEM, PGP, PKCS#10 |
| Certificate Type | ✅ | PGP, X.509 |
| Credential Type | ✅ | Attestation, Device, Username and Password |
| Cryptographic Algorithm | ✅ | 3DES, AES, Blowfish, CAST5, Camellia, DES, DH, DSA, EC, ECDH, ECDSA, ECMQV, HMAC-MD5, HMAC-SHA1, HMAC-SHA224, HMAC-SHA256, HMAC-SHA384, HMAC-SHA512, IDEA, MARS, RC2, RC4, RC5, RSA, SKIPJACK, Twofish |
| Derivation Method | ✅ | ENCRYPT, HASH, HMAC, NIST800-108-C, NIST800-108-DPI, NIST800-108-F, PBKDF2 |
| Digital Signature Algorithm | ✅ | DSA with SHA-1, DSA with SHA224, DSA with SHA256, ECDSA with SHA-1, ECDSA with SHA224, ECDSA with SHA256, ECDSA with SHA384, ECDSA with SHA512, MD2 with RSA Encryption (PKCS#1 v1.5), MD5 with RSA Encryption (PKCS#1 v1.5), RSASSA-PSS (PKCS#1 v2.1), SHA-1 with RSA Encryption (PKCS#1 v1.5), SHA-224 with RSA Encryption (PKCS#1 v1.5), SHA-256 with RSA Encryption (PKCS#1 v1.5), SHA-384 with RSA Encryption (PKCS#1 v1.5), SHA-512 with RSA Encryption (PKCS#1 v1.5) |
| Encoding Option | ✅ | No Encoding, TTLV Encoding |
| Hashing Algorithm | ✅ | MD2, MD4, MD5, RIPEMD-160, SHA-1, SHA-224, SHA-256, SHA-384, SHA-512, SHA-512/224, SHA-512/256, Tiger, Whirlpool |
| Key Compression Type | ✅ | EC Public Key Type Uncompressed, EC Public Key Type X9.62 Compressed Char2, EC Public Key Type X9.62 Compressed Prime, EC Public Key Type X9.62 Hybrid |
| Key Format Type | ✅ | ECPrivateKey, Opaque, PKCS#1, PKCS#8, Raw, Transparent DH Private Key, Transparent DH Public Key, Transparent DSA Private Key, Transparent DSA Public Key, Transparent ECDH Private Key, Transparent ECDH Public Key, Transparent ECDSA Private Key, Transparent ECDSA Public Key, Transparent ECMQV Private Key, Transparent ECMQV Public Key, Transparent RSA Private Key, Transparent RSA Public Key, Transparent Symmetric Key, X.509 |
| Key Role Type | ✅ | BDK, CVK, DEK, KEK, MAC16609, MAC97971, MAC97972, MAC97973, MAC97974, MAC97975, MKAC, MKCP, MKDAC, MKDN, MKOTH, MKSMC, MKSMI, PVKIBM, PVKOTH, PVKPVV, ZPK |
| Key Value Location Type | ✅ | URI, Uninterpreted Text String |
| Link Type | ✅ | Certificate Link, Child Link, Derivation Base Object Link, Derived Key Link, Next Link, Parent Link, Previous Link, Private Key Link, Public Key Link, Replaced Object Link, Replacement Object Link |
| Name Type | ✅ | URI, Uninterpreted Text String |
| Object Group Member | ✅ | Group Member Default, Group Member Fresh |
| Object Type | ✅ | Certificate, Opaque Object, PGP Key, Private Key, Public Key, Secret Data, Split Key, Symmetric Key, Template |
| Operation | ✅ | Activate, Add Attribute, Archive, Cancel, Certify, Check, Create, Create Key Pair, Create Split Key, Decrypt, Delete Attribute, Derive Key, Destroy, Discover Versions, Encrypt, Get, Get Attribute List, Get Attributes, Get Usage Allocation, Hash, Join Split Key, Locate, MAC, MAC Verify, Modify Attribute, Notify, Obtain Lease, Poll, Put, Query, RNG Retrieve, RNG Seed, Re-certify, Re-key, Re-key Key Pair, Recover, Register, Revoke, Sign, Signature Verify, Validate |
| Padding Method | ✅ | ANSI X9.23, ISO 10126, None, OAEP, PKCS1 v1.5, PKCS5, PSS, SSL3, X9.31, Zeros |
| Put Function | ✅ | New, Replace |
| Query Function | ✅ | Query Application Namespaces, Query Attestation Types, Query Extension List, Query Extension Map, Query Objects, Query Operations, Query Server Information |
| Recommended Curve | ✅ | ANSIX9C2PNB163V1, ANSIX9C2PNB163V2, ANSIX9C2PNB163V3, ANSIX9C2PNB176V1, ANSIX9C2PNB208W1, ANSIX9C2PNB272W1, ANSIX9C2PNB304W1, ANSIX9C2PNB368W1, ANSIX9C2TNB191V1, ANSIX9C2TNB191V2, ANSIX9C2TNB191V3, ANSIX9C2TNB239V1, ANSIX9C2TNB239V2, ANSIX9C2TNB239V3, ANSIX9C2TNB359V1, ANSIX9C2TNB431R1, ANSIX9P192V2, ANSIX9P192V3, ANSIX9P239V1, ANSIX9P239V2, ANSIX9P239V3, B-163, B-233, B-283, B-409, B-571, BRAINPOOLP160R1, BRAINPOOLP160T1, BRAINPOOLP192R1, BRAINPOOLP192T1, BRAINPOOLP224R1, BRAINPOOLP224T1, BRAINPOOLP256R1, BRAINPOOLP256T1, BRAINPOOLP320R1, BRAINPOOLP320T1, BRAINPOOLP384R1, BRAINPOOLP384T1, BRAINPOOLP512R1, BRAINPOOLP512T1, K-163, K-233, K-283, K-409, K-571, P-192, P-224, P-256, P-384, P-521, SECP112R1, SECP112R2, SECP128R1, SECP128R2, SECP160K1, SECP160R1, SECP160R2, SECP192K1, SECP224K1, SECP256K1, SECT113R1, SECT113R2, SECT131R1, SECT131R2, SECT163R1, SECT193R1, SECT193R2, SECT239K1 |
| Result Reason | ✅ | Application Namespace Not Supported, Attestation Failed, Attestation Required, Authentication Not Successful, Cryptographic Failure, Encoding Option Error, Feature Not Supported, General Failure, Illegal Operation, Index Out of Bounds, Invalid Field, Invalid Message, Item Not Found, Key Compression Type Not Supported, Key Format Type Not Supported, Key Value Not Present, Missing Data, Object archived, Operation Canceled By Requester, Operation Not Supported, Permission Denied, Response Too Large |
| Result Status | ✅ | Operation Failed, Operation Pending, Operation Undone, Success |
| Revocation Reason Code | ✅ | Affiliation Changed, CA Compromise, Cessation of Operation, Key Compromise, Privilege Withdrawn, Superseded, Unspecified |
| Secret Data Type | ✅ | Password, Seed |
| Split Key Method | ✅ | Polynomial Sharing GF (216), Polynomial Sharing GF (28), Polynomial Sharing Prime Field, XOR |
| State | ✅ | Active, Compromised, Deactivated, Destroyed, Destroyed Compromised, Pre-Active |
| Usage Limits Unit | ✅ | Byte, Object |
| Validity Indicator | ✅ | Invalid, Unknown, Valid |
| Wrapping Method | ✅ | Encrypt, Encrypt then MAC/sign, MAC/sign, MAC/sign then encrypt, TR-31 |

### v1.3

**Added values:**

| Enumeration | Impl? | New Values |
|---|---|---|
| Client Registration Method | ✅ | Client Generated, Client Registered, Server On-Demand, Server Pre-Generated, Unspecified |
| Cryptographic Algorithm | ✅ | One Time Pad |
| DRBG Algorithm | ✅ | CTR, Dual-EC, HMAC, Hash, Unspecified |
| Destroy Action | ✅ | Deleted, Key Material Deleted, Key Material Shredded, Meta Data Deleted, Meta Data Shredded, Shredded, Unspecified |
| FIPS186 Variation | ✅ | GP x-Change Notice, GP x-Original, Unspecified, k-Change Notice, k-Original, x-Change Notice, x-Original |
| Key Format Type | ✅ | Transparent EC Private Key, Transparent EC Public Key |
| Profile Name | ✅ | Advanced Cryptographic Client KMIP v1.2, Advanced Cryptographic Client KMIP v1.3, Advanced Cryptographic Server KMIP v1.2, Advanced Cryptographic Server KMIP v1.3, Advanced Symmetric Key Foundry Client KMIP v1.0, Advanced Symmetric Key Foundry Client KMIP v1.1, Advanced Symmetric Key Foundry Client KMIP v1.2, Advanced Symmetric Key Foundry Client KMIP v1.3, Asymmetric Key Lifecycle Client KMIP v1.0, Asymmetric Key Lifecycle Client KMIP v1.1, Asymmetric Key Lifecycle Client KMIP v1.2, Asymmetric Key Lifecycle Client KMIP v1.3, Asymmetric Key Lifecycle Server KMIP v1.0, Asymmetric Key Lifecycle Server KMIP v1.1, Asymmetric Key Lifecycle Server KMIP v1.2, Asymmetric Key Lifecycle Server KMIP v1.3, Baseline Client Basic KMIP v1.2, Baseline Client Basic KMIP v1.3, Baseline Client TLS v1.2 KMIP v1.2, Baseline Client TLS v1.2 KMIP v1.3, Baseline Server Basic KMIP v1.2, Baseline Server Basic KMIP v1.3, Baseline Server TLS v1.2 KMIP v1.2, Baseline Server TLS v1.2 KMIP v1.3, Basic Cryptographic Client KMIP v1.2, Basic Cryptographic Client KMIP v1.3, Basic Cryptographic Server KMIP v1.2, Basic Cryptographic Server KMIP v1.3, Basic Symmetric Key Foundry Client KMIP v1.0, Basic Symmetric Key Foundry Client KMIP v1.1, Basic Symmetric Key Foundry Client KMIP v1.2, Basic Symmetric Key Foundry Client KMIP v1.3, Complete Server Basic KMIP v1.2, Complete Server Basic KMIP v1.3, Complete Server TLS v1.2 KMIP v1.2, Complete Server TLS v1.2 KMIP v1.3, HTTPS Client KMIP v1.0, HTTPS Client KMIP v1.1, HTTPS Client KMIP v1.2, HTTPS Client KMIP v1.3, HTTPS Server KMIP v1.0, HTTPS Server KMIP v1.1, HTTPS Server KMIP v1.2, HTTPS Server KMIP v1.3, Intermediate Symmetric Key Foundry Client KMIP v1.0, Intermediate Symmetric Key Foundry Client KMIP v1.1, Intermediate Symmetric Key Foundry Client KMIP v1.2, Intermediate Symmetric Key Foundry Client KMIP v1.3, JSON Client KMIP v1.0, JSON Client KMIP v1.1, JSON Client KMIP v1.2, JSON Client KMIP v1.3, JSON Server KMIP v1.0, JSON Server KMIP v1.1, JSON Server KMIP v1.2, JSON Server KMIP v1.3, Opaque Managed Object Store Client KMIP v1.0, Opaque Managed Object Store Client KMIP v1.1, Opaque Managed Object Store Client KMIP v1.2, Opaque Managed Object Store Client KMIP v1.3, Opaque Managed Object Store Server KMIP v1.0, Opaque Managed Object Store Server KMIP v1.1, Opaque Managed Object Store Server KMIP v1.2, Opaque Managed Object Store Server KMIP v1.3, RNG Cryptographic Client KMIP v1.2, RNG Cryptographic Client KMIP v1.3, RNG Cryptographic Server KMIP v1.2, RNG Cryptographic Server KMIP v1.3, Storage Array with Self Encrypting Drive Client KMIP v1.0, Storage Array with Self Encrypting Drive Client KMIP v1.1, Storage Array with Self Encrypting Drive Client KMIP v1.2, Storage Array with Self Encrypting Drive Client KMIP v1.3, Storage Array with Self Encrypting Drive Server KMIP v1.0, Storage Array with Self Encrypting Drive Server KMIP v1.1, Storage Array with Self Encrypting Drive Server KMIP v1.2, Storage Array with Self Encrypting Drive Server KMIP v1.3, Suite B minLOS_128 Client KMIP v1.0, Suite B minLOS_128 Client KMIP v1.1, Suite B minLOS_128 Client KMIP v1.2, Suite B minLOS_128 Client KMIP v1.3, Suite B minLOS_128 Server KMIP v1.0, Suite B minLOS_128 Server KMIP v1.1, Suite B minLOS_128 Server KMIP v1.2, Suite B minLOS_128 Server KMIP v1.3, Suite B minLOS_192 Client KMIP v1.0, Suite B minLOS_192 Client KMIP v1.1, Suite B minLOS_192 Client KMIP v1.2, Suite B minLOS_192 Client KMIP v1.3, Suite B minLOS_192 Server KMIP v1.0, Suite B minLOS_192 Server KMIP v1.1, Suite B minLOS_192 Server KMIP v1.2, Suite B minLOS_192 Server KMIP v1.3, Symmetric Key Foundry Server KMIP v1.0, Symmetric Key Foundry Server KMIP v1.1, Symmetric Key Foundry Server KMIP v1.2, Symmetric Key Foundry Server KMIP v1.3, Symmetric Key Lifecycle Client KMIP v1.0, Symmetric Key Lifecycle Client KMIP v1.1, Symmetric Key Lifecycle Client KMIP v1.2, Symmetric Key Lifecycle Client KMIP v1.3, Symmetric Key Lifecycle Server KMIP v1.0, Symmetric Key Lifecycle Server KMIP v1.1, Symmetric Key Lifecycle Server KMIP v1.2, Symmetric Key Lifecycle Server KMIP v1.3, Tape Library Client KMIP v1.0, Tape Library Client KMIP v1.1, Tape Library Client KMIP v1.2, Tape Library Client KMIP v1.3, Tape Library Server KMIP v1.0, Tape Library Server KMIP v1.1, Tape Library Server KMIP v1.2, Tape Library Server KMIP v1.3, XML Client KMIP v1.0, XML Client KMIP v1.1, XML Client KMIP v1.2, XML Client KMIP v1.3, XML Server KMIP v1.0, XML Server KMIP v1.1, XML Server KMIP v1.2, XML Server KMIP v1.3 |
| Query Function | ✅ | Query Capabilities, Query Client Registration Methods, Query Profiles, Query RNGs, Query Validations |
| RNG Algorithm | ✅ | ANSI X9.31, ANSI X9.62, DRBG, FIPS 186-2, NRBG, Unspecified |
| RNG Mode | ✅ | Non-Shared Instantiation, Shared Instantiation, Unspecified |
| Shredding Algorithm | ✅ | Cryptographic, Unspecified, Unsupported |
| Unwrap Mode | ✅ | Not Processed, Processed, Unspecified |
| Validation Authority Type | ✅ | Common Criteria, NIST CMVP, Unspecified |
| Validation Type | ✅ | Firmware, Hardware, Hybrid, Software, Unspecified |

### v1.4

**Added values:**

| Enumeration | Impl? | New Values |
|---|---|---|
| Block Cipher Mode | ✅ | AEAD |
| Cryptographic Algorithm | ✅ | ChaCha20, ChaCha20Poly1305, HMAC-SHA3-224, HMAC-SHA3-256, HMAC-SHA3-384, HMAC-SHA3-512, Poly1305, SHA3-224, SHA3-256, SHA3-384, SHA3-512, SHAKE-128, SHAKE-256 |
| Derivation Method | ✅ | Asymmetric Key |
| Digital Signature Algorithm | ✅ | SHA3-256 with RSA Encryption, SHA3-384 with RSA Encryption, SHA3-512 with RSA Encryption |
| Hashing Algorithm | ✅ | SHA-3-224, SHA-3-256, SHA-3-384, SHA-3-512 |
| Key Format Type | ✅ | PKCS#12 |
| Key Role Type | ✅ | DUKPT, IV, TRKBK |
| Key Wrap Type | ✅ | As Registered, Not Wrapped |
| Link Type | ✅ | PKCS#12 Certificate Link, PKCS#12 Password Link |
| Mask Generator | ✅ | MGF1 |
| Operation | ✅ | Export, Import |
| Profile Name | ✅ | Advanced Cryptographic Client KMIP v1.4, Advanced Cryptographic Server KMIP v1.4, Advanced Symmetric Key Foundry Client KMIP v1.4, Asymmetric Key Lifecycle Client KMIP v1.4, Asymmetric Key Lifecycle Server KMIP v1.4, Baseline Client Basic KMIP v1.4, Baseline Client TLS v1.2 KMIP v1.4, Baseline Server Basic KMIP v1.4, Baseline Server TLS v1.2 KMIP v1.4, Basic Cryptographic Client KMIP v1.4, Basic Cryptographic Server KMIP v1.4, Basic Symmetric Key Foundry Client KMIP v1.4, Complete Server Basic KMIP v1.4, Complete Server TLS v1.2 KMIP v1.4, HTTPS Client KMIP v1.4, HTTPS Server KMIP v1.4, Intermediate Symmetric Key Foundry Client KMIP v1.4, JSON Client KMIP v1.4, JSON Server KMIP v1.4, Opaque Managed Object Store Client KMIP v1.4, Opaque Managed Object Store Server KMIP v1.4, RNG Cryptographic Client KMIP v1.4, RNG Cryptographic Server KMIP v1.4, Storage Array with Self Encrypting Drive Client KMIP v1.4, Storage Array with Self Encrypting Drive Server KMIP v1.4, Suite B minLOS_128 Client KMIP v1.4, Suite B minLOS_128 Server KMIP v1.4, Suite B minLOS_192 Client KMIP v1.4, Suite B minLOS_192 Server KMIP v1.4, Symmetric Key Foundry Server KMIP v1.4, Symmetric Key Lifecycle Client KMIP v1.4, Symmetric Key Lifecycle Server KMIP v1.4, Tape Library Client KMIP v1.4, Tape Library Server KMIP v1.4, XML Client KMIP v1.4, XML Server KMIP v1.4 |
| Result Reason | ✅ | Not Extractable, Object Already Exists, Sensitive |

### v2.0

**Added values:**

| Enumeration | Impl? | New Values |
|---|---|---|
| Adjustment Type | ✅ | Decrement, Negate |
| Asynchronous Indicator | ✅ | Optional, Prohibited |
| Certificate Type | ✅ | (PGP |
| Credential Type | ✅ | Hashed Password, One Time Password, Ticket |
| Cryptographic Algorithm | ✅ | ARIA, Ed25519, Ed448, GOST 28147-89, GOST R 34.10-2012, GOST R 34.11-2012, GOST R 34.13-2015, McEliece, McEliece-6960119, McEliece-8192128, SEED, SM2, SM3, SM4, SPHINCS-256, XMSS |
| Data | ✅ | Decrypt, Encrypt, Hash, MAC MAC Data, RNG Retrieve, Sign Signature Data, Signature Verify |
| Derivation Method | ✅ | AWS Signature Version 4, HKDF |
| Digital Signature Algorithm | ✅ | MD2 with RSA Encryption, MD5 with RSA Encryption, RSASSA-PSS, SHA-1 with RSA Encryption, SHA-224 with RSA Encryption, SHA-256 with RSA Encryption, SHA-384 with RSA Encryption, SHA-512 with RSA Encryption |
| Endpoint Role | ✅ | Server |
| Hashing Algorithm | ✅ | SHA3-224, SHA3-256, SHA3-384, SHA3-512 |
| Interop Function | ✅ | End, Reset |
| Item Type | ❌ | Big Integer, Boolean, Byte String, Date Time, Date Time Extended, Enumeration, Integer, Interval, Long Integer, Text String |
| Key Format Type | ✅ | PKCS1, PKCS8, Several Transparent Key types |
| Link Type | ✅ | Wrapping Key Link |
| Mask Generator | ✅ | MFG1 |
| NIST Key Type | ✅ | Private authentication key, Private authorization key, Private ephemeral key agreement key, Private key transport key, Private signature key, Private static key agreement key, Public authentication key, Public authorization key, Public ephemeral key agreement key, Public key transport key, Public signature verification key, Public static key agreement key, Symmetric authentication key, Symmetric authorization key, Symmetric data encryption key, Symmetric key agreement key, Symmetric key wrapping key, Symmetric master key, Symmetric random number generation key |
| Object Type | ✅ | Certificate Request |
| Operation | ✅ | Adjust Attribute, Delegated Login, Interop, Log, Login, Logout, PKCS#11, Re-Provision, Set Attribute, Set Endpoint Role |
| Profile Name | ✅ | AES XTS Client, AES XTS Server, Advanced Cryptographic Client, Advanced Cryptographic Server, Advanced Symmetric Key Foundry Client, Asymmetric Key Lifecycle Client, Asymmetric Key Lifecycle Server, Baseline Client, Baseline Server, Basic Cryptographic Client, Basic Cryptographic Server, Basic Symmetric Key Foundry Client, Complete Server, Complete Server Basic, Complete Server TLS v1.2, HTTPS Client, HTTPS Server, Intermediate Symmetric Key Foundry Client, JSON Client, JSON Server, Opaque Managed Object Store Client, Opaque Managed Object Store Server, PKCS#11 Client, PKCS#11 Server, Quantum Safe Client, Quantum Safe Server, RNG Cryptographic Client, RNG Cryptographic Server, Storage Array with Self Encrypting Drive Client, Storage Array with Self Encrypting Drive Server, Symmetric Key Foundry Server, Symmetric Key Lifecycle Client, Symmetric Key Lifecycle Server, Tape Library Client, Tape Library Server, XML Client, XML Server |
| Protection Level | ✅ | High, Low |
| Query Function | ✅ | Query Defaults Information, Query Storage Protection Masks |
| Recommended Curve | ✅ | CURVE25519, CURVE448 |
| Result Reason | ✅ | Attribute Instance Not Found, Attribute Not Found, Attribute Read Only, Attribute Single Instance, Authentication not successful, Bad Cryptographic Parameters, Bad Password, Codec Error, General failure, Illegal Object Type, Incompatible Cryptographic Usage Mask, Internal Server Error, Invalid Asynchronous Correlation Value, Invalid Attribute, Invalid Attribute Value, Invalid CSR, Invalid Correlation Value, Invalid Data Type, Invalid Object Type, Invalid Password, Invalid Ticket, Key Wrap Type Not Supported, Missing Initialization Vector, Missing data, Multi Valued Attribute, Non Unique Name Attribute, Numeric Range, Object Archived, Object Destroyed, Object Not Found, Object Type, Operation canceled by requester, PKCS#11 Codec Error, PKCS#11 Invalid Function, PKCS#11 Invalid Interface, Protection Storage Unavailable, Private Protection Storage Unavailable, Public Protection Storage Unavailable, Read Only Attribute, Server Limit Exceeded, Unknown Enumeration, Unknown Message Extension, Unknown Tag, Unsupported Attribute, Unsupported Cryptographic Parameters, Unsupported Protocol Version, Usage Limit Exceeded, Wrapping Object Archived, Wrapping Object Destroyed, Wrapping Object Not Found, Wrong Key Lifecycle State |
| Ticket Type | ✅ | Login |
| Unique Identifier | ✅ | Certify, Create, Create Key Pair, Create Key Pair Private Key, Create Key Pair Public Key, Create Split Key, Derive Key, ID Placeholder, Import, Join Split Key, Locate, Re-certify, Re-key, Re-key Key Pair, Re-key Key Pair Private Key, Re-key Key Pair Public Key, Register |
| Wrapping Method | ✅ | MAC/sign only, MAC/sign then encrypt. |

**Removed / reserved values:**

| Enumeration | Removed Values |
|---|---|
| Batch Error Continuation Option | Undo |
| Cancellation Result | Canceled |
| Certificate Request Type | PGP |
| Certificate Type | PGP |
| Client Registration Method | Server Pre-Generated, Unspecified |
| Derivation Method | PBKDF2 |
| Digital Signature Algorithm | MD2 with RSA Encryption (PKCS#1 v1.5), MD5 with RSA Encryption (PKCS#1 v1.5), RSASSA-PSS (PKCS#1 v2.1), SHA-1 with RSA Encryption (PKCS#1 v1.5), SHA-224 with RSA Encryption (PKCS#1 v1.5), SHA-256 with RSA Encryption (PKCS#1 v1.5), SHA-384 with RSA Encryption (PKCS#1 v1.5), SHA-512 with RSA Encryption (PKCS#1 v1.5) |
| Encoding Option | No Encoding |
| Hashing Algorithm | SHA-3-224, SHA-3-256, SHA-3-384, SHA-3-512 |
| Key Format Type | PKCS#1, PKCS#12, PKCS#8, Raw, Transparent DH Private Key, Transparent DH Public Key, Transparent DSA Private Key, Transparent DSA Public Key, Transparent EC Private Key, Transparent EC Public Key, Transparent ECDH Private Key, Transparent ECDH Public Key, Transparent ECDSA Private Key, Transparent ECDSA Public Key, Transparent ECMQV Private Key, Transparent ECMQV Public Key, Transparent RSA Private Key, Transparent RSA Public Key, Transparent Symmetric Key |
| Link Type | Private Key Link |
| Mask Generator | MGF1 |
| Object Type | Template |
| Profile Name | Advanced Cryptographic Client KMIP v1.2, Advanced Cryptographic Client KMIP v1.3, Advanced Cryptographic Client KMIP v1.4, Advanced Cryptographic Server KMIP v1.2, Advanced Cryptographic Server KMIP v1.3, Advanced Cryptographic Server KMIP v1.4, Advanced Symmetric Key Foundry Client KMIP v1.0, Advanced Symmetric Key Foundry Client KMIP v1.1, Advanced Symmetric Key Foundry Client KMIP v1.2, Advanced Symmetric Key Foundry Client KMIP v1.3, Advanced Symmetric Key Foundry Client KMIP v1.4, Asymmetric Key Lifecycle Client KMIP v1.0, Asymmetric Key Lifecycle Client KMIP v1.1, Asymmetric Key Lifecycle Client KMIP v1.2, Asymmetric Key Lifecycle Client KMIP v1.3, Asymmetric Key Lifecycle Client KMIP v1.4, Asymmetric Key Lifecycle Server KMIP v1.0, Asymmetric Key Lifecycle Server KMIP v1.1, Asymmetric Key Lifecycle Server KMIP v1.2, Asymmetric Key Lifecycle Server KMIP v1.3, Asymmetric Key Lifecycle Server KMIP v1.4, Baseline Client Basic KMIP v1.2, Baseline Client Basic KMIP v1.3, Baseline Client Basic KMIP v1.4, Baseline Client TLS v1.2 KMIP v1.2, Baseline Client TLS v1.2 KMIP v1.3, Baseline Client TLS v1.2 KMIP v1.4, Baseline Server Basic KMIP v1.2, Baseline Server Basic KMIP v1.3, Baseline Server Basic KMIP v1.4, Baseline Server TLS v1.2 KMIP v1.2, Baseline Server TLS v1.2 KMIP v1.3, Baseline Server TLS v1.2 KMIP v1.4, Basic Cryptographic Client KMIP v1.2, Basic Cryptographic Client KMIP v1.3, Basic Cryptographic Client KMIP v1.4, Basic Cryptographic Server KMIP v1.2, Basic Cryptographic Server KMIP v1.3, Basic Cryptographic Server KMIP v1.4, Basic Symmetric Key Foundry Client KMIP v1.0, Basic Symmetric Key Foundry Client KMIP v1.1, Basic Symmetric Key Foundry Client KMIP v1.2, Basic Symmetric Key Foundry Client KMIP v1.3, Basic Symmetric Key Foundry Client KMIP v1.4, Complete Server Basic KMIP v1.2, Complete Server Basic KMIP v1.3, Complete Server Basic KMIP v1.4, Complete Server TLS v1.2 KMIP v1.2, Complete Server TLS v1.2 KMIP v1.3, Complete Server TLS v1.2 KMIP v1.4, HTTPS Client KMIP v1.0, HTTPS Client KMIP v1.1, HTTPS Client KMIP v1.2, HTTPS Client KMIP v1.3, HTTPS Client KMIP v1.4, HTTPS Server KMIP v1.0, HTTPS Server KMIP v1.1, HTTPS Server KMIP v1.2, HTTPS Server KMIP v1.3, HTTPS Server KMIP v1.4, Intermediate Symmetric Key Foundry Client KMIP v1.0, Intermediate Symmetric Key Foundry Client KMIP v1.1, Intermediate Symmetric Key Foundry Client KMIP v1.2, Intermediate Symmetric Key Foundry Client KMIP v1.3, Intermediate Symmetric Key Foundry Client KMIP v1.4, JSON Client KMIP v1.0, JSON Client KMIP v1.1, JSON Client KMIP v1.2, JSON Client KMIP v1.3, JSON Client KMIP v1.4, JSON Server KMIP v1.0, JSON Server KMIP v1.1, JSON Server KMIP v1.2, JSON Server KMIP v1.3, JSON Server KMIP v1.4, Opaque Managed Object Store Client KMIP v1.0, Opaque Managed Object Store Client KMIP v1.1, Opaque Managed Object Store Client KMIP v1.2, Opaque Managed Object Store Client KMIP v1.3, Opaque Managed Object Store Client KMIP v1.4, Opaque Managed Object Store Server KMIP v1.0, Opaque Managed Object Store Server KMIP v1.1, Opaque Managed Object Store Server KMIP v1.2, Opaque Managed Object Store Server KMIP v1.3, Opaque Managed Object Store Server KMIP v1.4, RNG Cryptographic Client KMIP v1.2, RNG Cryptographic Client KMIP v1.3, RNG Cryptographic Client KMIP v1.4, RNG Cryptographic Server KMIP v1.2, RNG Cryptographic Server KMIP v1.3, RNG Cryptographic Server KMIP v1.4, Storage Array with Self Encrypting Drive Client KMIP v1.0, Storage Array with Self Encrypting Drive Client KMIP v1.1, Storage Array with Self Encrypting Drive Client KMIP v1.2, Storage Array with Self Encrypting Drive Client KMIP v1.3, Storage Array with Self Encrypting Drive Client KMIP v1.4, Storage Array with Self Encrypting Drive Server KMIP v1.0, Storage Array with Self Encrypting Drive Server KMIP v1.1, Storage Array with Self Encrypting Drive Server KMIP v1.2, Storage Array with Self Encrypting Drive Server KMIP v1.3, Storage Array with Self Encrypting Drive Server KMIP v1.4, Suite B minLOS_128 Client KMIP v1.0, Suite B minLOS_128 Client KMIP v1.1, Suite B minLOS_128 Client KMIP v1.2, Suite B minLOS_128 Client KMIP v1.3, Suite B minLOS_128 Client KMIP v1.4, Suite B minLOS_128 Server KMIP v1.0, Suite B minLOS_128 Server KMIP v1.1, Suite B minLOS_128 Server KMIP v1.2, Suite B minLOS_128 Server KMIP v1.3, Suite B minLOS_128 Server KMIP v1.4, Suite B minLOS_192 Client KMIP v1.0, Suite B minLOS_192 Client KMIP v1.1, Suite B minLOS_192 Client KMIP v1.2, Suite B minLOS_192 Client KMIP v1.3, Suite B minLOS_192 Client KMIP v1.4, Suite B minLOS_192 Server KMIP v1.0, Suite B minLOS_192 Server KMIP v1.1, Suite B minLOS_192 Server KMIP v1.2, Suite B minLOS_192 Server KMIP v1.3, Suite B minLOS_192 Server KMIP v1.4, Symmetric Key Foundry Server KMIP v1.0, Symmetric Key Foundry Server KMIP v1.1, Symmetric Key Foundry Server KMIP v1.2, Symmetric Key Foundry Server KMIP v1.3, Symmetric Key Foundry Server KMIP v1.4, Symmetric Key Lifecycle Client KMIP v1.0, Symmetric Key Lifecycle Client KMIP v1.1, Symmetric Key Lifecycle Client KMIP v1.2, Symmetric Key Lifecycle Client KMIP v1.3, Symmetric Key Lifecycle Client KMIP v1.4, Symmetric Key Lifecycle Server KMIP v1.0, Symmetric Key Lifecycle Server KMIP v1.1, Symmetric Key Lifecycle Server KMIP v1.2, Symmetric Key Lifecycle Server KMIP v1.3, Symmetric Key Lifecycle Server KMIP v1.4, Tape Library Client KMIP v1.0, Tape Library Client KMIP v1.1, Tape Library Client KMIP v1.2, Tape Library Client KMIP v1.3, Tape Library Client KMIP v1.4, Tape Library Server KMIP v1.0, Tape Library Server KMIP v1.1, Tape Library Server KMIP v1.2, Tape Library Server KMIP v1.3, Tape Library Server KMIP v1.4, XML Client KMIP v1.0, XML Client KMIP v1.1, XML Client KMIP v1.2, XML Client KMIP v1.3, XML Client KMIP v1.4, XML Server KMIP v1.0, XML Server KMIP v1.1, XML Server KMIP v1.2, XML Server KMIP v1.3, XML Server KMIP v1.4 |
| Result Reason | Application Namespace Not Supported, Authentication Not Successful, General Failure, Illegal Operation, Index Out of Bounds, Missing Data, Object archived, Operation Canceled By Requester |
| Wrapping Method | Encrypt, MAC/sign, MAC/sign then encrypt |

### v2.1

**Added values:**

| Enumeration | Impl? | New Values |
|---|---|---|
| Certificate Type | ✅ | PGP |
| Operation | ✅ | Get Constraints, Ping, Process, Query Asynchronous Requests, Set Constraints, Set Defaults |
| Processing Stage | ✅ | Completed, In Process, Submitted |
| Result Reason | ✅ | Constraint Violation, Duplicate Process Request, Unknown Object Group |
| Rotate Name Type | ✅ | URI, Uninterpreted Text String |

**Removed / reserved values:**

| Enumeration | Removed Values |
|---|---|
| Certificate Type | (PGP |

### v3.0

**Added values:**

| Enumeration | Impl? | New Values |
|---|---|---|
| Credential Type | ✅ | Certificate, Password |
| Cryptographic Algorithm | ✅ | ML-DSA-44, ML-DSA-65, ML-DSA-87, ML-KEM-1024, ML-KEM-512, ML-KEM-768, SLH-DSA-SHA2-128f, SLH-DSA-SHA2-128s, SLH-DSA-SHA2-192f, SLH-DSA-SHA2-192s, SLH-DSA-SHA2-256f, SLH-DSA-SHA2-256s, SLH-DSA-SHAKE-128f, SLH-DSA-SHAKE-128s, SLH-DSA-SHAKE-192f, SLH-DSA-SHAKE-192s, SLH-DSA-SHAKE-256f, SLH-DSA-SHAKE-256s |
| Deactivation Reason Code | ✅ | Deactivation Date, Protect Stop Date, Unspecified, Usage Limit |
| Ephemeral | ✅ | Empty, Unique Identifier |
| Item Type | ❌ | Identifier, Name Reference, Reference |
| OTP Algorithm | ✅ | TOTP |
| Object Class | ✅ | System, User |
| Object Type | ✅ | Device Credential, Group, Hashed Password Credential, One Time Password Credential, Password Credential, User |
| Operation | ✅ | Create Credential, Create Group, Create User, Deactivate, Obliterate |
| Query Function | ✅ | Query Credential Information |
| Result Reason | ✅ | Circular Link Error |
| Split Key Polynomial | ✅ | Polynomial-283, Polynomial-285 |
| Unique Identifier | ✅ | Create Credential, Create Group, Create User, Re-Provision |

**Removed / reserved values:**

| Enumeration | Removed Values |
|---|---|
| Link Type | Certificate Link, Child Link, Derivation Base Object Link, Derived Key Link, Next Link, PKCS#12 Certificate Link, PKCS#12 Password Link, Parent Link, Previous Link, Public Key Link, Replaced Object Link, Replacement Object Link, Wrapping Key Link |
| Name Type | URI, Uninterpreted Text String |
| Object Group Member | Group Member Default, Group Member Fresh |
| Result Reason | Unknown Object Group |
| Rotate Name Type | URI, Uninterpreted Text String |
