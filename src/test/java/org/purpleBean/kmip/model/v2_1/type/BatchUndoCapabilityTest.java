package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("BatchUndoCapability Domain Tests")
class BatchUndoCapabilityTest extends AbstractKmipDataTypeTestSuite<BatchUndoCapability> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<BatchUndoCapability> type() {
    return BatchUndoCapability.class;
  }

  @Override
  protected BatchUndoCapability createDefault() {
    return BatchUndoCapability.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}