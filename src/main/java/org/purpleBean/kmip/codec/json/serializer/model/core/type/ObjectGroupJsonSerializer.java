package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ObjectGroup, String> {

    public ObjectGroupJsonSerializer() {
        super(ObjectGroup::getValue);
    }
}