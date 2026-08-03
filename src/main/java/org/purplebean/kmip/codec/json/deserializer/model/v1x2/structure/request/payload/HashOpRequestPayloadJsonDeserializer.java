package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.request.payload.HashOpRequestPayload;

/**
 * JSON deserializer for {@link HashOpRequestPayload}.
 */
public class HashOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<HashOpRequestPayload,
        HashOpRequestPayload.HashOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link HashOpRequestPayloadJsonDeserializer}.
   */
  public HashOpRequestPayloadJsonDeserializer() {
    super(HashOpRequestPayload.kmipTag, HashOpRequestPayload.encodingType);
  }

  @Override
  protected HashOpRequestPayload.HashOpRequestPayloadBuilder createBuilder() {
    return HashOpRequestPayload.builder();
  }

  @Override
  protected void setValue(HashOpRequestPayload.HashOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected HashOpRequestPayload build(HashOpRequestPayload.HashOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}