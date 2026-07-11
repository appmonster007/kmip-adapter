# KMIP 1.2 — Attributes

Total: **43** attributes

## Summary

| Attribute                             | Data Type                                                                                       | Is Structure |
|---------------------------------------|-------------------------------------------------------------------------------------------------|--------------|
| 3.1 Unique Identifier                 | Text String                                                                                     |              |
| 3.2 Name                              | Structure                                                                                       | Yes          |
| 3.3 Object Type                       | Enumeration, see 9.1.3.2.12                                                                     |              |
| 3.4 Cryptographic Algorithm           | Enumeration, see 9.1.3.2.13                                                                     |              |
| 3.5 Cryptographic Length              | Integer                                                                                         |              |
| 3.6 Cryptographic Parameters          | Structure                                                                                       | Yes          |
| 3.7 Cryptographic Domain Parameters   | Structure                                                                                       | Yes          |
| 3.8 Certificate Type                  | Enumeration, see 9.1.3.2.6                                                                      |              |
| 3.9 Certificate Length                | Integer                                                                                         |              |
| 3.10 X.509 Certificate Identifier     | Structure                                                                                       | Yes          |
| 3.11 X.509 Certificate Subject        | Structure                                                                                       | Yes          |
| 3.12 X.509 Certificate Issuer         | Structure                                                                                       | Yes          |
| 3.13 Certificate Identifier           | Structure                                                                                       | Yes          |
| 3.14 Certificate Subject              | Structure                                                                                       | Yes          |
| 3.15 Certificate Issuer               | Structure                                                                                       | Yes          |
| 3.16 Digital Signature Algorithm      | Enumeration, see 9.1.3.2.7                                                                      |              |
| 3.17 Digest                           | Structure                                                                                       | Yes          |
| 3.18 Operation Policy Name            | Text String                                                                                     | Yes          |
| 3.19 Cryptographic Usage Mask         | Integer                                                                                         |              |
| 3.20 Lease Time                       | Interval                                                                                        |              |
| 3.21 Usage Limits                     | Structure                                                                                       | Yes          |
| 3.22 State                            | Enumeration, see 9.1.3.2.18                                                                     |              |
| 3.23 Initial Date                     | Date-Time                                                                                       |              |
| 3.24 Activation Date                  | Date-Time                                                                                       |              |
| 3.25 Process Start Date               | Date-Time                                                                                       |              |
| 3.26 Protect Stop Date                | Date-Time                                                                                       |              |
| 3.27 Deactivation Date                | Date-Time                                                                                       |              |
| 3.28 Destroy Date                     | Date-Time                                                                                       |              |
| 3.29 Compromise Occurrence Date       | Date-Time                                                                                       |              |
| 3.30 Compromise Date                  | Date-Time                                                                                       |              |
| 3.31 Revocation Reason                | Structure                                                                                       | Yes          |
| 3.32 Archive Date                     | Date-Time                                                                                       |              |
| 3.33 Object Group                     | Text String                                                                                     |              |
| 3.34 Fresh                            | Boolean                                                                                         |              |
| 3.35 Link                             | Structure                                                                                       | Yes          |
| 3.36 Application Specific Information | Structure                                                                                       | Yes          |
| 3.37 Contact Information              | Text String                                                                                     |              |
| 3.38 Last Change Date                 | Date-Time                                                                                       |              |
| 3.39 Custom Attribute                 | Any data type or structure. If a structure, then the structure SHALL NOT include sub structures |              |
| 3.40 Alternative Name                 | Structure                                                                                       | Yes          |
| 3.41 Key Value Present                | Boolean                                                                                         |              |
| 3.42 Key Value Location               | Structure                                                                                       | Yes          |
| 3.43 Original Creation Date           | Date-Time                                                                                       |              |

## Details

### 3.1 Unique Identifier

**Data Type:** Text String

### 3.2 Name

**Data Type:** Structure

| Field      | Encoding / Type             |
|------------|-----------------------------|
| Name Value | Text String                 |
| Name Type  | Enumeration, see 9.1.3.2.11 |

### 3.3 Object Type

**Data Type:** Enumeration, see 9.1.3.2.12

### 3.4 Cryptographic Algorithm

**Data Type:** Enumeration, see 9.1.3.2.13

### 3.5 Cryptographic Length

**Data Type:** Integer

### 3.6 Cryptographic Parameters

**Data Type:** Structure

