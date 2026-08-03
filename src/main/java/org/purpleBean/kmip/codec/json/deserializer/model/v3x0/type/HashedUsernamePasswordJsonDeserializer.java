package org.purpleBean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3x0.type.HashedUsernamePassword;

public class HashedUsernamePasswordJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<HashedUsernamePassword,
        HashedUsernamePassword.HashedUsernamePasswordBuilder> {

  public HashedUsernamePasswordJsonDeserializer() {
    super(HashedUsernamePassword.kmipTag, HashedUsernamePassword.encodingType);
  }

  @Override
  protected HashedUsernamePassword.HashedUsernamePasswordBuilder createBuilder() {
    return HashedUsernamePassword.builder();
  }

  @Override
  protected void setValue(HashedUsernamePassword.HashedUsernamePasswordBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected HashedUsernamePassword build(
      HashedUsernamePassword.HashedUsernamePasswordBuilder builder) {
    return builder.build();
  }
}
