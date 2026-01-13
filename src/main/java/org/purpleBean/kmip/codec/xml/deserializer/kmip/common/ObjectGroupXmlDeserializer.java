package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectGroup, String> {

    public ObjectGroupXmlDeserializer() {
        super(ObjectGroup.kmipTag, ObjectGroup.encodingType, String.class, value -> ObjectGroup.builder().value(value).build());
    }
}