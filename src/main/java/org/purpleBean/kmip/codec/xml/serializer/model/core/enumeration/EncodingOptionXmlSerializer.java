package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<EncodingOption, String> {

    public EncodingOptionXmlSerializer() {
        super(EncodingOption::getDescription);
    }
}