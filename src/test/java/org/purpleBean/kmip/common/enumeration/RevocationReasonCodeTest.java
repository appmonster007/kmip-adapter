package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("RevocationReasonCode Domain Tests")
class RevocationReasonCodeTest extends AbstractKmipEnumerationSuite<RevocationReasonCode> {

    @Override
    protected Class<RevocationReasonCode> type() {
        return RevocationReasonCode.class;
    }

    @Override
    protected RevocationReasonCode createDefault() {
        return new RevocationReasonCode(RevocationReasonCode.Standard.CERTIFICATE_SUPERSEDED);
    }

    @Override
    protected RevocationReasonCode createEqualToDefault() {
        return new RevocationReasonCode(RevocationReasonCode.Standard.CERTIFICATE_SUPERSEDED);
    }

    @Override
    protected RevocationReasonCode createDifferentFromDefault() {
        return new RevocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE);
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
        RevocationReasonCode.Value byName = RevocationReasonCode.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        RevocationReasonCode.Value byVal = RevocationReasonCode.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> RevocationReasonCode.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in RevocationReasonCode requires 8XXXXXXX (hex) range per implementation
        RevocationReasonCode.Value custom = RevocationReasonCode.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.V1_0)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> RevocationReasonCode.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RevocationReasonCode.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RevocationReasonCode.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RevocationReasonCode.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

