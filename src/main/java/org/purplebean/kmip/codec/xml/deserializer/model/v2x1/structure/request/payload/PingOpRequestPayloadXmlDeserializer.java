package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.request.payload.PingOpRequestPayload;

public class PingOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PingOpRequestPayload,
        PingOpRequestPayload.PingOpRequestPayloadBuilder> {

  public PingOpRequestPayloadXmlDeserializer() {
    super(PingOpRequestPayload.kmipTag, PingOpRequestPayload.encodingType);
  }

  @Override
  protected PingOpRequestPayload.PingOpRequestPayloadBuilder createBuilder() {
    return PingOpRequestPayload.builder();
  }

  @Override
  protected void setValue(PingOpRequestPayload.PingOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected PingOpRequestPayload build(PingOpRequestPayload.PingOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}