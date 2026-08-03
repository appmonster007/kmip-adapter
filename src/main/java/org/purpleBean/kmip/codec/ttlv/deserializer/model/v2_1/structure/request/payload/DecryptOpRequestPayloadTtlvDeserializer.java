package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DecryptOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionAdditionalData;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionTag;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

public class DecryptOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DecryptOpRequestPayload,
        DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder> {

  public DecryptOpRequestPayloadTtlvDeserializer() {
    super(DecryptOpRequestPayload.kmipTag, DecryptOpRequestPayload.encodingType);
  }

  @Override
  protected DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder createBuilder() {
    return DecryptOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(
          (org.purpleBean.kmip.api.DataValue) mapper.readValue(p,
              org.purpleBean.kmip.api.KmipDataType.class));
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
      case KmipTag.Standard.AUTHENTICATED_ENCRYPTION_TAG ->
          builder.authenticatedEncryptionTag(mapper.readValue(p, AuthenticatedEncryptionTag.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DecryptOpRequestPayload build(
      DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
