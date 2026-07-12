# KMIP 3.0 — Managed Objects

Total: **12** structures

## 2.1.1 User

_No fields extracted (see spec for definition)_

## 2.1.2 Group

_No fields extracted (see spec for definition)_

## 2.1.3 Credentials

| Field                   | Encoding / Type                     | Required |
|-------------------------|-------------------------------------|----------|
| Password                | Text String                         | No       |
| Password Salt           | Byte String                         | No       |
| Password Salt Algorithm | Cryptographic Algorithm Enumeration | No       |
| Salted Password         | Byte String                         | No       |
| Iteration Count         | Integer                             | No       |
| Device Serial Number    | Text String                         | No       |
| Device Identifier       | Text String                         | No       |
| Network Identifier      | Text String                         | No       |
| Machine Identifier      | Text String                         | No       |
| Media Identifier        | Text String                         | No       |
| OTP Algorithm           | Enumeration                         | Yes      |
| OTP Digest              | Cryptographic Algorithm Enumeration | No       |
| OTP Serial              | Text String                         | No       |
| OTP Seed                | Byte String                         | No       |
| OTP Interval            | Interval                            | No       |
| OTP Digits              | Integer                             | No       |
| Hashing Algorithm       | Enumeration                         | No       |
| Hash Username Password  | Byte String                         | Yes      |
| Hash Password Username  | Byte String                         | Yes      |

## 2.2.1 Certificate

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Certificate Type  | Enumeration     | Yes      |
| Certificate Value | Byte String     | Yes      |

## 2.2.2 Certificate Request

| Field                     | Encoding / Type | Required |
|---------------------------|-----------------|----------|
| Certificate Request Type  | Enumeration     | Yes      |
| Certificate Request Value | Byte String     | Yes      |

## 2.2.3 Opaque Object

| Field             | Encoding / Type | Required |
|-------------------|-----------------|----------|
| Opaque Data Type  | Enumeration     | Yes      |
| Opaque Data Value | Byte String     | Yes      |

## 2.2.4 PGP Key

| Field           | Encoding / Type       | Required |
|-----------------|-----------------------|----------|
| PGP Key Version | Integer               | Yes      |
| Key Block       | Object Data Structure | Yes      |

## 2.2.5 Private Key

| Field     | Encoding / Type       | Required |
|-----------|-----------------------|----------|
| Key Block | Object Data Structure | Yes      |

## 2.2.6 Public Key

| Field     | Encoding / Type       | Required |
|-----------|-----------------------|----------|
| Key Block | Object Data Structure | Yes      |

## 2.2.7 Secret Data

| Field            | Encoding / Type       | Required |
|------------------|-----------------------|----------|
| Secret Data Type | Enumeration           | Yes      |
| Key Block        | Object Data Structure | Yes      |

## 2.2.8 Split Key

| Field               | Encoding / Type       | Required                                                                 |
|---------------------|-----------------------|--------------------------------------------------------------------------|
| Split Key Parts     | Integer               | Yes                                                                      |
| Key Part Identifier | Integer               | Yes                                                                      |
| Split Key Threshold | Integer               | Yes                                                                      |
| Split Key Method    | Enumeration           | Yes                                                                      |
| Prime Field Size    | Big Integer           | No, REQUIRED only if Split Key Method is Polynomial Sharing Prime Field. |
| Key Block           | Object Data Structure | Yes                                                                      |

## 2.2.9 Symmetric Key

| Field     | Encoding / Type | Required |
|-----------|-----------------|----------|
| Key Block | Structure       | Yes      |

