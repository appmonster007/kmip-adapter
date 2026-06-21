# Wrapping Method

- **Spec versions**: `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`
- **Introduced**: v1.2

| Name | Value | Versions |
|---|---|---|
| Encrypt then MAC/sign | `` | `v2.0` `v2.1` `v3.0` |
| MAC/sign then encrypt. | `` | `v2.0` `v2.1` `v3.0` |
| TR-31 | `` | `v2.0` `v2.1` `v3.0` |
| Encrypt | `0x00000001` | `v1.2` `v1.3` `v1.4` |
| MAC/sign | `0x00000002` | `v1.2` `v1.3` `v1.4` |
| Encrypt then MAC/sign | `0x00000003` | `v1.2` `v1.3` `v1.4` |
| MAC/sign then encrypt | `0x00000004` | `v1.2` `v1.3` `v1.4` |
| TR-31 | `0x00000005` | `v1.2` `v1.3` `v1.4` |
| MAC/sign only | `either MACing the Key Value with a symmetric key, or signing the Key Value with a private key` | `v2.0` `v2.1` `v3.0` |
