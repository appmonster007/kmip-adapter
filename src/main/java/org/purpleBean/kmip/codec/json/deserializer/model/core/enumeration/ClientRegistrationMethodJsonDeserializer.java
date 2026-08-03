package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ClientRegistrationMethod,
        ClientRegistrationMethod.ClientRegistrationMethodBuilder> {

  public ClientRegistrationMethodJsonDeserializer() {
    super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType);
  }

  @Override
  protected ClientRegistrationMethod.ClientRegistrationMethodBuilder createBuilder() {
    return ClientRegistrationMethod.builder();
  }

  @Override
  protected void setValue(ClientRegistrationMethod.ClientRegistrationMethodBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ClientRegistrationMethod.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ClientRegistrationMethod build(
      ClientRegistrationMethod.ClientRegistrationMethodBuilder builder) {
    return builder.build();
  }
}
