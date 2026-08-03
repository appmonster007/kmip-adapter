package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.Pkcs12PasswordLink;

public class Pkcs12PasswordLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs12PasswordLink,
        Pkcs12PasswordLink.Pkcs12PasswordLinkBuilder> {

  public Pkcs12PasswordLinkXmlDeserializer() {
    super(Pkcs12PasswordLink.kmipTag, Pkcs12PasswordLink.encodingType);
  }

  @Override
  protected Pkcs12PasswordLink.Pkcs12PasswordLinkBuilder createBuilder() {
    return Pkcs12PasswordLink.builder();
  }

  @Override
  protected void setValue(Pkcs12PasswordLink.Pkcs12PasswordLinkBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs12PasswordLink build(Pkcs12PasswordLink.Pkcs12PasswordLinkBuilder builder) {
    return builder.build();
  }
}
