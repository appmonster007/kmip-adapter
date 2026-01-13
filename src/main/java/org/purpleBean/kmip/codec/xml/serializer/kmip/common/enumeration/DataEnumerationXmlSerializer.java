package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DataEnumeration, String> {

    public DataEnumerationXmlSerializer() {
        super(DataEnumeration::getDescription);
    }
}