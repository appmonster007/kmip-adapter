package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;

public class SubjectAlternativeNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SubjectAlternativeName,
        SubjectAlternativeName.SubjectAlternativeNameBuilder> {

  public SubjectAlternativeNameXmlDeserializer() {
    super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType);
  }

  @Override
  protected SubjectAlternativeName.SubjectAlternativeNameBuilder createBuilder() {
    return SubjectAlternativeName.builder();
  }

  @Override
  protected void setValue(SubjectAlternativeName.SubjectAlternativeNameBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected SubjectAlternativeName build(
      SubjectAlternativeName.SubjectAlternativeNameBuilder builder) {
    return builder.build();
  }
}