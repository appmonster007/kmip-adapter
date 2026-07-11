# KMIP 1.2 — Structures (Base Objects & Message Contents)

Total: **25** structures

## 2.1.1 Attribute

| Field           | Encoding / Type                               | Required                                                |
|-----------------|-----------------------------------------------|---------------------------------------------------------|
| Attribute Name  | Text String                                   | Yes                                                     |
| Attribute Index | Integer                                       | No                                                      |
| Attribute Value | Varies, depending on attribute. See Section 3 | Yes, except for the Notify operation (see Section 5.1 ) |

## 2.1.2 Credential

| Field                   | Encoding / Type                  | Required |
|-------------------------|----------------------------------|----------|
| Credential Type         | Enumeration, see 9.1.3.2.1       | Yes      |
| Credential Value        | Varies based on Credential Type. | Yes      |
| Credential Value        | Structure                        |          |
| Username                | Text String                      | Yes      |
| Password                | Text String                      | No       |
| Credential Value        | Structure                        |          |
| Device Serial Number    | Text String                      | No       |
| Password                | Text String                      | No       |
| Device Identifier       | Text String                      | No       |
| Network Identifier      | Text String                      | No       |
| Machine Identifier      | Text String                      | No       |
| Media Identifier        | Text String                      | No       |
| Credential Value        | Structure                        |          |
| Nonce                   | Structure, see 2.1.14            | Yes      |
| Attestation Type        | Enumeration, see 9.1.3.2.36      | Yes      |
| Attestation Measurement | Byte String                      | No       |
| Attestation Assertion   | Byte String                      | No       |

## 2.1.3 Key Block

| Field                   | Encoding / Type                                                                   | Required                                                                                                                                                                                                                                |
|-------------------------|-----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Key Block               | Structure                                                                         |                                                                                                                                                                                                                                         |
| Key Format Type         | Enumeration, see 9.1.3.2.3                                                        | Yes                                                                                                                                                                                                                                     |
| Key Compression Type    | Enumeration, see 9.1.3.2.2                                                        | No                                                                                                                                                                                                                                      |
| Key Value               | Byte String: for wrapped Key Value; Structure: for plaintext Key Value, see 2.1.4 | No                                                                                                                                                                                                                                      |
| Cryptographic Algorithm | Enumeration, see 9.1.3.2.13                                                       | Yes. MAY be omitted only if this information is available from the Key Value. Does not apply to Secret Data (see Section 2.2.7 ) or Opaque Objects (see Section 2.2.8 ). If present, the Cryptographic Length SHALL also be present.    |
| Cryptographic Length    | Integer                                                                           | Yes. MAY be omitted only if this information is available from the Key Value. Does not apply to Secret Data (see Section 2.2.7 ) or Opaque Objects (see Section 2.2.8 ). If present, the Cryptographic Algorithm SHALL also be present. |
| Key Wrapping Data       | Structure, see 2.1.5                                                              | No. SHALL only be present if the key is wrapped.                                                                                                                                                                                        |

## 2.1.4 Key Value

| Field        | Encoding / Type                                                                                                                                    | Required            |
|--------------|----------------------------------------------------------------------------------------------------------------------------------------------------|---------------------|
| Key Value    | Structure                                                                                                                                          |                     |
| Key Material | Byte String: for Raw, Opaque, PKCS1, PKCS8, ECPrivateKey, or Extension Key Format types; Structure: for Transparent, or Extension Key Format Types | Yes                 |
| Attribute    | Attribute Object, see Section 2.1.1                                                                                                                | No. MAY be repeated |

## 2.1.5 Key Wrapping Data

| Field                         | Encoding / Type             | Required                                                                                                                                              |
|-------------------------------|-----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| Key Wrapping Data             | Structure                   |                                                                                                                                                       |
| Wrapping Method               | Enumeration, see 9.1.3.2.4  | Yes                                                                                                                                                   |
| Encryption Key Information    | Structure, see below        | No. Corresponds to the key that was used to encrypt the Key Value.                                                                                    |
| MAC/Signature Key Information | Structure, see below        | No. Corresponds to the symmetric key used to MAC the Key Value or the private key used to sign the Key Value                                          |
| MAC/Signature                 | Byte String                 | No                                                                                                                                                    |
| IV/Counter/Nonce              | Byte String                 | No                                                                                                                                                    |
| Encoding Option               | Enumeration, see 9.1.3.2.32 | No. Specifies the encoding of the Key Value Byte String. If not present, the wrapped Key Value structure SHALL be TTLV encoded.                       |
| Encryption Key Information    | Structure                   |                                                                                                                                                       |
| Unique Identifier             | Text string, see 3.1        | Yes                                                                                                                                                   |
| Cryptographic Parameters      | Structure, see 3.6          | No                                                                                                                                                    |
| MAC/Signature Key Information | Structure                   |                                                                                                                                                       |
| Unique Identifier             | Text string, see 3.1        | Yes. It SHALL be either the Unique Identifier of the Symmetric Key used to MAC, or of the Private Key (or its corresponding Public Key) used to sign. |
| Cryptographic Parameters      | Structure, see 3.6          | No                                                                                                                                                    |

## 2.1.6 Key Wrapping Specificati on

| Field                         | Encoding / Type             | Required                                                                            |
|-------------------------------|-----------------------------|-------------------------------------------------------------------------------------|
| Key Wrapping Specification    | Structure                   |                                                                                     |
| Wrapping Method               | Enumeration, see 9.1.3.2.4  | Yes                                                                                 |
| Encryption Key Information    | Structure, see 2.1.5        | No, SHALL be present if MAC/Signature Key Information is omitted                    |
| MAC/Signature Key Information | Structure, see 2.1.5        | No, SHALL be present if Encryption Key Information is omitted                       |
| Attribute Name                | Text String                 | No, MAY be repeated                                                                 |
| Encoding Option               | Enumeration, see 9.1.3.2.32 | No. If Encoding Option is not present, the wrapped Key Value SHALL be TTLV encoded. |

