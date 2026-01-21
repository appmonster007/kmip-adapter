package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.MaximumItems;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.LocateOpRequestPayload;

import java.io.IOException;

public class LocateOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<LocateOpRequestPayload, LocateOpRequestPayload.LocateOpRequestPayloadBuilder> {

    public LocateOpRequestPayloadJsonDeserializer() {
        super(LocateOpRequestPayload.kmipTag, LocateOpRequestPayload.encodingType);
    }

    @Override
    protected LocateOpRequestPayload.LocateOpRequestPayloadBuilder createBuilder() {
        return LocateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MAXIMUM_ITEMS -> builder.maximumItems(ctxt.readValue(p, MaximumItems.class));
            case KmipTag.Standard.STORAGE_STATUS_MASK ->
                    builder.storageStatusMask(ctxt.readValue(p, StorageStatusMask.class));
            case KmipTag.Standard.OBJECT_GROUP_MEMBER ->
                    builder.objectGroupMember(ctxt.readValue(p, ObjectGroupMember.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected LocateOpRequestPayload build(LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
