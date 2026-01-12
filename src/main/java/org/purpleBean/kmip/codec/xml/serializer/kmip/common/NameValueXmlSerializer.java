package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueXmlSerializer extends AbstractKmipXmlSerializer<NameValue, String> {

    public NameValueXmlSerializer() {
        super(NameValue::getValue);
    }
}
