package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


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