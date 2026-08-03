package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.TransparentRsaPublicKey;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.model.core.type.PublicExponent;

public class TransparentRsaPublicKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentRsaPublicKey,
        TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder> {

  public TransparentRsaPublicKeyTtlvDeserializer() {
    super(TransparentRsaPublicKey.kmipTag, TransparentRsaPublicKey.encodingType);
  }

  @Override
  protected TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder createBuilder() {
    return TransparentRsaPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.MODULUS -> builder.modulus(mapper.readValue(p, Modulus.class));
      case KmipTag.Standard.PUBLIC_EXPONENT ->
          builder.publicExponent(mapper.readValue(p, PublicExponent.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentRsaPublicKey build(
      TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder) {
    return builder.build();
  }
}