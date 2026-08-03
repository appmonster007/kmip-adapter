package org.purplebean.kmip.codec.xml.model.v1x2.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseBatchItem;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseHeader;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseMessage;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResponseMessage Xml Serialization Tests")
class ResponseMessageXmlTest extends AbstractXmlSerializationTestSuite<ResponseMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ResponseMessage> type() {
    return ResponseMessage.class;
  }

  @Override
  public ResponseMessage createDefault() {
    return ResponseMessage
        .builder()
        .responseHeader(ResponseHeader
            .builder()
            .protocolVersion(
                ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
            .timeStamp(TimeStamp.of(OffsetDateTime.now(ZoneOffset.UTC)))
            .batchCount(BatchCount.of(1))
            .build())
        .responseBatchItem(ResponseBatchItem
            .builder()
            .operation(Operation.of(Operation.Standard.CREATE))
            .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
            .build())
        .responseBatchItemError(null)
        .build();
  }

  @Override
  public ResponseMessage createVariant() {
    return ResponseMessage
        .builder()
        .responseHeader(ResponseHeader
            .builder()
            .protocolVersion(
                ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(3)))
            .timeStamp(TimeStamp.of(OffsetDateTime
                .now(ZoneOffset.UTC)
                .plusDays(1)))
            .batchCount(BatchCount.of(2))
            .build())
        .responseBatchItem(ResponseBatchItem
            .builder()
            .operation(Operation.of(Operation.Standard.GET))
            .resultStatus(ResultStatus.of(ResultStatus.Standard.OPERATION_FAILED))
            .build())
        .responseBatchItemError(null)
        .build();
  }
}
