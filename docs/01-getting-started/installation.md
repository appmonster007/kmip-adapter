# Installation Guide

## Prerequisites

- Java 21 or higher
- Build tool:
  - Maven 3.6+
  - Gradle 7.0+ (or Android Gradle Plugin 7.0+ for Android)
- Network access to Maven Central

## Maven

Add the following dependency to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.purpleBean</groupId>
        <artifactId>kmip-adapter</artifactId>
        <version>1.0.0</version>
    </dependency>
    
    <!-- Optional: Add these if you need JSON or XML support -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.0</version>
    </dependency>
    
    <dependency>
        <groupId>com.fasterxml.jackson.dataformat</groupId>
        <artifactId>jackson-dataformat-xml</artifactId>
        <version>2.15.0</version>
    </dependency>
</dependencies>
```

## Gradle (Kotlin DSL)

Add to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("org.purpleBean:kmip-adapter:1.0.0")
    
    // Optional: Add these if you need JSON or XML support
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.0")
}
```

## Gradle (Groovy DSL)

Add to your `build.gradle`:

```groovy
dependencies {
    implementation 'org.purpleBean:kmip-adapter:1.0.0'
    
    // Optional: Add these if you need JSON or XML support
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.0'
    implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.0'
}
```

## Building from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/your-org/kmip-adapter.git
   cd kmip-adapter
   ```

2. Build the project:
   ```bash
   # Using Maven
   mvn clean install
   
   # Or using Gradle
   ./gradlew build
   ```

## Verifying Installation

Create a simple test class to verify the installation:

```java
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;

public class KmipTest {
    public static void main(String[] args) {
        // Set the KMIP specification version
        KmipContext.setSpec(KmipSpec.V1_2);
        
        // Verify the context is set correctly
        System.out.println("KMIP Spec: " + KmipContext.getSpec());
        System.out.println("KMIP Adapter is working!");
    }
}
```

## Common Issues

1. **Unsupported Class File Version**: Ensure you're using Java 21 or higher.
   ```bash
   java -version
   ```

2. **Dependency Conflicts**: If you encounter dependency conflicts, use Maven's dependency tree to identify issues:
   ```bash
   mvn dependency:tree -Dverbose -Dincludes=com.fasterxml.jackson
   ```

3. **Missing Dependencies**: Make sure all required dependencies are included in your build file.

## Next Steps

- [Configuration Guide](./configuration.md)
- [Quick Start Guide](./quickstart.md)
- [API Documentation](../04-api/)

## Support

For issues and feature requests, please use the [GitHub Issue Tracker](https://github.com/your-org/kmip-adapter/issues).
