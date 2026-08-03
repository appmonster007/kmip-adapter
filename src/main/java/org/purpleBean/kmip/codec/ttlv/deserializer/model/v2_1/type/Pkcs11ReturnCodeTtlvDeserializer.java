package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11ReturnCode;

public class Pkcs11ReturnCodeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs11ReturnCode,
        Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder> {

  public Pkcs11ReturnCodeTtlvDeserializer() {
    super(Pkcs11ReturnCode.kmipTag, Pkcs11ReturnCode.encodingType);
  }

  @Override
  protected Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder createBuilder() {
    return Pkcs11ReturnCode.builder();
  }

  @Override
  protected void setValue(Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected Pkcs11ReturnCode build(Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder builder) {
    return builder.build();
  }
}