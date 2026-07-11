# KMIP 2.1 — Managed Objects

Total: **9** structures

## 2.1 Certificate

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Certificate Type  | Enumeration     | Yes      |
| Certificate Value | Byte String     | Yes      |

## 2.2 Certificate Request

| Field                     | Encoding / Type | Required |
|---------------------------|-----------------|----------|
| Certificate Request Type  | Enumeration     | Yes      |
| Certificate Request Value | Byte String     | Yes      |

## 2.3 Opaque Object

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Opaque Data Type  | Enumeration     | Yes      |
| Opaque Data Value | Byte String     | Yes      |

## 2.4 PGP Key

| Field           | Encoding / Type       | Required |
|-----------------|-----------------------|----------|
| PGP Key Version | Integer               | Yes      |
| Key Block       | Object Data Structure | Yes      |

## 2.5 Private Key

| Field     | Encoding / Type       | Required |
|-----------|-----------------------|----------|
| Key Block | Object Data Structure | Yes      |

## 2.6 Public Key

| Field     | Encoding / Type       | Required |
|-----------|-----------------------|----------|
| Key Block | Object Data Structure | Yes      |

## 2.7 Secret Data

| Field            | Encoding / Type       | Required |
|------------------|-----------------------|----------|
| Secret Data Type | Enumeration           | Yes      |
| Key Block        | Object Data Structure | Yes      |

## 2.8 Split Key

| Field               | Encoding / Type       | Required                                                                 |
|---------------------|-----------------------|--------------------------------------------------------------------------|
| Split Key Parts     | Integer               | Yes                                                                      |
| Key Part Identifier | Integer               | Yes                                                                      |
| Split Key Threshold | Integer               | Yes                                                                      |
| Split Key Method    | Enumeration           | Yes                                                                      |
| Prime Field Size    | Big Integer           | No, REQUIRED only if Split Key Method is Polynomial Sharing Prime Field. |
| Key Block           | Object Data Structure | Yes                                                                      |

## 2.9 Symmetric Key

| Field     | Encoding / Type | Required |
|-----------|-----------------|----------|
| Key Block | Structure       | Yes      |

