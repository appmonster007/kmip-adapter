package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.HashOpRequestPayload;

public class HashOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<HashOpRequestPayload,
        HashOpRequestPayload.HashOpRequestPayloadBuilder> {

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