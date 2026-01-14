package org.purpleBean.kmip;

import java.util.List;

public interface KmipStructure extends KmipDataType {
    EncodingType encodingType = EncodingType.STRUCTURE;

    List<KmipDataType> getValues();
}
