# Cancellation Result

- **Spec versions**: `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`
- **Introduced**: v1.2

| Name | Value | Versions |
|---|---|---|
| Canceled | `0x00000001` | `v1.2` `v1.3` `v1.4` |
| Unable to Cancel | `0x00000002` | `v1.2` `v1.3` `v1.4` |
| Completed | `0x00000003` | `v1.2` `v1.3` `v1.4` |
| Failed | `0x00000004` | `v1.2` `v1.3` `v1.4` |
| Unavailable | `0x00000005` | `v1.2` `v1.3` `v1.4` |
| Unable to Cancel | `The cancel operation is unable to cancel the pending operation.` | `v2.0` `v2.1` `v3.0` |
| Completed | `The pending operation completed successfully before the cancellation operation was able to cancel it.` | `v2.0` `v2.1` `v3.0` |
| Failed | `The pending operation completed with a failure before the cancellation operation was able to cancel it.` | `v2.0` `v2.1` `v3.0` |
| Unavailable | `Unavailable – The specified correlation value did not match any recently pending or completed asynchronous operations.` | `v2.0` `v2.1` `v3.0` |
