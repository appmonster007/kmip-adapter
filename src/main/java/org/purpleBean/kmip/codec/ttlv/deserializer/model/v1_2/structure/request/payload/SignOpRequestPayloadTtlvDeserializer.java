package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.SignOpRequestPayload;

public class SignOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SignOpRequestPayload,
        SignOpRequestPayload.SignOpRequestPayloadBuilder> {

  public SignOpRequestPayloadTtlvDeserializer() {
    super(SignOpRequestPayload.kmipTag, SignOpRequestPayload.encodingType);
  }

  @Override
  protected SignOpRequestPayload.SignOpRequestPayloadBuilder createBuilder() {
    return SignOpRequestPayload.builder();
  }

  @Override
  protected void setValue(SignOpRequestPayload.SignOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SignOpRequestPayload build(SignOpRequestPayload.SignOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}