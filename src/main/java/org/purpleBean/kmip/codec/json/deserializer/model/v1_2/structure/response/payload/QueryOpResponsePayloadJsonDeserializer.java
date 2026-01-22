package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.ExtensionInformation;
import org.purpleBean.kmip.model.core.structure.ServerInformation;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;
import org.purpleBean.kmip.model.core.type.VendorIdentification;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.QueryOpResponsePayload;

import java.io.IOException;

public class QueryOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<QueryOpResponsePayload, QueryOpResponsePayload.QueryOpResponsePayloadBuilder> {

    public QueryOpResponsePayloadJsonDeserializer() {
        super(QueryOpResponsePayload.kmipTag, QueryOpResponsePayload.encodingType);
    }

    @Override
    protected QueryOpResponsePayload.QueryOpResponsePayloadBuilder createBuilder() {
        return QueryOpResponsePayload.builder();
    }

    @Override
    protected void setValue(QueryOpResponsePayload.QueryOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
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
            case KmipTag.Standard.ATTESTATION_TYPE -> builder.attestationType(ctxt.readValue(p, AttestationType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected QueryOpResponsePayload build(QueryOpResponsePayload.QueryOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
