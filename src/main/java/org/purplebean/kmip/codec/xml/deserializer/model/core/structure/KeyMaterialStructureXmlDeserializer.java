package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.KeyMaterialStructure;

/**
 * XML deserializer for {@link KeyMaterialStructure}.
 */
public class KeyMaterialStructureXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<KeyMaterialStructure,
        KeyMaterialStructure.KeyMaterialStructureBuilder> {

  /**
   * Constructs a new {@link KeyMaterialStructureXmlDeserializer}.
   */
  public KeyMaterialStructureXmlDeserializer() {
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