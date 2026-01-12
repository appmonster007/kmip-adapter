package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AttributeValueBoolean;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AttributeValueBooleanXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanXmlSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}