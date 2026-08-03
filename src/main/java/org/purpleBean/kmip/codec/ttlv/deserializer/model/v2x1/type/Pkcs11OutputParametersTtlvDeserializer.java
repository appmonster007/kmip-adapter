package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;

public class Pkcs11OutputParametersTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs11OutputParameters,
        Pkcs11OutputParameters.Pkcs11OutputParametersBuilder> {

  public Pkcs11OutputParametersTtlvDeserializer() {
    super(Pkcs11OutputParameters.kmipTag, Pkcs11OutputParameters.encodingType);
  }

  @Override
  protected Pkcs11OutputParameters.Pkcs11OutputParametersBuilder createBuilder() {
    return Pkcs11OutputParameters.builder();
  }

  @Override
  protected void setValue(Pkcs11OutputParameters.Pkcs11OutputParametersBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected Pkcs11OutputParameters build(
      Pkcs11OutputParameters.Pkcs11OutputParametersBuilder builder) {
    return builder.build();
  }
}