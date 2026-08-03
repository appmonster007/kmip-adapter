package org.purplebean.kmip.model.v1x2.structure.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ResponseBatchItem Domain Tests")
class ResponseBatchItemTest extends AbstractKmipStructureTestSuite<ResponseBatchItem> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<ResponseBatchItem> type() {
    return ResponseBatchItem.class;
  }

  @Override
  protected ResponseBatchItem createDefault() {
    return ResponseBatchItem
        .builder()
        .operation(Operation.of(Operation.Standard.CREATE))
        .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
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
    assertThat(values.get(0)).isInstanceOf(Operation.class);
    assertThat(values.get(1)).isInstanceOf(ResultStatus.class);
  }
}
