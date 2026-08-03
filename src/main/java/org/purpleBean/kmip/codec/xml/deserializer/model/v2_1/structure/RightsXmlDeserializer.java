package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.Right;
import org.purpleBean.kmip.model.v2_1.structure.Rights;

public class RightsXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Rights, Rights.RightsBuilder> {

  public RightsXmlDeserializer() {
    super(Rights.kmipTag, Rights.encodingType);
  }

  @Override
  protected Rights.RightsBuilder createBuilder() {
    return Rights.builder();
  }

  @Override
  protected void setValue(Rights.RightsBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RIGHT -> builder.right(ctxt.readValue(p, Right.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Rights build(Rights.RightsBuilder builder) {
    return builder.build();
  }
}