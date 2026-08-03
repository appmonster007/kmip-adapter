package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.Pkcs11Interface;

/**
 * TTLV deserializer for {@link Pkcs11Interface}.
 */
public class Pkcs11InterfaceTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs11Interface, Pkcs11Interface.Pkcs11InterfaceBuilder> {

  /**
   * Constructs a new {@link Pkcs11InterfaceTtlvDeserializer}.
   */
  public Pkcs11InterfaceTtlvDeserializer() {
    super(Pkcs11Interface.kmipTag, Pkcs11Interface.encodingType);
  }

  @Override
  protected Pkcs11Interface.Pkcs11InterfaceBuilder createBuilder() {
    return Pkcs11Interface.builder();
  }

  @Override
  protected void setValue(Pkcs11Interface.Pkcs11InterfaceBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected Pkcs11Interface build(Pkcs11Interface.Pkcs11InterfaceBuilder builder) {
    return builder.build();
  }
}