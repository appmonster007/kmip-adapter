package org.purplebean.kmip.codec.xml.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponseBatchItemStructure;
import org.purplebean.kmip.api.response.ResponseHeaderStructure;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseMessage;

public class SimpleResponseMessageXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SimpleResponseMessage,
        SimpleResponseMessage.SimpleResponseMessageBuilder> {

  public SimpleResponseMessageXmlDeserializer() {
    super(SimpleResponseMessage.kmipTag, SimpleResponseMessage.encodingType);
  }

  @Override
  protected SimpleResponseMessage.SimpleResponseMessageBuilder createBuilder() {
    return SimpleResponseMessage.builder();
  }

  @Override
  protected void setValue(SimpleResponseMessage.SimpleResponseMessageBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RESPONSE_HEADER ->
          builder.responseHeader(ctxt.readValue(p, ResponseHeaderStructure.class));
      case KmipTag.Standard.BATCH_ITEM -> {
        if (p.isExpectedStartArrayToken()) { // Jackson XML may present repeated elements as an
          // array token sequence
          while (p.nextToken() != com.fasterxml.jackson.core.JsonToken.END_ARRAY) {
            try {
              builder.responseBatchItem(ctxt.readValue(p, ResponseBatchItemStructure.class));
              builder.responseBatchItemError(null);
            } catch (Exception e) {
              builder.responseBatchItem(null);
              builder.responseBatchItemError(e);
            }
          }
        } else {
          try {
            builder.responseBatchItem(ctxt.readValue(p, ResponseBatchItemStructure.class));
            builder.responseBatchItemError(null);
          } catch (Exception e) {
            builder.responseBatchItem(null);
            builder.responseBatchItemError(e);
          }
        }
      }
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SimpleResponseMessage build(
      SimpleResponseMessage.SimpleResponseMessageBuilder builder) {
    return builder.build();
  }
}
