# KMIP 2.1 — Object Attributes

Total: **63** attributes

## Summary

| Attribute                            | Data Type                           | Is Structure |
|--------------------------------------|-------------------------------------|--------------|
| 4.1 Activation Date                  | Date-Time                           |              |
| 4.2 Alternative Name                 | Structure                           | Yes          |
| 4.3 Always Sensitive                 | Boolean                             | Yes          |
| 4.4 Application Specific Information | Structure                           | Yes          |
| 4.5 Archive Date                     | Date-Time                           |              |
| 4.6 Certificate Attributes           | Text String                         | Yes          |
| 4.7 Certificate Type                 | Enumeration                         |              |
| 4.8 Certificate Length               | Integer                             |              |
| 4.9 Comment                          | Text String                         |              |
| 4.10 Compromise Date                 | Date-Time                           |              |
| 4.11 Compromise Occurrence Date      | Date-Time                           |              |
| 4.12 Contact Information             | Text String                         |              |
| 4.13 Cryptographic Algorithm         | Enumeration                         |              |
| 4.14 Cryptographic Domain Parameters | Structure                           | Yes          |
| 4.15 Cryptographic Length            | Integer                             |              |
| 4.16 Cryptographic Parameters        | Structure                           | Yes          |
| 4.17 Cryptographic Usage Mask        | Integer                             |              |
| 4.18 Deactivation Date               | Date-Time                           |              |
| 4.19 Description                     | Text String                         |              |
| 4.20 Destroy Date                    | Date-Time                           |              |
| 4.21 Digest                          | Structure                           | Yes          |
| 4.22 Digital Signature Algorithm     | Enumeration                         |              |
| 4.23 Extractable                     | Boolean                             |              |
| 4.24 Fresh                           | Boolean                             |              |
| 4.25 Initial Date                    | Date-Time                           |              |
| 4.26 Key Format Type                 | Enumeration                         | Yes          |
| 4.27 Key Value Location              | Structure                           | Yes          |
| 4.28 Key Value Present               | Boolean                             |              |
| 4.29 Last Change Date                | Date-Time                           |              |
| 4.30 Lease Time                      | Interval                            |              |
| 4.31 Link                            | Structure                           | Yes          |
| 4.32 Name                            | Structure                           | Yes          |
| 4.33 Never Extractable               | Boolean                             |              |
| 4.34 NIST Key Type                   | Enumeration                         |              |
| 4.35 Object Group                    | Text String                         |              |
| 4.36 Object Type                     | Enumeration                         |              |
| 4.37 Opaque Data Type                | Enumeration                         |              |
| 4.38 Original Creation Date          | Date-Time                           |              |
| 4.39 PKCS#12 Friendly Name           | Text String                         |              |
| 4.40 Process Start Date              | Date-Time                           |              |
| 4.41 Protect Stop Date               | Date-Time                           |              |
| 4.42 Protection Level                | Enumeration                         |              |
| 4.43 Protection Period               | Interval                            |              |
| 4.44 Protection Storage Mask         | Integer                             |              |
| 4.45 Quantum Safe                    | Boolean                             |              |
| 4.46 Random Number Generator         | RNG Parameters                      |              |
| 4.47 Revocation Reason               | Structure                           | Yes          |
| 4.48 Rotate Automatic                | Boolean                             |              |
| 4.49 Rotate Date                     | Date Time                           |              |
| 4.50 Rotate Generation               | Integer                             |              |
| 4.51 Rotate Interval                 | Interval                            |              |
| 4.52 Rotate Latest                   | Boolean                             |              |
| 4.53 Rotate Name                     | Structure                           | Yes          |
| 4.54 Rotate Offset                   | Interval                            |              |
| 4.55 Sensitive                       | Boolean                             |              |
| 4.56 Short Unique Identifier         | Byte String                         |              |
| 4.57 State                           | Enumeration                         |              |
| 4.58 Unique Identifier               | Text String, Enumeration or Integer | Yes          |
| 4.59 Usage Limits                    | —                                   | Yes          |
| 4.60 Vendor Attribute                | Structure                           | Yes          |
| 4.61 X.509 Certificate Identifier    | Structure                           | Yes          |
| 4.62 X.509 Certificate Issuer        | Structure                           | Yes          |
| 4.63 X.509 Certificate Subject       | Structure                           | Yes          |

