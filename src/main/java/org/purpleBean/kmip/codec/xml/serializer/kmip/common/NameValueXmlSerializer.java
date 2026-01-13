package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NameValue, String> {

    public NameValueXmlSerializer() {
        super(NameValue::getValue);
    }
}
