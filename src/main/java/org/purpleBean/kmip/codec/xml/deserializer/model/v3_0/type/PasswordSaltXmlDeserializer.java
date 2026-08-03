package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.type.PasswordSalt;

public class PasswordSaltXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<PasswordSalt, PasswordSalt.PasswordSaltBuilder> {

  public PasswordSaltXmlDeserializer() {
    super(PasswordSalt.kmipTag, PasswordSalt.encodingType);
  }

  @Override
  protected PasswordSalt.PasswordSaltBuilder createBuilder() {
    return PasswordSalt.builder();
  }

  @Override
  protected void setValue(PasswordSalt.PasswordSaltBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected PasswordSalt build(PasswordSalt.PasswordSaltBuilder builder) {
    return builder.build();
  }
}