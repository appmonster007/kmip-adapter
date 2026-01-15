package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ObjectGroup, String> {

    public ObjectGroupTtlvSerializer() {
        super(ObjectGroup::getValue);
    }
}