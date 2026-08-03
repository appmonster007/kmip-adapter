package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AlternativeNameType,
        AlternativeNameType.AlternativeNameTypeBuilder> {

  public AlternativeNameTypeXmlDeserializer() {
    super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType);
  }


  @Override
  protected AlternativeNameType.AlternativeNameTypeBuilder createBuilder() {
    return AlternativeNameType.builder();
  }

  @Override
  protected void setValue(AlternativeNameType.AlternativeNameTypeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(AlternativeNameType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected AlternativeNameType build(AlternativeNameType.AlternativeNameTypeBuilder builder) {
    return builder.build();
  }
}