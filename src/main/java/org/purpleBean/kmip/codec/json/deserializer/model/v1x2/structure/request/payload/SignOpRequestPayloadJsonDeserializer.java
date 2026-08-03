package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.SignOpRequestPayload;

public class SignOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SignOpRequestPayload,
        SignOpRequestPayload.SignOpRequestPayloadBuilder> {

  public SignOpRequestPayloadJsonDeserializer() {
    super(SignOpRequestPayload.kmipTag, SignOpRequestPayload.encodingType);
  }

  @Override
  protected SignOpRequestPayload.SignOpRequestPayloadBuilder createBuilder() {
    return SignOpRequestPayload.builder();
  }

  @Override
  protected void setValue(SignOpRequestPayload.SignOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SignOpRequestPayload build(SignOpRequestPayload.SignOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}