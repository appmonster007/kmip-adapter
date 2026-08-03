package org.purpleBean.kmip.codec.json.deserializer.model.v3x0.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.core.type.ResultMessage;
import org.purpleBean.kmip.model.v3x0.structure.response.ResponseBatchItem;

public class ResponseBatchItemJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ResponseBatchItem,
        ResponseBatchItem.ResponseBatchItemBuilder> {

  public ResponseBatchItemJsonDeserializer() {
    super(ResponseBatchItem.kmipTag, ResponseBatchItem.encodingType);
  }

  @Override
  protected ResponseBatchItem.ResponseBatchItemBuilder createBuilder() {
    return ResponseBatchItem.builder();
  }

  @Override
  protected void setValue(ResponseBatchItem.ResponseBatchItemBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPERATION -> {
        Operation operation = ctxt.readValue(p, Operation.class);
        builder.operation(operation);
        ctxt.setAttribute("operation", operation.getDescription());
      }
      case KmipTag.Standard.RESULT_STATUS ->
          builder.resultStatus(ctxt.readValue(p, ResultStatus.class));
      case KmipTag.Standard.RESULT_REASON ->
          builder.resultReason(ctxt.readValue(p, ResultReason.class));
      case KmipTag.Standard.RESULT_MESSAGE ->
          builder.resultMessage(ctxt.readValue(p, ResultMessage.class));
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          ctxt.readValue(p, AsynchronousCorrelationValue.class));
      case KmipTag.Standard.RESPONSE_PAYLOAD ->
          builder.responsePayloadStructure(ctxt.readValue(p, ResponsePayloadStructure.class));
      case KmipTag.Standard.MESSAGE_EXTENSION ->
          builder.messageExtension(ctxt.readValue(p, MessageExtension.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ResponseBatchItem build(ResponseBatchItem.ResponseBatchItemBuilder builder) {
    return builder.build();
  }
}