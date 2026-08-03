package org.purpleBean.kmip.model.v3_0.structure.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RequestBatchItem Domain Tests")
class RequestBatchItemTest extends AbstractKmipStructureTestSuite<RequestBatchItem> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<RequestBatchItem> type() {
    return RequestBatchItem.class;
  }

  @Override
  protected RequestBatchItem createDefault() {
    return RequestBatchItem
        .builder()
        .operation(Operation.of(Operation.Standard.CREATE))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 1;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values.size()).isGreaterThanOrEqualTo(1);
    assertThat(values.get(0)).isInstanceOf(Operation.class);
  }
}