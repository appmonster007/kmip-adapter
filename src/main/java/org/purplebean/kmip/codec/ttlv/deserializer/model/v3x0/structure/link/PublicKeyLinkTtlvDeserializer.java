package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.structure.link.PublicKeyLink;

/**
 * TTLV deserializer for {@link PublicKeyLink}.
 */
public class PublicKeyLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PublicKeyLink, PublicKeyLink.PublicKeyLinkBuilder> {

  /**
   * Constructs a new {@link PublicKeyLinkTtlvDeserializer}.
   */
  public PublicKeyLinkTtlvDeserializer() {
    super(PublicKeyLink.kmipTag, PublicKeyLink.encodingType);
  }

  @Override
  protected PublicKeyLink.PublicKeyLinkBuilder createBuilder() {
    return PublicKeyLink.builder();
  }

  @Override
  protected void setValue(PublicKeyLink.PublicKeyLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected PublicKeyLink build(PublicKeyLink.PublicKeyLinkBuilder builder) {
    return builder.build();
  }
}
