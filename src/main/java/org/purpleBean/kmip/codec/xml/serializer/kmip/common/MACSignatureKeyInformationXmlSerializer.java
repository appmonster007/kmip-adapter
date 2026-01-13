package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationXmlSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}