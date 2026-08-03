package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.G;

public class GTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<G, G.GBuilder> {

  public GTtlvDeserializer() {
    super(G.kmipTag, G.encodingType);
  }

  @Override
  protected G.GBuilder createBuilder() {
    return G.builder();
  }

  @Override
  protected void setValue(G.GBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected G build(G.GBuilder builder) {
    return builder.build();
  }
}
