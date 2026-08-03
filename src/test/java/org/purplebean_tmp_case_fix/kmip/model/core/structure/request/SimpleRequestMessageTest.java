package org.purplebean.kmip.model.core.structure.request;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SimpleRequestMessage Structure Semantics")
class SimpleRequestMessageTest extends AbstractKmipStructureTestSuite<SimpleRequestMessage> {

  @Override
  protected Class<SimpleRequestMessage> type() {
    return SimpleRequestMessage.class;
  }

  @Override
  protected SimpleRequestMessage createDefault() {
    SimpleRequestHeader header = SimpleRequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(1, 2))
        .build();
    SimpleRequestBatchItem item = SimpleRequestBatchItem
        .builder()
        .requestPayloadStructure(SimpleRequestPayload.of())
        .build();
    return SimpleRequestMessage
        .builder()
        .requestHeader(header)
        .requestBatchItem(item)
        .requestBatchItemError(null)
        // leave requestBatchItemErrors empty via @Singular
        .build();
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2; // header + at least one batch item
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // First should be header, subsequent are items
    assert values.get(0) instanceof SimpleRequestHeader;
    for (int i = 1; i < values.size(); i++) {
      assert values.get(i) instanceof SimpleRequestBatchItem;
    }
  }
}
