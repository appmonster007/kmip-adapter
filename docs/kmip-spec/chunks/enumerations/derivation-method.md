# Derivation Method

- **Spec versions**: `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`
- **Introduced**: v1.2

| Name | Value | Versions |
|---|---|---|
| PBKDF2 | `0x00000001` | `v1.2` `v1.3` `v1.4` |
| HASH | `0x00000002` | `v1.2` `v1.3` `v1.4` |
| HMAC | `0x00000003` | `v1.2` `v1.3` `v1.4` |
| ENCRYPT | `0x00000004` | `v1.2` `v1.3` `v1.4` |
| NIST800-108-C | `0x00000005` | `v1.2` `v1.3` `v1.4` |
| NIST800-108-F | `0x00000006` | `v1.2` `v1.3` `v1.4` |
| NIST800-108-DPI | `0x00000007` | `v1.2` `v1.3` `v1.4` |
| Asymmetric Key | `0x00000008` | `v1.4` |
| AWS Signature Version 4 | `As defined in Amazon Web Services Signature Version 4.` | `v2.0` `v2.1` `v3.0` |
| HKDF | `HMAC-based Extract-and-Expand Key Derivation Function` | `v2.0` `v2.1` `v3.0` |
| HASH | `This method derives a key by computing a hash over the derivation key or the derivation data.` | `v2.0` `v2.1` `v3.0` |
| HMAC | `This method derives a key by computing an HMAC over the derivation data.` | `v2.0` `v2.1` `v3.0` |
| NIST800-108-C | `This method derives a key by computing the KDF in Counter Mode` | `v2.0` `v2.1` `v3.0` |
| NIST800-108-DPI | `This method derives a key by computing the KDF in Double-Pipeline Iteration Mode` | `v2.0` `v2.1` `v3.0` |
| NIST800-108-F | `This method derives a key by computing the KDF in Feedback Mode` | `v2.0` `v2.1` `v3.0` |
| ENCRYPT | `This method derives a key by encrypting the derivation data.` | `v2.0` `v2.1` `v3.0` |
| Asymmetric Key | `This method derives a key using asymmetric key agreement between a private and public key.` | `v2.0` `v2.1` `v3.0` |
