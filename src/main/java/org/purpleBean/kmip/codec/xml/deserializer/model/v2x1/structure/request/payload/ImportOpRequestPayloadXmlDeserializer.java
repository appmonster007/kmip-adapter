package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.ImportOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.ReplaceExisting;

public class ImportOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ImportOpRequestPayload,
        ImportOpRequestPayload.ImportOpRequestPayloadBuilder> {

  public ImportOpRequestPayloadXmlDeserializer() {
    super(ImportOpRequestPayload.kmipTag, ImportOpRequestPayload.encodingType);
  }

  @Override
  protected ImportOpRequestPayload.ImportOpRequestPayloadBuilder createBuilder() {
    return ImportOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ImportOpRequestPayload.ImportOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.REPLACE_EXISTING ->
          builder.replaceExisting(ctxt.readValue(p, ReplaceExisting.class));
      case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION ->
          builder.keyWrappingSpecification(ctxt.readValue(p, KeyWrappingSpecification.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(
          ctxt.readValue(p, org.purpleBean.kmip.model.v2x1.structure.Attributes.class));
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
  protected ImportOpRequestPayload build(
      ImportOpRequestPayload.ImportOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}