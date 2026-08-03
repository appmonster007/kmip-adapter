package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3_0.type.NistSecurityCategory;

public class NistSecurityCategoryJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<NistSecurityCategory,
        NistSecurityCategory.NistSecurityCategoryBuilder> {

  public NistSecurityCategoryJsonDeserializer() {
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