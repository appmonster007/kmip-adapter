package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.TransparentDhPrivateKey;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.J;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.X;

/**
 * TTLV deserializer for {@link TransparentDhPrivateKey}.
 */
public class TransparentDhPrivateKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentDhPrivateKey,
        TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder> {

  /**
   * Constructs a new {@link TransparentDhPrivateKeyTtlvDeserializer}.
   */
  public TransparentDhPrivateKeyTtlvDeserializer() {
    super(TransparentDhPrivateKey.kmipTag, TransparentDhPrivateKey.encodingType);
  }

  @Override
  protected TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder createBuilder() {
    return TransparentDhPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.P -> builder.p(mapper.readValue(p, P.class));
      case KmipTag.Standard.Q -> builder.q(mapper.readValue(p, Q.class));
      case KmipTag.Standard.G -> builder.g(mapper.readValue(p, G.class));
      case KmipTag.Standard.J -> builder.j(mapper.readValue(p, J.class));
      case KmipTag.Standard.X -> builder.x(mapper.readValue(p, X.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentDhPrivateKey build(
      TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder) {
    return builder.build();
  }
}