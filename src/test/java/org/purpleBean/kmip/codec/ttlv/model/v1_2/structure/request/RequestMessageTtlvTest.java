package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestHeader;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestMessage;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateOpRequestPayload;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestMessage Ttlv Serialization Tests")
class RequestMessageTtlvTest extends AbstractTtlvSerializationTestSuite<RequestMessage> {

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