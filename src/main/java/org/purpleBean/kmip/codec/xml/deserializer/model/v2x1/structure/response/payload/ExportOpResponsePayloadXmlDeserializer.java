package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.ExportOpResponsePayload;

public class ExportOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ExportOpResponsePayload,
        ExportOpResponsePayload.ExportOpResponsePayloadBuilder> {

  public ExportOpResponsePayloadXmlDeserializer() {
    super(ExportOpResponsePayload.kmipTag, ExportOpResponsePayload.encodingType);
  }

  @Override
  protected ExportOpResponsePayload.ExportOpResponsePayloadBuilder createBuilder() {
    return ExportOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ExportOpResponsePayload.ExportOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      default -> {
        if (ManagedObject.isManagedObject(nodeTag)) {
          builder.object(ctxt.readValue(p, ManagedObject.class));
        } else {
          throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
      }
    }
  }

  @Override
  protected ExportOpResponsePayload build(
      ExportOpResponsePayload.ExportOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}