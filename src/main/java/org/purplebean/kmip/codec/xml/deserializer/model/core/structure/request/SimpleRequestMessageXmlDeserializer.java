package org.purplebean.kmip.codec.xml.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestBatchItemStructure;
import org.purplebean.kmip.api.request.RequestHeaderStructure;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestMessage;

/**
 * XML deserializer for {@link SimpleRequestMessage}.
 */
public class SimpleRequestMessageXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SimpleRequestMessage,
        SimpleRequestMessage.SimpleRequestMessageBuilder> {

  /**
   * Constructs a new {@link SimpleRequestMessageXmlDeserializer}.
   */
  public SimpleRequestMessageXmlDeserializer() {
    super(SimpleRequestMessage.kmipTag, SimpleRequestMessage.encodingType);
  }

  @Override
  protected SimpleRequestMessage.SimpleRequestMessageBuilder createBuilder() {
    return SimpleRequestMessage.builder();
  }

  @Override
  protected void setValue(SimpleRequestMessage.SimpleRequestMessageBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.REQUEST_HEADER ->
          builder.requestHeader(ctxt.readValue(p, RequestHeaderStructure.class));
      case KmipTag.Standard.BATCH_ITEM -> {
        if (p.isExpectedStartArrayToken()) { // TODO: can be removed?
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
  protected SimpleRequestMessage build(SimpleRequestMessage.SimpleRequestMessageBuilder builder) {
    return builder.build();
  }
}