# Client Registration Method

- **Spec versions**: `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`
- **Introduced**: v1.3

| Name | Value | Versions |
|---|---|---|
| Unspecified | `0x00000001` | `v1.3` `v1.4` |
| Server Pre-Generated | `0x00000002` | `v1.3` `v1.4` |
| Server On-Demand | `0x00000003` | `v1.3` `v1.4` |
| Client Generated | `0x00000004` | `v1.3` `v1.4` |
| Client Registered | `0x00000005` | `v1.3` `v1.4` |
| Client Generated | `The client generates the private key and sends a Certificate Signing Request to the server to generate the certificate. The returned PKCS#12 is protected with HEX(SHA256(Username || Password)).` | `v2.0` `v2.1` `v3.0` |
| Client Registered | `The client generates the private key and the certificates and registers the certificate with the server.` | `v2.0` `v2.1` `v3.0` |
| Server On-Demand | `The server generates the client’s private key on demand. The returned PKCS#12 is protected with HEX(SHA256(Username || Password)).` | `v2.0` `v2.1` `v3.0` |
