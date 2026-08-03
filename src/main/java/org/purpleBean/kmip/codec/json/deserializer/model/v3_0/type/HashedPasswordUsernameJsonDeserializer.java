package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3_0.type.HashedPasswordUsername;

public class HashedPasswordUsernameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<HashedPasswordUsername,
        HashedPasswordUsername.HashedPasswordUsernameBuilder> {

  public HashedPasswordUsernameJsonDeserializer() {
    super(HashedPasswordUsername.kmipTag, HashedPasswordUsername.encodingType);
  }

  @Override
  protected HashedPasswordUsername.HashedPasswordUsernameBuilder createBuilder() {
    return HashedPasswordUsername.builder();
  }

  @Override
  protected void setValue(HashedPasswordUsername.HashedPasswordUsernameBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected HashedPasswordUsername build(
      HashedPasswordUsername.HashedPasswordUsernameBuilder builder) {
    return builder.build();
  }
}
