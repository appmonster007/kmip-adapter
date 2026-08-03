package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.Issuer;

public class IssuerTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Issuer, Issuer.IssuerBuilder> {

  public IssuerTtlvDeserializer() {
    super(Issuer.kmipTag, Issuer.encodingType);
  }

  @Override
  protected Issuer.IssuerBuilder createBuilder() {
    return Issuer.builder();
  }

  @Override
  protected void setValue(Issuer.IssuerBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected Issuer build(Issuer.IssuerBuilder builder) {
    return builder.build();
  }
}
