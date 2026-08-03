package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateOpRequestPayload;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RequestBatchItem Xml Serialization Tests")
class RequestBatchItemXmlTest extends AbstractXmlSerializationTestSuite<RequestBatchItem> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RequestBatchItem> type() {
    return RequestBatchItem.class;
  }

  @Override
  public RequestBatchItem createDefault() {
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
  public RequestBatchItem createVariant() {
    return RequestBatchItem
        .builder()
        .operation(Operation.Standard.GET.inst())
        .requestPayloadStructure(GetOpRequestPayload
            .builder()
            .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
            .build())
        .build();
  }
}