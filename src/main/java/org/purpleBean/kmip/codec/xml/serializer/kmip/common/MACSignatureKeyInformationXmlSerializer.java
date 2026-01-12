package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationXmlSerializer extends AbstractKmipXmlSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationXmlSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}