## Details

### 4.1 Activation Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Server or Client
- **Modifiable by server:** Yes, only while in Pre-Active state
- **Modifiable by client:** Yes, only while in Pre-Active state
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Activate Certify, Re-certify, Re-key, Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.2 Alternative Name

**Data Type:** Structure

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** Yes (Only if no value present)
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** Yes
- **Applies to Object Types:** All Objects

| Field                  | Encoding / Type | Required |
|------------------------|-----------------|----------|
| Alternative Name Value | Text String     | Yes      |
| Alternative Name Type  | Enumeration     | Yes      |

### 4.3 Always Sensitive

**Data Type:** Boolean

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When Sensitive attribute is set or changed
- **Applies to Object Types:** All Objects

| Field     | Encoding / Type | Required |
|-----------|-----------------|----------|
| Sensitive | Boolean         |          |

### 4.4 Application Specific Information

**Data Type:** Structure

- **SHALL always have a value:** No
- **Initially set by:** Client or Server (only if the Application Data is omitted, in the client request)
- **Modifiable by server:** Yes (only if the Application Data is omitted in the client request)
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** Yes
- **When implicitly set:** Re-key , Re-key Key Pair , Re-certify
- **Applies to Object Types:** All Objects

| Field                 | Encoding / Type | Required |
|-----------------------|-----------------|----------|
| Ite m                 | Encoding        | REQUIRED |
| Application Namespace | Text String     | Yes      |
| Application Data      | Text String     | No       |

### 4.5 Archive Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Archive
- **Applies to Object Types:** All Objects

### 4.6 Certificate Attributes

**Data Type:** Text String

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** Yes
- **When implicitly set:** Register, Certify, Re-certify
- **Applies to Object Types:** Certificates

| Field                             | Encoding / Type | Required |
|-----------------------------------|-----------------|----------|
| Certificate Subject CN            | Text String     |          |
| Certificate Subject O             | Text String     |          |
| Certificate Subject OU            | Text String     |          |
| Certificate Subject Email         | Text String     |          |
| Certificate Subject C             | Text String     |          |
| Certificate Subject ST            | Text String     |          |
| Certificate Subject L             | Text String     |          |
| Certificate Subject UID           | Text String     |          |
| Certificate Subject Serial Number | Text String     |          |
| Certificate Subject Title         | Text String     |          |
| Certificate Subject DC            | Text String     |          |
| Certificate Subject DN Qualifier  | Text String     |          |
| Certificate Issuer CN             | Text String     |          |
| Certificate Issuer O              | Text String     |          |
| Certificate Issuer OU             | Text String     |          |
| Certificate Issuer Email          | Text String     |          |
| Certificate Issuer C              | Text String     |          |
| Certificate Issuer ST             | Text String     |          |
| Certificate Issuer L              | Text String     |          |
| Certificate Issuer UID            | Text String     |          |
| Certificate Issuer Serial Number  | Text String     |          |
| Certificate Issuer Title          | Text String     |          |
| Certificate Issuer DC             | Text String     |          |
| Certificate Issuer DN Qualifier   | Text String     |          |

### 4.7 Certificate Type

**Data Type:** Enumeration

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Register, Certify, Re-certify
- **Applies to Object Types:** Certificates

### 4.8 Certificate Length

**Data Type:** Integer

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Register, Certify, Re-certify
- **Applies to Object Types:** Certificates

### 4.9 Comment

**Data Type:** Text String

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** No
- **Applies to Object Types:** All Objects

### 4.10 Compromise Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Revoke
- **Applies to Object Types:** All Objects

### 4.11 Compromise Occurrence Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Revoke
- **Applies to Object Types:** All Objects

