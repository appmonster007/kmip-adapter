package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RngRetrieveOpResponsePayload;

/**
 * JSON deserializer for {@link RngRetrieveOpResponsePayload}.
 */
public class RngRetrieveOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RngRetrieveOpResponsePayload,
        RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link RngRetrieveOpResponsePayloadJsonDeserializer}.
   */
  public RngRetrieveOpResponsePayloadJsonDeserializer() {
    super(RngRetrieveOpResponsePayload.kmipTag, RngRetrieveOpResponsePayload.encodingType);
  }

  @Override
  protected RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder createBuilder() {
    return RngRetrieveOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.DATA)) {
      builder.data(ctxt.readValue(p, DataByteString.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngRetrieveOpResponsePayload build(
      RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
