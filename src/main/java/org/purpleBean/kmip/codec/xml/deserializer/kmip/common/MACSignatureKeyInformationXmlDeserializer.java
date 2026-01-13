package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationXmlDeserializer() {
        super(MACSignatureKeyInformation.kmipTag, MACSignatureKeyInformation.encodingType, String.class, value -> MACSignatureKeyInformation.builder().value(value).build());
    }
}