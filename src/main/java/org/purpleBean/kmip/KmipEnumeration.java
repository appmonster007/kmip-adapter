package org.purpleBean.kmip;

public interface KmipEnumeration extends KmipDataType {
    EncodingType encodingType = EncodingType.ENUMERATION;

    int getValue();

    String getDescription();
}
