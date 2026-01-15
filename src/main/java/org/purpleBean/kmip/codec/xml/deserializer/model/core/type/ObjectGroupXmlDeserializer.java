package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectGroup, String> {

    public ObjectGroupXmlDeserializer() {
        super(ObjectGroup.kmipTag, ObjectGroup.encodingType, String.class, value -> ObjectGroup.builder().value(value).build());
    }
}