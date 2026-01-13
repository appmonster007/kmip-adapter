package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DataEnumeration, String> {

    public DataEnumerationXmlDeserializer() {
        super(DataEnumeration.kmipTag, DataEnumeration.encodingType, String.class, value -> new DataEnumeration(DataEnumeration.fromName(value)));
    }
}