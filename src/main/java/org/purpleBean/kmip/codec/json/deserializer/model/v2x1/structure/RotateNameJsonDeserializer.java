package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.RotateNameType;
import org.purplebean.kmip.model.v2x1.structure.RotateName;
import org.purplebean.kmip.model.v2x1.type.RotateNameValue;

public class RotateNameJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<RotateName, RotateName.RotateNameBuilder> {

  public RotateNameJsonDeserializer() {
    super(RotateName.kmipTag, RotateName.encodingType);
  }

  @Override
  protected RotateName.RotateNameBuilder createBuilder() {
    return RotateName.builder();
  }

  @Override
  protected void setValue(RotateName.RotateNameBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ROTATE_NAME_VALUE ->
          builder.rotateNameValue(ctxt.readValue(p, RotateNameValue.class));
      case KmipTag.Standard.ROTATE_NAME_TYPE ->
          builder.rotateNameType(ctxt.readValue(p, RotateNameType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RotateName build(RotateName.RotateNameBuilder builder) {
    return builder.build();
  }
}