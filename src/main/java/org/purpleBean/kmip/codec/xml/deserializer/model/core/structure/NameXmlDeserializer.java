package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.model.core.type.NameValue;

public class NameXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Name, Name.NameBuilder> {

  public NameXmlDeserializer() {
    super(Name.kmipTag, Name.encodingType);
  }

  @Override
  protected Name.NameBuilder createBuilder() {
    return Name.builder();
  }

  @Override
  protected void setValue(Name.NameBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.NAME_VALUE -> builder.nameValue(ctxt.readValue(p, NameValue.class));
      case KmipTag.Standard.NAME_TYPE -> builder.nameType(ctxt.readValue(p, NameType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Name build(Name.NameBuilder builder) {
    return builder.build();
  }
}