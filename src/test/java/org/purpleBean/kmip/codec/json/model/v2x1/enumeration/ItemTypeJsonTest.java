package org.purplebean.kmip.codec.json.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.ItemType;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ItemType Json Serialization Tests")
class ItemTypeJsonTest extends AbstractJsonSerializationTestSuite<ItemType> {

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