## 2.1.7.1 Transparent Symmetric Key

| Field        | Encoding / Type | Required |
|--------------|-----------------|----------|
| Key Material | Structure       |          |

## 2.1.7.2 Transparent DSA Private Key

| Field        | Encoding / Type | Required |
|--------------|-----------------|----------|
| Key Material | Structure       |          |
| P            | Big Integer     | Yes      |
| Q            | Big Integer     | Yes      |
| G            | Big Integer     | Yes      |
| X            | Big Integer     | Yes      |

## 2.1.7.3 Transparent DSA Public Key

| Field        | Encoding / Type | Required |
|--------------|-----------------|----------|
| Key Material | Structure       |          |
| P            | Big Integer     | Yes      |
| Q            | Big Integer     | Yes      |
| G            | Big Integer     | Yes      |
| Y            | Big Integer     | Yes      |

## 2.1.7.4 Transparent RSA Private Key

| Field            | Encoding / Type | Required |
|------------------|-----------------|----------|
| Key Material     | Structure       |          |
| Modulus          | Big Integer     | Yes      |
| Private Exponent | Big Integer     | No       |
| Public Exponent  | Big Integer     | No       |
| P                | Big Integer     | No       |
| Q                | Big Integer     | No       |
| Prime Exponent P | Big Integer     | No       |
| Prime Exponent Q | Big Integer     | No       |
| CRT Coefficient  | Big Integer     | No       |

## 2.1.7.5 Transparent RSA Public Key

| Field           | Encoding / Type | Required |
|-----------------|-----------------|----------|
| Key Material    | Structure       |          |
| Modulus         | Big Integer     | Yes      |
| Public Exponent | Big Integer     | Yes      |

## 2.1.7.6 Transparent DH Private Key

| Field        | Encoding / Type | Required |
|--------------|-----------------|----------|
| Key Material | Structure       |          |
| P            | Big Integer     | Yes      |
| Q            | Big Integer     | No       |
| G            | Big Integer     | Yes      |
| J            | Big Integer     | No       |
| X            | Big Integer     | Yes      |

## 2.1.7.7 Transparent DH Public Key

| Field        | Encoding / Type | Required |
|--------------|-----------------|----------|
| Key Material | Structure       |          |
| P            | Big Integer     | Yes      |
| Q            | Big Integer     | No       |
| G            | Big Integer     | Yes      |
| J            | Big Integer     | No       |
| Y            | Big Integer     | Yes      |

## 2.1.7.8 Transparent ECDSA Private Key

| Field             | Encoding / Type            | Required |
|-------------------|----------------------------|----------|
| Key Material      | Structure                  |          |
| Recommended Curve | Enumeration, see 9.1.3.2.5 | Yes      |
| D                 | Big Integer                | Yes      |

## 2.1.7.9 Transparent ECDSA Public Key

| Field             | Encoding / Type            | Required |
|-------------------|----------------------------|----------|
| Key Material      | Structure                  |          |
| Recommended Curve | Enumeration, see 9.1.3.2.5 | Yes      |
| Q String          | Byte String                | Yes      |

## 2.1.7.10 Transparent ECDH Private Key

| Field             | Encoding / Type            | Required |
|-------------------|----------------------------|----------|
| Key Material      | Structure                  |          |
| Recommended Curve | Enumeration, see 9.1.3.2.5 | Yes      |
| D                 | Big Integer                | Yes      |

## 2.1.7.11 Transparent ECDH Public Key

| Field             | Encoding / Type            | Required |
|-------------------|----------------------------|----------|
| Key Material      | Structure                  |          |
| Recommended Curve | Enumeration, see 9.1.3.2.5 | Yes      |
| Q String          | Byte String                | Yes      |

## 2.1.7.12 Transparent ECMQV Private Key

| Field             | Encoding / Type            | Required |
|-------------------|----------------------------|----------|
| Key Material      | Structure                  |          |
| Recommended Curve | Enumeration, see 9.1.3.2.5 | Yes      |
| D                 | Big Integer                | Yes      |

## 2.1.7.13 Transparent ECMQV Public Key

| Field             | Encoding / Type            | Required |
|-------------------|----------------------------|----------|
| Key Material      | Structure                  |          |
| Recommended Curve | Enumeration, see 9.1.3.2.5 | Yes      |
| Q String          | Byte String                | Yes      |

## 2.1.9 Extension Information

| Field                 | Encoding / Type | Required |
|-----------------------|-----------------|----------|
| Extension Information | Structure       |          |
| Extension Name        | Text String     | Yes      |
| Extension Tag         | Integer         | No       |
| Extension Type        | Integer         | No       |

## 2.1.10 Data

_No fields extracted (see spec for definition)_

## 2.1.11 Data Length

| Field       | Encoding / Type | Required |
|-------------|-----------------|----------|
| Data Length | Integer         |          |

## 2.1.12 Signature Data

| Field          | Encoding / Type | Required |
|----------------|-----------------|----------|
| Signature Data | Byte String     |          |

## 2.1.13 MAC Data

| Field    | Encoding / Type | Required |
|----------|-----------------|----------|
| MAC Data | Byte String     |          |

## 2.1.14 Nonce

| Field       | Encoding / Type | Required |
|-------------|-----------------|----------|
| Nonce ID    | Byte String     | Yes      |
| Nonce Value | Byte String     | Yes      |

