package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.NameValue;

public class NameValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NameValue, String> {

    public NameValueXmlSerializer() {
        super(NameValue::getValue);
    }
}
