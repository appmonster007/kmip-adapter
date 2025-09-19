package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("CancellationResult Domain Tests")
class CancellationResultTest extends AbstractKmipEnumerationSuite<CancellationResult> {

    @Override
    protected Class<CancellationResult> type() {
        return CancellationResult.class;
    }

    @Override
    protected CancellationResult createDefault() {
        return new CancellationResult(CancellationResult.Standard.CANCELED);
    }

    @Override
    protected CancellationResult createEqualToDefault() {
        return new CancellationResult(CancellationResult.Standard.CANCELED);
    }

    @Override
    protected CancellationResult createDifferentFromDefault() {
        return new CancellationResult(CancellationResult.Standard.UNABLE_TO_CANCEL);
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
        CancellationResult.Value byName = CancellationResult.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        CancellationResult.Value byVal = CancellationResult.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> CancellationResult.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in CancellationResult requires 8XXXXXXX (hex) range per implementation
        CancellationResult.Value custom = CancellationResult.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.V1_0)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> CancellationResult.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CancellationResult.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CancellationResult.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CancellationResult.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

