package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.VendorIdentification;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class VendorIdentificationXmlSerializer extends AbstractKmipXmlSerializer<VendorIdentification, String> {

    public VendorIdentificationXmlSerializer() {
        super(VendorIdentification::getValue);
    }
}