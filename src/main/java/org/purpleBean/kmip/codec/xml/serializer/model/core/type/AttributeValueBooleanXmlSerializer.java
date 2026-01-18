package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBoolean;

public class AttributeValueBooleanXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanXmlSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}