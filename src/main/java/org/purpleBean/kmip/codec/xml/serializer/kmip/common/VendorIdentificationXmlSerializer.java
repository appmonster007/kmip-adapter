package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<VendorIdentification, String> {

    public VendorIdentificationXmlSerializer() {
        super(VendorIdentification::getValue);
    }
}