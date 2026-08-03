package org.purplebean.kmip.codec.json.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestPayload;

/**
 * JSON deserializer for {@link SimpleRequestPayload}.
 */
public class SimpleRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SimpleRequestPayload,
        SimpleRequestPayload.SimpleRequestPayloadBuilder> {

  /**
   * Constructs a new {@link SimpleRequestPayloadJsonDeserializer}.
   */
  public SimpleRequestPayloadJsonDeserializer() {
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
    KmipTag.Value nodeTag = KmipTag.fromName(tag);// No fields to set
  }

  @Override
  protected SimpleRequestPayload build(SimpleRequestPayload.SimpleRequestPayloadBuilder builder) {
    return builder.build();
  }
}