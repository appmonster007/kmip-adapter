package org.purplebean.kmip.model.core.structure.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.ResultReason;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.core.type.ResultMessage;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SimpleResponseBatchItem Domain Tests")
class SimpleResponseBatchItemTest extends AbstractKmipStructureTestSuite<SimpleResponseBatchItem> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<SimpleResponseBatchItem> type() {
    return SimpleResponseBatchItem.class;
  }

  @Override
  protected SimpleResponseBatchItem createDefault() {
    return SimpleResponseBatchItem
        .builder()
        .operation(Operation.of(Operation.Standard.CREATE))
        .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
        .resultReason(ResultReason.of(ResultReason.Standard.ITEM_NOT_FOUND))
        .resultMessage(ResultMessage.of("Success"))
        .responsePayloadStructure(SimpleResponsePayload
            .builder()
            .build())
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
    assertThat(values).hasSize(5);
    assertThat(values.get(0)).isInstanceOf(Operation.class);
    assertThat(values.get(1)).isInstanceOf(ResultStatus.class);
    assertThat(values.get(2)).isInstanceOf(ResultReason.class);
    assertThat(values.get(3)).isInstanceOf(ResultMessage.class);
    assertThat(values.get(4)).isInstanceOf(SimpleResponsePayload.class);
  }
}
