# KMIP 1.2 — Managed Objects

Total: **9** structures

## 2.2.1 Certificate

| Field             | Encoding / Type            | Required |
|-------------------|----------------------------|----------|
| Certificate Type  | Enumeration, see 9.1.3.2.6 | Yes      |
| Certificate Value | Byte String                | Yes      |

## 2.2.2 Symmetric Key

| Field         | Encoding / Type      | Required |
|---------------|----------------------|----------|
| Symmetric Key | Structure            |          |
| Key Block     | Structure, see 2.1.3 | Yes      |

## 2.2.3 Public Key

| Field      | Encoding / Type      | Required |
|------------|----------------------|----------|
| Public Key | Structure            |          |
| Key Block  | Structure, see 2.1.3 | Yes      |

## 2.2.4 Private Key

| Field       | Encoding / Type      | Required |
|-------------|----------------------|----------|
| Private Key | Structure            |          |
| Key Block   | Structure, see 2.1.3 | Yes      |

## 2.2.5 Split Key

| Field               | Encoding / Type            | Required                                                                 |
|---------------------|----------------------------|--------------------------------------------------------------------------|
| Split Key           | Structure                  |                                                                          |
| Split Key Parts     | Integer                    | Yes                                                                      |
| Key Part Identifier | Integer                    | Yes                                                                      |
| Split Key Threshold | Integer                    | Yes                                                                      |
| Split Key Method    | Enumeration, see 9.1.3.2.8 | Yes                                                                      |
| Prime Field Size    | Big Integer                | No, REQUIRED only if Split Key Method is Polynomial Sharing Prime Field. |
| Key Block           | Structure, see 2.1.3       | Yes                                                                      |

## 2.2.6 Template

| Field     | Encoding / Type             | Required              |
|-----------|-----------------------------|-----------------------|
| Attribute | Attribute Object, see 2.1.1 | Yes. MAY be repeated. |

## 2.2.7 Secret Data

| Field            | Encoding / Type            | Required |
|------------------|----------------------------|----------|
| Secret Data      | Structure                  |          |
| Secret Data Type | Enumeration, see 9.1.3.2.9 | Yes      |
| Key Block        | Structure, see 2.1.3       | Yes      |

## 2.2.8 Opaque Object

| Field             | Encoding / Type             | Required |
|-------------------|-----------------------------|----------|
| Opaque Object     | Structure                   |          |
| Opaque Data Type  | Enumeration, see 9.1.3.2.10 | Yes      |
| Opaque Data Value | Byte String                 | Yes      |

## 2.2.9 PGP Key

| Field           | Encoding / Type      | Required |
|-----------------|----------------------|----------|
| PGP Key         | Structure            |          |
| PGP Key Version | Integer              | Yes      |
| Key Block       | Structure, see 2.1.3 | Yes      |

