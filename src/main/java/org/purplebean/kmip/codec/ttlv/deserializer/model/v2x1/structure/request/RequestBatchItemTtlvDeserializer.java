package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.core.type.UniqueBatchItemID;
import org.purplebean.kmip.model.v2x1.structure.request.RequestBatchItem;
import org.purplebean.kmip.model.v2x1.type.Ephemeral;

public class RequestBatchItemTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RequestBatchItem,
        RequestBatchItem.RequestBatchItemBuilder> {

  public RequestBatchItemTtlvDeserializer() {
    super(RequestBatchItem.kmipTag, RequestBatchItem.encodingType);
  }

  @Override
  protected RequestBatchItem.RequestBatchItemBuilder createBuilder() {
    return RequestBatchItem.builder();
  }

  @Override
  protected void setValue(RequestBatchItem.RequestBatchItemBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPERATION -> {
        Operation operation = mapper.readValue(p, Operation.class);
        builder.operation(operation);
        mapper.setAttribute("operation", operation.getDescription());
      }
      case KmipTag.Standard.EPHEMERAL -> builder.ephemeral(mapper.readValue(p, Ephemeral.class));
      case KmipTag.Standard.UNIQUE_BATCH_ITEM_ID ->
          builder.uniqueBatchItemID(mapper.readValue(p, UniqueBatchItemID.class));
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          mapper.readValue(p, AsynchronousCorrelationValue.class));
      case KmipTag.Standard.REQUEST_PAYLOAD ->
          builder.requestPayloadStructure(mapper.readValue(p, RequestPayloadStructure.class));
      case KmipTag.Standard.MESSAGE_EXTENSION ->
          builder.messageExtension(mapper.readValue(p, MessageExtension.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RequestBatchItem build(RequestBatchItem.RequestBatchItemBuilder builder) {
    return builder.build();
  }
}
