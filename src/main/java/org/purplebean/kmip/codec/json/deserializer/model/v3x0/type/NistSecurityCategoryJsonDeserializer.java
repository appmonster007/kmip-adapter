package org.purplebean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.type.NistSecurityCategory;

/**
 * JSON deserializer for {@link NistSecurityCategory}.
 */
public class NistSecurityCategoryJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<NistSecurityCategory,
        NistSecurityCategory.NistSecurityCategoryBuilder> {

  /**
   * Constructs a new {@link NistSecurityCategoryJsonDeserializer}.
   */
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