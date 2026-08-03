package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.ExtensionInformation;
import org.purplebean.kmip.model.core.structure.ServerInformation;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.model.v1x2.structure.response.payload.QueryOpResponsePayload;

/**
 * TTLV deserializer for {@link QueryOpResponsePayload}.
 */
public class QueryOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<QueryOpResponsePayload,
        QueryOpResponsePayload.QueryOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link QueryOpResponsePayloadTtlvDeserializer}.
   */
  public QueryOpResponsePayloadTtlvDeserializer() {
    super(QueryOpResponsePayload.kmipTag, QueryOpResponsePayload.encodingType);
  }

  @Override
  protected QueryOpResponsePayload.QueryOpResponsePayloadBuilder createBuilder() {
    return QueryOpResponsePayload.builder();
  }

  @Override
  protected void setValue(QueryOpResponsePayload.QueryOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPERATION -> builder.operation(mapper.readValue(p, Operation.class));
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.VENDOR_IDENTIFICATION ->
          builder.vendorIdentification(mapper.readValue(p, VendorIdentification.class));
      case KmipTag.Standard.SERVER_INFORMATION ->
          builder.serverInformation(mapper.readValue(p, ServerInformation.class));
      case KmipTag.Standard.APPLICATION_NAMESPACE ->
          builder.applicationNamespace(mapper.readValue(p, ApplicationNamespace.class));
      case KmipTag.Standard.EXTENSION_INFORMATION ->
          builder.extensionInformation(mapper.readValue(p, ExtensionInformation.class));
      case KmipTag.Standard.ATTESTATION_TYPE ->
          builder.attestationType(mapper.readValue(p, AttestationType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected QueryOpResponsePayload build(
      QueryOpResponsePayload.QueryOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
