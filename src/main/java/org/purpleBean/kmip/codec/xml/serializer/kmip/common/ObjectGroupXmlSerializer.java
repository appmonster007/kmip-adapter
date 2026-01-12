package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupXmlSerializer extends AbstractKmipXmlSerializer<ObjectGroup, String> {

    public ObjectGroupXmlSerializer() {
        super(ObjectGroup::getValue);
    }
}