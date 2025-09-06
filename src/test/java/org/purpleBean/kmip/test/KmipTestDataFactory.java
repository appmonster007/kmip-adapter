package org.purpleBean.kmip.test;

import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Factory class for creating test data objects with consistent, reusable patterns.
 * Provides both deterministic and randomized test data generation.
 */
public final class KmipTestDataFactory {

    private static final Random RANDOM = new Random(42); // Fixed seed for reproducible tests
    private static final OffsetDateTime BASE_DATE = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

    private KmipTestDataFactory() {
        // Utility class - prevent instantiation
    }

    /**
     * Creates a standard ProtocolVersion for testing.
     */
    public static ProtocolVersion createProtocolVersion() {
        return ProtocolVersion.of(1, 2);
    }

    /**
     * Creates a ProtocolVersion with specified major and minor versions.
     */
    public static ProtocolVersion createProtocolVersion(int major, int minor) {
        return ProtocolVersion.of(major, minor);
    }

    /**
     * Creates a list of ProtocolVersions for testing multiple versions.
     */
    public static List<ProtocolVersion> createProtocolVersions() {
        return List.of(
            ProtocolVersion.of(1, 0),
            ProtocolVersion.of(1, 1),
            ProtocolVersion.of(1, 2),
            ProtocolVersion.of(2, 0)
        );
    }

    /**
     * Creates a standard State for testing.
     */
    public static State createState() {
        return new State(State.Standard.ACTIVE);
    }

    /**
     * Creates a State with a specific standard value.
     */
    public static State createState(State.Standard standard) {
        return new State(standard);
    }

    /**
     * Creates a custom State for testing extensibility.
     */
    public static State createCustomState() {
        State.Value customValue = State.register(-1000050, "TestCustom", Set.of(KmipSpec.V1_2));
        return new State(customValue);
    }

    /**
     * Creates multiple States for comprehensive testing.
     */
    public static List<State> createStates() {
        return List.of(
            new State(State.Standard.PRE_ACTIVE),
            new State(State.Standard.ACTIVE),
            new State(State.Standard.DEACTIVATED),
            new State(State.Standard.COMPROMISED),
            new State(State.Standard.DESTROYED)
        );
    }

    /**
     * Creates a standard ActivationDateAttribute for testing.
     */
    public static ActivationDateAttribute createActivationDateAttribute() {
        return ActivationDateAttribute.builder()
            .dateTime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
            .build();
    }
    
    public static ActivationDateAttribute createNullActivationDateAttribute() {
        // Since @NonNull is enforced, we cannot create with null dateTime
        // Return a valid attribute with a default date instead
        return ActivationDateAttribute.builder()
            .dateTime(OffsetDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
            .build();
    }

    /**
     * Creates an ActivationDateAttribute with a specific date.
     */
    public static ActivationDateAttribute createActivationDateAttribute(OffsetDateTime dateTime) {
        return ActivationDateAttribute.builder()
                .dateTime(dateTime)
                .build();
    }

    /**
     * Creates a random ActivationDateAttribute for testing edge cases.
     */
    public static ActivationDateAttribute createRandomActivationDateAttribute() {
        OffsetDateTime randomDate = BASE_DATE.plusDays(RANDOM.nextInt(365))
                .plusHours(RANDOM.nextInt(24))
                .plusMinutes(RANDOM.nextInt(60));
        return ActivationDateAttribute.builder()
                .dateTime(randomDate)
                .build();
    }

    /**
     * Creates a standard SampleStructure for testing.
     */
    public static SampleStructure createSampleStructure() {
        return SampleStructure.builder()
                .activationDate(createActivationDateAttribute())
                .state(createState())
                .build();
    }

    /**
     * Creates a SampleStructure with specific components.
     */
    public static SampleStructure createSampleStructure(ActivationDateAttribute activationDate, State state) {
        return SampleStructure.builder()
                .activationDate(activationDate)
                .state(state)
                .build();
    }

    /**
     * Creates multiple SampleStructures for batch testing.
     */
    public static List<SampleStructure> createSampleStructures(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> SampleStructure.builder()
                        .activationDate(createRandomActivationDateAttribute())
                        .state(createState(State.Standard.values()[i % State.Standard.values().length]))
                        .build())
                .toList();
    }

    /**
     * Creates a custom KmipTag for testing extensibility.
     */
    public static KmipTag.Value createCustomKmipTag() {
        return KmipTag.register(0x540001, "TestCustomTag", Set.of(KmipSpec.V1_2));
    }

    /**
     * Creates multiple custom KmipTags for testing.
     */
    public static List<KmipTag.Value> createCustomKmipTags(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> KmipTag.register(0x540001 + i, "TestCustomTag" + i, Set.of(KmipSpec.V1_2)))
                .toList();
    }

    /**
     * Creates test data for boundary conditions.
     */
    public static class BoundaryData {
        public static ProtocolVersion minProtocolVersion() {
            return ProtocolVersion.of(0, 0);
        }

        public static ProtocolVersion maxProtocolVersion() {
            return ProtocolVersion.of(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        public static OffsetDateTime minDateTime() {
            return OffsetDateTime.MIN;
        }

        public static OffsetDateTime maxDateTime() {
            return OffsetDateTime.MAX;
        }

        public static OffsetDateTime epochDateTime() {
            return OffsetDateTime.ofInstant(java.time.Instant.EPOCH, ZoneOffset.UTC);
        }
    }

    /**
     * Creates test data for error conditions.
     */
    public static class ErrorData {
        public static ProtocolVersion negativeProtocolVersion() {
            return ProtocolVersion.of(-1, -1);
        }

        public static byte[] invalidTagBytes() {
            return new byte[]{(byte) 0xFF, (byte) 0xFF}; // Invalid length
        }

        public static byte[] validTagBytes() {
            return new byte[]{0x42, 0x00, 0x01}; // Valid 3-byte tag
        }
    }

    /**
     * Creates performance test data with large datasets.
     */
    public static class PerformanceData {
        public static List<SampleStructure> largeSampleStructureList() {
            return createSampleStructures(10000);
        }

        public static List<ProtocolVersion> largeProtocolVersionList() {
            return java.util.stream.IntStream.range(0, 1000)
                    .mapToObj(i -> ProtocolVersion.of(i / 100, i % 100))
                    .toList();
        }
    }
}
