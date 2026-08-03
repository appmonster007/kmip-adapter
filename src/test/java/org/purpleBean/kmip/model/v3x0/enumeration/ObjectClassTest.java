package org.purpleBean.kmip.model.v3x0.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("ObjectClass Domain Tests")
class ObjectClassTest extends AbstractKmipEnumerationTestSuite<ObjectClass> {

  @Override
  protected Class<ObjectClass> type() {
    return ObjectClass.class;
  }

  @Override
  protected ObjectClass createDefault() {
    return ObjectClass.Standard.USER.inst();
  }

  @Override
  protected ObjectClass createEqualToDefault() {
    return ObjectClass.Standard.USER.inst();
  }

  @Override
  protected ObjectClass createDifferentFromDefault() {
    return ObjectClass.Standard.SYSTEM.inst();
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
    withKmipSpec(
        KmipSpec.UnknownVersion,
        () -> {
          ObjectClass.Value byName = ObjectClass.fromName("X-Enum-Custom");
          ObjectClass.Value byVal = ObjectClass.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    // Lookup by name/value with unsupported version
    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> ObjectClass.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // Valid registration in ObjectClass requires 8XXXXXXX (hex) range per implementation
    ObjectClass.Value custom =
        ObjectClass.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
    assertThat(custom.isCustom()).isTrue();
    assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

    withKmipSpec(KmipSpec.UnknownVersion, () -> {
      assertThat(custom.isSupported()).isTrue();
    });
    withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
      assertThat(custom.isSupported()).isFalse();
    });

    // Negative cases: invalid range, empty description, empty versions
    assertThatThrownBy(
        () -> ObjectClass.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ObjectClass.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ObjectClass.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> ObjectClass.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}

