package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseBatchItem;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseHeader;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResponseMessage Ttlv Serialization Tests")
class ResponseMessageTtlvTest extends AbstractTtlvSerializationTestSuite<ResponseMessage> {

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
