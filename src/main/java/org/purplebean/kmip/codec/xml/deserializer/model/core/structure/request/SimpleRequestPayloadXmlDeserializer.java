package org.purplebean.kmip.codec.xml.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestPayload;

/**
 * XML deserializer for {@link SimpleRequestPayload}.
 */
public class SimpleRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SimpleRequestPayload,
        SimpleRequestPayload.SimpleRequestPayloadBuilder> {

  /**
   * Constructs a new {@link SimpleRequestPayloadXmlDeserializer}.
   */
  public SimpleRequestPayloadXmlDeserializer() {
    super(SimpleRequestPayload.kmipTag, SimpleRequestPayload.encodingType);
  }

  @Override
  protected SimpleRequestPayload.SimpleRequestPayloadBuilder createBuilder() {
    return SimpleRequestPayload.builder();
  }

  @Override
  protected void setValue(SimpleRequestPayload.SimpleRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields to set
  }

  @Override
  protected SimpleRequestPayload build(SimpleRequestPayload.SimpleRequestPayloadBuilder builder) {
    return builder.build();
  }
}