| Field                       | Encoding / Type             |
|-----------------------------|-----------------------------|
| Block Cipher Mode           | Enumeration, see 9.1.3.2.14 |
| Padding Method              | Enumeration, see 9.1.3.2.15 |
| Hashing Algorithm           | Enumeration, see 9.1.3.2.16 |
| Key Role Type               | Enumeration, see 9.1.3.2.17 |
| Digital Signature Algorithm | Enumeration, see 9.1.3.2.7  |
| Cryptographic Algorithm     | Enumeration, see 9.1.3.2.13 |
| Random IV                   | Boolean                     |
| IV Length                   | Integer                     |
| Tag Length                  | Integer                     |
| Fixed Field Length          | Integer                     |
| Invocation Field Length     | Integer                     |
| Counter Length              | Integer                     |
| Initial Counter Value       | Integer                     |

### 3.7 Cryptographic Domain Parameters

**Data Type:** Structure

| Field             | Encoding / Type            |
|-------------------|----------------------------|
| Qlength           | Integer                    |
| Recommended Curve | Enumeration, see 9.1.3.2.5 |

### 3.8 Certificate Type

**Data Type:** Enumeration, see 9.1.3.2.6

### 3.9 Certificate Length

**Data Type:** Integer

### 3.10 X.509 Certificate Identifier

**Data Type:** Structure

| Field                     | Encoding / Type |
|---------------------------|-----------------|
| Issuer Distinguished Name | Byte String     |
| Certificate Serial Number | Byte String     |

### 3.11 X.509 Certificate Subject

**Data Type:** Structure

| Field                      | Encoding / Type |
|----------------------------|-----------------|
| Subject Distinguished Name | Byte String     |
| Subject Alternative Name   | Byte String     |

### 3.12 X.509 Certificate Issuer

**Data Type:** Structure

| Field                     | Encoding / Type |
|---------------------------|-----------------|
| Issuer Distinguished Name | Byte String     |
| Issuer Alternative Name   | Byte String     |

### 3.13 Certificate Identifier

**Data Type:** Structure

| Field         | Encoding / Type |
|---------------|-----------------|
| Issuer        | Text String     |
| Serial Number | Text String     |

### 3.14 Certificate Subject

**Data Type:** Structure

| Field                                  | Encoding / Type |
|----------------------------------------|-----------------|
| Certificate Subject Distinguished Name | Text String     |
| Certificate Subject Alternative Name   | Text String     |

### 3.15 Certificate Issuer

**Data Type:** Structure

| Field                                 | Encoding / Type |
|---------------------------------------|-----------------|
| Certificate Issuer Distinguished Name | Text String     |
| Certificate Issuer Alternative Name   | Text String     |

### 3.16 Digital Signature Algorithm

**Data Type:** Enumeration, see 9.1.3.2.7

### 3.17 Digest

**Data Type:** Structure

| Field                                                                                        | Encoding / Type                                                                              |
|----------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|
| Hashing Algorithm                                                                            | Enumeration, see 9.1.3.2.16                                                                  |
| Digest Value                                                                                 | Byte String                                                                                  |
| Key Format Type                                                                              | Enumeration, see 9.1.3.2.3                                                                   |
| Server                                                                                       | Server                                                                                       |
| No                                                                                           | No                                                                                           |
| No                                                                                           | No                                                                                           |
| No                                                                                           | No                                                                                           |
| Yes                                                                                          | Yes                                                                                          |
| Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair | Create, Create Key Pair, Register, Derive Key, Certify, Re-certify, Re-key , Re-key Key Pair |
| All Cryptographic Objects, Opaque Objects                                                    | All Cryptographic Objects, Opaque Objects                                                    |

### 3.18 Operation Policy Name

**Data Type:** Text String

