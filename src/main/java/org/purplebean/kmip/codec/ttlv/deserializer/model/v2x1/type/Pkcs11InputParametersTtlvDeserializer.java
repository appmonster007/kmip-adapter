package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.Pkcs11InputParameters;

/**
 * TTLV deserializer for {@link Pkcs11InputParameters}.
 */
public class Pkcs11InputParametersTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs11InputParameters,
        Pkcs11InputParameters.Pkcs11InputParametersBuilder> {

  /**
   * Constructs a new {@link Pkcs11InputParametersTtlvDeserializer}.
   */
  public Pkcs11InputParametersTtlvDeserializer() {
    super(Pkcs11InputParameters.kmipTag, Pkcs11InputParameters.encodingType);
  }

  @Override
  protected Pkcs11InputParameters.Pkcs11InputParametersBuilder createBuilder() {
    return Pkcs11InputParameters.builder();
  }

  @Override
  protected void setValue(Pkcs11InputParameters.Pkcs11InputParametersBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected Pkcs11InputParameters build(
      Pkcs11InputParameters.Pkcs11InputParametersBuilder builder) {
    return builder.build();
  }
}