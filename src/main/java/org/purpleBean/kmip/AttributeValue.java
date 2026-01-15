package org.purpleBean.kmip;

/**
 * KMIP AttributeValue dataType.
 */
public interface AttributeValue extends KmipDataType {
    KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE_VALUE.inst();
}
