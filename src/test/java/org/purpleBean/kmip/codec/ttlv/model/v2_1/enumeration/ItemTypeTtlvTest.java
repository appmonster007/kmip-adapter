package org.purpleBean.kmip.codec.ttlv.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.ItemType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ItemType Ttlv Serialization Tests")
class ItemTypeTtlvTest extends AbstractTtlvSerializationTestSuite<ItemType> {

  @Override
  public Class<ItemType> type() {
    return ItemType.class;
  }

  @Override
  public ItemType createDefault() {
    return ItemType.Standard.values()[0].inst();
  }

  @Override
  public ItemType createVariant() {
    return ItemType.Standard.values()[1].inst();
  }
}