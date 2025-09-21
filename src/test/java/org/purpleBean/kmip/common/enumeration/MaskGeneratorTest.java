package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("MaskGenerator Domain Tests")
class MaskGeneratorTest extends AbstractKmipEnumerationSuite<MaskGenerator> {

    @Override
    protected Class<MaskGenerator> type() {
        return MaskGenerator.class;
    }

    @Override
    protected MaskGenerator createDefault() {
        return new MaskGenerator(MaskGenerator.Standard.MFG1);
    }

    @Override
    protected MaskGenerator createEqualToDefault() {
        return new MaskGenerator(MaskGenerator.Standard.MFG1);
    }

    @Override
    protected MaskGenerator createDifferentFromDefault() {
        return new MaskGenerator(MaskGenerator.register(0x80000000, "MaskGenExtension", Set.of(KmipSpec.UnknownVersion)));
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
        MaskGenerator.Value byName = MaskGenerator.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        MaskGenerator.Value byVal = MaskGenerator.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> MaskGenerator.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in MaskGenerator requires 8XXXXXXX (hex) range per implementation
        MaskGenerator.Value custom = MaskGenerator.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> MaskGenerator.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> MaskGenerator.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> MaskGenerator.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> MaskGenerator.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

