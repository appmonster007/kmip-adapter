package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationXmlSerializer extends AbstractKmipXmlSerializer<VendorIdentification, String> {

    public VendorIdentificationXmlSerializer() {
        super(VendorIdentification::getValue);
    }
}