package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v1x2.structure.response.payload.PollOpResponsePayload;

/**
 * XML deserializer for {@link PollOpResponsePayload}.
 */
public class PollOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PollOpResponsePayload,
        PollOpResponsePayload.PollOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link PollOpResponsePayloadXmlDeserializer}.
   */
  public PollOpResponsePayloadXmlDeserializer() {
    super(PollOpResponsePayload.kmipTag, PollOpResponsePayload.encodingType);
  }

  @Override
  protected PollOpResponsePayload.PollOpResponsePayloadBuilder createBuilder() {
    return PollOpResponsePayload.builder();
  }

  @Override
  protected void setValue(PollOpResponsePayload.PollOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected PollOpResponsePayload build(
      PollOpResponsePayload.PollOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
