package org.purpleBean.kmip.codec.xml.deserializer.model.v1x2.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v1x2.structure.request.RequestMessage;

public class RequestMessageXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RequestMessage, RequestMessage.RequestMessageBuilder> {

  public RequestMessageXmlDeserializer() {
    super(RequestMessage.kmipTag, RequestMessage.encodingType);
  }

  @Override
  protected RequestMessage.RequestMessageBuilder createBuilder() {
    return RequestMessage.builder();
  }

  @Override
  protected void setValue(RequestMessage.RequestMessageBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.REQUEST_HEADER ->
          builder.requestHeader(ctxt.readValue(p, RequestHeaderStructure.class));
      case KmipTag.Standard.BATCH_ITEM -> {
        if (p.isExpectedStartArrayToken()) {
          while (p.nextToken() != com.fasterxml.jackson.core.JsonToken.END_ARRAY) {
            try {
              builder.requestBatchItem(ctxt.readValue(p, RequestBatchItemStructure.class));
              builder.requestBatchItemError(null);
            } catch (Exception e) {
              builder.requestBatchItem(null);
              builder.requestBatchItemError(e);
            }
          }
        } else {
          try {
            builder.requestBatchItem(ctxt.readValue(p, RequestBatchItemStructure.class));
            builder.requestBatchItemError(null);
          } catch (Exception e) {
            builder.requestBatchItem(null);
            builder.requestBatchItemError(e);
          }
        }
      }
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RequestMessage build(RequestMessage.RequestMessageBuilder builder) {
    return builder.build();
  }
}