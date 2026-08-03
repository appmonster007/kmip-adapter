package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.UsernameAndPassword;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.core.type.Username;

public class UsernameAndPasswordXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<UsernameAndPassword,
        UsernameAndPassword.UsernameAndPasswordBuilder> {

  public UsernameAndPasswordXmlDeserializer() {
    super(UsernameAndPassword.kmipTag, UsernameAndPassword.encodingType);
  }

  @Override
  protected UsernameAndPassword.UsernameAndPasswordBuilder createBuilder() {
    return UsernameAndPassword.builder();
  }

  @Override
  protected void setValue(UsernameAndPassword.UsernameAndPasswordBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.USERNAME -> builder.username(ctxt.readValue(p, Username.class));
      case KmipTag.Standard.PASSWORD -> builder.password(ctxt.readValue(p, Password.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected UsernameAndPassword build(UsernameAndPassword.UsernameAndPasswordBuilder builder) {
    return builder.build();
  }
}