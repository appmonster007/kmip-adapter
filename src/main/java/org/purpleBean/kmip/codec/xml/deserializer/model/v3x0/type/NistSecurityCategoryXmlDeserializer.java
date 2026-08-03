package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3x0.type.NistSecurityCategory;

public class NistSecurityCategoryXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<NistSecurityCategory,
        NistSecurityCategory.NistSecurityCategoryBuilder> {

  public NistSecurityCategoryXmlDeserializer() {
    super(NistSecurityCategory.kmipTag, NistSecurityCategory.encodingType);
  }

  @Override
  protected NistSecurityCategory.NistSecurityCategoryBuilder createBuilder() {
    return NistSecurityCategory.builder();
  }

  @Override
  protected void setValue(NistSecurityCategory.NistSecurityCategoryBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected NistSecurityCategory build(NistSecurityCategory.NistSecurityCategoryBuilder builder) {
    return builder.build();
  }
}