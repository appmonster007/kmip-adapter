package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.MACSignatureKeyInformation;

public class MACSignatureKeyInformationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationTtlvSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}