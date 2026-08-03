package org.purpleBean.kmip.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.test.BaseKmipTest;

/**
 * Unit tests for {@link KmipContext}.
 * <p>
 * These tests verify the basic lifecycle of the KmipContext, including setting, getting,
 * and clearing the current KMIP specification.
 */
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
