package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.BatchUndoCapability;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("BatchUndoCapability Xml Serialization Tests")
class BatchUndoCapabilityXmlTest extends AbstractXmlSerializationTestSuite<BatchUndoCapability> {

  @Override
  public Class<BatchUndoCapability> type() {
    return BatchUndoCapability.class;
  }

  @Override
  public BatchUndoCapability createDefault() {
    return BatchUndoCapability.of(true);
  }

  @Override
  public BatchUndoCapability createVariant() {
    return BatchUndoCapability.of(false);
  }
}