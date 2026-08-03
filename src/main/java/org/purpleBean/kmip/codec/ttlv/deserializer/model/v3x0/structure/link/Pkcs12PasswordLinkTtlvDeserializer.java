package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.Pkcs12PasswordLink;

public class Pkcs12PasswordLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs12PasswordLink,
        Pkcs12PasswordLink.Pkcs12PasswordLinkBuilder> {

  public Pkcs12PasswordLinkTtlvDeserializer() {
    super(Pkcs12PasswordLink.kmipTag, Pkcs12PasswordLink.encodingType);
  }

  @Override
  protected Pkcs12PasswordLink.Pkcs12PasswordLinkBuilder createBuilder() {
    return Pkcs12PasswordLink.builder();
  }

  @Override
  protected void setValue(Pkcs12PasswordLink.Pkcs12PasswordLinkBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs12PasswordLink build(Pkcs12PasswordLink.Pkcs12PasswordLinkBuilder builder) {
    return builder.build();
  }
}
