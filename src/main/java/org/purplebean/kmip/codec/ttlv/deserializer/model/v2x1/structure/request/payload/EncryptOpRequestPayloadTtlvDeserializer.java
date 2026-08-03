package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.EncryptOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionAdditionalData;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.FinalIndicator;
import org.purplebean.kmip.model.v2x1.type.InitIndicator;

/**
 * TTLV deserializer for {@link EncryptOpRequestPayload}.
 */
public class EncryptOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<EncryptOpRequestPayload,
        EncryptOpRequestPayload.EncryptOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link EncryptOpRequestPayloadTtlvDeserializer}.
   */
  public EncryptOpRequestPayloadTtlvDeserializer() {
    super(EncryptOpRequestPayload.kmipTag, EncryptOpRequestPayload.encodingType);
  }

  @Override
  protected EncryptOpRequestPayload.EncryptOpRequestPayloadBuilder createBuilder() {
    return EncryptOpRequestPayload.builder();
  }

  @Override
  protected void setValue(EncryptOpRequestPayload.EncryptOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(
          (org.purplebean.kmip.api.DataValue) mapper.readValue(p,
              org.purplebean.kmip.api.KmipDataType.class));
      case KmipTag.Standard.IV_COUNTER_NONCE ->
          builder.ivCounterNonce(mapper.readValue(p, IVCounterNonce.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
      case KmipTag.Standard.INIT_INDICATOR ->
          builder.initIndicator(mapper.readValue(p, InitIndicator.class));
      case KmipTag.Standard.FINAL_INDICATOR ->
          builder.finalIndicator(mapper.readValue(p, FinalIndicator.class));
      case KmipTag.Standard.AUTHENTICATED_ENCRYPTION_ADDITIONAL_DATA ->
          builder.authenticatedEncryptionAdditionalData(
              mapper.readValue(p, AuthenticatedEncryptionAdditionalData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected EncryptOpRequestPayload build(
      EncryptOpRequestPayload.EncryptOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
