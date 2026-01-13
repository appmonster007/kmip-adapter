package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

public class EncodingOptionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<EncodingOption, String> {

    public EncodingOptionXmlSerializer() {
        super(EncodingOption::getDescription);
    }
}