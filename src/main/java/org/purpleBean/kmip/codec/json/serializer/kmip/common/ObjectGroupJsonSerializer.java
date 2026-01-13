package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ObjectGroup, String> {

    public ObjectGroupJsonSerializer() {
        super(ObjectGroup::getValue);
    }
}