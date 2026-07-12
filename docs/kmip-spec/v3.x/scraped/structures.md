# KMIP 3.0 — Data Structures (§3, §5, §7, §9)

Total: **79** structures

## 3.1 Key Block

| Field                   | Encoding / Type                                                                             | Required                                                                                                                                                                               |
|-------------------------|---------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Value                   | Description                                                                                 |                                                                                                                                                                                        |
| RSA keys                | Typically 1024, 2048 or 3072 bits in length.                                                |                                                                                                                                                                                        |
| 3DES keys               | Typically from 112 to 192 bits (depending upon key length and the presence of parity bits). |                                                                                                                                                                                        |
| AES keys                | 128, 192 or 256 bits in length                                                              |                                                                                                                                                                                        |
| Key Format Type         | Enumeration                                                                                 | Yes                                                                                                                                                                                    |
| Key Compression Type    | Enumeration                                                                                 | No                                                                                                                                                                                     |
| Key Value               | Byte String: for wrapped Key Value; Structure: for plaintext Key Value                      | No                                                                                                                                                                                     |
| Cryptographic Algorithm | Enumeration                                                                                 | Yes. MAY be omitted only if this information is available from the Key Value. Does not apply to Secret Data or Opaque If present, the Cryptographic Length SHALL also be present.      |
| Cryptographic Length    | Integer                                                                                     | Yes. MAY be omitted only if this information is available from the Key Value. Does not apply to Secret Data (or Opaque. If present, the Cryptographic Algorithm SHALL also be present. |
| Key Wrapping Data       | Object Data Structure                                                                       | No. SHALL only be present if the key is wrapped.                                                                                                                                       |

## 3.2 Key Value

| Field        | Encoding / Type                                                                                                                                     | Required |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| Key Material | Byte String: for Raw, Opaque, PKCS1, PKCS8, ECPrivateKey , or Extension Key Format types; Structure: for Transparent, or Extension Key Format Types | Yes      |
| Attributes   | Structure                                                                                                                                           | No       |

## 3.3 Key Wrapping Data

| Field                         | Encoding / Type                                                                                        | Required                                                                                                                                              |
|-------------------------------|--------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| Value                         | Description                                                                                            |                                                                                                                                                       |
| Wrapping Method               | Indicates the method used to wrap the Key Value.                                                       |                                                                                                                                                       |
| Encryption Key Information    | Contains the Unique Identifier value of the encryption key and associated cryptographic parameters.    |                                                                                                                                                       |
| MAC/Signature Key Information | Contains the Unique Identifier value of the MAC/signature key and associated cryptographic parameters. |                                                                                                                                                       |
| MAC/Signature                 | Contains a MAC or signature of the Key Value                                                           |                                                                                                                                                       |
| IV/Counter/Nonce              | If REQUIRED by the wrapping method.                                                                    |                                                                                                                                                       |
| Wrapping Method               | Enumeration                                                                                            | Yes                                                                                                                                                   |
| Encryption Key Information    | Structure, see below                                                                                   | No. Corresponds to the key that was used to encrypt the Key Value.                                                                                    |
| MAC/Signature Key Information | Structure, see below                                                                                   | No. Corresponds to the symmetric key used to MAC the Key Value or the private key used to sign the Key Value                                          |
| MAC/Signature                 | Byte String                                                                                            | No                                                                                                                                                    |
| IV/Counter/Nonce              | Byte String                                                                                            | No                                                                                                                                                    |
| Encoding Option               | Enumeration                                                                                            | No. Specifies the encoding of the Key Value Byte String. If not present, the wrapped Key Value structure SHALL be TTLV encoded.                       |
| Unique Identifier             | Reference or Name Reference or Unique Identifier Enumeration or Integer                                | Yes                                                                                                                                                   |
| Cryptographic Parameters      | Structure                                                                                              | No                                                                                                                                                    |
| Unique Identifier             | Reference or Name Reference or Unique Identifier Enumeration or Integer                                | Yes. It SHALL be either the Unique Identifier of the Symmetric Key used to MAC, or of the Private Key (or its corresponding Public Key) used to sign. |
| Cryptographic Parameters      | Structure                                                                                              | No                                                                                                                                                    |

## 3.4 Transparent Symmetric Key

| Field | Encoding / Type | Required |
|-------|-----------------|----------|
| Key   | Byte String     | Yes      |

## 3.5 Transparent DSA Private Key

| Field | Encoding / Type | Required |
|-------|-----------------|----------|
| P     | Big Integer     | Yes      |
| Q     | Big Integer     | Yes      |
| G     | Big Integer     | Yes      |
| X     | Big Integer     | Yes      |

## 3.6 Transparent DSA Public Key

| Field | Encoding / Type | Required |
|-------|-----------------|----------|
| P     | Big Integer     | Yes      |
| Q     | Big Integer     | Yes      |
| G     | Big Integer     | Yes      |
| Y     | Big Integer     | Yes      |

## 3.7 Transparent RSA Private Key

| Field            | Encoding / Type | Required |
|------------------|-----------------|----------|
| Modulus          | Big Integer     | Yes      |
| Private Exponent | Big Integer     | No       |
| Public Exponent  | Big Integer     | No       |
| P                | Big Integer     | No       |
| Q                | Big Integer     | No       |
| Prime Exponent P | Big Integer     | No       |
| Prime Exponent Q | Big Integer     | No       |
| CRT Coefficient  | Big Integer     | No       |

## 3.8 Transparent RSA Public Key

| Field           | Encoding / Type | Required |
|-----------------|-----------------|----------|
| Modulus         | Big Integer     | Yes      |
| Public Exponent | Big Integer     | Yes      |

## 3.9 Transparent DH Private Key

| Field | Encoding / Type | Required |
|-------|-----------------|----------|
| P     | Big Integer     | Yes      |
| Q     | Big Integer     | No       |
| G     | Big Integer     | Yes      |
| J     | Big Integer     | No       |
| X     | Big Integer     | Yes      |

## 3.10 Transparent DH Public Key

| Field | Encoding / Type | Required |
|-------|-----------------|----------|
| P     | Big Integer     | Yes      |
| Q     | Big Integer     | No       |
| G     | Big Integer     | Yes      |
| J     | Big Integer     | No       |
| Y     | Big Integer     | Yes      |

## 3.11 Transparent EC Private Key

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Recommended Curve | Enumeration     | Yes      |
| D                 | Big Integer     | Yes      |

## 3.12 Transparent EC Public Key

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Recommended Curve | Enumeration     | Yes      |
| Q String          | Byte String     | Yes      |

## 5.1 Attributes

| Field                                   | Encoding / Type       | Required |
|-----------------------------------------|-----------------------|----------|
| Any attribute in §4 - Object Attributes | Any , MAY be repeated | No       |

## 5.2 Common Attributes

| Field                                   | Encoding / Type       | Required |
|-----------------------------------------|-----------------------|----------|
| Any attribute in §4 - Object Attributes | Any , MAY be repeated | No       |

## 5.3 Private Key Attributes

| Field                                   | Encoding / Type       | Required |
|-----------------------------------------|-----------------------|----------|
| Any attribute in §4 - Object Attributes | Any , MAY be repeated | No       |

## 5.4 Public Key Attributes

| Field                                   | Encoding / Type       | Required |
|-----------------------------------------|-----------------------|----------|
| Any attribute in §4 - Object Attributes | Any , MAY be repeated | No       |

## 5.5 Attribute Reference

| Field                 | Encoding / Type                                                                              | Required |
|-----------------------|----------------------------------------------------------------------------------------------|----------|
| Vendor Identification | Text String (with usage limited to alphanumeric, underscore and period – i.e. [A-Za-z0-9_.]) | Yes      |
| Attribute Name        | Text String                                                                                  | Yes      |
| Attribute Reference   | Enumeration (Tag)                                                                            | Yes      |

## 5.6 Current Attribute

| Field                                   | Encoding / Type | Required |
|-----------------------------------------|-----------------|----------|
| Any attribute in §4 - Object Attributes | Any             | Yes      |

## 5.7 New Attribute

| Field                                   | Encoding / Type | Required |
|-----------------------------------------|-----------------|----------|
| Any attribute in §4 - Object Attributes | Any             | Yes      |

## 7.1 Asynchronous Correlation Values

| Field                          | Encoding / Type | Required |
|--------------------------------|-----------------|----------|
| Asynchronous Correlation Value | Byte String     | No       |

## 7.2 Asynchronous Request

| Field                          | Encoding / Type    | Required |
|--------------------------------|--------------------|----------|
| Asynchronous Correlation Value | Byte String        | Yes      |
| Operation                      | Enumeration        | Yes      |
| Submission Date                | Date Time Extended | Yes      |
| Processing Stage               | Enumeration        | Yes      |

## 7.3 Authenticated Encryption Additional Data

| Field                                    | Encoding / Type | Required |
|------------------------------------------|-----------------|----------|
| Authenticated Encryption Additional Data | Byte String     | No       |

## 7.4 Authenticated Encryption Tag

| Field                        | Encoding / Type | Required |
|------------------------------|-----------------|----------|
| Authenticated Encryption Tag | Byte String     | No       |

## 7.5 Capability Information

| Field                     | Encoding / Type | Required |
|---------------------------|-----------------|----------|
| Streaming Capability      | Boolean         | No       |
| Asynchronous Capability   | Boolean         | No       |
| Attestation Capability    | Boolean         | No       |
| Batch Undo Capability     | Boolean         | No       |
| Batch Continue Capability | Boolean         | No       |
| Unwrap Mode               | Enumeration     | No       |
| Destroy Action            | Enumeration     | No       |
| Shredding Algorithm       | Enumeration     | No       |
| RNG Mode                  | Enumeration     | No       |
| Quantum Safe Capability   | Boolean         | No       |

## 7.6 Constraint

| Field         | Encoding / Type | Required |
|---------------|-----------------|----------|
| Constraint    | Structure       | YES      |
| Object Types  | Structure       | No       |
| Object Groups | Structure       | No       |
| Attributes    | Structure       | No       |

## 7.7 Constraints

| Field       | Encoding / Type | Required             |
|-------------|-----------------|----------------------|
| Constraints | Structure       | YES                  |
| Constraint  | Structure       | No, May be repeated. |

## 7.8 Correlation Value

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Correlation Value | Byte String     |          |

## 7.9 Credential Information

| Field           | Encoding / Type              | Required |
|-----------------|------------------------------|----------|
| Credential Type | Enumeration, may be repeated | Yes      |

## 7.10 Data

| Field       | Encoding / Type                                                                                                                   | Required |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------|----------|
| Byte String | The Data                                                                                                                          |          |
| Enumeration | Data Enumeration                                                                                                                  |          |
| Integer     | Zero based nth Data in the response. If negative the count is backwards from the beginning of the current operation’s batch item. |          |
| Data        | Byte String, Enumeration or Integer                                                                                               |          |

## 7.11 Data Length

| Field       | Encoding / Type | Required |
|-------------|-----------------|----------|
| Data Length | Integer         |          |

## 7.12 Defaults Information

| Field           | Encoding / Type            | Required |
|-----------------|----------------------------|----------|
| Object Defaults | Structure, may be repeated | No       |

## 7.13 Derivation Parameters

| Field                     | Encoding / Type | Required                                                                                                                             |
|---------------------------|-----------------|--------------------------------------------------------------------------------------------------------------------------------------|
| Derivation Parameters     | Structure       | Yes.                                                                                                                                 |
| Cryptographic Parameters, | Structure       | No, depends on the PRF.                                                                                                              |
| Initialization Vector     | Byte String     | No, depends on the PRF (if different than those defined in [PKCS#5] ) and mode of operation: an empty IV is assumed if not provided. |
| Derivation Data           | Byte String     | Yes, unless the Unique Identifier of a Secret Data object is provided. May be repeated.                                              |
| Salt                      | Byte String     | Yes if Derivation method is PBKDF2.                                                                                                  |
| Iteration Count           | Integer         | Yes if Derivation method is PBKDF2.                                                                                                  |

## 7.14 Extension Information

| Field                          | Encoding / Type | Required |
|--------------------------------|-----------------|----------|
| Extension Name                 | Text String     | Yes      |
| Extension Tag                  | Integer         | No       |
| Extension Enumeration          | Integer         | No       |
| Extension Attribute            | Boolean         | No       |
| Extension Parent Structure Tag | Integer         | No       |
| Extension Description          | Text String     | No       |

## 7.15 Final Indicator

| Field           | Encoding / Type | Required |
|-----------------|-----------------|----------|
| Final Indicator | Boolean         |          |

## 7.16 Interop Function

| Field            | Encoding / Type | Required |
|------------------|-----------------|----------|
| Interop Function | Enumeration     |          |

## 7.17 Interop Identifier

| Field              | Encoding / Type | Required |
|--------------------|-----------------|----------|
| Interop identifier | TextString      |          |

## 7.18 Init Indicator

| Field          | Encoding / Type | Required |
|----------------|-----------------|----------|
| Init Indicator | Boolean         |          |

## 7.19 Key Wrapping Specification

| Field                         | Encoding / Type              | Required                                                                            |
|-------------------------------|------------------------------|-------------------------------------------------------------------------------------|
| Wrapping Method               | Enumeration                  | Yes                                                                                 |
| Encryption Key Information    | Structure                    | No, SHALL be present if MAC/Signature Key Information is omitted                    |
| MAC/Signature Key Information | Structure                    | No, SHALL be present if Encryption Key Information is omitted                       |
| Attribute Reference           | Text String, MAY be repeated | No                                                                                  |
| Encoding Option               | Enumeration                  | No. If Encoding Option is not present, the wrapped Key Value SHALL be TTLV encoded. |

## 7.20 Log Message

| Field       | Encoding / Type | Required |
|-------------|-----------------|----------|
| Log Message | Text String     |          |

## 7.21 MAC Data

| Field    | Encoding / Type | Required |
|----------|-----------------|----------|
| MAC Data | Byte String     |          |

## 7.22 Objects

| Field             | Encoding / Type                                       | Required             |
|-------------------|-------------------------------------------------------|----------------------|
| Unique Identifier | Identifier, Unique Identifier, Enumeration or Integer | No, May be repeated. |

## 7.23 Object Defaults

| Field                     | Encoding / Type         | Required |
|---------------------------|-------------------------|----------|
| Object Type | ObjectTypes | Enumeration | Structure | Yes      |
| Attributes                | Structure               | Yes      |
| Object Groups             | Structure               | No       |

## 7.24 Object Groups

| Field      | Encoding / Type                              | Required |
|------------|----------------------------------------------|----------|
| Group Link | Reference or Name Reference, May be repeated | No       |

## 7.25 Object Types

| Field       | Encoding / Type | Required             |
|-------------|-----------------|----------------------|
| Object Type | Enumeration     | No, May be repeated. |

## 7.26 Operations

| Field     | Encoding / Type | Required             |
|-----------|-----------------|----------------------|
| Operation | Enumeration     | No, May be repeated. |

## 7.27 PKCS#11 Function

| Field            | Encoding / Type | Required |
|------------------|-----------------|----------|
| PKCS#11 Function | Enumeration     | Yes      |

## 7.28 PKCS#11 Input Parameters

| Field                    | Encoding / Type | Required |
|--------------------------|-----------------|----------|
| PKCS#11 Input Parameters | ByteString      | No       |

## 7.29 PKCS#11 Interface

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| PKCS#11 Interface | TextString      | No       |

## 7.30 PKCS#11 Output Parameters

| Field                     | Encoding / Type | Required |
|---------------------------|-----------------|----------|
| PKCS#11 Output Parameters | ByteString      | No       |

## 7.31 PKCS#11 Return Code

| Field               | Encoding / Type | Required |
|---------------------|-----------------|----------|
| PKCS#11 Return Code | Enumeration     | Yes      |

## 7.32 Profile Information

| Field           | Encoding / Type | Required |
|-----------------|-----------------|----------|
| Profile Name    | Enumeration     | Yes      |
| Profile Version | Structure       | No       |
| Server URI      | Text String     | No       |
| Server Port     | Integer         | No       |

## 7.33 Profile Version

| Field                 | Encoding / Type | Required |
|-----------------------|-----------------|----------|
| Profile Version Major | Integer         | Yes      |
| Profile Version Minor | Integer         | Yes      |

## 7.34 Protection Storage Masks

_No sub-fields (see spec for definition)_

## 7.35 Right

| Field         | Encoding / Type | Required |
|---------------|-----------------|----------|
| Usage Limits  | Structure       | No       |
| Operations    | Structure       | No       |
| Objects       | Structure       | No       |
| Object Groups | Structure       | No       |

## 7.36 Rights

| Field | Encoding / Type | Required             |
|-------|-----------------|----------------------|
| Right | Structure       | No, May be repeated. |

## 7.37 RNG Parameters

| Field                   | Encoding / Type | Required |
|-------------------------|-----------------|----------|
| RNG Algorithm           | Enumeration     | Yes      |
| Cryptographic Algorithm | Enumeration     | No       |
| Cryptographic Length    | Integer         | No       |
| Hashing Algorithm       | Enumeration     | No       |
| DRBG Algorithm          | Enumeration     | No       |
| Recommended Curve       | Enumeration     | No       |
| FIPS186 Variation       | Enumeration     | No       |
| Prediction Resistance   | Boolean         | No       |

## 7.38 Server Information

| Field                          | Encoding / Type              | Required |
|--------------------------------|------------------------------|----------|
| Server name                    | Text String                  | No       |
| Server serial number           | Text String                  | No       |
| Server version                 | Text String                  | No       |
| Server load                    | Text String                  | No       |
| Product name                   | Text String                  | No       |
| Build level                    | Text String                  | No       |
| Build date                     | Text String                  | No       |
| Cluster info                   | Text String                  | No       |
| Alternative failover endpoints | Text String, MAY be repeated | No       |
| Vendor-Specific                | Any, MAY be repeated         | No       |

## 7.39 Signature Data

| Field          | Encoding / Type | Required |
|----------------|-----------------|----------|
| Signature Data | Byte String     |          |

## 7.40 Ticket

| Field        | Encoding / Type | Required |
|--------------|-----------------|----------|
| Ticket Type  | Enumeration     | Yes      |
| Ticket Value | Byte String     | Yes      |

## 7.41 Usage Limits

| Field              | Encoding / Type | Required |
|--------------------|-----------------|----------|
| Usage Limits Total | Long Integer    | Yes      |
| Usage Limits Count | Long Integer    | Yes      |
| Usage Limits Unit  | Enumeration     | Yes      |

## 7.42 Validation Information

| Field                             | Encoding / Type              | Required |
|-----------------------------------|------------------------------|----------|
| Validation Authority Type         | Enumeration                  | Yes      |
| Validation Authority Country      | Text String                  | No       |
| Validation Authority URI          | Text String                  | No       |
| Validation Version Major          | Integer                      | Yes      |
| Validation Version Minor          | Integer                      | No       |
| Validation Type                   | Enumeration                  | Yes      |
| Validation Level                  | Integer                      | Yes      |
| Validation Certificate Identifier | Text String                  | No       |
| Validation Certificate URI        | Text String                  | No       |
| Validation Vendor URI             | Text String                  | No       |
| Validation Profile                | Text String, MAY be repeated | No       |

## 9.1 Asynchronous Correlation Value

| Field                          | Encoding / Type | Required |
|--------------------------------|-----------------|----------|
| Asynchronous Correlation Value | Byte String     |          |

## 9.2 Asynchronous Indicator

| Field                  | Encoding / Type | Required |
|------------------------|-----------------|----------|
| Asynchronous Indicator | Enumeration     |          |

## 9.3 Attestation Capable Indicator

| Field                         | Encoding / Type | Required |
|-------------------------------|-----------------|----------|
| Attestation Capable Indicator | Boolean         |          |

## 9.4 Authentication

_No sub-fields (see spec for definition)_

## 9.5 Batch Error Continuation Option

| Field                           | Encoding / Type | Required |
|---------------------------------|-----------------|----------|
| Batch Error Continuation Option | Enumeration     |          |

## 9.6 Batch Item

_No sub-fields (see spec for definition)_

## 9.7 Correlation Value (Client)

| Field                    | Encoding / Type | Required |
|--------------------------|-----------------|----------|
| Client Correlation Value | Text String     |          |

## 9.8 Correlation Value (Server)

| Field                    | Encoding / Type | Required |
|--------------------------|-----------------|----------|
| Server Correlation Value | Text String     |          |

## 9.9 Credential

| Field                   | Encoding / Type                  | Required |
|-------------------------|----------------------------------|----------|
| Credential Type         | Enumeration                      | Yes      |
| Credential Value        | Varies based on Credential Type. | Yes      |
| Username                | Text String                      | Yes      |
| Password                | Text String                      | No       |
| Device Serial Number    | Text String                      | No       |
| Password                | Text String                      | No       |
| Device Identifier       | Text String                      | No       |
| Network Identifier      | Text String                      | No       |
| Machine Identifier      | Text String                      | No       |
| Media Identifier        | Text String                      | No       |
| Nonce                   | Structure                        | Yes      |
| Attestation Type        | Enumeration                      | Yes      |
| Attestation Measurement | Byte String                      | No       |
| Attestation Assertion   | Byte String                      | No       |
| Username                | Text String                      | Yes      |
| Password                | Text String                      | No       |
| One Time Password       | Text String                      | Yes      |
| Username                | Text String                      | Yes      |
| Timestamp               | Date Time Extended               | Yes      |
| Hashing Algorithm       | Enumeration                      | No       |
| Hashed Password         | Byte String                      | Yes      |
| Ticket                  | Structure                        | Yes      |

## 9.10 Maximum Response Size

| Field                 | Encoding / Type | Required |
|-----------------------|-----------------|----------|
| Maximum Response Size | Integer         |          |

## 9.11 Message Extension

| Field                 | Encoding / Type                                                                              | Required |
|-----------------------|----------------------------------------------------------------------------------------------|----------|
| Vendor Identification | Text String (with usage limited to alphanumeric, underscore and period – i.e. [A-Za-z0-9_.]) |          |
| Criticality Indicator | Boolean                                                                                      |          |

## 9.12 Nonce

| Field       | Encoding / Type | Required |
|-------------|-----------------|----------|
| Nonce ID    | Byte String     | Yes      |
| Nonce Value | Byte String     | Yes      |

## 9.13 Operation

| Field     | Encoding / Type | Required |
|-----------|-----------------|----------|
| Operation | Enumeration     |          |

## 9.14 Protocol Version

| Field                  | Encoding / Type | Required |
|------------------------|-----------------|----------|
| Protocol Version Major | Integer         |          |
| Protocol Version Minor | Integer         |          |

## 9.15 Result Message

| Field          | Encoding / Type | Required |
|----------------|-----------------|----------|
| Result Message | Text String     |          |

## 9.16 Result Reason

| Field         | Encoding / Type | Required |
|---------------|-----------------|----------|
| Result Reason | Enumeration     |          |

## 9.17 Result Status

| Field         | Encoding / Type | Required |
|---------------|-----------------|----------|
| Result Status | Enumeration     |          |

## 9.18 Time Stamp

| Field      | Encoding / Type | Required |
|------------|-----------------|----------|
| Time Stamp | Date-Time       |          |

