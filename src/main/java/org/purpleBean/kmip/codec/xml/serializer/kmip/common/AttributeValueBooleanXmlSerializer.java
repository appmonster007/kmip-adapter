package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanXmlSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}