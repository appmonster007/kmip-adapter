package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.type.ServerHashedPassword;

public class ServerHashedPasswordXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ServerHashedPassword,
        ServerHashedPassword.ServerHashedPasswordBuilder> {

  public ServerHashedPasswordXmlDeserializer() {
    super(ServerHashedPassword.kmipTag, ServerHashedPassword.encodingType);
  }

  @Override
  protected ServerHashedPassword.ServerHashedPasswordBuilder createBuilder() {
    return ServerHashedPassword.builder();
  }

  @Override
  protected void setValue(ServerHashedPassword.ServerHashedPasswordBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected ServerHashedPassword build(ServerHashedPassword.ServerHashedPasswordBuilder builder) {
    return builder.build();
  }
}