package org.purpleBean.kmip.codec.xml.model.core.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseMessage;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
