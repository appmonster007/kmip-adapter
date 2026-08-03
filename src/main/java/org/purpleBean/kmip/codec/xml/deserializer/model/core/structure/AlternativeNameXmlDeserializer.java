package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;
import org.purplebean.kmip.model.core.structure.AlternativeName;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;

public class AlternativeNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AlternativeName, AlternativeName.AlternativeNameBuilder> {

  public AlternativeNameXmlDeserializer() {
    super(AlternativeName.kmipTag, AlternativeName.encodingType);
  }

  @Override
  protected AlternativeName.AlternativeNameBuilder createBuilder() {
    return AlternativeName.builder();
  }

  @Override
  protected void setValue(AlternativeName.AlternativeNameBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ALTERNATIVE_NAME_TYPE ->
          builder.alternativeNameType(ctxt.readValue(p, AlternativeNameType.class));
      case KmipTag.Standard.ALTERNATIVE_NAME_VALUE ->
          builder.alternativeNameValue(ctxt.readValue(p, AlternativeNameValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AlternativeName build(AlternativeName.AlternativeNameBuilder builder) {
    return builder.build();
  }
}