package org.purpleBean.kmip.model.v1x2.structure.request;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.CreateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RequestMessage Domain Tests")
class RequestMessageTest extends AbstractKmipStructureTestSuite<RequestMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<RequestMessage> type() {
    return RequestMessage.class;
  }

  @Override
  protected RequestMessage createDefault() {
    RequestHeader header = RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(1, 2))
        .batchCount(BatchCount.of(0))
        .build();
    RequestBatchItem item = RequestBatchItem
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
    return RequestMessage
        .builder()
        .requestHeader(header)
        .requestBatchItem(item)
        .requestBatchItemError(null)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    // TODO: Set the expected minimum number of components
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // TODO: Validate the components of the structure
    // assertThat(values).hasSize(0);
  }
}