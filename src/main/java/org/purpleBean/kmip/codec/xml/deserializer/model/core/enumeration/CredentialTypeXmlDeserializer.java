package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

public class CredentialTypeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CredentialType, CredentialType.CredentialTypeBuilder> {

  public CredentialTypeXmlDeserializer() {
    super(CredentialType.kmipTag, CredentialType.encodingType);
  }

  @Override
  protected CredentialType.CredentialTypeBuilder createBuilder() {
    return CredentialType.builder();
  }

  @Override
  protected void setValue(CredentialType.CredentialTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(CredentialType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected CredentialType build(CredentialType.CredentialTypeBuilder builder) {
    return builder.build();
  }
}