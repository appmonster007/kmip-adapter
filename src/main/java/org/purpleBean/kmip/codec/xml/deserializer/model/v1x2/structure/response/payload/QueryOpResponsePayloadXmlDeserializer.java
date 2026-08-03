package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.ExtensionInformation;
import org.purplebean.kmip.model.core.structure.ServerInformation;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.model.v1x2.structure.response.payload.QueryOpResponsePayload;

public class QueryOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<QueryOpResponsePayload,
        QueryOpResponsePayload.QueryOpResponsePayloadBuilder> {

  public QueryOpResponsePayloadXmlDeserializer() {
    super(QueryOpResponsePayload.kmipTag, QueryOpResponsePayload.encodingType);
  }

  @Override
  protected QueryOpResponsePayload.QueryOpResponsePayloadBuilder createBuilder() {
    return QueryOpResponsePayload.builder();
  }

  @Override
  protected void setValue(QueryOpResponsePayload.QueryOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPERATION -> builder.operation(ctxt.readValue(p, Operation.class));
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.VENDOR_IDENTIFICATION ->
          builder.vendorIdentification(ctxt.readValue(p, VendorIdentification.class));
      case KmipTag.Standard.SERVER_INFORMATION ->
          builder.serverInformation(ctxt.readValue(p, ServerInformation.class));
      case KmipTag.Standard.APPLICATION_NAMESPACE ->
          builder.applicationNamespace(ctxt.readValue(p, ApplicationNamespace.class));
      case KmipTag.Standard.EXTENSION_INFORMATION ->
          builder.extensionInformation(ctxt.readValue(p, ExtensionInformation.class));
      case KmipTag.Standard.ATTESTATION_TYPE ->
          builder.attestationType(ctxt.readValue(p, AttestationType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected QueryOpResponsePayload build(
      QueryOpResponsePayload.QueryOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
