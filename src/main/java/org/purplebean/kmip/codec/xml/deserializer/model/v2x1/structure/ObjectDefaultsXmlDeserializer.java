package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;
import org.purplebean.kmip.model.v2x1.structure.ObjectTypes;

/**
 * XML deserializer for {@link ObjectDefaults}.
 */
public class ObjectDefaultsXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ObjectDefaults, ObjectDefaults.ObjectDefaultsBuilder> {

  /**
   * Constructs a new {@link ObjectDefaultsXmlDeserializer}.
   */
  public ObjectDefaultsXmlDeserializer() {
    super(ObjectDefaults.kmipTag, ObjectDefaults.encodingType);
  }

  @Override
  protected ObjectDefaults.ObjectDefaultsBuilder createBuilder() {
    return ObjectDefaults.builder();
  }

  @Override
  protected void setValue(ObjectDefaults.ObjectDefaultsBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPES ->
          builder.objectTypes(ctxt.readValue(p, ObjectTypes.class));
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      case KmipTag.Standard.OBJECT_GROUPS ->
          builder.objectGroups(ctxt.readValue(p, ObjectGroups.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObjectDefaults build(ObjectDefaults.ObjectDefaultsBuilder builder) {
    return builder.build();
  }
}