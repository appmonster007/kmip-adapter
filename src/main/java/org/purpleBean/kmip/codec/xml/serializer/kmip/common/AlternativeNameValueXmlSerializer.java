package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AlternativeNameValueXmlSerializer extends AbstractKmipXmlSerializer<AlternativeNameValue, String> {

    public AlternativeNameValueXmlSerializer() {
        super(AlternativeNameValue::getValue);
    }
}