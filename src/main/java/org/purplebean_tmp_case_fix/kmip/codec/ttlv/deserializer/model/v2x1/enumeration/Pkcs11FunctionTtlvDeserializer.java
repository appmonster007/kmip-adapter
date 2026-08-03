package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;

public class Pkcs11FunctionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs11Function, Pkcs11Function.Pkcs11FunctionBuilder> {

  public Pkcs11FunctionTtlvDeserializer() {
    super(Pkcs11Function.kmipTag, Pkcs11Function.encodingType);
  }

  @Override
  protected Pkcs11Function.Pkcs11FunctionBuilder createBuilder() {
    return Pkcs11Function.builder();
  }

  @Override
  protected void setValue(Pkcs11Function.Pkcs11FunctionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(Pkcs11Function.fromValue(mapper.readValue(p, Integer.class)));
  }

  @Override
  protected Pkcs11Function build(Pkcs11Function.Pkcs11FunctionBuilder builder) {
    return builder.build();
  }
}