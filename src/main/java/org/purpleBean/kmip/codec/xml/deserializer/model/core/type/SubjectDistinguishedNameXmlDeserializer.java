package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

public class SubjectDistinguishedNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SubjectDistinguishedName,
        SubjectDistinguishedName.SubjectDistinguishedNameBuilder> {

  public SubjectDistinguishedNameXmlDeserializer() {
    super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType);
  }

  @Override
  protected SubjectDistinguishedName.SubjectDistinguishedNameBuilder createBuilder() {
    return SubjectDistinguishedName.builder();
  }

  @Override
  protected void setValue(SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected SubjectDistinguishedName build(
      SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder) {
    return builder.build();
  }
}