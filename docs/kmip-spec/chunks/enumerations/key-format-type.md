# Key Format Type

- **Spec versions**: `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`
- **Introduced**: v1.2

| Name | Value | Versions |
|---|---|---|
| Transparent ECDSA Private Key | `0000000E (deprecated)` | `v1.3` `v1.4` |
| Transparent ECDSA Public Key | `0000000F (deprecated)` | `v1.3` `v1.4` |
| Transparent ECDH Private Key | `00000010 (deprecated)` | `v1.3` `v1.4` |
| Transparent ECDH Public Key | `00000011 (deprecated)` | `v1.3` `v1.4` |
| Transparent ECMQV Private Key | `00000012 (deprecated)` | `v1.3` `v1.4` |
| Transparent ECMQV Public Key | `00000013 (deprecated)` | `v1.3` `v1.4` |
| Raw | `0x00000001` | `v1.2` `v1.3` `v1.4` |
| Opaque | `0x00000002` | `v1.2` `v1.3` `v1.4` |
| PKCS#1 | `0x00000003` | `v1.2` `v1.3` `v1.4` |
| PKCS#8 | `0x00000004` | `v1.2` `v1.3` `v1.4` |
| X.509 | `0x00000005` | `v1.2` `v1.3` `v1.4` |
| ECPrivateKey | `0x00000006` | `v1.2` `v1.3` `v1.4` |
| Transparent Symmetric Key | `0x00000007` | `v1.2` `v1.3` `v1.4` |
| Transparent DSA Private Key | `0x00000008` | `v1.2` `v1.3` `v1.4` |
| Transparent DSA Public Key | `0x00000009` | `v1.2` `v1.3` `v1.4` |
| Transparent RSA Private Key | `0x0000000A` | `v1.2` `v1.3` `v1.4` |
| Transparent RSA Public Key | `0x0000000B` | `v1.2` `v1.3` `v1.4` |
| Transparent DH Private Key | `0x0000000C` | `v1.2` `v1.3` `v1.4` |
| Transparent DH Public Key | `0x0000000D` | `v1.2` `v1.3` `v1.4` |
| Transparent ECDSA Private Key | `0x0000000E` | `v1.2` |
| Transparent ECDSA Public Key | `0x0000000F` | `v1.2` |
| Transparent ECDH Private Key | `0x00000010` | `v1.2` |
| Transparent ECDH Public Key | `0x00000011` | `v1.2` |
| Transparent ECMQV Private Key | `0x00000012` | `v1.2` |
| Transparent ECMQV Public Key | `0x00000013` | `v1.2` |
| Transparent EC Private Key | `0x00000014` | `v1.3` `v1.4` |
| Transparent EC Public Key | `0x00000015` | `v1.3` `v1.4` |
| PKCS#12 | `0x00000016` | `v1.4` |
| ECPrivateKey | `An ASN.1 encoded elliptic curve private key.` | `v2.0` `v2.1` `v3.0` |
| X.509 | `An encoded object, expressed as a DER-encoded ASN.1 X.509 object.` | `v2.0` `v2.1` `v3.0` |
| PKCS8 | `An encoded private key, expressed as a DER-encoded ASN.1 PKCS#8 object, supporting both the RSAPrivateKey syntax and EncryptedPrivateKey` | `v2.0` `v2.1` `v3.0` |
| Several Transparent Key types | `algorithm-specific structures containing defined values for the various key types.` | `v2.0` `v2.1` `v3.0` |
| Opaque | `an encoded key for which the encoding is unknown to the key management system. It is encoded as a string of bytes.` | `v2.0` `v2.1` `v3.0` |
| PKCS1 | `an encoded private key, expressed as a DER-encoded ASN.1 PKCS#1 object.` | `v2.0` `v2.1` `v3.0` |
