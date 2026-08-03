package org.purpleBean.kmip.model.v2_1.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("ItemType Domain Tests")
class ItemTypeTest extends AbstractKmipEnumerationTestSuite<ItemType> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<ItemType> type() {
    return ItemType.class;
  }

  @Override
  public ItemType createDefault() {
    return ItemType.Standard.STRUCTURE.inst();
  }

  @Override
  public ItemType createEqualToDefault() {
    return ItemType.Standard.STRUCTURE.inst();
  }

  @Override
  public ItemType createDifferentFromDefault() {
    return ItemType.Standard.INTEGER.inst();
  }

  @Override
  public EncodingType expectedEncodingType() {
    return EncodingType.ENUMERATION;
  }

  @Override
  public boolean supportsRegistryBehavior() {
    // Set to true if the enum supports custom extension values and lookup methods
    return true; // Assuming all generated enums will support this
  }

  @Override
  public void assertLookupBehaviour() {
    withKmipSpec(
        KmipSpec.V2_1,
        () -> {
          ItemType.Value byName = ItemType.fromName("Structure");
          ItemType.Value byVal = ItemType.fromValue(0x00000001);
          assertThat(byName.getDescription()).isEqualTo("Structure");
          assertThat(byVal.getValue()).isEqualTo(0x00000001);
        }
    );

    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> ItemType.fromName("Structure"))
    );
  }

  @Override
  public void assertEnumerationRegistryBehavior() {
    // TODO: Customize these assertions based on actual enum values and expected registry behavior
    // Example:
    ItemType.Value custom =
        ItemType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
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
        () -> ItemType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ItemType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> ItemType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> ItemType.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}