| Field                                                             | Encoding / Type       |
|-------------------------------------------------------------------|-----------------------|
| Operation                                                         | Policy                |
| Re-key                                                            | Allowed to owner only |
| Re-key Key Pair                                                   | Allowed to owner only |
| Derive Key                                                        | Allowed to owner only |
| Locate                                                            | Allowed to owner only |
| Check                                                             | Allowed to owner only |
| Get                                                               | Allowed to owner only |
| Get Attributes                                                    | Allowed to owner only |
| Get Attribute List                                                | Allowed to owner only |
| Add Attribute                                                     | Allowed to owner only |
| Modify Attribute                                                  | Allowed to owner only |
| Delete Attribute                                                  | Allowed to owner only |
| Obtain Lease                                                      | Allowed to owner only |
| Get Usage Allocation                                              | Allowed to owner only |
| Activate                                                          | Allowed to owner only |
| Revoke                                                            | Allowed to owner only |
| Destroy                                                           | Allowed to owner only |
| Archive                                                           | Allowed to owner only |
| Recover                                                           | Allowed to owner only |
| Operation                                                         | Policy                |
| Locate                                                            | Allowed to all        |
| Check                                                             | Allowed to all        |
| Get                                                               | Allowed to all        |
| Get Attributes                                                    | Allowed to all        |
| Get Attribute List                                                | Allowed to all        |
| Add Attribute                                                     | Allowed to owner only |
| Modify Attribute                                                  | Allowed to owner only |
| Delete Attribute                                                  | Allowed to owner only |
| Obtain Lease                                                      | Allowed to all        |
| Activate                                                          | Allowed to owner only |
| Revoke                                                            | Allowed to owner only |
| Destroy                                                           | Allowed to owner only |
| Archive                                                           | Allowed to owner only |
| Recover                                                           | Allowed to owner only |
| Operation                                                         | Policy                |
| Locate                                                            | Allowed to owner only |
| Get                                                               | Allowed to owner only |
| Get Attributes                                                    | Allowed to owner only |
| Get Attribute List                                                | Allowed to owner only |
| Add Attribute                                                     | Allowed to owner only |
| Modify Attribute                                                  | Allowed to owner only |
| Delete Attribute                                                  | Allowed to owner only |
| Destroy                                                           | Allowed to owner only |
| Any operation referencing the Template using a Template-Attribute | Allowed to owner only |
| Operation                                                         | Policy                |
| Locate                                                            | Allowed to all        |
| Get                                                               | Allowed to all        |
| Get Attributes                                                    | Allowed to all        |
| Get Attribute List                                                | Allowed to all        |
| Add Attribute                                                     | Disallowed to all     |
| Modify Attribute                                                  | Disallowed to all     |
| Delete Attribute                                                  | Disallowed to all     |
| Destroy                                                           | Disallowed to all     |
| Any operation referencing the Template using a Template-Attribute | Allowed to all        |

### 3.19 Cryptographic Usage Mask

**Data Type:** Integer

### 3.20 Lease Time

**Data Type:** Interval

### 3.21 Usage Limits

**Data Type:** Structure

| Field              | Encoding / Type             |
|--------------------|-----------------------------|
| Usage Limits Total | Long Integer                |
| Usage Limits Count | Long Integer                |
| Usage Limits Unit  | Enumeration, see 9.1.3.2.31 |

### 3.22 State

**Data Type:** Enumeration, see 9.1.3.2.18

### 3.23 Initial Date

**Data Type:** Date-Time

### 3.24 Activation Date

**Data Type:** Date-Time

### 3.25 Process Start Date

**Data Type:** Date-Time

### 3.26 Protect Stop Date

**Data Type:** Date-Time

### 3.27 Deactivation Date

**Data Type:** Date-Time

### 3.28 Destroy Date

**Data Type:** Date-Time

### 3.29 Compromise Occurrence Date

**Data Type:** Date-Time

### 3.30 Compromise Date

**Data Type:** Date-Time

### 3.31 Revocation Reason

**Data Type:** Structure

| Field                  | Encoding / Type             |
|------------------------|-----------------------------|
| Revocation Reason Code | Enumeration, see 9.1.3.2.19 |
| Revocation Message     | Text String                 |

### 3.32 Archive Date

**Data Type:** Date-Time

### 3.33 Object Group

**Data Type:** Text String

### 3.34 Fresh

**Data Type:** Boolean

### 3.35 Link

**Data Type:** Structure

| Field                             | Encoding / Type             |
|-----------------------------------|-----------------------------|
| Link Type                         | Enumeration, see 9.1.3.2.20 |
| Linked Object Identifier, see 3.1 | Text String                 |

### 3.36 Application Specific Information

**Data Type:** Structure

| Field                 | Encoding / Type |
|-----------------------|-----------------|
| Application Namespace | Text String     |
| Application Data      | Text String     |

### 3.37 Contact Information

**Data Type:** Text String

### 3.38 Last Change Date

**Data Type:** Date-Time

### 3.39 Custom Attribute

**Data Type:** Any data type or structure. If a structure, then the structure SHALL NOT include sub structures

### 3.40 Alternative Name

**Data Type:** Structure

| Field                  | Encoding / Type             |
|------------------------|-----------------------------|
| Alternative Name Value | Text String                 |
| Alternative Name Type  | Enumeration, see 9.1.3.2.34 |

### 3.41 Key Value Present

**Data Type:** Boolean

### 3.42 Key Value Location

**Data Type:** Structure

| Field                    | Encoding / Type             |
|--------------------------|-----------------------------|
| Key Value Location Value | Text String                 |
| Key Value Location Type  | Enumeration, see 9.1.3.2.35 |

### 3.43 Original Creation Date

**Data Type:** Date-Time

