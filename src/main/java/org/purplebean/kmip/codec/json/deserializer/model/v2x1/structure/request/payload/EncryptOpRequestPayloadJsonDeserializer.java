package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.EncryptOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionAdditionalData;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.FinalIndicator;
import org.purplebean.kmip.model.v2x1.type.InitIndicator;

public class EncryptOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<EncryptOpRequestPayload,
        EncryptOpRequestPayload.EncryptOpRequestPayloadBuilder> {

  public EncryptOpRequestPayloadJsonDeserializer() {
    super(EncryptOpRequestPayload.kmipTag, EncryptOpRequestPayload.encodingType);
  }

  @Override
  protected EncryptOpRequestPayload.EncryptOpRequestPayloadBuilder createBuilder() {
    return EncryptOpRequestPayload.builder();
  }

  @Override
  protected void setValue(EncryptOpRequestPayload.EncryptOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(
          (org.purplebean.kmip.api.DataValue) ctxt.readValue(p,
              org.purplebean.kmip.api.KmipDataType.class));
      case KmipTag.Standard.IV_COUNTER_NONCE ->
          builder.ivCounterNonce(ctxt.readValue(p, IVCounterNonce.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
      case KmipTag.Standard.INIT_INDICATOR ->
          builder.initIndicator(ctxt.readValue(p, InitIndicator.class));
      case KmipTag.Standard.FINAL_INDICATOR ->
          builder.finalIndicator(ctxt.readValue(p, FinalIndicator.class));
      case KmipTag.Standard.AUTHENTICATED_ENCRYPTION_ADDITIONAL_DATA ->
          builder.authenticatedEncryptionAdditionalData(
              ctxt.readValue(p, AuthenticatedEncryptionAdditionalData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected EncryptOpRequestPayload build(
      EncryptOpRequestPayload.EncryptOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
