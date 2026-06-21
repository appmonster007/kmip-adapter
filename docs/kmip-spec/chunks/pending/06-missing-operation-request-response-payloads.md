## 6. Missing Operation Request/Response Payloads

### 6.2 Fully Missing (no model classes yet)

| Operation | Hex | Introduced | Request | Response |
|---|---|---|---|---|

### 6.3 Stub Payloads (registered, field-level design pending)

Request and response payload classes exist and register against the operation in
`RequestPayloadStructure.PAYLOAD_REGISTRY` / `ResponsePayloadStructure.PAYLOAD_REGISTRY`,
but `getValue()` returns empty — actual field layout from OASIS spec still to be wired in.

| Operation | Hex | Introduced |
|---|---|---|
| Adjust Attribute | `0x00000030` | v2.0 |
| Create Credential | `0x0000003F` | v3.0 |
| Create Group | `0x0000003C` | v3.0 |
| Create User | `0x0000003E` | v3.0 |
| Deactivate | `0x00000040` | v3.0 |
| Delegated Login | `0x0000002F` | v2.0 |
| Export | `0x0000002B` | v1.4 |
| Get Constraints | `0x00000038` | v2.1 |
| Import | `0x0000002A` | v1.4 |
| Interop | `0x00000034` | v2.0 |
| Log | `0x0000002C` | v2.0 |
| Login | `0x0000002D` | v2.0 |
| Logout | `0x0000002E` | v2.0 |
| Obliterate | `0x0000003D` | v3.0 |
| PKCS#11 | `0x00000033` | v2.0 |
| Ping | `0x0000003B` | v2.1 |
| Poll | `0x0000001A` | v1.2 |
| Process | `0x0000003A` | v2.1 |
| Query Asynchronous Requests | `0x00000039` | v2.1 |
| Re-Provision | `0x00000035` | v2.0 |
| Set Attribute | `0x00000031` | v2.0 |
| Set Constraints | `0x00000037` | v2.1 |
| Set Defaults | `0x00000036` | v2.1 |
| Set Endpoint Role | `0x00000032` | v2.0 |
