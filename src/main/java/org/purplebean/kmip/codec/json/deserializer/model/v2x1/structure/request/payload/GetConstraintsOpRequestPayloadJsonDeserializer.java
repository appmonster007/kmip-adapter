package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.request.payload.GetConstraintsOpRequestPayload;

/**
 * JSON deserializer for {@link GetConstraintsOpRequestPayload}.
 */
public class GetConstraintsOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<GetConstraintsOpRequestPayload,
        GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link GetConstraintsOpRequestPayloadJsonDeserializer}.
   */
  public GetConstraintsOpRequestPayloadJsonDeserializer() {
    super(GetConstraintsOpRequestPayload.kmipTag, GetConstraintsOpRequestPayload.encodingType);
  }

  @Override
  protected GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder createBuilder() {
    return GetConstraintsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected GetConstraintsOpRequestPayload build(
      GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
