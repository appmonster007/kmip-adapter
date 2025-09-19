package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("BatchErrorContinuationOption Domain Tests")
class BatchErrorContinuationOptionTest extends AbstractKmipEnumerationSuite<BatchErrorContinuationOption> {

    @Override
    protected Class<BatchErrorContinuationOption> type() {
        return BatchErrorContinuationOption.class;
    }

    @Override
    protected BatchErrorContinuationOption createDefault() {
        return new BatchErrorContinuationOption(BatchErrorContinuationOption.Standard.CONTINUE);
    }

    @Override
    protected BatchErrorContinuationOption createEqualToDefault() {
        return new BatchErrorContinuationOption(BatchErrorContinuationOption.Standard.CONTINUE);
    }

    @Override
    protected BatchErrorContinuationOption createDifferentFromDefault() {
        return new BatchErrorContinuationOption(BatchErrorContinuationOption.Standard.STOP);
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
        BatchErrorContinuationOption.Value byName = BatchErrorContinuationOption.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        BatchErrorContinuationOption.Value byVal = BatchErrorContinuationOption.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> BatchErrorContinuationOption.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in BatchErrorContinuationOption requires 8XXXXXXX (hex) range per implementation
        BatchErrorContinuationOption.Value custom = BatchErrorContinuationOption.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.V1_0)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> BatchErrorContinuationOption.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> BatchErrorContinuationOption.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> BatchErrorContinuationOption.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> BatchErrorContinuationOption.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

