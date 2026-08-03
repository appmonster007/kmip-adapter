package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.ResultReason;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.core.type.ResultMessage;
import org.purplebean.kmip.model.core.type.UniqueBatchItemID;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseBatchItem;

public class ResponseBatchItemTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ResponseBatchItem,
        ResponseBatchItem.ResponseBatchItemBuilder> {

  public ResponseBatchItemTtlvDeserializer() {
    super(ResponseBatchItem.kmipTag, ResponseBatchItem.encodingType);
  }

  @Override
  protected ResponseBatchItem.ResponseBatchItemBuilder createBuilder() {
    return ResponseBatchItem.builder();
  }

  @Override
  protected void setValue(ResponseBatchItem.ResponseBatchItemBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPERATION -> {
        Operation operation = mapper.readValue(p, Operation.class);
        builder.operation(operation);
        mapper.setAttribute("operation", operation.getDescription());
      }
      case KmipTag.Standard.UNIQUE_BATCH_ITEM_ID ->
          builder.uniqueBatchItemID(mapper.readValue(p, UniqueBatchItemID.class));
      case KmipTag.Standard.RESULT_STATUS ->
          builder.resultStatus(mapper.readValue(p, ResultStatus.class));
      case KmipTag.Standard.RESULT_REASON ->
          builder.resultReason(mapper.readValue(p, ResultReason.class));
      case KmipTag.Standard.RESULT_MESSAGE ->
          builder.resultMessage(mapper.readValue(p, ResultMessage.class));
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          mapper.readValue(p, AsynchronousCorrelationValue.class));
      case KmipTag.Standard.RESPONSE_PAYLOAD ->
          builder.responsePayloadStructure(mapper.readValue(p, ResponsePayloadStructure.class));
      case KmipTag.Standard.MESSAGE_EXTENSION ->
          builder.messageExtension(mapper.readValue(p, MessageExtension.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ResponseBatchItem build(ResponseBatchItem.ResponseBatchItemBuilder builder) {
    return builder.build();
  }
}