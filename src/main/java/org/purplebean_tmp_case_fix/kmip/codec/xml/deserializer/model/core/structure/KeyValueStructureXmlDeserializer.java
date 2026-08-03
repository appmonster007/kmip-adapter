package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KeyMaterial;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.KeyValueStructure;

public class KeyValueStructureXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<KeyValueStructure,
        KeyValueStructure.KeyValueStructureBuilder> {

  public KeyValueStructureXmlDeserializer() {
    super(KeyValueStructure.kmipTag, KeyValueStructure.encodingType);
  }

  @Override
  protected KeyValueStructure.KeyValueStructureBuilder createBuilder() {
    return KeyValueStructure.builder();
  }

  @Override
  protected void setValue(KeyValueStructure.KeyValueStructureBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_MATERIAL ->
          builder.keyMaterial(ctxt.readValue(p, KeyMaterial.class));
      default -> builder.attribute(ctxt.readValue(p, KmipAttribute.class));
    }
  }

  @Override
  protected KeyValueStructure build(KeyValueStructure.KeyValueStructureBuilder builder) {
    return builder.build();
  }
}