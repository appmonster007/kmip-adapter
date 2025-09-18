package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.test.BaseKmipTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KmipContext basic lifecycle")
class KmipContextTest extends BaseKmipTest {

    @Test
    @DisplayName("Default spec is UnknownVersion; set and clear work")
    void lifecycle() {
        // default from BaseKmipTest
        assertThat(KmipContext.getSpec()).isEqualTo(KmipSpec.UnknownVersion);

        KmipContext.setSpec(KmipSpec.V1_2);
        assertThat(KmipContext.getSpec()).isEqualTo(KmipSpec.V1_2);

        KmipContext.clear();
        assertThat(KmipContext.getSpec()).isEqualTo(KmipSpec.UnknownVersion);
    }
}
