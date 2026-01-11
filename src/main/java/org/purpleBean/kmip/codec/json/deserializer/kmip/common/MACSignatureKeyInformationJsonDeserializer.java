package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationJsonDeserializer extends AbstractKmipJsonDeserializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationJsonDeserializer() {
        super(MACSignatureKeyInformation.kmipTag, MACSignatureKeyInformation.encodingType, String.class, value -> MACSignatureKeyInformation.builder().value(value).build());
    }
}