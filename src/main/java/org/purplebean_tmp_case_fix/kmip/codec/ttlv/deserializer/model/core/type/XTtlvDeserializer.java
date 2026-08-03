package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.X;

public class XTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<X, X.XBuilder> {

  public XTtlvDeserializer() {
    super(X.kmipTag, X.encodingType);
  }

  @Override
  protected X.XBuilder createBuilder() {
    return X.builder();
  }

  @Override
  protected void setValue(X.XBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, BigInteger.class));
  }

  @Override
  protected X build(X.XBuilder builder) {
    return builder.build();
  }
}