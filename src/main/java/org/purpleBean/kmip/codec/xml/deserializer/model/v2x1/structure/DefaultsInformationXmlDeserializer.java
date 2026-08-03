package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2x1.structure.ObjectDefaults;

public class DefaultsInformationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DefaultsInformation,
        DefaultsInformation.DefaultsInformationBuilder> {

  public DefaultsInformationXmlDeserializer() {
    super(DefaultsInformation.kmipTag, DefaultsInformation.encodingType);
  }

  @Override
  protected DefaultsInformation.DefaultsInformationBuilder createBuilder() {
    return DefaultsInformation.builder();
  }

  @Override
  protected void setValue(DefaultsInformation.DefaultsInformationBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_DEFAULTS ->
          builder.objectDefault(ctxt.readValue(p, ObjectDefaults.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DefaultsInformation build(DefaultsInformation.DefaultsInformationBuilder builder) {
    return builder.build();
  }
}