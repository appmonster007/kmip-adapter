# Batch Error Continuation Option

- **Spec versions**: `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`
- **Introduced**: v1.2

| Name | Value | Versions |
|---|---|---|
| Continue | `0x00000001` | `v1.2` `v1.3` `v1.4` |
| Stop | `0x00000002` | `v1.2` `v1.3` `v1.4` |
| Undo | `0x00000003` | `v1.2` `v1.3` `v1.4` |
| Stop | `If an operation fails, then the server SHALL NOT continue processing subsequent operations in the request. Completed operations SHALL NOT be undone. Batch item fails and Result Status is set to Operation Failed. Responses to other batch items are returned normally.` | `v2.0` `v2.1` `v3.0` |
| Continue | `Return an error for the failed operation, and continue processing subsequent operations in the request. Batch item fails and Result Status is set to Operation Failed. Batch items that had been processed have been undone and their responses are returned with Undone result status.` | `v2.0` `v2.1` `v3.0` |
