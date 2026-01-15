package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MACSignatureKeyInformation;

public class MACSignatureKeyInformationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationXmlDeserializer() {
        super(MACSignatureKeyInformation.kmipTag, MACSignatureKeyInformation.encodingType, String.class, value -> MACSignatureKeyInformation.builder().value(value).build());
    }
}