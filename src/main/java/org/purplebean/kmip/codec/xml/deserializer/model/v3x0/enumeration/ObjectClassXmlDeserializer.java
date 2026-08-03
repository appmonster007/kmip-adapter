package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.enumeration.ObjectClass;

/**
 * XML deserializer for {@link ObjectClass}.
 */
public class ObjectClassXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ObjectClass, ObjectClass.ObjectClassBuilder> {

  /**
   * Constructs a new {@link ObjectClassXmlDeserializer}.
   */
  public ObjectClassXmlDeserializer() {
    super(ObjectClass.kmipTag, ObjectClass.encodingType);
  }

  @Override
  protected ObjectClass.ObjectClassBuilder createBuilder() {
    return ObjectClass.builder();
  }

  @Override
  protected void setValue(ObjectClass.ObjectClassBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ObjectClass.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ObjectClass build(ObjectClass.ObjectClassBuilder builder) {
    return builder.build();
  }
}