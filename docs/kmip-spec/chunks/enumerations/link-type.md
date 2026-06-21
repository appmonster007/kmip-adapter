# Link Type

- **Spec versions**: `v1.2` `v1.3` `v1.4` `v2.0` `v2.1`
- **Introduced**: v1.2

| Name | Value | Versions |
|---|---|---|
| PKCS#12 Certificate Link | `` | `v2.0` `v2.1` |
| PKCS#12 Password Link | `` | `v2.0` `v2.1` |
| Certificate Link | `0x00000101` | `v1.2` `v1.3` `v1.4` |
| Public Key Link | `0x00000102` | `v1.2` `v1.3` `v1.4` |
| Private Key Link | `0x00000103` | `v1.2` `v1.3` `v1.4` |
| Derivation Base Object Link | `0x00000104` | `v1.2` `v1.3` `v1.4` |
| Derived Key Link | `0x00000105` | `v1.2` `v1.3` `v1.4` |
| Replacement Object Link | `0x00000106` | `v1.2` `v1.3` `v1.4` |
| Replaced Object Link | `0x00000107` | `v1.2` `v1.3` `v1.4` |
| Parent Link | `0x00000108` | `v1.2` `v1.3` `v1.4` |
| Child Link | `0x00000109` | `v1.2` `v1.3` `v1.4` |
| Previous Link | `0x0000010A` | `v1.2` `v1.3` `v1.4` |
| Next Link | `0x0000010B` | `v1.2` `v1.3` `v1.4` |
| PKCS#12 Certificate Link | `0x0000010C` | `v1.4` |
| PKCS#12 Password Link | `0x0000010D` | `v1.4` |
| Certificate Link | `For Certificate objects: the parent certificate for a certificate in a certificate chain. For Public Key objects: the corresponding certificate(s), containing the same public key.` | `v2.0` `v2.1` |
| Public Key Link | `For a Private Key object: the public key corresponding to the private key. For a Certificate object: the public key contained in the certificate.` | `v2.0` `v2.1` |
| Replacement Object Link | `For a Symmetric Key, an Asymmetric Private Key, or an Asymmetric Public Key object: the key that resulted from the re-key of the current key. For a Certificate object: the certificate that resulted from the re-certify. Note that there SHALL be only one such replacement object per Managed Object.` | `v2.0` `v2.1` |
| Replaced Object Link | `For a Symmetric Key, an Asymmetric Private Key, or an Asymmetric Public Key object: the key that was re-keyed to obtain the current key. For a Certificate object: the certificate that was re-certified to obtain the current certificate.` | `v2.0` `v2.1` |
| Derivation Base Object Link | `For a derived Symmetric Key or Secret Data object: the object(s) from which the current symmetric key was derived.` | `v2.0` `v2.1` |
| Parent Link | `For all object types: the container or other parent object corresponding to the object.` | `v2.0` `v2.1` |
| Next Link | `For all object types: the next object to this object.` | `v2.0` `v2.1` |
| Previous Link | `For all object types: the previous object to this object.` | `v2.0` `v2.1` |
| Child Link | `For all object types: the subordinate, derived or other child object corresponding to the object.` | `v2.0` `v2.1` |
| Wrapping Key Link | `For wrapped objects: the object that was used to wrap this object.` | `v2.0` `v2.1` |
| Derived Key Link | `The symmetric key(s) or Secret Data object(s) that were derived from the current object.` | `v2.0` `v2.1` |
