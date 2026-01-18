package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeName;

public class AttributeNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeName, String> {

    public AttributeNameXmlSerializer() {
        super(AttributeName::getValue);
    }
}