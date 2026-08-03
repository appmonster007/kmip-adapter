package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DecryptOpRequestPayload;

public class DecryptOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DecryptOpRequestPayload,
        DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder> {

  public DecryptOpRequestPayloadXmlDeserializer() {
    super(DecryptOpRequestPayload.kmipTag, DecryptOpRequestPayload.encodingType);
  }

  @Override
  protected DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder createBuilder() {
    return DecryptOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      case KmipTag.Standard.IV_COUNTER_NONCE ->
          builder.ivCounterNonce(ctxt.readValue(p, IVCounterNonce.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DecryptOpRequestPayload build(
      DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}