### 4.12 Contact Information

**Data Type:** Text String

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.13 Cryptographic Algorithm

**Data Type:** Enumeration

- **SHALL always have a value:** Yes (except for Secret Data and Opaque Object)
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Certify, Create, Create Key Pair, Re-certify, Register, Derive Key, Re-key , Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.14 Cryptographic Domain Parameters

**Data Type:** Structure

- **Shall always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Re-key , Re-key Key Pair
- **Applies to Object Types:** Public Keys, Private Keys

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Qlength           | Integer         | No       |
| Recommended Curve | Enumeration     | No       |

### 4.15 Cryptographic Length

**Data Type:** Integer

- **SHALL always have a value:** Yes (Except for Opaque Object)
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Certify, Create, Create Key Pair, Re-certify, Register, Derive Key, Re-key , Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.16 Cryptographic Parameters

**Data Type:** Structure

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** No
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** Yes
- **When implicitly set:** Re-key , Re-key Key Pair , Re-certify
- **Applies to Object Types:** All Objects

| Field                            | Encoding / Type | Required                                                                               |
|----------------------------------|-----------------|----------------------------------------------------------------------------------------|
| Block Cipher Mode                | Enumeration     | No                                                                                     |
| Padding Method                   | Enumeration     | No                                                                                     |
| Hashing Algorithm                | Enumeration     | No                                                                                     |
| Key Role Type                    | Enumeration     | No                                                                                     |
| Digital Signature Algorithm      | Enumeration     | No                                                                                     |
| Cryptographic Algorithm          | Enumeration     | No                                                                                     |
| Random IV                        | Boolean         | No                                                                                     |
| IV Length                        | Integer         | No unless Block Cipher Mode supports variable IV lengths                               |
| Tag Length                       | Integer         | No unless Block Cipher Mode is GCM                                                     |
| Fixed Field Length               | Integer         | No                                                                                     |
| Invocation Field Length          | Integer         | No                                                                                     |
| Counter Length                   | Integer         | No                                                                                     |
| Initial Counter Value            | Integer         | No                                                                                     |
| Salt Length                      | Integer         | No (if omitted, defaults to the block size of the Mask Generator Hashing Algorithm)    |
| Mask Generator                   | Enumeration     | No (if omitted defaults to MGF1).                                                      |
| Mask Generator Hashing Algorithm | Enumeration     | No. (if omitted defaults to SHA-1).                                                    |
| P Source                         | Byte String     | No (if omitted, defaults to an empty byte string for encoding input P in OAEP padding) |
| Trailer Field                    | Integer         | No (if omitted, defaults to the standard one-byte trailer in PSS padding)              |

### 4.17 Cryptographic Usage Mask

**Data Type:** Integer

- **SHALL always have a value:** Yes (Except for Opaque Object)
- **Initially set by:** Server or Client
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.18 Deactivation Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Server or Client
- **Modifiable by server:** Yes, only while in Pre-Active or Active state
- **Modifiable by client:** Yes, only while in Pre-Active or Active state
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Revoke Certify, Re-certify, Re-key, Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.19 Description

**Data Type:** Text String

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** No
- **Applies to Object Types:** All Objects

### 4.20 Destroy Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Destroy
- **Applies to Object Types:** All Objects

### 4.21 Digest

**Data Type:** Structure

- **SHALL always have a value:** Yes, if the server has access to the Digest Value or the Key Material (for keys and secret data), the Certificate Value (for certificates) or the Opaque Data Value (for opaque objects).
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** Yes
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair
- **Applies to Object Types:** All Objects

| Field             | Encoding / Type | Required                                                                                                                                                                                  |
|-------------------|-----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Hashing Algorithm | Enumeration     | Yes                                                                                                                                                                                       |
| Digest Value      | Byte String     | Yes, if the server has access to the Digest Value or the Key Material (for keys and secret data), the Certificate Value (for certificates) or the Opaque Data Value (for opaque objects). |
| Key Format Type   | Enumeration     | Yes, if the Managed Object is a key or secret data object.                                                                                                                                |

