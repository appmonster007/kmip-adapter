package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationTtlvDeserializer extends AbstractKmipTtlvDeserializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationTtlvDeserializer() {
        super(MACSignatureKeyInformation.kmipTag, MACSignatureKeyInformation.encodingType, String.class, value -> MACSignatureKeyInformation.builder().value(value).build());
    }
}