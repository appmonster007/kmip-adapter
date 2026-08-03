package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Ephemeral;
import org.purplebean.kmip.model.v3x0.structure.request.RequestBatchItem;

public class RequestBatchItemXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RequestBatchItem,
        RequestBatchItem.RequestBatchItemBuilder> {

  public RequestBatchItemXmlDeserializer() {
    super(RequestBatchItem.kmipTag, RequestBatchItem.encodingType);
  }

  @Override
  protected RequestBatchItem.RequestBatchItemBuilder createBuilder() {
    return RequestBatchItem.builder();
  }

  @Override
  protected void setValue(RequestBatchItem.RequestBatchItemBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPERATION -> {
        Operation operation = ctxt.readValue(p, Operation.class);
        builder.operation(operation);
        ctxt.setAttribute("operation", operation.getDescription());
      }
      case KmipTag.Standard.EPHEMERAL -> builder.ephemeral(ctxt.readValue(p, Ephemeral.class));
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          ctxt.readValue(p, AsynchronousCorrelationValue.class));
      case KmipTag.Standard.REQUEST_PAYLOAD ->
          builder.requestPayloadStructure(ctxt.readValue(p, RequestPayloadStructure.class));
      case KmipTag.Standard.MESSAGE_EXTENSION ->
          builder.messageExtension(ctxt.readValue(p, MessageExtension.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RequestBatchItem build(RequestBatchItem.RequestBatchItemBuilder builder) {
    return builder.build();
  }
}