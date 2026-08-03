package org.purplebean.kmip.codec.json.model.v1x2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.RequestBatchItem;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateOpRequestPayload;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RequestBatchItem Json Serialization Tests")
class RequestBatchItemJsonTest extends AbstractJsonSerializationTestSuite<RequestBatchItem> {

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