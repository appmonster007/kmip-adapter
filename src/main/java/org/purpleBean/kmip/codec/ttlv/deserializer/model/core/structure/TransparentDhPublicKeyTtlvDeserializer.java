package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TransparentDhPublicKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.J;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;

public class TransparentDhPublicKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentDhPublicKey,
        TransparentDhPublicKey.TransparentDhPublicKeyBuilder> {

  public TransparentDhPublicKeyTtlvDeserializer() {
    super(TransparentDhPublicKey.kmipTag, TransparentDhPublicKey.encodingType);
  }

  @Override
  protected TransparentDhPublicKey.TransparentDhPublicKeyBuilder createBuilder() {
    return TransparentDhPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentDhPublicKey.TransparentDhPublicKeyBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.P -> builder.p(mapper.readValue(p, P.class));
      case KmipTag.Standard.Q -> builder.q(mapper.readValue(p, Q.class));
      case KmipTag.Standard.G -> builder.g(mapper.readValue(p, G.class));
      case KmipTag.Standard.J -> builder.j(mapper.readValue(p, J.class));
      case KmipTag.Standard.Y -> builder.y(mapper.readValue(p, Y.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentDhPublicKey build(
      TransparentDhPublicKey.TransparentDhPublicKeyBuilder builder) {
    return builder.build();
  }
}