### 4.22 Digital Signature Algorithm

**Data Type:** Enumeration

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** Yes for PGP keys. No for X.509 certificates.
- **When implicitly set:** Certify, Re-certify, Register
- **Applies to Object Types:** Certificates, PGP keys

### 4.23 Extractable

**Data Type:** Boolean

- **SHALL always have a value:** Yes
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When object is created or registered
- **Applies to Object Types:** All Objects

### 4.24 Fresh

**Data Type:** Boolean

- **SHALL always have a value:** Yes
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key, Re-key Key Pair , Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.25 Initial Date

**Data Type:** Date-Time

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key, Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.26 Key Format Type

**Data Type:** Enumeration

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **Applies to Object Types:** All Objects

| Field               | Encoding / Type             | Required |
|---------------------|-----------------------------|----------|
| Objec t             | Encoding                    |          |
| Certificate         | X.509                       |          |
| Certificate Request | PKCS#10                     |          |
| Opaque Object       | Opaque                      |          |
| PGP Key             | Raw                         |          |
| Secret Data         | Raw                         |          |
| Symmetric Key       | Raw                         |          |
| Split Key           | Raw                         |          |
| RSA Private Key     | PKCS#1                      |          |
| RSA Public Key      | PKCS#1                      |          |
| EC Private Key      | Transparent EC Private Key  |          |
| EC Public Key       | Transparent EC Public Key   |          |
| DSA Private Key     | Transparent DSA Private Key |          |
| DSA Public Key      | Transparent DSA Public Key  |          |

### 4.27 Key Value Location

**Data Type:** Structure

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** No
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** Yes
- **When implicitly set:** Never
- **Applies to Object Types:** All Objects

| Field                    | Encoding / Type | Required |
|--------------------------|-----------------|----------|
| Obje ct                  | Encoding        | REQUIRED |
| Key Value Location Value | Text String     | Yes      |
| Key Value Location Type  | Enumeration     | Yes      |

### 4.28 Key Value Present

**Data Type:** Boolean

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** During Register operation
- **Applies to Object Types:** All Objects

### 4.29 Last Change Date

**Data Type:** Date-Time

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Activate, Revoke, Destroy, Archive, Recover, Certify, Re-certify, Re-key , Re-key Key Pair , Add Attribute, Modify Attribute, Delete Attribute, Get Usage Allocation
- **Applies to Object Types:** All Objects

### 4.30 Lease Time

**Data Type:** Interval

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.31 Link

**Data Type:** Structure

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** Yes
- **When implicitly set:** Create Key Pair, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair, Register
- **Applies to Object Types:** All Objects

| Field                    | Encoding / Type                                                                                                                                | Required |
|--------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| Text String              | Unique Identifier of a Managed Object.                                                                                                         |          |
| Enumeration              | Unique Identifier Enumeration                                                                                                                  |          |
| Integer                  | Zero based nth Unique Identifier in the response. If negative the count is backwards from the beginning of the current operation's batch item. |          |
| Link Type                | Enumeration                                                                                                                                    | Yes      |
| Linked Object Identifier | Text String/Enumeration/Integer                                                                                                                | Yes      |

### 4.32 Name

**Data Type:** Structure

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** Yes
- **When implicitly set:** Re-key, Re-key Key Pair, Re-certify
- **Applies to Object Types:** All Objects

| Field      | Encoding / Type | Required |
|------------|-----------------|----------|
| Name Value | Text String     | Yes      |
| Name Type  | Enumeration     | Yes      |

### 4.33 Never Extractable

**Data Type:** Boolean

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When Never Extractable attribute is set or changed
- **Applies to Object Types:** All Objects

### 4.34 NIST Key Type

**Data Type:** Enumeration

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** Yes
- **Applies to Object Types:** All Objects

### 4.35 Object Group

**Data Type:** Text String

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** Yes
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.36 Object Type

**Data Type:** Enumeration

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.37 Opaque Data Type

