package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;

import java.io.IOException;

public class ObjectGroupMemberJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectGroupMember, ObjectGroupMember.ObjectGroupMemberBuilder> {

    public ObjectGroupMemberJsonDeserializer() {
        super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType);
    }

    @Override
    protected ObjectGroupMember.ObjectGroupMemberBuilder createBuilder() {
        return ObjectGroupMember.builder();
    }

    @Override
    protected void setValue(ObjectGroupMember.ObjectGroupMemberBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ObjectGroupMember.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ObjectGroupMember build(ObjectGroupMember.ObjectGroupMemberBuilder builder) {
        return builder.build();
    }
}
