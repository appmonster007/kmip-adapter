package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ClientRegistrationMethod,
        ClientRegistrationMethod.ClientRegistrationMethodBuilder> {

  public ClientRegistrationMethodTtlvDeserializer() {
    super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType);
  }

  @Override
  protected ClientRegistrationMethod.ClientRegistrationMethodBuilder createBuilder() {
    return ClientRegistrationMethod.builder();
  }

  @Override
  protected void setValue(ClientRegistrationMethod.ClientRegistrationMethodBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ClientRegistrationMethod.fromValue(value));
  }

  @Override
  protected ClientRegistrationMethod build(
      ClientRegistrationMethod.ClientRegistrationMethodBuilder builder) {
    return builder.build();
  }
}
