package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;

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
      case KmipTag.Standard.UNIQUE_BATCH_ITEM_ID ->
          builder.uniqueBatchItemID(ctxt.readValue(p, UniqueBatchItemID.class));
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