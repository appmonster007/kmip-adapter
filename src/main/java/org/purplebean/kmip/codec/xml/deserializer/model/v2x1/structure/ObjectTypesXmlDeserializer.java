package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.ObjectTypes;

/**
 * XML deserializer for {@link ObjectTypes}.
 */
public class ObjectTypesXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ObjectTypes, ObjectTypes.ObjectTypesBuilder> {

  /**
   * Constructs a new {@link ObjectTypesXmlDeserializer}.
   */
  public ObjectTypesXmlDeserializer() {
    super(ObjectTypes.kmipTag, ObjectTypes.encodingType);
  }

  @Override
  protected ObjectTypes.ObjectTypesBuilder createBuilder() {
    return ObjectTypes.builder();
  }

  @Override
  protected void setValue(ObjectTypes.ObjectTypesBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObjectTypes build(ObjectTypes.ObjectTypesBuilder builder) {
    return builder.build();
  }
}