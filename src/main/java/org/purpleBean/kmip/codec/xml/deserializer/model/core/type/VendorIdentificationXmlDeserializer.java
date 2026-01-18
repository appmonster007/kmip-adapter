package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

public class VendorIdentificationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<VendorIdentification, String> {

    public VendorIdentificationXmlDeserializer() {
        super(VendorIdentification.kmipTag, VendorIdentification.encodingType, String.class, value -> VendorIdentification.builder().value(value).build());
    }
}