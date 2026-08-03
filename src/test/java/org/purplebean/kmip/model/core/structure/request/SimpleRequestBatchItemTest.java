package org.purplebean.kmip.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SimpleRequestBatchItem Structure Semantics")
class SimpleRequestBatchItemTest extends AbstractKmipStructureTestSuite<SimpleRequestBatchItem> {

  @Override
  protected Class<SimpleRequestBatchItem> type() {
    return SimpleRequestBatchItem.class;
  }

  @Override
  protected SimpleRequestBatchItem createDefault() {
    return SimpleRequestBatchItem
        .builder()
        .requestPayloadStructure(SimpleRequestPayload.of())
        .build();
  }

  @Override
  protected int expectedMinComponentCount() {
    return 0; // no inner components for now
  }
}
