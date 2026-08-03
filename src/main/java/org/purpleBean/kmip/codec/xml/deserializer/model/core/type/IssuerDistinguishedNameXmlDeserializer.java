package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

public class IssuerDistinguishedNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<IssuerDistinguishedName,
        IssuerDistinguishedName.IssuerDistinguishedNameBuilder> {

  public IssuerDistinguishedNameXmlDeserializer() {
    super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType);
  }

  @Override
  protected IssuerDistinguishedName.IssuerDistinguishedNameBuilder createBuilder() {
    return IssuerDistinguishedName.builder();
  }

  @Override
  protected void setValue(IssuerDistinguishedName.IssuerDistinguishedNameBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected IssuerDistinguishedName build(
      IssuerDistinguishedName.IssuerDistinguishedNameBuilder builder) {
    return builder.build();
  }
}