**Data Type:** Enumeration

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Register
- **Applies to Object Types:** Opaque Objects

### 4.38 Original Creation Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Client or Server (when object is generated by Server)
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Derive Key, Re-key, Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.39 PKCS#12 Friendly Name

**Data Type:** Text String

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** No
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** No
- **Applies to Object Types:** All Objects

### 4.40 Process Start Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Server or Client
- **Modifiable by server:** Yes, only while in Pre-Active or Active state and as long as the Process Start Date has been not reached.
- **Modifiable by client:** Yes, only while in Pre-Active or Active state and as long as the Process Start Date has been not reached.
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Register, Derive Key, Re-key
- **Applies to Object Types:** All Objects

### 4.41 Protect Stop Date

**Data Type:** Date-Time

- **SHALL always have a value:** No
- **Initially set by:** Server or Client
- **Modifiable by server:** Yes, only while in Pre-Active or Active state and as long as the Protect Stop Date has not been reached.
- **Modifiable by client:** Yes, only while in Pre-Active or Active state and as long as the Protect Stop Date has not been reached.
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Register, Derive Key, Re-key
- **Applies to Object Types:** All Objects

### 4.42 Protection Level

**Data Type:** Enumeration

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** No
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** No
- **When implicitly set:** 
- **Applies to Object Types:** All Objects

### 4.43 Protection Period

**Data Type:** Interval

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** No
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** No
- **When implicitly set:** 
- **Applies to Object Types:** All Objects

### 4.44 Protection Storage Mask

**Data Type:** Integer

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When object is stored
- **Applies to Object Types:** All Objects

### 4.45 Quantum Safe

**Data Type:** Boolean

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** No
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** No
- **When implicitly set:** 
- **Applies to Object Types:** All Objects

### 4.46 Random Number Generator

**Data Type:** RNG Parameters

- **SHALL always have a value:** No
- **Initially set by:** Client (when the object is generated by the Client and registered) or Server (when object is generated by Server)
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Derive Key, Re-key, Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.47 Revocation Reason

**Data Type:** Structure

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Revoke
- **Applies to Object Types:** All Objects

| Field                  | Encoding / Type | Required |
|------------------------|-----------------|----------|
| Revocation Reason Code | Enumeration     | Yes      |
| Revocation Message     | Text String     | No       |

### 4.48 Rotate Automatic

**Data Type:** Boolean

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** N/A
- **Applies to Object Types:** All Objects

### 4.49 Rotate Date

**Data Type:** Date Time

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When object is rotated
- **Applies to Object Types:** All Objects

### 4.50 Rotate Generation

**Data Type:** Integer

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When object is rotated
- **Applies to Object Types:** All Objects

### 4.51 Rotate Interval

**Data Type:** Interval

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When created or registered
- **Applies to Object Types:** All Objects

### 4.52 Rotate Latest

**Data Type:** Boolean

- **SHALL always have a value:** No
- **Initially set by:** Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When object is rotated by the server
- **Applies to Object Types:** All Objects

### 4.53 Rotate Name

**Data Type:** Structure

- **SHALL always have a value:** No
- **Initially set by:** Client
- **Modifiable by server:** No
- **Modifiable by client:** Yes
- **Deletable by client:** Yes
- **Multiple instances permitted:** No
- **Applies to Object Types:** All Objects

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Rotate Name Value | Text String     | Yes      |
| Rotate Name Type  | Enumeration     | Yes      |

### 4.54 Rotate Offset

**Data Type:** Interval

- **SHALL always have a value:** No
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When object is created or registered
- **Applies to Object Types:** All Objects

### 4.55 Sensitive

**Data Type:** Boolean

- **SHALL always have a value:** Yes
- **Initially set by:** Client or Server
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** When object is created or registered
- **Applies to Object Types:** All Objects

### 4.56 Short Unique Identifier

**Data Type:** Byte String

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key, Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.57 State

