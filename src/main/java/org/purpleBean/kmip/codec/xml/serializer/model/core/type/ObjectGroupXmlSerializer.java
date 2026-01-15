package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ObjectGroup, String> {

    public ObjectGroupXmlSerializer() {
        super(ObjectGroup::getValue);
    }
}