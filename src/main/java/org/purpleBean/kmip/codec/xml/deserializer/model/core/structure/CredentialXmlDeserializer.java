package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.structure.Credential;

public class CredentialXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Credential, Credential.CredentialBuilder> {

  public CredentialXmlDeserializer() {
    super(Credential.kmipTag, Credential.encodingType);
  }

  @Override
  protected Credential.CredentialBuilder createBuilder() {
    return Credential.builder();
  }

  @Override
  protected void setValue(Credential.CredentialBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CREDENTIAL_TYPE -> {
        CredentialType credentialType = ctxt.readValue(p, CredentialType.class);
        builder.credentialType(credentialType);
        ctxt.setAttribute("credentialType", credentialType.getDescription());
      }
      case KmipTag.Standard.CREDENTIAL_VALUE ->
          builder.credentialValue(ctxt.readValue(p, CredentialValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Credential build(Credential.CredentialBuilder builder) {
    return builder.build();
  }
}