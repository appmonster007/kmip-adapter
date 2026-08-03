package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.OpaqueDataType;
import org.purplebean.kmip.model.core.structure.OpaqueObject;
import org.purplebean.kmip.model.core.type.OpaqueDataValue;

/**
 * XML deserializer for {@link OpaqueObject}.
 */
public class OpaqueObjectXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<OpaqueObject, OpaqueObject.OpaqueObjectBuilder> {

  /**
   * Constructs a new {@link OpaqueObjectXmlDeserializer}.
   */
  public OpaqueObjectXmlDeserializer() {
    super(OpaqueObject.kmipTag, OpaqueObject.encodingType);
  }

  @Override
  protected OpaqueObject.OpaqueObjectBuilder createBuilder() {
    return OpaqueObject.builder();
  }

  @Override
  protected void setValue(OpaqueObject.OpaqueObjectBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPAQUE_DATA_TYPE ->
          builder.opaqueDataType(ctxt.readValue(p, OpaqueDataType.class));
      case KmipTag.Standard.OPAQUE_DATA_VALUE ->
          builder.opaqueDataValue(ctxt.readValue(p, OpaqueDataValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected OpaqueObject build(OpaqueObject.OpaqueObjectBuilder builder) {
    return builder.build();
  }
}