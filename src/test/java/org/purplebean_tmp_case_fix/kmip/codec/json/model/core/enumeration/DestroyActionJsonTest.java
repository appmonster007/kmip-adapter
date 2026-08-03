package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.DestroyAction;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DestroyAction JSON Serialization")
class DestroyActionJsonTest extends AbstractJsonSerializationTestSuite<DestroyAction> {
  @Override
  public Class<DestroyAction> type() {
    return DestroyAction.class;
  }

  @Override
  public DestroyAction createDefault() {
    return DestroyAction.Standard.UNSPECIFIED.inst();
  }

  @Override
  public DestroyAction createVariant() {
    return DestroyAction.Standard.KEY_MATERIAL_DELETED.inst();
  }
}
