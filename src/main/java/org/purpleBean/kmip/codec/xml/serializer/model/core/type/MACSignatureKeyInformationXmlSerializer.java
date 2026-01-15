package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.MACSignatureKeyInformation;

public class MACSignatureKeyInformationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationXmlSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}