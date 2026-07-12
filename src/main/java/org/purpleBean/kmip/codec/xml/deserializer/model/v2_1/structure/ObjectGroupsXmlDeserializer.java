package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.ObjectGroups;

import java.io.IOException;

public class ObjectGroupsXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectGroups, ObjectGroups.ObjectGroupsBuilder> {

    public ObjectGroupsXmlDeserializer() {
        super(ObjectGroups.kmipTag, ObjectGroups.encodingType);
    }

    @Override
    protected ObjectGroups.ObjectGroupsBuilder createBuilder() {
        return ObjectGroups.builder();
    }

    @Override
    protected void setValue(ObjectGroups.ObjectGroupsBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_GROUP -> builder.objectGroup(ctxt.readValue(p, ObjectGroup.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ObjectGroups build(ObjectGroups.ObjectGroupsBuilder builder) {
        return builder.build();
    }
}