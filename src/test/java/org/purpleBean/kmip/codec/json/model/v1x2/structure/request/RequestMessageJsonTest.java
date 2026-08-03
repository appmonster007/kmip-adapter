package org.purplebean.kmip.codec.json.model.v1x2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.RequestBatchItem;
import org.purplebean.kmip.model.v1x2.structure.request.RequestHeader;
import org.purplebean.kmip.model.v1x2.structure.request.RequestMessage;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateOpRequestPayload;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RequestMessage Json Serialization Tests")
class RequestMessageJsonTest extends AbstractJsonSerializationTestSuite<RequestMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RequestMessage> type() {
    return RequestMessage.class;
  }

  @Override
  public RequestMessage createDefault() {
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
  public RequestMessage createVariant() {
    RequestHeader header = RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(1, 2))
        .batchCount(BatchCount.of(0))
        .build();
    RequestBatchItem item = RequestBatchItem
        .builder()
        .operation(Operation.Standard.GET.inst())
        .requestPayloadStructure(GetOpRequestPayload
            .builder()
            .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
            .build())
        .build();
    return RequestMessage
        .builder()
        .requestHeader(header)
        .requestBatchItem(item)
        .requestBatchItemError(null)
        .build();
  }
}