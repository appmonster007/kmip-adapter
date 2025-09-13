# KMIP Implementation Reference

This directory contains detailed implementation references for the KMIP Adapter components. For quick-start guides and code templates, see the [Development Guide](/docs/03-guides/development/boilerplate.md).

## Reference Documentation

### Core Components
- [Core Concepts](./core/README.md) - Base interfaces and design patterns
- [Enumerations](./enumerations/README.md) - Type-safe enumeration implementation
- [Attributes](./attributes/README.md) - Attribute patterns and validation
- [Structures](./structures/README.md) - Complex data structure composition

### Advanced Topics
- [Serialization](./serialization/README.md) - JSON, XML, and TTLV serialization patterns
- [Testing](./testing/README.md) - Unit and integration testing strategies
- [Validation](./validation/README.md) - Input validation patterns and utilities

## How to Use This Reference

### For New Development
1. Start with the [Development Guide](/docs/03-guides/development/boilerplate.md)
2. Follow the patterns in the relevant implementation guide
3. Refer to the [API Reference](/docs/04-api/) for detailed specifications

### For Maintenance
1. Check the [Version Compatibility](#version-compatibility) section below
2. Review the relevant implementation guide
3. Update tests to maintain coverage

## Version Compatibility

### Current KMIP Version Support
- KMIP 1.2 (Work in Progress)
- KMIP 1.3 (Planned, not yet started)
- KMIP 1.4 (Planned, not yet started)

### Version-Specific Status
- **KMIP 1.2**: Core implementation in progress. Basic functionality available, some features may be missing or incomplete.
- **KMIP 1.3**: Development has not started. Planned for future implementation.
- **KMIP 1.4**: Development has not started. Planned for future implementation.

## Contributing

When contributing to the implementation:
1. Follow the established patterns in the relevant guide
2. Ensure backward compatibility or provide migration paths
3. Update relevant documentation when making changes
4. Add or update tests for new or modified functionality

## Related Resources
- [API Reference](/docs/04-api/)
- [Development Guide](/docs/03-guides/development/boilerplate.md)
- [Performance Testing Guide](/docs/04-performance/performance-testing-guide.md)

If you have questions or need clarification, please refer to the relevant guide or open an issue in the project's issue tracker.
