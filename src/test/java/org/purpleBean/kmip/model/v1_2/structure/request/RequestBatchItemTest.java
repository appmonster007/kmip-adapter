package org.purpleBean.kmip.model.v1_2.structure.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RequestBatchItem Domain Tests")
class RequestBatchItemTest extends AbstractKmipStructureTestSuite<RequestBatchItem> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<RequestBatchItem> type() {
    return RequestBatchItem.class;
  }

  @Override
  protected RequestBatchItem createDefault() {
    return RequestBatchItem
        .builder()
        .operation(Operation.Standard.CREATE.inst())
        .requestPayloadStructure(CreateOpRequestPayload
            .builder()
            .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
            .templateAttribute(TemplateAttribute
                .builder()
                .build())
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
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(Operation.class);
    assertThat(values.get(1)).isInstanceOf(RequestPayloadStructure.class);
  }
}