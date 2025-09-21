package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ObjectGroupMember Domain Tests")
class ObjectGroupMemberTest extends AbstractKmipEnumerationSuite<ObjectGroupMember> {

    @Override
    protected Class<ObjectGroupMember> type() {
        return ObjectGroupMember.class;
    }

    @Override
    protected ObjectGroupMember createDefault() {
        return new ObjectGroupMember(ObjectGroupMember.Standard.GROUP_MEMBER_FRESH);
    }

    @Override
    protected ObjectGroupMember createEqualToDefault() {
        return new ObjectGroupMember(ObjectGroupMember.Standard.GROUP_MEMBER_FRESH);
    }

    @Override
    protected ObjectGroupMember createDifferentFromDefault() {
        return new ObjectGroupMember(ObjectGroupMember.Standard.GROUP_MEMBER_DEFAULT);
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
        ObjectGroupMember.Value byName = ObjectGroupMember.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        ObjectGroupMember.Value byVal = ObjectGroupMember.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> ObjectGroupMember.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in ObjectGroupMember requires 8XXXXXXX (hex) range per implementation
        ObjectGroupMember.Value custom = ObjectGroupMember.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> ObjectGroupMember.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ObjectGroupMember.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ObjectGroupMember.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ObjectGroupMember.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

