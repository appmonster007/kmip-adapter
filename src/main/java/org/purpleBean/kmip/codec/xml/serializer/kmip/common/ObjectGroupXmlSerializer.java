package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.ObjectGroup;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ObjectGroupXmlSerializer extends AbstractKmipXmlSerializer<ObjectGroup, String> {

    public ObjectGroupXmlSerializer() {
        super(ObjectGroup::getValue);
    }
}