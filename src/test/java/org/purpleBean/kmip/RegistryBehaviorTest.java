package org.purpleBean.kmip;

import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.Set;

/**
 * Test to understand the exact registry behavior for duplicate registrations
 */
public class RegistryBehaviorTest {

    @Test
    void stateRegistry_overwritesLastRegistration() {
        // Given
        int customValue = -1000080;
        String description1 = "First";
        String description2 = "Second";

        // When
        State.Value first =
                State.register(customValue, description1, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
        State.Value second =
                State.register(customValue, description2, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

        // Look up from registry
        State.Value fromRegistry = State.fromValue(KmipSpec.V1_2, customValue);

        // Then
        org.assertj.core.api.Assertions.assertThat(first).isNotSameAs(second);
        org.assertj.core.api.Assertions.assertThat(first).isNotEqualTo(second);
        org.assertj.core.api.Assertions.assertThat(fromRegistry.getDescription()).isEqualTo(description2);
    }

    @Test
    void kmipTagRegistry_overwritesLastRegistration() {
        // Given
        int customValue = 0x540030;
        String description1 = "First";
        String description2 = "Second";

        // When
        KmipTag.Value first =
                KmipTag.register(customValue, description1, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
        KmipTag.Value second =
                KmipTag.register(customValue, description2, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

        // Look up from registry
        KmipTag.Value fromRegistry = KmipTag.fromValue(KmipSpec.V1_2, customValue);

        // Then
        org.assertj.core.api.Assertions.assertThat(first).isNotSameAs(second);
        org.assertj.core.api.Assertions.assertThat(first).isNotEqualTo(second);
        org.assertj.core.api.Assertions.assertThat(fromRegistry.getDescription()).isEqualTo(description2);
    }
}
