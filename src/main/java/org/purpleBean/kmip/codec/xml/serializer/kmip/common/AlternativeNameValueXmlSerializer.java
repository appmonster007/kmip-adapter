package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueXmlSerializer extends AbstractKmipXmlSerializer<AlternativeNameValue, String> {

    public AlternativeNameValueXmlSerializer() {
        super(AlternativeNameValue::getValue);
    }
}