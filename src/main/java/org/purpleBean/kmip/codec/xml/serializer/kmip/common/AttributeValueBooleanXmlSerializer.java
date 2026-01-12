package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanXmlSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}