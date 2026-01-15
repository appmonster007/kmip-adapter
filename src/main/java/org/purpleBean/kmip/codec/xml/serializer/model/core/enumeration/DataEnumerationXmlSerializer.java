package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

public class DataEnumerationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DataEnumeration, String> {

    public DataEnumerationXmlSerializer() {
        super(DataEnumeration::getDescription);
    }
}