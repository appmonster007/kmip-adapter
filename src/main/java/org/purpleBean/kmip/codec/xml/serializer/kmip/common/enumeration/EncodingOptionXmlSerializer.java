package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.EncodingOption;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class EncodingOptionXmlSerializer extends AbstractKmipXmlSerializer<EncodingOption, String> {

    public EncodingOptionXmlSerializer() {
        super(EncodingOption::getDescription);
    }
}