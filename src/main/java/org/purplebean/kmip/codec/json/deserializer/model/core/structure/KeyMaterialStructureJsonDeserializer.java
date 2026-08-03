package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.KeyMaterialStructure;

/**
 * JSON deserializer for {@link KeyMaterialStructure}.
 */
public class KeyMaterialStructureJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyMaterialStructure,
        KeyMaterialStructure.KeyMaterialStructureBuilder> {

  /**
   * Constructs a new {@link KeyMaterialStructureJsonDeserializer}.
   */
  public KeyMaterialStructureJsonDeserializer() {
    super(KeyMaterialStructure.kmipTag, KeyMaterialStructure.encodingType);
  }

  @Override
  protected KeyMaterialStructure.KeyMaterialStructureBuilder createBuilder() {
    return KeyMaterialStructure.builder();
  }

  @Override
  protected void setValue(KeyMaterialStructure.KeyMaterialStructureBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, KmipDataType.class));
  }

  @Override
  protected KeyMaterialStructure build(KeyMaterialStructure.KeyMaterialStructureBuilder builder) {
    return builder.build();
  }
}
