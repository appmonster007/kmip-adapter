package org.purpleBean.kmip.codec.xml.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestMessage;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SimpleRequestMessage XML Serialization")
class SimpleRequestMessageXmlTest extends AbstractXmlSerializationTestSuite<SimpleRequestMessage> {

  @Override
  public Class<SimpleRequestMessage> type() {
    return SimpleRequestMessage.class;
  }

  @Override
  public SimpleRequestMessage createDefault() {
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
        .build();
  }

  @Override
  public SimpleRequestMessage createVariant() {
    SimpleRequestHeader header = SimpleRequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 0))
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
        .build();
  }

  @Override
  public boolean unsupportedSpecShouldFailSerialize() {
    return false; // model supports UnsupportedVersion
  }
}
