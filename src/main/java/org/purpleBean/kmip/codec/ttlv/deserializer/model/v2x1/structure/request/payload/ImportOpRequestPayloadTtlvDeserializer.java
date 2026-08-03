package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ImportOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.ReplaceExisting;

public class ImportOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ImportOpRequestPayload,
        ImportOpRequestPayload.ImportOpRequestPayloadBuilder> {

  public ImportOpRequestPayloadTtlvDeserializer() {
    super(ImportOpRequestPayload.kmipTag, ImportOpRequestPayload.encodingType);
  }

  @Override
  protected ImportOpRequestPayload.ImportOpRequestPayloadBuilder createBuilder() {
    return ImportOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ImportOpRequestPayload.ImportOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.REPLACE_EXISTING ->
          builder.replaceExisting(mapper.readValue(p, ReplaceExisting.class));
      case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION ->
          builder.keyWrappingSpecification(mapper.readValue(p, KeyWrappingSpecification.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(
          mapper.readValue(p, org.purplebean.kmip.model.v2x1.structure.Attributes.class));
      default -> {
        if (ManagedObject.isManagedObject(nodeTag)) {
          builder.object(mapper.readValue(p, ManagedObject.class));
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