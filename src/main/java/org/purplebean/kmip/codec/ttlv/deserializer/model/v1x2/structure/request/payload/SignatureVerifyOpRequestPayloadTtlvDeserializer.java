package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.SignatureData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.SignatureVerifyOpRequestPayload;

/**
 * TTLV deserializer for {@link SignatureVerifyOpRequestPayload}.
 */
public class SignatureVerifyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SignatureVerifyOpRequestPayload,
        SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link SignatureVerifyOpRequestPayloadTtlvDeserializer}.
   */
  public SignatureVerifyOpRequestPayloadTtlvDeserializer() {
    super(SignatureVerifyOpRequestPayload.kmipTag, SignatureVerifyOpRequestPayload.encodingType);
  }

  @Override
  protected SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder createBuilder() {
    return SignatureVerifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      case KmipTag.Standard.SIGNATURE_DATA ->
          builder.signatureData(mapper.readValue(p, SignatureData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SignatureVerifyOpRequestPayload build(
      SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}