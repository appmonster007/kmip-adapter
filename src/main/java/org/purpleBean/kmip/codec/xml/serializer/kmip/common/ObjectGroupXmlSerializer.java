package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ObjectGroup, String> {

    public ObjectGroupXmlSerializer() {
        super(ObjectGroup::getValue);
    }
}