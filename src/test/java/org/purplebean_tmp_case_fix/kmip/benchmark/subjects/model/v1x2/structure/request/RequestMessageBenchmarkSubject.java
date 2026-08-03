package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.v1x2.structure.request.RequestBatchItem;
import org.purplebean.kmip.model.v1x2.structure.request.RequestHeader;
import org.purplebean.kmip.model.v1x2.structure.request.RequestMessage;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateOpRequestPayload;

public class RequestMessageBenchmarkSubject extends KmipBenchmarkSubject<RequestMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public RequestMessageBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
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
            .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
            .build())
        .build();
    RequestMessage subject = RequestMessage
        .builder()
        .requestHeader(header)
        .requestBatchItem(item)
        .requestBatchItemError(null)
        .build();
    initialize(subject, RequestMessage.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "RequestMessage";
  }
}