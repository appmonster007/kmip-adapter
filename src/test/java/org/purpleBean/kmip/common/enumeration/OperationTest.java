package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Operation Domain Tests")
class OperationTest extends AbstractKmipEnumerationSuite<Operation> {

    @Override
    protected Class<Operation> type() {
        return Operation.class;
    }

    @Override
    protected Operation createDefault() {
        return new Operation(Operation.Standard.CREATE);
    }

    @Override
    protected Operation createEqualToDefault() {
        return new Operation(Operation.Standard.CREATE);
    }

    @Override
    protected Operation createDifferentFromDefault() {
        return new Operation(Operation.Standard.CREATE_KEY_PAIR);
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
    protected void assertLookupBehaviour() {
        // Lookup by name/value
        Operation.Value byName = Operation.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        Operation.Value byVal = Operation.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> Operation.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in Operation requires 8XXXXXXX (hex) range per implementation
        Operation.Value custom = Operation.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.V1_0)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> Operation.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Operation.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Operation.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Operation.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

