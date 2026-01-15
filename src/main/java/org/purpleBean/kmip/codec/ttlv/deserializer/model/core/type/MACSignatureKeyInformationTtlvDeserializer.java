package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.MACSignatureKeyInformation;

public class MACSignatureKeyInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationTtlvDeserializer() {
        super(MACSignatureKeyInformation.kmipTag, MACSignatureKeyInformation.encodingType, String.class, value -> MACSignatureKeyInformation.builder().value(value).build());
    }
}