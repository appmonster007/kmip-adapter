package org.purpleBean.kmip;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.Set;

/**
 * Test to understand the exact registry behavior for duplicate registrations
 */
@Slf4j
public class RegistryBehaviorTest {

    @Test
    void testStateRegistryBehavior() {
        // Given
        int customValue = -1000080;
        String description1 = "First";
        String description2 = "Second";

        // When
        State.Value first =
                State.register(customValue, description1, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
        State.Value second =
                State.register(customValue, description2, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

        // Debug output
        System.out.println("First: " + first);
        System.out.println("Second: " + second);
        System.out.println("Same instance: " + (first == second));
        System.out.println("Equal: " + first.equals(second));
        System.out.println("First description: " + first.getDescription());
        System.out.println("Second description: " + second.getDescription());

        // Look up from registry
        State.Value fromRegistry = State.fromValue(KmipSpec.V1_2, customValue);
        System.out.println("From registry: " + fromRegistry);
        System.out.println("Registry description: " + fromRegistry.getDescription());
    }

    @Test
    void testKmipTagRegistryBehavior() {
        // Given
        int customValue = 0x540030;
        String description1 = "First";
        String description2 = "Second";

        // When
        KmipTag.Value first =
                KmipTag.register(customValue, description1, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
        KmipTag.Value second =
                KmipTag.register(customValue, description2, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

        // Debug output
        System.out.println("First: " + first);
        System.out.println("Second: " + second);
        System.out.println("Same instance: " + (first == second));
        System.out.println("Equal: " + first.equals(second));
        System.out.println("First description: " + first.getDescription());
        System.out.println("Second description: " + second.getDescription());

        // Look up from registry
        KmipTag.Value fromRegistry = KmipTag.fromValue(KmipSpec.V1_2, customValue);
        System.out.println("From registry: " + fromRegistry);
        System.out.println("Registry description: " + fromRegistry.getDescription());
    }
}
