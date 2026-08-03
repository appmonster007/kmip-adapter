package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.type.SaltedPassword;

public class SaltedPasswordXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SaltedPassword, SaltedPassword.SaltedPasswordBuilder> {

  public SaltedPasswordXmlDeserializer() {
    super(SaltedPassword.kmipTag, SaltedPassword.encodingType);
  }

  @Override
  protected SaltedPassword.SaltedPasswordBuilder createBuilder() {
    return SaltedPassword.builder();
  }

  @Override
  protected void setValue(SaltedPassword.SaltedPasswordBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected SaltedPassword build(SaltedPassword.SaltedPasswordBuilder builder) {
    return builder.build();
  }
}