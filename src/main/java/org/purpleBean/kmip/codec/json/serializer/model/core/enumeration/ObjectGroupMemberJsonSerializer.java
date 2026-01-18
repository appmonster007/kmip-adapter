package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;

public class ObjectGroupMemberJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ObjectGroupMember, String> {

    public ObjectGroupMemberJsonSerializer() {
        super(ObjectGroupMember::getDescription);
    }
}