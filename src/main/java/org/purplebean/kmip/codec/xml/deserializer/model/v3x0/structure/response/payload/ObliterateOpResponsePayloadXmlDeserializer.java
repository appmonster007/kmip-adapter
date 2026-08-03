package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.structure.response.payload.ObliterateOpResponsePayload;

/**
 * XML deserializer for {@link ObliterateOpResponsePayload}.
 */
public class ObliterateOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ObliterateOpResponsePayload,
        ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ObliterateOpResponsePayloadXmlDeserializer}.
   */
  public ObliterateOpResponsePayloadXmlDeserializer() {
    super(ObliterateOpResponsePayload.kmipTag, ObliterateOpResponsePayload.encodingType);
  }

  @Override
  protected ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder createBuilder() {
    return ObliterateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected ObliterateOpResponsePayload build(
      ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}