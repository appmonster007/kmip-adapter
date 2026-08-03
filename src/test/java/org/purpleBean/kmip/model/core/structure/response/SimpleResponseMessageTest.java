package org.purpleBean.kmip.model.core.structure.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SimpleResponseMessage Domain Tests")
class SimpleResponseMessageTest extends AbstractKmipStructureTestSuite<SimpleResponseMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<SimpleResponseMessage> type() {
    return SimpleResponseMessage.class;
  }

  @Override
  protected SimpleResponseMessage createDefault() {
    return SimpleResponseMessage
        .builder()
        .responseHeader(SimpleResponseHeader
            .builder()
            .protocolVersion(
                ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
            .build())
        .responseBatchItem(SimpleResponseBatchItem
            .builder()
            .operation(Operation.of(Operation.Standard.CREATE))
            .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
            .responsePayloadStructure(SimpleResponsePayload
                .builder()
                .build())
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
    assertThat(values.get(0)).isInstanceOf(SimpleResponseHeader.class);
    assertThat(values.get(1)).isInstanceOf(SimpleResponseBatchItem.class);
  }
}
