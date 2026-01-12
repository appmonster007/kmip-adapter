package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupJsonSerializer extends AbstractKmipJsonSerializer<ObjectGroup, String> {

    public ObjectGroupJsonSerializer() {
        super(ObjectGroup::getValue);
    }
}