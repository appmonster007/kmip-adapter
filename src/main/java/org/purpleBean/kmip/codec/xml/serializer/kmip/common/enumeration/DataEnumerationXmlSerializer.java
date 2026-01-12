package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationXmlSerializer extends AbstractKmipXmlSerializer<DataEnumeration, String> {

    public DataEnumerationXmlSerializer() {
        super(DataEnumeration::getDescription);
    }
}