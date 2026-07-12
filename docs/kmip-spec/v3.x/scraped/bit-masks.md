# KMIP 3.0 — Bit Masks

Total: **4** bit mask types

## Cryptographic Usage Mask

| Value            | Description                                                                                                              |
|------------------|--------------------------------------------------------------------------------------------------------------------------|
| Sign             | Allow for signing. Applies to Sign operation. Valid for PGP Key, Private Key                                             |
| Verify           | Allow for signature verification. Applies to Signature Verify and Validate operations. Valid for PGP Key, Certificate an |
| Encrypt          | Allow for encryption. Applies to Encrypt operation. Valid for PGP Key, Private Key, Public Key and Symmetric Key. Encryp |
| Decrypt          | Allow for decryption. Applies to Decrypt operation. Valid for PGP Key, Private Key, Public Key and Symmetric Key. Decryp |
| Wrap Key         | Allow for key wrapping. Applies to Get operation when wrapping is required by Wrapping Specification is provided on the  |
| Unwrap Key       | Allow for key unwrapping. Applies to Get operation when unwrapping is required on the object used to Unwrap. Valid for P |
| (Reserved)       |                                                                                                                          |
| MAC Generate     | Allow for MAC generation. Applies to MAC operation. Valid for Symmetric Keys                                             |
| MAC Verify       | Allow for MAC verification. Applies to MAC Verify operation. Valid for Symmetric Keys                                    |
| Derive Key       | Allow for key derivation. Applied to Derive Key operation. Valid for PGP Keys, Private Keys, Public Keys, Secret Data an |
| Key Agreement    | Allow for Key Agreement. Valid for PGP Keys, Private Keys, Public Keys, Secret Data and Symmetric Keys                   |
| Certificate Sign | Allow for Certificate Signing. Applies to Certify operation on a private key. Valid for Private Keys.                    |
| CRL Sign         | Allow for CRL Sign. Valid for Private Keys                                                                               |
| Authenticate     | Allow for Authentication. Valid for Secret Data.                                                                         |
| Unrestricted     | Cryptographic Usage Mask contains no Usage Restrictions.                                                                 |
| FPE Encrypt      | Allow for Format Preserving Encrypt. Valid for Symmetric Keys, Public Keys and Private Keys                              |
| FPE Decrypt      | Allow for Format Preserving Decrypt. Valid for Symmetric Keys, Public Keys and Private Keys                              |
| Extensions       | Extensions                                                                                                               |

## Protection Storage Mask

| Value             | Description |
|-------------------|-------------|
| Software          | 00000001    |
| Hardware          | 00000002    |
| On Processor      | 00000004    |
| On System         | 00000008    |
| Off System        | 00000010    |
| Hypervisor        | 00000020    |
| Operating System  | 00000040    |
| Container         | 00000080    |
| On Premises       | 00000100    |
| Off Premises      | 00000200    |
| Self Managed      | 00000400    |
| Outsourced        | 00000800    |
| Validated         | 00001000    |
| Same Jurisdiction | 00002000    |
| Extensions        | XXXX0000    |

## Storage Status Mask

| Value             | Description |
|-------------------|-------------|
| On-line storage   | 00000001    |
| Archival storage  | 00000002    |
| Destroyed storage | 00000004    |
| Extensions        | XXXX0000    |

## Object Class Mask

| Value      | Description |
|------------|-------------|
| User       | 00000001    |
| System     | 00000002    |
| Extensions | XXXX0000    |

