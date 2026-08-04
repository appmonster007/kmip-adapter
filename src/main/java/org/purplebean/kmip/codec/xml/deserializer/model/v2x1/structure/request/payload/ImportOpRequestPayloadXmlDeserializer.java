package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.KeyWrapType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ImportOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.ReplaceExisting;

/**
 * XML deserializer for {@link ImportOpRequestPayload}.
 */
public class ImportOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ImportOpRequestPayload,
        ImportOpRequestPayload.ImportOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ImportOpRequestPayloadXmlDeserializer}.
   */
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
      case KmipTag.Standard.KEY_WRAP_TYPE ->
          builder.keyWrapType(ctxt.readValue(p, KeyWrapType.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(
          ctxt.readValue(p, org.purplebean.kmip.model.v2x1.structure.Attributes.class));
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