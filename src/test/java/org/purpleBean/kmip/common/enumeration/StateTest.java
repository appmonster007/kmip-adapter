package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

@DisplayName("State Domain Tests")
class StateTest extends AbstractKmipEnumerationSuite<State> {

    @Override
    protected Class<State> type() {
        return State.class;
    }

    @Override
    protected State createDefault() {
        return new State(State.Standard.ACTIVE);
    }

    @Override
    protected State createEqualToDefault() {
        return new State(State.Standard.ACTIVE);
    }

    @Override
    protected State createDifferentFromDefault() {
        return new State(State.Standard.DEACTIVATED);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.ENUMERATION;
    }

    @Override
    protected boolean supportsRegistryBehavior() {
        return true;
    }

    @Override
    protected void assertEnumerationRegistryBehaviorPositive() {
        // Valid registration in State requires 8XXXXXXX (hex) range per implementation
        State.Value custom = State.register(0x80000010, "X-Enum-Custom", java.util.Set.of(KmipSpec.V1_2));
        org.assertj.core.api.Assertions.assertThat(custom.isCustom()).isTrue();
        org.assertj.core.api.Assertions.assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        org.assertj.core.api.Assertions.assertThat(custom.isSupportedFor(KmipSpec.V1_2)).isTrue();

        // Lookup by name/value
        State.Value byName = State.fromName(KmipSpec.V1_2, "X-Enum-Custom");
        State.Value byVal = State.fromValue(KmipSpec.V1_2, 0x80000010);
        org.assertj.core.api.Assertions.assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        org.assertj.core.api.Assertions.assertThat(byVal.getValue()).isEqualTo(0x80000010);
    }

    @Override
    protected void assertEnumerationRegistryBehaviorNegative() {
        // Negative cases: invalid range, empty description, empty versions
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->
                        State.register(0x7FFFFFFF, "Bad-Range", java.util.Set.of(KmipSpec.V1_2)))
                .isInstanceOf(IllegalArgumentException.class);
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->
                        State.register(0x00000001, "Bad-Range", java.util.Set.of(KmipSpec.V1_2)))
                .isInstanceOf(IllegalArgumentException.class);
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->
                        State.register(0x80000011, "   ", java.util.Set.of(KmipSpec.V1_2)))
                .isInstanceOf(IllegalArgumentException.class);
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->
                        State.register(0x80000012, "X-Empty-Versions", java.util.Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