**Data Type:** Enumeration

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** Yes
- **Modifiable by client:** No, but only by the server in response to certain requests (see above)
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Activate, Revoke, Destroy, Certify, Re-certify, Re-key, Re-key Key Pair
- **Applies to Object Types:** All Objects

### 4.58 Unique Identifier

**Data Type:** Text String, Enumeration or Integer

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key, Re-key Key Pair
- **Applies to Object Types:** All Objects

| Field       | Encoding / Type                                                                                                                                | Required |
|-------------|------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| Text String | Unique Identifier of a Managed Object.                                                                                                         |          |
| Enumeration | Unique Identifier Enumeration                                                                                                                  |          |
| Integer     | Zero based nth Unique Identifier in the response. If negative the count is backwards from the beginning of the current operation's batch item. |          |

### 4.59 Usage Limits

**Data Type:** —

- **SHALL always have a value:** No
- **Initially set by:** Server (Usage Limits Total, Usage Limits Count, and Usage Limits Unit) or Client (Usage Limits Total and/or Usage Limits Unit only)
- **Modifiable by server:** Yes
- **Modifiable by client:** Yes (Usage Limits Total and/or Usage Limits Unit only, as long as Get Usage Allocation has not been performed)
- **Deletable by client:** Yes, as long as Get Usage Allocation has not been performed
- **Multiple instances permitted:** No
- **When implicitly set:** Create, Create Key Pair, Register, Derive Key, Re-key , Re-key Key Pair , Get Usage Allocation
- **Applies to Object Types:** All Objects

| Field              | Encoding / Type                                                                                                                                                                                                             | Required |
|--------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| Usage Limits Total | The total number of Usage Limits Units allowed to be protected. This is the total value for the entire life of the object and SHALL NOT be changed once the object begins to be used for applying cryptographic protection. |          |
| Usage Limits Count | The currently remaining number of Usage Limits Units allowed to be protected by the object.                                                                                                                                 |          |
| Usage Limits Unit  | The type of quantity for which this structure specifies a usage limit (e.g., byte, object).                                                                                                                                 |          |

### 4.60 Vendor Attribute

**Data Type:** Structure

| Field                 | Encoding / Type                                                                              | Required                             |
|-----------------------|----------------------------------------------------------------------------------------------|--------------------------------------|
| Vendor Identification | Text String (with usage limited to alphanumeric, underscore and period  i.e. [A-Za-z0-9_.]) | Yes                                  |
| Attribute Name        | Text String                                                                                  | Yes                                  |
| Attribute Value       | Varies, depending on attribute.                                                              | Yes, except for the Notify operation |

### 4.61 X.509 Certificate Identifier

**Data Type:** Structure

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Register, Certify, Re-certify
- **Applies to Object Types:** X.509 Certificates

| Field                     | Encoding / Type | Required |
|---------------------------|-----------------|----------|
| Issuer Distinguished Name | Byte String     | Yes      |
| Certificate Serial Number | Byte String     | Yes      |

### 4.62 X.509 Certificate Issuer

**Data Type:** Structure

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Register, Certify, Re-certify
- **Applies to Object Types:** X.509 Certificates

| Field                     | Encoding / Type              | Required |
|---------------------------|------------------------------|----------|
| Issuer Distinguished Name | Byte String                  | Yes      |
| Issuer Alternative Name   | Byte String, MAY be repeated | No       |

### 4.63 X.509 Certificate Subject

**Data Type:** Structure

- **SHALL always have a value:** Yes
- **Initially set by:** Server
- **Modifiable by server:** No
- **Modifiable by client:** No
- **Deletable by client:** No
- **Multiple instances permitted:** No
- **When implicitly set:** Register, Certify, Re-certify
- **Applies to Object Types:** X.509 Certificates

| Field                      | Encoding / Type              | Required                                                   |
|----------------------------|------------------------------|------------------------------------------------------------|
| Subject Distinguished Name | Byte String                  | Yes, but MAY be the empty string                           |
| Subject Alternative Name   | Byte String, MAY be repeated | Yes, if the Subject Distinguished Name is an empty string. |

