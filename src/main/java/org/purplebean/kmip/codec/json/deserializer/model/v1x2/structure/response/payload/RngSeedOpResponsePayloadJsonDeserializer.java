package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.DataLength;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RngSeedOpResponsePayload;

/**
 * JSON deserializer for {@link RngSeedOpResponsePayload}.
 */
public class RngSeedOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RngSeedOpResponsePayload,
        RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link RngSeedOpResponsePayloadJsonDeserializer}.
   */
  public RngSeedOpResponsePayloadJsonDeserializer() {
    super(RngSeedOpResponsePayload.kmipTag, RngSeedOpResponsePayload.encodingType);
  }

  @Override
  protected RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder createBuilder() {
    return RngSeedOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.DATA_LENGTH)) {
      builder.dataLength(ctxt.readValue(p, DataLength.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngSeedOpResponsePayload build(
      RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
