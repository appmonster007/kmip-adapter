package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.Authentication;
import org.purplebean.kmip.model.core.structure.Credential;

public class AuthenticationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Authentication, Authentication.AuthenticationBuilder> {

  public AuthenticationXmlDeserializer() {
    super(Authentication.kmipTag, Authentication.encodingType);
  }

  @Override
  protected Authentication.AuthenticationBuilder createBuilder() {
    return Authentication.builder();
  }

  @Override
  protected void setValue(Authentication.AuthenticationBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CREDENTIAL -> builder.credential(ctxt.readValue(p, Credential.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Authentication build(Authentication.AuthenticationBuilder builder) {
    return builder.build();
  }
}