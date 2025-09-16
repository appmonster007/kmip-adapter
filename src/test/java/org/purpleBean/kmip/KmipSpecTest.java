package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KmipSpec invariants")
class KmipSpecTest {

    @Test
    @DisplayName("toString format and fromValue mapping")
    void toString_and_fromValue() {
        ProtocolVersion v12 = ProtocolVersion.of(1, 2);
        assertThat(KmipSpec.fromValue(v12)).isEqualTo(KmipSpec.V1_2);
        assertThat(KmipSpec.V1_2.toString()).isEqualTo("V1.2");
        assertThat(KmipSpec.UnknownVersion.toString()).isEqualTo("V-1.-1");
        assertThat(KmipSpec.UnsupportedVersion.toString()).isEqualTo("V-9.-9");
    }
}
