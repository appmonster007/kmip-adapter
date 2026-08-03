package org.purplebean.kmip.codec.xml.model.core.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseHeader;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseMessage;
import org.purplebean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SimpleResponseMessage Xml Serialization Tests")
class SimpleResponseMessageXmlTest
    extends AbstractXmlSerializationTestSuite<SimpleResponseMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<SimpleResponseMessage> type() {
    return SimpleResponseMessage.class;
  }

  @Override
  public SimpleResponseMessage createDefault() {
    return SimpleResponseMessage
        .builder()
        .responseHeader(SimpleResponseHeader
            .builder()
            .protocolVersion(
                ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
            .build())
        .responseBatchItem(SimpleResponseBatchItem
            .builder()
            .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
            .responsePayloadStructure(SimpleResponsePayload
                .builder()
                .build())
            .build())
        .responseBatchItemError(null)
        .build();
  }

  @Override
  public SimpleResponseMessage createVariant() {
    return SimpleResponseMessage
        .builder()
        .responseHeader(SimpleResponseHeader
            .builder()
            .protocolVersion(
                ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(3)))
            .build())
        .responseBatchItem(SimpleResponseBatchItem
            .builder()
            .resultStatus(ResultStatus.of(ResultStatus.Standard.OPERATION_FAILED))
            .responsePayloadStructure(SimpleResponsePayload
                .builder()
                .build())
            .build())
        .responseBatchItemError(null)
        .build();
  }
}
