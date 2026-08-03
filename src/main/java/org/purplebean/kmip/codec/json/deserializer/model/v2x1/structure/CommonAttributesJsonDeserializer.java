package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.CommonAttributes;

public class CommonAttributesJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CommonAttributes,
        CommonAttributes.CommonAttributesBuilder> {

  public CommonAttributesJsonDeserializer() {
    super(CommonAttributes.kmipTag, CommonAttributes.encodingType);
  }

  @Override
  protected CommonAttributes.CommonAttributesBuilder createBuilder() {
    return CommonAttributes.builder();
  }

  @Override
  protected void setValue(CommonAttributes.CommonAttributesBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.attribute(ctxt.readValue(p, KmipAttribute.class));
  }

  @Override
  protected CommonAttributes build(CommonAttributes.CommonAttributesBuilder builder) {
    return builder.build();
  }
}