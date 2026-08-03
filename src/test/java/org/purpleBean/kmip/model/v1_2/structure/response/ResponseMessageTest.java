package org.purpleBean.kmip.model.v1_2.structure.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ResponseMessage Domain Tests")
class ResponseMessageTest extends AbstractKmipStructureTestSuite<ResponseMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<ResponseMessage> type() {
    return ResponseMessage.class;
  }

  @Override
  protected ResponseMessage createDefault() {
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
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(ResponseHeader.class);
    assertThat(values.get(1)).isInstanceOf(ResponseBatchItem.class);
  }
}
