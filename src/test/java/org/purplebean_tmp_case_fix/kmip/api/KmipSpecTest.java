package org.purplebean.kmip.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;

/**
 * Unit tests for {@link KmipSpec}.
 * <p>
 * These tests verify the invariants of the KmipSpec enum, including its string representation
 * and conversion from ProtocolVersion.
 */
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
