package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.Pkcs12FriendlyName;

public class Pkcs12FriendlyNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs12FriendlyName,
        Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder> {

  public Pkcs12FriendlyNameTtlvDeserializer() {
    super(Pkcs12FriendlyName.kmipTag, Pkcs12FriendlyName.encodingType);
  }

  @Override
  protected Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder createBuilder() {
    return Pkcs12FriendlyName.builder();
  }

  @Override
  protected void setValue(Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected Pkcs12FriendlyName build(Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder builder) {
    return builder.build();
  }
}