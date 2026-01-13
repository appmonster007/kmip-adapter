package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationJsonDeserializer() {
        super(MACSignatureKeyInformation.kmipTag, MACSignatureKeyInformation.encodingType, String.class, value -> MACSignatureKeyInformation.builder().value(value).build());
    }
}